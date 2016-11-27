package com.example.android.gocrazy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sanjaypradeep on 17-08-2016.
 */
public class Sign_In extends Activity{

    private static final String TAG = "Myactivity";
    boolean b = false;
    DBHandler dbHandler;
    TextView create_new;
    EditText log,pass;
    TextView forgot;
    Button login;
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sign_in);

        log = (EditText) findViewById(R.id.loginUsername);
        pass = (EditText) findViewById(R.id.loginPassword);

        dbHandler = new DBHandler(this,null,null,1);

        forgot = (TextView) findViewById(R.id.forgotPassword);
        create_new = (TextView) findViewById(R.id.createNewAccount);
        login = (Button) findViewById(R.id.loginButton);

        Intent i = getIntent();
        Bundle bun = i.getExtras();
        if(bun!=null){
            String pas =  bun.getString("pass");
            String logg =  bun.getString("user");
            Log.d(TAG,"User Name is "+logg+"And Pass is "+pas);
            Toast.makeText(this,"User Name is "+logg+" and  your password is "+pas+" \nDon't forget it next time champ!!!",Toast.LENGTH_LONG).show();
            log.setText(logg);
            pass.setText(pas);
        }

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Forgot_Password.class);
                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.d(TAG," Getting inside Sign In");
                b=check();
                if(b==true){
                    Intent i = new Intent(getApplicationContext(),Main_frame.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getBaseContext(),"Enter a Valid Username and Password",Toast.LENGTH_LONG).show();
                    log.setText(" ");
                    pass.setText(" ");
                }
            }
        });

        create_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
    public boolean check(){
        boolean c;
        Account_Data acc = new Account_Data(log.getText().toString().trim(),pass.getText().toString().trim());
        //Log.d(TAG,"IT reached here but not there ");
        if (dbHandler.check_Account(acc))
            c = true;
        else
            c = false;

        //Log.d(TAG,"Value of check_Account is "+dbHandler.check_Account(acc));
        return c;
    }
}
