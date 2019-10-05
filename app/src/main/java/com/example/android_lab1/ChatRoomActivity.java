package com.example.android_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.LinkedList;

public class ChatRoomActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnSend,btnReceiver;
    private EditText msgInput;
    private LinkedList<FriendInfo> friends;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        btnSend = (Button) findViewById(R.id.btn_send);
        btnReceiver = (Button) findViewById(R.id.btn_receiver);
        msgInput = (EditText) findViewById(R.id.msg_input);

        listView = (ListView)findViewById(R.id.list_chart);
        //setData();
        friends = new LinkedList<FriendInfo>();
        setListViewAdapter();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friends.add(new FriendInfo(msgInput.getText().toString(), true));
                setListViewAdapter();
                msgInput.setText("");
            }
        });
        btnReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friends.add(new FriendInfo(msgInput.getText().toString(), false));
                setListViewAdapter();
                msgInput.setText("");
            }
        });


    }

    private void setListViewAdapter() {
        adapter = new ListViewAdapter(this, R.layout.row_send, friends);
        listView.setAdapter(adapter);
    }

    /*set "demo" data for list view adapter
    private void setData() {
        friends = new LinkedList<FriendInfo>();

        friends.add(new FriendInfo("Hello", false));
        friends.add(new FriendInfo("Hello!", true));
        friends.add(new FriendInfo("How are you?", true));
        friends.add(new FriendInfo("I'm fine", false));
        friends.add(new FriendInfo("The weather is great", true));
        friends.add(new FriendInfo("it is", false));
        friends.add(new FriendInfo("Are you go home?", true));
         friends.add(new FriendInfo("No, I'm going to school", true));
        friends.add(new FriendInfo("great", false));
        friends.add(new FriendInfo("see you", false));
         friends.add(new FriendInfo("baby", true));
    }*/
}
