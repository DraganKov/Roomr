package com.example.ryan.roomr_ticketservice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button selectContractor;
    Button openChat;
    ArrayList<PropertyObj> propertyList;
    private RecyclerView mRecyclerView;
    private myAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerItemClickListener openChatplz;

    private String[] myDataset = {
            "123 Harry Street",
            "419 Marla Avenue",
            "667 Sarah Crescent"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.my_recycler_view);

        selectContractor = findViewById(R.id.btnSelectContractor);
        selectContractor.setOnClickListener(onSelectContractor);
        openChat = findViewById(R.id.btnOpenChat);
        openChat.setOnClickListener(onOpenChat);


        // Construct the data source
        //createTestTenants();


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        //mAdapter = new myAdapter(myDataset);
        mAdapter = new myAdapter(myDataset, openChatplz) {
            private static final String TAG = "Clicked";

            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                // do what ever you want to do with it
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        };
        mRecyclerView.setAdapter(mAdapter);







    }

    private Context getContext() {

        return this;
    };


    private View.OnClickListener onOpenChat = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener onSelectContractor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final AlertDialog dialog = buildDialog();
            dialog.show();
        }
    };

    private AlertDialog buildDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Pick a Contractor");
        builder.setItems(R.array.names, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String contractorName;
                switch (which) {
                    case 0:
                        contractorName = "Electrician";
                        break;
                    case 1:
                        contractorName = "Dry Wall Repair";
                        break;
                    case 2:
                        contractorName = "Plumbing";
                        break;
                    case 3:
                        contractorName = "Locksmith";
                        break;
                    default:
                        contractorName = "";
                }
                Intent intent = new Intent(MainActivity.this, DemoCommuncation.class);
                intent.putExtra("NAME", contractorName);
                startActivity(intent);
            }

        });
        return builder.create();
    }

    //replace this with an actual database fetch for tenants based on Landlords Properties
    private void createTestTenants() {
        TenantObj ten1 = new TenantObj("Mike Rose", "123 Harry Street");
        TenantObj ten2 = new TenantObj("Elise Rose", "123 Harry Street");
        TenantObj ten3 = new TenantObj("Madison Escalade", "123 Harry Street");
        TenantObj ten4 = new TenantObj("Mike Rose", "123 Harry Street");

        TenantObj ten5 = new TenantObj("George Hyman", "419 Marla Avenue");
        TenantObj ten6 = new TenantObj("Ryan Bustman", "419 Marla Avenue");
        TenantObj ten7 = new TenantObj("Kevin Gates", "419 Marla Avenue");

        TenantObj ten8 = new TenantObj("Igor Titov", "667 Sarah Crescent");
        TenantObj ten9 = new TenantObj("Maria Titov", "667 Sarah Crescent");

        //put tenants in ArrayLists based on Property
        ArrayList<TenantObj> prop1 = new ArrayList<TenantObj>();
        prop1.add(ten1);
        prop1.add(ten2);
        prop1.add(ten3);
        prop1.add(ten4);

        ArrayList<TenantObj> prop2 = new ArrayList<TenantObj>();
        prop2.add(ten5);
        prop2.add(ten6);
        prop2.add(ten7);

        ArrayList<TenantObj> prop3 = new ArrayList<TenantObj>();
        prop3.add(ten8);
        prop3.add(ten9);


        PropertyObj harry123 = new PropertyObj("123 Harry Street", prop1);
        PropertyObj marla419 = new PropertyObj("419 Marla Avenue", prop2);
        PropertyObj sarah667 = new PropertyObj("667 Sarah Crescent", prop3);

        propertyList.add(harry123);
        propertyList.add(marla419);
        propertyList.add(sarah667);


    }





/////////////////////////////////fin/////////////////////////////
}
