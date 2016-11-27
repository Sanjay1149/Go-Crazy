package com.example.android.gocrazy;

import android.content.Context;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by sanjaypradeep on 04-10-2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final String TAG = "MyAdapter";
    public LinkedHashMap<String,String> hash = new LinkedHashMap<>();
    public int total=0;
    public static int val;
    //private String[] mDataset;
    String t1= "",t2 = "";
    int t3,c=1;
    DBHandler_Card dbHandler_card ;
    Card_Data card_data = new Card_Data();
    int count = card_data.getId();
    Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView1,mTextView2,mTextView3;
        public ViewHolder(View v) {
            super(v);
            mTextView1 = (TextView) v.findViewById(R.id.info_text);
            mTextView2 = (TextView) v.findViewById(R.id.card_amount);
            mTextView3 = (TextView) v.findViewById(R.id.quantity_text);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context c) {
        mContext=c;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //Toast.makeText(mContext,"Hash Size is "+hash_size,Toast.LENGTH_LONG).show();

        dbHandler_card = new DBHandler_Card(mContext,null,null,1);
        dbHandler_card.input(c);

        t1 = card_data.getFood();
        t2 = card_data.getAmt();
        t3 = card_data.getQuan();

        String s1 = t2.replace("Rs .","");
        total += Integer.parseInt(s1);
       // Log.d(TAG,"c value is "+c+"food value is "+t1+"quantity is "+t3);
        holder.mTextView1.setText(t1);
        holder.mTextView2.setText(t2);
        holder.mTextView3.setText("" + t3);

        val=total;

        c++;
        //holder.mTextView1.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        //Log.d(TAG,"Count value is "+count+"\n\n\n\n");
        return count+1;
    }
}