package com.example.ryan.roomr_ticketservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivityTenant extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    intent = new Intent(MainActivityTenant.this, ChatActivity.class);
                    boolean t = Boolean.parseBoolean("True");
                    intent.putExtra("isTenant", t);
                    intent.putExtra("channel", "FGcdTGfNcZHXPRIA");
                    intent.putExtra("tenantName", "Ryan Sneyd");
                    //intent.putExtra("isTenant", "True");

                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    intent = new Intent(MainActivityTenant.this, NotificationActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
