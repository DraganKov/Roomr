package com.example.ryan.roomr_ticketservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
import java.util.Timer;
import java.util.TimerTask;

public class NotificationActivity extends AppCompatActivity implements NotificationRecycleviewAdapter.ItemClickListener{

    RecyclerView notificationList;
    Button testNotification;
    NotificationRecycleviewAdapter adapter;
    LandlordTicket ticket;
    ArrayList<LandlordTicket> notification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notification = new ArrayList<>();
        ticket = new LandlordTicket();
        // set up the RecyclerView

        notificationList = findViewById(R.id.rcyNotifications);
        testNotification = findViewById(R.id.btnTestRequest);
        testNotification.setOnClickListener(onTestRequest);






    }



    private void parseJson(String response) throws JSONException {
        JSONObject json = new JSONObject(response);

        ticket.setPhotoString(json.get("Photo").toString());
        ticket.setDescription(json.get("Description").toString());
        ticket.setPriority(json.get("Priority").toString());
        ticket.setName(json.get("Name").toString());
        for (int i = 1; i <= 5; i++){
            try{
                String name = json.get("Name" + Integer.toString(i)).toString();
                ticket.setNames(name);
            }
            catch (Exception ex){
                //contractorNames.add("");

            }
            try{
                ticket.setPhoneNumbers((json.get("PhoneNumber" + Integer.toString(i)).toString()));
            }
            catch (Exception ex){
                //contractorPhoneNumbers.add("");

            }
            try{
                ticket.setRatings(json.get("Rating" + Integer.toString(i) ).toString());
            }
            catch (Exception ex){
                //contractorRatings.add("0.0");

            }
        }
        notification.add(ticket);
        notificationList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotificationRecycleviewAdapter(this, notification);
        adapter.setClickListener(this);
        notificationList.setAdapter(adapter);

    }


    private View.OnClickListener onTestRequest = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NetworkHelperTask thread = new NetworkHelperTask();
            thread.execute();
        }
    };



    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, Integer.toString(position), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(NotificationActivity.this, TestLandlordTicket.class);
        intent.putStringArrayListExtra("NAMES", ticket.getNames());
        intent.putStringArrayListExtra("PHONE", ticket.getPhoneNumbers());
        intent.putStringArrayListExtra("RATING", ticket.getRatings());
        intent.putExtra("CONTRACTOR", ticket.getName());
        startActivity(intent);



    }

    private class NetworkHelperTask extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... voids) {
            onRequest(StoreValue.getIpAddress());
            return "";
        }


    }

    private void onRequest(String url) {

        final RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url + "ticket", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(NotificationActivity.this, response, Toast.LENGTH_SHORT).show();
                if (!response.equals(StoreValue.getValue())){
                    try {
                        parseJson(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                StoreValue.setValue(response);
                queue.stop();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NotificationActivity.this, "Error", Toast.LENGTH_SHORT).show();

                queue.stop();
            }
        });

        queue.add(stringRequest);

    }


}
