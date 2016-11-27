package com.example.android.gocrazy;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * Created by sanjaypradeep on 17-08-2016.
 */

public class Forgot_Password extends Activity{

    TextView text;
    DBHandler dbHandler;
    String data;
    TextView back_to_login;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        text = (TextView) findViewById(R.id.usenameOrEmail);
        dbHandler = new DBHandler(this,null,null,1);
       /* data=dbHandler.viewalldata();

        text.setText(data);*/


        reset = (Button) findViewById(R.id.resetPassword) ;
        back_to_login= (TextView) findViewById(R.id.backToLogin);

        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Sign_In.class);
                startActivity(i);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                data = dbHandler.getpass(text.getText().toString());
                if (data.equals("")){
                    Toast.makeText(getBaseContext(),"EmailID does not exist",Toast.LENGTH_LONG).show();
                    text.setText(" ");
                }
                else
                {
                    Intent intent = new Intent(getBaseContext(), Sign_In.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("pass",data);
                    intent.putExtra("user",text.getText().toString());
                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent,
                            PendingIntent.FLAG_ONE_SHOT);

                    Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getBaseContext())
                            .setContentTitle("GoCrazy Message")
                            .setTicker("The Password of your email ID "+text.getText()+" is "+data)
                            .setSmallIcon(R.drawable.go_crazy)
                            .setContentText("You have recieved new Messages")
                            .setAutoCancel(true)
                            .setSound(uri)
                            .setPriority(Notification.PRIORITY_HIGH)
                            .setContentIntent(pendingIntent);

                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    notificationManager.notify(0 , notificationBuilder.build());
                }
            }
        });
    }
}
