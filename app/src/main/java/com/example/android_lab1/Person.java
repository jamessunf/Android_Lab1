package com.example.android_lab1;

public class Person {

    private int send;
    private String massage;

    public Person(int send, String massage) {
        this.send = send;
        this.massage = massage;
    }

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
