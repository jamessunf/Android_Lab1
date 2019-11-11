package com.example.android_lab1;

import android.graphics.Bitmap;

public class OttawaWeather {
    private String temp_value;
    private String temp_min;
    private String temp_max;
    private String uv_value;
    private Bitmap weather_img;
    private String icon_name;

    public String getTemp_value() {
        return temp_value;
    }

    public void setTemp_value(String temp_value) {
        this.temp_value = temp_value;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getUv_value() {
        return uv_value;
    }

    public void setUv_value(String uv_value) {
        this.uv_value = uv_value;
    }

    public Bitmap getWeather_img() {
        return weather_img;
    }

    public void setWeather_img(Bitmap weather_img) {
        this.weather_img = weather_img;
    }

    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }
}
