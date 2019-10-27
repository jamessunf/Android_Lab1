package com.example.android_lab1;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

public class ListViewActivity extends Activity {
    private DatabaseHelper myDb;
    private ListView myListView;
    private Button btnSend, btnReceive;
    private EditText edtInput;

    ArrayList<Person> person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        Toast.makeText(ListViewActivity.this, "Create", Toast.LENGTH_SHORT).show();

        myDb = new DatabaseHelper(this);

        btnSend = (Button) findViewById(R.id.btn_send);
        btnReceive = (Button) findViewById(R.id.btn_receive);
        edtInput = (EditText) findViewById(R.id.edt_input);
        myListView = (ListView) findViewById(R.id.lst_person);

        person = new ArrayList<>();
        viewData();


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertData(new Person(0,edtInput.getText().toString()));
                edtInput.setText(null);

            }
        });

        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertData(new Person(1,edtInput.getText().toString()));
                edtInput.setText(null);

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(ListViewActivity.this, "Destroy", Toast.LENGTH_SHORT).show();
        //deleteAll();
    }

    @Override
    protected void onStart() {
        super.onStart();
        printCursor();
        Toast.makeText(ListViewActivity.this, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(ListViewActivity.this, "Restart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(ListViewActivity.this, "Resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(ListViewActivity.this, "Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void printCursor(){

        Cursor res = myDb.getAllData();
        Integer v = myDb.getV();
        int col_count = res.getColumnCount();
        int results_count = res.getCount();


        Log.v("Version",v.toString());
        Log.v("Columns", String.valueOf(col_count));
        Log.v("Column Names", Arrays.toString(res.getColumnNames()));
        Log.v("Results", String.valueOf(results_count));
        res.moveToFirst();
        while (res.moveToNext()){
            Log.v("","ID:" + res.getString(0) + " SEND:" + res.getString(1) + " MASSAGE:" + res.getString(2) );
        }





    }

    private void viewData(){
        Cursor res = myDb.getAllData();


        if(res.getCount() != 0){
            person.clear();
            while (res.moveToNext()){
                person.add(new Person(res.getInt(1),res.getString(2)));
            }
            myListView.setAdapter(new CustomListAdapter(ListViewActivity.this,person));
        }

    }

    private void insertData(Person p){
        Boolean isSave = myDb.insertData(p);
        if (isSave) {
            Toast.makeText(ListViewActivity.this, "Data Inserted.", Toast.LENGTH_LONG).show();
            viewData();
        }else {
            Toast.makeText(ListViewActivity.this, "Data did not save!", Toast.LENGTH_LONG).show();
        }

    }
/*
    private void deleteData(Person p){
        Integer deleteRows = myDb.deleteData(p);
        if(deleteRows>0)
            Toast.makeText(ListViewActivity.this,"Delete successful.",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(ListViewActivity.this,"The data not deleted.",Toast.LENGTH_LONG).show();

    }
*/
  private void deleteAll(){
        myDb.deleteAll();
  }



}
