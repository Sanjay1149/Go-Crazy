package com.example.android.gocrazy;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sanjaypradeep on 04-10-2016.
 */
public class MyActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static final String TAG = "Myactivity";
   // public static int total=0;
   // LinkedHashMap<String ,String> hashMap = new LinkedHashMap<>();
    //private String[] myDataset = {"A","B","C","D","A","B","C","D","A","B","C","D","A","B","C","D","A","B","C","D","A","B","C","D"};
   // public static String[] foo,amt;
   // public static int[] q;
    DBHandler_Card dbHandler_card;
    public  MyActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        dbHandler_card = new DBHandler_Card(this,null,null,1);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.pay,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.pay_from_cart:
            {
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        Log.d(TAG,"we are in PAY FROM CART");
                        final ProgressDialog dialog = new ProgressDialog(MyActivity.this);
                        dialog.setTitle("Payment for COD processing ....");
                        dialog.setMessage("Please wait");
                        dialog.setIndeterminate(true);
                        dialog.setCancelable(true);
                        dialog.show();

                        long delayInmillis = 80000;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        },delayInmillis);
                        Toast.makeText(MyActivity.this,"Amount Paid !!!",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getBaseContext(),Main_frame.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent,
                                PendingIntent.FLAG_ONE_SHOT);

                        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


                        android.support.v4.app.NotificationCompat.Builder notificationBuilder = new android.support.v4.app.NotificationCompat.Builder(getBaseContext())
                                .setContentTitle("Total Amount Rs."+MyAdapter.val)
                                .setTicker("You Paid the amount .... Food will be delivered in 30 mins")
                                .setSmallIcon(R.drawable.go_crazy)
                                .setContentText("You Paid the amount .... Food will be delivered in 30 mins")
                                .setAutoCancel(true)
                                .setSound(uri)
                                .setPriority(Notification.PRIORITY_HIGH)
                                .setContentIntent(pendingIntent);

                        NotificationManager notificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                        dialog.dismiss();
                        notificationManager.notify(0 , notificationBuilder.build());
                        startActivity(intent);
                        return true;
                    }
                });
                break;
            }
           case R.id.delete_from_cart:

                Log.d(TAG,"we are in DELETE ");
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        dbHandler_card.delete();
                        Intent i = new Intent(getBaseContext(),Main_frame.class);
                        startActivity(i);
                        return true;
                    }
                });
                break;


            default:
                return super.onOptionsItemSelected(item);

        }
        return super.onOptionsItemSelected(item);
    }
}