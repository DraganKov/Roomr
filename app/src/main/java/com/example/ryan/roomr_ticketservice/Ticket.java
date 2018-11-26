package com.example.ryan.roomr_ticketservice;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class Ticket {
    private String _image;
    private String _description;
    private String _name;
    private int _priority;


    public Ticket(){

    }

    public Ticket(String image, String name, String description, int priority){
        this._name = name;
        this._image = image;
        this._description = description;
        this._priority = priority;
    }

    public String get_image() {
        return _image;
    }

    public void set_image(String _image) {
        this._image = _image;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_priority() {
        return _priority;
    }

    public void set_priority(int _priority) {
        this._priority = _priority;
    }

    public int validateJSONValues(){


        if (validateString(this.get_name())){
            return 1;
        }
        if (validateString(this.get_image())){
            return 2;
        }
        if (validateString(this.get_description())){
            return 3;
        }

        if (validateString(Integer.toString(this.get_priority()))){
            return 4;
        }
        return 0;
    }

    public JSONObject createJson(){
        if (validateJSONValues() == 0){
            JSONObject json = new JSONObject();

            try {
                json.put("Name", this.get_name());
                json.put("Photo", this.get_image());
                json.put("Description", this.get_description());
                json.put("Priority", Integer.toString(this.get_priority()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return json;
        }
        else {
            return null;
        }

    }

    public String bitmapToString(Drawable image){

        BitmapDrawable drawable = (BitmapDrawable) image;
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream btmp = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, btmp);
        byte [] bytes = btmp.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);


    }

    private boolean validateString(String value){
        try{
            if (value == null || value.equals("") ){
                return true;
            }
            else{
                return false;
            }
        }
        catch (IllegalArgumentException ex){
            return false;
        }

    }
}
