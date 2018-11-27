package com.example.ryan.roomr_ticketservice;

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

import java.util.ArrayList;

public class TestLandlordTicket extends AppCompatActivity implements LandlordTicketAdapter.ItemClickListener {


    Button testRequest;
    TextView textView;
    LandlordTicketAdapter adapter;
    RecyclerView ticketView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_landlord_ticket);
        testRequest = findViewById(R.id.btnTestRequest);
        textView = findViewById(R.id.textView);
        ticketView = findViewById(R.id.rcyTicketView);
        testRequest.setOnClickListener(onTestRequest);
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");
        ticketView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LandlordTicketAdapter(this, animalNames);
        adapter.setClickListener(this);
        ticketView.setAdapter(adapter);

    }



    private View.OnClickListener onTestRequest = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onRequest("http://10.16.26.173:5000/");
            textView.setText(storeJSON.getValue());
        }
    };

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
