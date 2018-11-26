package com.example.ryan.roomr_ticketservice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder>  {
    private List<String> mAddressSet;
    private List<String> mTenantSet;
    public Context mContext;


    public myAdapter(Context context, List<String> addresses, List<String> names){
        mAddressSet = addresses;
        mTenantSet = names;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public TextView mTextView2;
        public ImageButton mRemoveButton;
        public ImageView mImageView;


        public ViewHolder(View v){
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textAddress);
            mTextView2 = (TextView) v.findViewById(R.id.textTenants);
            mRemoveButton = (ImageButton) v.findViewById(R.id.removebtn);
            mImageView = (ImageView) v.findViewById(R.id.crossButton);
        }
    }

    @Override
    public myAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Create a new View
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item_1,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        holder.mTextView.setText((String) mAddressSet.get(position));
        holder.mTextView2.setText((String) mTenantSet.get(position));
        // Set a click listener for TextView
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = mAddressSet.get(position);
                Toast.makeText(mContext,address,Toast.LENGTH_SHORT).show();

                //This is where you change what house chat you enter //////////////important
                Intent intent = new Intent(mContext, ChatActivity.class);
                mContext.startActivity(intent);
            }
        });

        // Set a click listener for item remove button
        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
                String itemLabel = mAddressSet.get(position);
                // Remove the item on remove/button click
                mAddressSet.remove(position);
                // and notify the position removed
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mAddressSet.size());

                // Show the removed item label
                Toast.makeText(mContext,"Removed : " + itemLabel,Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount(){
        return mAddressSet.size();
    }

}