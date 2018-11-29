package com.example.ryan.roomr_ticketservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestLandlordTicket extends AppCompatActivity implements LandlordTicketAdapter.ItemClickListener {



    TextView textView;
    ArrayList<String> names;
    ArrayList<String> phoneNumbers;
    ArrayList<String> ratings;
    RecyclerView ticketView;

    LandlordTicketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_landlord_ticket);

        ticketView = findViewById(R.id.rcyTicketView);
        Intent intent = getIntent();
        names = intent.getStringArrayListExtra("NAMES");
        phoneNumbers = intent.getStringArrayListExtra("PHONE");
        ratings = intent.getStringArrayListExtra("RATING");

        ticketView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LandlordTicketAdapter(this, names, phoneNumbers, ratings);
        adapter.setClickListener(this);
        ticketView.setAdapter(adapter);




    }






    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, Integer.toString(position), Toast.LENGTH_SHORT).show();
    }
}
