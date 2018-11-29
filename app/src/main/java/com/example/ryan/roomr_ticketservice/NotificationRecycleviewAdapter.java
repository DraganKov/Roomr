package com.example.ryan.roomr_ticketservice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NotificationRecycleviewAdapter extends RecyclerView.Adapter<NotificationRecycleviewAdapter.ViewHolder> {

    private List<LandlordTicket> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    NotificationRecycleviewAdapter(Context context, List<LandlordTicket> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.notification_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        LandlordTicket ticket = mData.get(position);
        holder.name.setText(ticket.getName());
        holder.description.setText(ticket.getDescription());
        holder.priority.setText(ticket.getPriority());
        holder.photo.setImageBitmap(ticket.convertStringToBitmap(ticket.getPhotoString()));

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView priority;
        ImageView photo;
        TextView description;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtProblemName);
            priority = itemView.findViewById(R.id.txtProblemPriority);
            photo = itemView.findViewById(R.id.imgProblemPhoto);
            description = itemView.findViewById(R.id.txtProblemDescription);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    LandlordTicket getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
