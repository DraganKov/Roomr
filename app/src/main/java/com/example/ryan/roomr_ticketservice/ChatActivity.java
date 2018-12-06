package com.example.ryan.roomr_ticketservice;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaledrone.lib.Listener;
import com.scaledrone.lib.Member;
import com.scaledrone.lib.Room;
import com.scaledrone.lib.RoomListener;
import com.scaledrone.lib.Scaledrone;

import java.util.Random;

public class ChatActivity extends AppCompatActivity implements RoomListener {

    // replace this with a real channelID from Scaledrone dashboard

    private String channelID;
    private String roomName = "observable-room1";
    private EditText editText;
    private Scaledrone scaledrone;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    private Button ten1, ten2, ten3;
    private String tenantName, landlordName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Boolean t = false;
        Boolean intentData = i.getBooleanExtra("isTenant", t);
        channelID =  i.getStringExtra("channel");


        if(intentData){
            setContentView(R.layout.content_activity_chat);
            tenantName = i.getStringExtra("tenantName");
            initChat(tenantName);
        }
        else{
            setContentView(R.layout.activity_chat);
            landlordName = i.getStringExtra("landlordName");
            ten1 = findViewById(R.id.tenant1);
            ten2 = findViewById(R.id.tenant2);


            ten1.setText("667 Sarah Crescent");
            ten2.setText("Ryan Sneyd");


            ten2.setOnClickListener(onSelectTenant2);
            ten1.setOnClickListener(onSelectTenant1);

            initChat(landlordName);
        }

    }
    public void initChat(String name){
        editText = (EditText) findViewById(R.id.editText);

        messageAdapter = new MessageAdapter(this);
        messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        MemberData data = new MemberData(name, getRandomColor());

        scaledrone = new Scaledrone(channelID, data);
        scaledrone.connect(new Listener() {
            @Override
            public void onOpen() {
                System.out.println("Scaledrone connection open");
                System.out.println(channelID);
                scaledrone.subscribe(roomName, ChatActivity.this);
            }

            @Override
            public void onOpenFailure(Exception ex) {
                System.err.println(ex);
            }

            @Override
            public void onFailure(Exception ex) {
                System.err.println(ex);
            }

            @Override
            public void onClosed(String reason) {
                System.err.println(reason);
            }
        });
    }
    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            scaledrone.publish(roomName, message);
            editText.getText().clear();
        }
    }

    @Override
    public void onOpen(Room room) {
        System.out.println("Connected to RoomR Chat");
    }

    @Override
    public void onOpenFailure(Room room, Exception ex) {
        System.err.println(ex);
    }

    @Override
    public void onMessage(Room room, final JsonNode json, final Member member) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final MemberData data = mapper.treeToValue(member.getClientData(), MemberData.class);
            boolean belongsToCurrentUser = member.getId().equals(scaledrone.getClientID());
            final Message message = new Message(json.asText(), data, belongsToCurrentUser);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageAdapter.add(message);
                    messagesView.setSelection(messagesView.getCount() - 1);
                }
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private String getRandomName() {


        return tenantName;
    }

    //when a tenant is clicked
    private View.OnClickListener onSelectTenant1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = getIntent();
            i.putExtra("channel","4lmrrOD8Ll2SkO2A");

            startActivity(i);

        }
    };
    //when a tenant is clicked
    private View.OnClickListener onSelectTenant2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = getIntent();

            i.putExtra("channel","FGcdTGfNcZHXPRIA");

            startActivity(i);

        }
    };


    private String getRandomColor() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer("#");
        while(sb.length() < 7){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 7);
    }
}

class MemberData {
    private String name;
    private String color;

    public MemberData(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public MemberData() {
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "MemberData{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

}
