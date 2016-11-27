package com.example.android.gocrazy;


import android.content.Context;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by sanjaypradeep on 10-08-2016.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public Integer[] mThumbIds = {
            R.drawable.dosa,R.drawable.idly,R.drawable.muton_briyani
            ,R.drawable.naan,R.drawable.parotta,R.drawable.tandoori,R.drawable.vada
            ,R.drawable.veg_briyani
    };

    ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public Object getItem(int i) {
        return mThumbIds[i];
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

      //  System.out.print("Hello");

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[i]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(305,305));

        return imageView;
    }
}

