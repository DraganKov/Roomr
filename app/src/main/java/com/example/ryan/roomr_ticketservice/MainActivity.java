package com.example.ryan.roomr_ticketservice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;
    private myAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Button selectContractor;
    Button openChat;
    ArrayList<PropertyObj> propertyList;

    RecyclerItemClickListener openChatplz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        // Change the action bar color
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#FFFF00BF"))
        );

        // Get the widgets reference from XML layout
        //mRelativeLayout = (RelativeLayout) findViewById(R.id.);

        openChat = (Button) findViewById(R.id.btnOpenChat);
        selectContractor = (Button) findViewById(R.id.btnSelectContractor);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        final String[] myDataset = {
                "123 Harry Street",
                "419 Marla Avenue",
                "667 Sarah Crescent"
        };

        // Intilize an array list from array
        final List<String> addressList = new ArrayList(Arrays.asList(myDataset));

        // Define a layout for RecyclerView
        mLayoutManager = new GridLayoutManager(mContext, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize a new instance of RecyclerView Adapter instance
        mAdapter = new myAdapter(mContext, addressList);

        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        // Set a click listener for add item button
        openChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Specify the position
                int position = 0;
                String itemLabel = "testing";

                // Add an item to animals list
                addressList.add(position, "" + itemLabel);


                // Notify the adapter that an item inserted
                mAdapter.notifyItemInserted(position);

                // Scroll to newly added item position
                mRecyclerView.scrollToPosition(position);

                // Show the added item label
                Toast.makeText(mContext, "Added : " + itemLabel, Toast.LENGTH_SHORT).show();

            }
        });


        /////////////////////////////////
        //RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.my_recycler_view);

        selectContractor = findViewById(R.id.btnSelectContractor);
        selectContractor.setOnClickListener(onSelectContractor);
    }
    private View.OnClickListener onSelectContractor = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final AlertDialog dialog = buildDialog();
            dialog.show();
        }
    };

    private AlertDialog buildDialog () {

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
    private void createTestTenants () {
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
        //openChat = findViewById(R.id.btnOpenChat);
        //openChat.setOnClickListener(onOpenChat);


        // Construct the data source
        //createTestTenants();





            /*private View.OnClickListener onOpenChat = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                    startActivity(intent);
                }
            };*/



/////////////////////////////////fin///////////////////////////
}
