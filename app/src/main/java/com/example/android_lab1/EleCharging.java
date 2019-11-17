package com.example.android_lab1;

public class EleCharging {
    private String localTitle;
    private String addr;

    private double dLatitude;
    private double dLongitude;
    private String phoneNumber;

    public EleCharging(String localTitle, String addr, double dLatitude, double dLongitude, String phoneNumber) {
        this.localTitle = localTitle;
        this.addr = addr;
        this.dLatitude = dLatitude;
        this.dLongitude = dLongitude;
        this.phoneNumber = phoneNumber;
    }



    public String getLocalTitle() {
        return localTitle;
    }

    public void setLocalTitle(String localTitle) {
        this.localTitle = localTitle;
    }

    public double getdLatitude() {
        return dLatitude;
    }

    public void setdLatitude(double dLatitude) {
        this.dLatitude = dLatitude;
    }

    public double getdLongitude() {
        return dLongitude;
    }

    public void setdLongitude(double dLongitude) {
        this.dLongitude = dLongitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
