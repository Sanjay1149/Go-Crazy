package com.example.android.gocrazy;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "Myactivity";
    TextView already,name,email,pass;
    Button create;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        already = (TextView) findViewById(R.id.alreadyAccount);
        create = (Button) findViewById(R.id.createProfile);
        name = (TextView) findViewById(R.id.fullNameText);
        email = (TextView) findViewById(R.id.emailIDText);
        pass = (TextView) findViewById(R.id.passwordText);
        dbHandler = new DBHandler(this,null,null,1);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addAccountdetails();
                name.setText("Enter your full name");
                email.setText("Enter Email id");
                pass.setText("Enter Password");
                Intent i = new Intent(getApplicationContext(), Sign_In.class);

                startActivity(i);
            }
        });

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Sign_In.class);
                startActivity(i);
            }
        });


    }

    public void addAccountdetails(){
        Log.d(TAG,"name " + name.getText().toString()+"email "+email.getText().toString()+"pass "+pass.getText().toString());
          Account_Data acc = new Account_Data(name.getText().toString().trim(),email.getText().toString().trim(),pass.getText().toString().trim());
          dbHandler.addAccount(acc);
    }
}
