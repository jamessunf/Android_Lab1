package com.example.android_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btn;
    Button btn2;
    EditText edtEmail;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_main_grid);
        //setContentView(R.layout.activity_main_linear);
       // setContentView(R.layout.activity_main_relative);
        setContentView(R.layout.activity_main_login);

        btn = (Button) findViewById(R.id.btn_login);
        btn2 = (Button) findViewById(R.id.btn_login2);

        edtEmail = (EditText) findViewById(R.id.login_email);
        edtPassword = (EditText) findViewById(R.id.login_password);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(getApplicationContext(),"It's magic!", Toast.LENGTH_LONG).show();
                saveInfo(view);



            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayData(view);
            }
        });


    }

    private void saveInfo(View view) {

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("userEmail", edtEmail.getText().toString());
        editor.putString("userPassword",edtPassword.getText().toString());
        editor.apply();

        Toast.makeText(this,"Saved!" ,Toast.LENGTH_LONG).show();
    }
    public void displayData(View view){

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String email = sharedPref.getString("userEmail","");
        String password = sharedPref.getString("userPassword","");
        Toast.makeText(this,email+ ","+ password,Toast.LENGTH_LONG).show();


    }
}
