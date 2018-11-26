package com.example.ryan.roomr_ticketservice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder>  {
    private List<String> mDataSet;
    private Context mContext;


    public myAdapter(Context context, List<String> list){
        mDataSet = list;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ImageButton mRemoveButton;

        public ViewHolder(View v){
            super(v);
            mTextView = (TextView) v.findViewById(R.id.nameTextView);
            mRemoveButton = (ImageButton) v.findViewById(R.id.removebtn);
        }
    }

    @Override
    public myAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Create a new View
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        holder.mTextView.setText((String)mDataSet.get(position));

        // Set a click listener for TextView
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String animal = mDataSet.get(position);
                Toast.makeText(mContext,animal,Toast.LENGTH_SHORT).show();
            }
        });

        // Set a click listener for item remove button
        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
                String itemLabel = mDataSet.get(position);

                // Remove the item on remove/button click
                mDataSet.remove(position);

                /*
                    public final void notifyItemRemoved (int position)
                        Notify any registered observers that the item previously located at position
                        has been removed from the data set. The items previously located at and
                        after position may now be found at oldPosition - 1.

                        This is a structural change event. Representations of other existing items
                        in the data set are still considered up to date and will not be rebound,
                        though their positions may be altered.

                    Parameters
                        position : Position of the item that has now been removed
                */
                notifyItemRemoved(position);

                /*
                    public final void notifyItemRangeChanged (int positionStart, int itemCount)
                        Notify any registered observers that the itemCount items starting at
                        position positionStart have changed. Equivalent to calling
                        notifyItemRangeChanged(position, itemCount, null);.

                        This is an item change event, not a structural change event. It indicates
                        that any reflection of the data in the given position range is out of date
                        and should be updated. The items in the given range retain the same identity.

                    Parameters
                        positionStart : Position of the first item that has changed
                        itemCount : Number of items that have changed
                */
                notifyItemRangeChanged(position,mDataSet.size());

                // Show the removed item label
                Toast.makeText(mContext,"Removed : " + itemLabel,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount(){
        return mDataSet.size();
    }

}