package com.example.android.gocrazy;

import android.util.Log;

/**
 * Created by sanjaypradeep on 28-09-2016.
 */
public class Account_Data {

    private static final String TAG = "Myactivity";
    int id;
    String fname;
    String email;
    String pass;

    public Account_Data(String email,String pass){
        this.email=email;
        this.pass=pass;
        Log.d(TAG,"email "+email+ " pass "+pass);
    }

    public Account_Data(String fname,String email,String pass){
        this.fname = fname;
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
