package com.example.android_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.xmlpull.v1.XmlPullParser.END_TAG;
import static org.xmlpull.v1.XmlPullParser.START_TAG;
import static org.xmlpull.v1.XmlPullParser.TEXT;

public class WeatherForecastActivity extends AppCompatActivity {

    OttawaWeather ottawaWeather;
    TextView txtTempValue, txtTempMax, txtTempMin,txtUvValue;
    Button btn_test;
    ImageView img_pic;


    ProgressBar proBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        ottawaWeather = new OttawaWeather();

        txtTempValue = (TextView) findViewById(R.id.txt_temp_value);
        txtTempMax =(TextView) findViewById(R.id.txt_temp_max);
        txtTempMin = (TextView) findViewById(R.id.txt_temp_min);
        txtUvValue = (TextView) findViewById(R.id.txt_uv_value);


        btn_test = (Button) findViewById(R.id.btn_test);
        img_pic = (ImageView) findViewById(R.id.img_pic);
        proBar = (ProgressBar) findViewById(R.id.pro_bar);
        proBar.setVisibility(View.VISIBLE);
        openHttp();


        btn_test.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                openHttp();

            }
        });


    }

    private void openHttp() {
        String[] urlPath = {"http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=7e943c97096a9784391a981c4d878b22&mode=xml&units=metric",
                            "http://api.openweathermap.org/data/2.5/uvi?appid=7e943c97096a9784391a981c4d878b22&lat=45.348945&lon=-75.759389" };

        new HttpUtil().execute(urlPath);

    }


    private class HttpUtil extends AsyncTask<String, Integer, OttawaWeather> {

        @Override
        protected OttawaWeather doInBackground(String... strings) {
            //xml
            readXml(strings[0]);
            //deal with icon

            //json
            readJson(strings[1]);

            return ottawaWeather;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtTempValue.setText(ottawaWeather.getTemp_value());
            txtTempMax.setText(ottawaWeather.getTemp_max());
            txtTempMin.setText(ottawaWeather.getTemp_min());
            txtUvValue.setText(ottawaWeather.getUv_value());
            img_pic.setImageBitmap(ottawaWeather.getWeather_img());
            proBar.setVisibility(View.INVISIBLE);

        }



        @Override
        protected void onProgressUpdate(Integer... values) {

            super.onProgressUpdate(values);

           // proBar.setVisibility(View.VISIBLE);

        }

        //***********XML******************
        private void readXml(String url){

            try {
                URL xmlUrl = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) xmlUrl.openConnection();
                InputStream inStream = urlConnection.getInputStream();

                //Set up the XML parser:
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput( inStream  , "UTF-8");

                //Iterate over the XML tags:
                int EVENT_TYPE;

                ottawaWeather = new OttawaWeather();
                while((EVENT_TYPE = xpp.getEventType()) != XmlPullParser.END_DOCUMENT){

                    switch (EVENT_TYPE){
                        case START_TAG:
                            String tagName = xpp.getName();
                            if(tagName.equals("temperature")){
                                ottawaWeather.setTemp_value(xpp.getAttributeValue(null,"value"));
                                ottawaWeather.setTemp_min(xpp.getAttributeValue(null,"min"));
                                ottawaWeather.setTemp_max(xpp.getAttributeValue(null,"max"));

                            }else if(tagName.equals("weather")){

                                ottawaWeather.setIcon_name(xpp.getAttributeValue(null,"icon") + "@2x.png");
                                if (fileExistance(ottawaWeather.getIcon_name())){

                                    readImage(ottawaWeather.getIcon_name());

                                }else{

                                    readImageOnline(ottawaWeather.getIcon_name());
                                }
                            }
                            break;
                        case END_TAG:

                            break;
                        case TEXT:

                            break;
                    }

                    xpp.next();

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

        }


        }
    //*************ICON****************
    private void readImageOnline(String imgName){


        String urlOfImg = "http://openweathermap.org/img/wn/" + imgName;

        URL imgUrl = null;
        try {
            imgUrl = new URL(urlOfImg);
            HttpURLConnection connection = (HttpURLConnection) imgUrl.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if(responseCode == 200) {

                ottawaWeather.setWeather_img(BitmapFactory.decodeStream(connection.getInputStream()));

                //save the image
                FileOutputStream fileOutputStream = openFileOutput(imgName, Context.MODE_PRIVATE);
                ottawaWeather.getWeather_img().compress(Bitmap.CompressFormat.PNG,80,fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                Log.i("The image is saved as ",imgName);


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private boolean fileExistance(String fname){
        File file = getFileStreamPath(fname);
        Log.i("Image is Existed : ",file.toString());
        return  file.exists();
    }

    private void readImage(String imgName){
        FileInputStream fis = null;
        try {
            fis = openFileInput(imgName);
            Log.i("Read Image From Local: ",imgName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ottawaWeather.setWeather_img(BitmapFactory.decodeStream(fis));

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

                    String value = Double.toString(jObject.getDouble("value"));
                    ottawaWeather.setUv_value(value);
                    Log.i("UV value(JSON):",value);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    //*****************************

}
