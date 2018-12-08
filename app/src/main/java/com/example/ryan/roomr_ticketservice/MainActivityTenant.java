package com.example.ryan.roomr_ticketservice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityTenant extends AppCompatActivity {

    private TextView mTextMessage;
    private Button selectContractor;

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

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityTenant.this);
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

                Intent intent = new Intent(MainActivityTenant.this, ReportProblemActivity.class);
                intent.putExtra("NAME", contractorName);
                startActivity(intent);
            }

        });
        return builder.create();
    }

}
