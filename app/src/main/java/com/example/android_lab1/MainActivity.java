package com.example.android_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    Button btn;
   // Button btn2;
    EditText edtLoginEmail, edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        //setContentView(R.layout.activity_profile);





        btn = (Button) findViewById(R.id.btn_login);
        edtLoginEmail = (EditText) findViewById(R.id.email_login);
        edtPassword = (EditText) findViewById(R.id.login_password);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(getApplicationContext(),"It's magic!", Toast.LENGTH_LONG).show();
                //edtUserE.setText(edtEmail.getText());
                saveInfo();
                openProfile();

            }
        });


    }

    public void openProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);

        String userEmail = edtLoginEmail.getText().toString();
        intent.putExtra("userEmail", userEmail);



        startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();

        saveInfo();

        Log.i(TAG, "onPause");

    }

    private void saveInfo() {

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("userEmail", edtLoginEmail.getText().toString());
        editor.putString("userPassword",edtPassword.getText().toString());
        editor.apply();

        Toast.makeText(this,"Saved!" ,Toast.LENGTH_LONG).show();
    }
    public void displayData(){

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String email = sharedPref.getString("userEmail","");
        String password = sharedPref.getString("userPassword","");
        Toast.makeText(this,email+ ","+ password,Toast.LENGTH_LONG).show();


    }
}
