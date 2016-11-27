package com.example.android.gocrazy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Main_frame extends AppCompatActivity {

    Intent i;

    public static int count=0;
    String[] Characters = {"Dosa","Idly","Mutton Briyani","Naan","Parotta","Tandoori"
            ,"Vada","Veg Briyani"};
    int[] rupee = {50,30,150,35,15,130,10,100};

    DBHandler_Card dbHandler_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goku_layout);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));

        dbHandler_card = new DBHandler_Card(this,null,null,1);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int in, long l) {

               // Toast.makeText(getBaseContext(),"Here We are 444 ",Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(),FullImageActivity.class);
                i.putExtra("id",in);
                i.putExtra("rupee",rupee);
                i.putExtra("name",Characters);
                Toast.makeText(getBaseContext(),""+Characters[in]+"  is clicked",Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_to_cart:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //Toast.makeText(getBaseContext(),"when is it here",Toast.LENGTH_LONG).show();
                        add_op();
                        return true;
                    }
                });
                break;
            case R.id.delete_from_cart:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        dbHandler_card.delete();
                        Intent i = new Intent(getBaseContext(),Main_frame.class);
                        startActivity(i);
                        return false;
                    }
                });
                  break;
            default:
                return super.onOptionsItemSelected(item);

        }return super.onOptionsItemSelected(item);}

    public void add_op(){
        //Toast.makeText(this,"IT is here",Toast.LENGTH_LONG).show();
        //MyActivity myActivity = new MyActivity();
        dbHandler_card.getcount();
        Intent i = new Intent(this,MyActivity.class);
        startActivity(i);
    }
}
