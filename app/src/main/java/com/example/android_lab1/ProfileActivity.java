package com.example.android_lab1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Toast;



public class ProfileActivity extends AppCompatActivity {

     DatabaseHelper mydb;


    static final int REQUEST_IMAGE_CAPTURE = 1;

    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";

    EditText edtProfileEmail;
    ImageButton btnPic;
    Button btnChat, btnToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtProfileEmail = (EditText) findViewById(R.id.email_profile);
        btnPic = (ImageButton) findViewById(R.id.btn_pic);
        btnChat = (Button) findViewById(R.id.btn_gotochat);
        btnToolbar = (Button) findViewById(R.id.btn_goto_toolbar);

        Bundle loginEmail = getIntent().getExtras();
        if(loginEmail == null){
            return;
        }
        String loginE = loginEmail.getString("userEmail");
        edtProfileEmail.setText(loginE);

        //picture

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewAll();
            }


        });

        //***********Lab 4 ******************

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Intent intent = new Intent(ProfileActivity.this, ListViewActivity.class);
               // startActivity(intent);

            }
        });

        btnToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivity.this, ToolbarActivity.class );
                startActivity(intent);

            }
        });


        //*********Log***************************
        Log.i(ACTIVITY_NAME, "In function: onCreate");


    }




    @Override
    protected void onStart() {
        super.onStart();

        Log.i(ACTIVITY_NAME, "In function: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(ACTIVITY_NAME, "In function: onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();

        Log.i(ACTIVITY_NAME, "In function: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(ACTIVITY_NAME, "In function: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(ACTIVITY_NAME, "In function: onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            btnPic.setImageBitmap(imageBitmap);
        }

        Log.i(ACTIVITY_NAME, "In function: onActivityResult");
    }

    private void viewAll() {

       // Cursor res = mydb.getAllData();
/*
        res.moveToFirst();
        while(!res.isAfterLast()){
            String msg = res.getString(res.getColumnIndex("MSG"));
            int type =res.getInt(res.getColumnIndex("TYPE"));
            if (type == 1) {
                //friends.add(new FriendInfo(msg, true));
                Toast.makeText(ProfileActivity.this,"MSG: " + msg,Toast.LENGTH_LONG).show();
            }else if (type == 2){

                // friends.add(new FriendInfo(msg, false));
                Toast.makeText(ProfileActivity.this,"MSG: " + msg,Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(ChatRoomActivity.this,"MSG: " + msg,Toast.LENGTH_LONG).show();
            res.moveToNext();
        }*/
    }

}
