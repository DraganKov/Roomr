package com.example.ryan.roomr_ticketservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.util.ArrayList;

public class LandlordTicket  {
    private String photoString;
    private String name;
    private String priority;
    private String description;
    private ArrayList<String> phoneNumbers;
    private ArrayList<String> names;
    private ArrayList<String> ratings;

    public LandlordTicket(){
        phoneNumbers = new ArrayList<>();
        names = new ArrayList<>();
        ratings = new ArrayList<>();

    }




    public String getPhotoString() {
        return photoString;
    }

    public void setPhotoString(String photoString) {
        this.photoString = photoString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap convertStringToBitmap(String string){
        try{
            //deccode string
            byte[] bytes = Base64.decode(string, Base64.DEFAULT);
            //covert byte array to bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            //return bitmap
            return bitmap;
        }catch (Exception ex){
            ex.getMessage();
            return null;
        }
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumber) {
        this.phoneNumbers.add(phoneNumber);
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(String name) {
        this.names.add(name);
    }

    public ArrayList<String> getRatings() {
        return ratings;
    }

    public void setRatings(String rating) {
        this.ratings.add(rating);
    }


}
