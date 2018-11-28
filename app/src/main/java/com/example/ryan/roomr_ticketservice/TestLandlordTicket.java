package com.example.ryan.roomr_ticketservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
        testRequest.setOnClickListener(onTestRequest);
        contractorNames = new ArrayList<>();
        contractorPhoneNumbers = new ArrayList<>();
        contractorRatings = new ArrayList<>();



    }




    private void parseJSON() throws JSONException {
        JSONObject json = new JSONObject(storeJSON.getValue());
        contractorNames.add(json.get("Name1").toString());
        contractorNames.add(json.get("Name2").toString());
        contractorNames.add(json.get("Name3").toString());
        contractorNames.add(json.get("Name4").toString());
        //contractorNames.add(json.get("Name5").toString());
        contractorPhoneNumbers.add(json.get("PhoneNumber1").toString());
        contractorPhoneNumbers.add(json.get("PhoneNumber2").toString());
        contractorPhoneNumbers.add(json.get("PhoneNumber3").toString());
        contractorPhoneNumbers.add(json.get("PhoneNumber4").toString());
        //contractorPhoneNumbers.add(json.get("PhoneNumber5").toString());
        contractorRatings.add(json.get("Rating1").toString());
        contractorRatings.add(json.get("Rating2").toString());
        contractorRatings.add(json.get("Rating3").toString());
        contractorRatings.add(json.get("Rating4").toString());
        //contractorRatings.add(json.get("Rating5").toString());

        textView.setText(json.get("Name1").toString() + "," + json.get("Rating1") + "," + json.get("PhoneNumber1"));
        ticketView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LandlordTicketAdapter(this, contractorNames, contractorPhoneNumbers, contractorRatings);
        adapter.setClickListener(this);
        ticketView.setAdapter(adapter);


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
            onRequest("http://10.16.25.62:5000/");
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
                storeJSON.setValue(response);

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
