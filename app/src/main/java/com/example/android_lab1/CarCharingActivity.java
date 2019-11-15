package com.example.android_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

    ArrayList<EleCharging>  eleChargings = new ArrayList<>();


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

                    String[] str = {"https://api.openchargemap.io/v3/poi/?output=xml&countrycode=CA&latitude=" + dLat + "&longitude=" + dLon + "&maxresults=10"};
                    new HttpUtil().execute(str);



                }else{

                    Toast.makeText(CarCharingActivity.this, "Please input your location's latitude & longitude.", Toast.LENGTH_SHORT).show();



                }

            }
        });
    }


    private class HttpUtil extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            strings[0] = "https://api.openchargemap.io/v3/poi/?output=json&countrycode=CA&latitude=45.347571&longitude=-75.756140&maxresults=1";

            readJson(strings[0]);

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(CarCharingActivity.this, eleChargings.get(0).getLocalTitle(), Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }


        //************JSON*****************
        private void readJson(String url){

            try {
                URL jsonUrl = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) jsonUrl.openConnection();
                InputStream inputStream = urlConnection.getInputStream();

                //Set up the JSON object parser:
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"),8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                String result = sb.toString();

                JSONObject jObject = new JSONObject(result);

                String value = jObject.getString("Title");
               // ottawaWeather.setUv_value(value);
                Log.i("UV value(JSON):",value);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }





}
