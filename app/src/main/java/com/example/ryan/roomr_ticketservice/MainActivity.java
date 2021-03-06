package com.example.ryan.roomr_ticketservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private PropertyRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    intent = new Intent(MainActivity.this, ChatActivity.class);
                    intent.putExtra("channel", "4lmrrOD8Ll2SkO2A");
                    intent.putExtra("landlordName", "Dragan Kovacevic");
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    intent = new Intent(MainActivity.this, NotificationActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

       StoreValue.setIpAddress("http://10.16.27.169:5000/");

       //data that appears on the main view
        final String[] myDataset = {
                "123 Harry Street",
                "419 Marla Avenue",
                "667 Sarah Crescent",
                "1440 Sixth Line Unit #4",
                "340 Marlborough Court Unit #212"
        };
        final String[] myNameset = {
                "Mike Rose, Elise Rose",
                "Rodrigo Hurtado",
                "Ryan Sneyd",
                "Alison Grace",
                "Johnny Walker"
        };
        final String[] myImgSet = {
                "http://www.wallmark.ca/assets/bulkUpload/_resampled/ResizeCroppedImage480296-IMG-0002.jpg",
                "https://www.brolenhomes.com.au/wp-content/uploads/2017/12/Custom2-Facade-720x440-250x153.jpg",
                "https://lequebec.info/images/property/25549404_1.jpg",
                "http://www.southamptonbeachhouse.ca/images/slider-img-03.jpg",
                "https://images.glaciermedia.ca/polopoly_fs/1.383964.1373149983!/fileImage/httpImage/image.jpg_gen/derivatives/landscape_804_583/img-0-7465939-jpg.jpg"
        };

        // Intilize an array list from array
        final List<String> addressList = new ArrayList(Arrays.asList(myDataset));
        final List<String> nameList = new ArrayList(Arrays.asList(myNameset));
        final List<String> imgList = new ArrayList(Arrays.asList(myImgSet));

        // Define a layout for RecyclerView
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);


        // Initialize a new instance of RecyclerView Adapter instance
        mAdapter = new PropertyRecyclerAdapter(mContext, addressList, nameList, imgList);

        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(mAdapter);

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
            case R.id.lblEdit:
                Toast.makeText(mContext, "Edit", Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(MainActivity.this, LoginActivity.class);
                //startActivity(i);
                return true;
            case R.id.lblLogout:
                Toast.makeText(mContext, "Logout", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
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


        //House harry123 = new House("123 Harry Street", prop1);
        //House marla419 = new House("419 Marla Avenue", prop2);
        //House sarah667 = new House("667 Sarah Crescent", prop3);

        //propertyList.add(harry123);
        //propertyList.add(marla419);
        //propertyList.add(sarah667);


    }

/////////////////////////////////fin///////////////////////////
}
