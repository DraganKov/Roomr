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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    ArrayList<House> propertyList;
    Button testNotification;
    RecyclerItemClickListener openChatplz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testNotification = findViewById(R.id.btnTestNotification);
        testNotification.setOnClickListener(onTestNotification);
        mContext = getApplicationContext();

       StoreValue.setIpAddress("http://10.16.27.151:5000/");

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
        final String[] myNameset = {
                "Mike Rose",
                "Elise Rose",
                "Madison Escalade"
        };

        // Intilize an array list from array
        final List<String> addressList = new ArrayList(Arrays.asList(myDataset));
        final List<String> nameList = new ArrayList(Arrays.asList(myNameset));

        // Define a layout for RecyclerView
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);


        // Initialize a new instance of RecyclerView Adapter instance
        mAdapter = new myAdapter(mContext, addressList, nameList);

        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        // Set a click listener for add item button
        openChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Specify the position
                int position = 3;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logout_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.lblLogout:
                Toast.makeText(mContext, "Logout", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private View.OnClickListener onTestNotification = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, TestLandlordTicket.class);
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
                Intent intent = new Intent(MainActivity.this, ReportProblemActivity.class);
                intent.putExtra("NAME", contractorName);
                startActivity(intent);
            }

        });
        return builder.create();
    }

    //replace this with an actual database fetch for tenants based on Landlords Properties
    private void createTestTenants () {
        Tenant ten1 = new Tenant("Mike Rose", "123 Harry Street");
        Tenant ten2 = new Tenant("Elise Rose", "123 Harry Street");
        Tenant ten3 = new Tenant("Madison Escalade", "123 Harry Street");


        Tenant ten5 = new Tenant("George Hyman", "419 Marla Avenue");
        Tenant ten6 = new Tenant("Ryan Bustman", "419 Marla Avenue");
        Tenant ten7 = new Tenant("Kevin Gates", "419 Marla Avenue");

        Tenant ten8 = new Tenant("Igor Titov", "667 Sarah Crescent");
        Tenant ten9 = new Tenant("Maria Titov", "667 Sarah Crescent");

        //put tenants in ArrayLists based on Property
        ArrayList<Tenant> prop1 = new ArrayList<Tenant>();
        prop1.add(ten1);
        prop1.add(ten2);
        prop1.add(ten3);


        ArrayList<Tenant> prop2 = new ArrayList<Tenant>();
        prop2.add(ten5);
        prop2.add(ten6);
        prop2.add(ten7);

        ArrayList<Tenant> prop3 = new ArrayList<Tenant>();
        prop3.add(ten8);
        prop3.add(ten9);


        House harry123 = new House("123 Harry Street", prop1);
        House marla419 = new House("419 Marla Avenue", prop2);
        House sarah667 = new House("667 Sarah Crescent", prop3);

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
