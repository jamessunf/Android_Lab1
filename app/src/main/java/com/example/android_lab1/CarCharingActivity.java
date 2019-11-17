package com.example.android_lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.xmlpull.v1.XmlPullParser.END_TAG;
import static org.xmlpull.v1.XmlPullParser.START_TAG;
import static org.xmlpull.v1.XmlPullParser.TEXT;

public class CarCharingActivity extends AppCompatActivity {

    Button btnFind;
    ListView lstResults;
    EditText edtLat,edtLon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_charing);

        btnFind = (Button) findViewById(R.id.btn_find);
        lstResults = (ListView) findViewById(R.id.lst_results);
        edtLat =(EditText) findViewById(R.id.edt_lat);
        edtLon = (EditText) findViewById(R.id.edt_lon);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtLat.getText().toString().trim().length() != 0 && edtLon.getText().toString().trim().length() != 0){

                    String dLat = edtLat.getText().toString();
                    String dLon = edtLon.getText().toString();

                    String[] str = {"https://api.openchargemap.io/v3/poi/?output=json&countrycode=CA&latitude=" + dLat + "&longitude=" + dLon + "&maxresults=10"};
                    Log.i("url:",str[0]);
                    new HttpUtil().execute(str);



                }else{

                    Toast.makeText(CarCharingActivity.this, "Please input your location's latitude & longitude.", Toast.LENGTH_SHORT).show();



                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this,"This is the initial message",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item4:
                Toast.makeText(this,"You clicked on the overflow",Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.icon4);
                builder.setTitle("What is your new message?");

                // Set up the input
                final EditText input = new EditText(this);
                input.setHint("Type here");
                input.setTextSize(25);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //  m_Text = input.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

                return true;


        }

        return super.onOptionsItemSelected(item);
    }


    private class HttpUtil extends AsyncTask<String,String,ArrayList<EleCharging>>{

        @Override
        protected ArrayList<EleCharging> doInBackground(String... strings) {

           // strings[0] = "https://api.openchargemap.io/v3/poi/?output=json&countrycode=CA&latitude=45.347571&longitude=-75.756140&maxresults=10";



           return readJson(strings[0]);


        }

        @Override
        protected void onPostExecute(final ArrayList<EleCharging> eleChargings) {




            lstResults.setAdapter(new CarCharingListAdapter(CarCharingActivity.this,eleChargings));
            lstResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Toast.makeText(CarCharingActivity.this,eleChargings.get(i).getLocalTitle(),Toast.LENGTH_SHORT).show();

                }
            });




        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }


        //************JSON*****************
        private ArrayList<EleCharging> readJson(String url){

           // EleCharging eleCharging = null;
            ArrayList<EleCharging> eleChargings = new ArrayList<>();

            try {
                URL jsonUrl = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) jsonUrl.openConnection();
                InputStream inputStream = urlConnection.getInputStream();

                //Set up the JSON object parser:
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"),8);
                StringBuilder sb = new StringBuilder();

                String line = null;
               // eleCharging = new EleCharging();
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                String result = sb.toString();


                JSONArray root = new JSONArray(result);


                for(int i=0;i<root.length();i++){

                    JSONObject jsonObject = root.getJSONObject(i);
                        JSONObject addressInfo = jsonObject.getJSONObject("AddressInfo");

                       eleChargings.add(new EleCharging(addressInfo.getString("Title"),
                                                        addressInfo.getString("AddressLine1"),
                                                        addressInfo.getDouble("Latitude"),
                                                        addressInfo.getDouble("Longitude"),
                                                        addressInfo.getString("ContactTelephone1")));

                }

                for(int i=0;i<eleChargings.size();i++){
                    Log.i("title:",eleChargings.get(i).getLocalTitle());
                    Log.i("title:",eleChargings.get(i).getAddr());
                    Log.i("Lon:",Double.toString(eleChargings.get(i).getdLongitude()));
                    Log.i("Lat:",Double.toString(eleChargings.get(i).getdLatitude()));
                    Log.i("Phone:",eleChargings.get(i).getPhoneNumber());

                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

                return eleChargings;
        }


    }





}
