package com.example.ryan.roomr_ticketservice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.security.KeyException;
import java.util.List;

public class LandlordTicketAdapter extends RecyclerView.Adapter<LandlordTicketAdapter.ViewHolder>{

    private List<String> names;
    private List<String> phoneNumbers;
    private List<String> ratings;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    LandlordTicketAdapter(Context context, List<String> data1, List<String> data2, List<String> data3) {
        this.mInflater = LayoutInflater.from(context);
        this.names = data1;
        this.phoneNumbers = data2;
        this.ratings = data3;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.ticket_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String name;
        String phoneNumber;
        String rating;
        try{
            name = this.names.get(position);
        }
        catch (IndexOutOfBoundsException ex){
            name = "";
        }
        try{
            phoneNumber = this.phoneNumbers.get(position);

        }
        catch (IndexOutOfBoundsException ex){
            phoneNumber = "";
        }
        try{
            rating = this.ratings.get(position);
        }
        catch (IndexOutOfBoundsException ex){
            rating = "0.0";
        }


        holder.contractorNames.setText(name);
        holder.contractorPhoneNumbers.setText(phoneNumber);
        holder.onRatingChanged(holder.ratingBar, Float.parseFloat(rating), true);


    }

    // total number of rows
    @Override
    public int getItemCount() {

        int size1 = this.names.size();
        int size2 = this.phoneNumbers.size();
        int size3 = this.ratings.size();

        int sizes[] = {size1, size2, size3};
        int max = sizes[0];
        for (int i = 0; i < sizes.length; i++){
            if (sizes[i] > max){
                max = sizes[i];
            }
        }
        return max;


    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, RatingBar.OnRatingBarChangeListener {
        TextView contractorNames;
        TextView contractorPhoneNumbers;
        RatingBar ratingBar;
        ViewHolder(View itemView) {
            super(itemView);
            contractorNames = itemView.findViewById(R.id.txtContractorName);
            contractorPhoneNumbers = itemView.findViewById(R.id.txtContractorPhoneNumber);
            ratingBar = itemView.findViewById(R.id.rtbRating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }


        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            ratingBar.setMax(5);

            ratingBar.setRating(rating);
            ratingBar.setIsIndicator(fromUser);

        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return this.names.get(id);
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




