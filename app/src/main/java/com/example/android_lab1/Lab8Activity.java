package com.example.android_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class Lab8Activity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private ListView myListView;
    private Button btnSend, btnReceive,btnDel;
    private EditText edtInput;

    private FirstFragment firstFragment;

    ArrayList<Person> person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab8);

        myDb = new DatabaseHelper(this);

        btnSend = (Button) findViewById(R.id.btn_send);
        edtInput = (EditText) findViewById(R.id.edt_input);
        myListView = (ListView) findViewById(R.id.lst_person);
        person = new ArrayList<>();
        viewData();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String strId = String.valueOf(position);

                Toast.makeText(Lab8Activity.this,strId,Toast.LENGTH_SHORT).show();
               firstFragment = FirstFragment.newInstance(strId,person.get(position).getMassage());
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,firstFragment,"first_frag").commitAllowingStateLoss();

               // myDb.deleteData(person.get(position).getMassage());
                viewData();




                    }
                });

       // getSupportFragmentManager().beginTransaction().add(R.id.fl_container,firstFragment,"first_frag").commitAllowingStateLoss();



        FirstFragment firstFragment = new FirstFragment();





        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertData(new Person(0,edtInput.getText().toString()));
                edtInput.setText(null);

            }
        });


    }

    private void viewData(){
        Cursor res = myDb.getAllData();


        if(res.getCount() != 0){
            person.clear();
            while (res.moveToNext()){
                person.add(new Person(res.getInt(1),res.getString(2)));
            }
            myListView.setAdapter(new CustomListAdapter(Lab8Activity.this,person));


        }

    }

    private void insertData(Person p){
        Boolean isSave = myDb.insertData(p);
        if (isSave) {
            Toast.makeText(Lab8Activity.this, "Data Inserted.", Toast.LENGTH_LONG).show();
            viewData();
        }else {
            Toast.makeText(Lab8Activity.this, "Data did not save!", Toast.LENGTH_LONG).show();
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
}
