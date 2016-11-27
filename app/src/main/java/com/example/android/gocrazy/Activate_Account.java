package com.example.android.gocrazy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sanjaypradeep on 17-08-2016.
 */
public class Activate_Account extends Activity {

    TextView already;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activate_account);

        already= (TextView) findViewById(R.id.activate_text2);

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Sign_In.class);
                startActivity(i);
            }
        });
    }
}
