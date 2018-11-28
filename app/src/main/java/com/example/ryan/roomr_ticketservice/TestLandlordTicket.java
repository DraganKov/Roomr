package com.example.ryan.roomr_ticketservice;

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


    Button testRequest;
    TextView textView;
    ImageView problemImage;
    TextView priority;
    LandlordTicketAdapter adapter;
    RecyclerView ticketView;
    ArrayList<String> contractorNames;
    ArrayList<String> contractorPhoneNumbers;
    ArrayList<String> contractorRatings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_landlord_ticket);
        testRequest = findViewById(R.id.btnTestRequest);
        textView = findViewById(R.id.textView);
        ticketView = findViewById(R.id.rcyTicketView);
        problemImage = findViewById(R.id.imgTicket);
        priority = findViewById(R.id.txtPriorityLandlord);
        testRequest.setOnClickListener(onTestRequest);
        contractorNames = new ArrayList<>();
        contractorPhoneNumbers = new ArrayList<>();
        contractorRatings = new ArrayList<>();



    }


    private void parseJSON() throws JSONException {
        JSONObject json = new JSONObject(StoreValue.getValue());
        for (int i = 1; i <= 5; i++){
            try{
                contractorNames.add(json.get("Name" + Integer.toString(i)).toString());
            }
            catch (Exception ex){
                //contractorNames.add("");
                continue;
            }
            try{
                contractorPhoneNumbers.add(json.get("PhoneNumber" + Integer.toString(i)).toString());
            }
            catch (Exception ex){
                //contractorPhoneNumbers.add("");
                continue;
            }
            try{
                contractorRatings.add(json.get("Rating" + Integer.toString(i) ).toString());
            }
            catch (Exception ex){
                //contractorRatings.add("0.0");
                continue;
            }
        }
        problemImage.setImageBitmap(convertStringToBitMap(json.get("Photo").toString()));
        priority.setText(json.get("Priority").toString());
        textView.setText(json.get("Name1").toString() + "," + json.get("Rating1") + "," + json.get("PhoneNumber1"));
        ticketView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LandlordTicketAdapter(this, contractorNames, contractorPhoneNumbers, contractorRatings);
        adapter.setClickListener(this);
        ticketView.setAdapter(adapter);



    }

    private Bitmap convertStringToBitMap(String string){
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

    private View.OnClickListener onTestRequest = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NetworkHelperTask helperTask = new NetworkHelperTask();
            helperTask.execute();

        }
    };
    private class NetworkHelperTask extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... voids) {
            onRequest(StoreValue.getIpAddress());
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                parseJSON();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void onRequest(String url) {

        final RequestQueue queue = Volley.newRequestQueue(this);






        StringRequest stringRequest = new StringRequest(Request.Method.POST, url + "ticket", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(TestLandlordTicket.this, response, Toast.LENGTH_SHORT).show();
                StoreValue.setValue(response);

                queue.stop();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TestLandlordTicket.this, "Error", Toast.LENGTH_SHORT).show();
                queue.stop();
            }
        });

        queue.add(stringRequest);

    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, Integer.toString(position), Toast.LENGTH_SHORT).show();
    }
}
