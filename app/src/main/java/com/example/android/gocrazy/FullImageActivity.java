package com.example.android.gocrazy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by sanjaypradeep on 10-08-2016.
 */
public class FullImageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button add_to_cart_button;
    TextView textView;
    int[] rup;
    int position;
    public static int count=0;
    public static String food ;
    public static String amount ;
    public static int quan;

    DBHandler_Card dbHandler_card;

   // public static LinkedHashMap<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        dbHandler_card = new DBHandler_Card(this,null,null,1);

       // hashMap = new LinkedHashMap<>();

        Intent i = getIntent();

         position = i.getExtras().getInt("id");
        final String[] name = i.getStringArrayExtra("name");
        rup = i.getIntArrayExtra("rupee");
        count = i.getExtras().getInt("count");

        add_to_cart_button = (Button) findViewById(R.id.cart_button);


        //textView = (TextView) findViewById(R.id.text);
       // textView.setText(name[position]);

        ImageAdapter imageAdapter = new ImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);

        imageView.setImageResource(imageAdapter.mThumbIds[position]);

        textView = (TextView) findViewById(R.id.amount_text);
        textView.setText("Rs. "+ rup[position]);

       final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.number,android.R.layout.simple_spinner_item);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        add_to_cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hashMap.put(name[position],textView.getText().toString());
                food=name[position];
                amount=textView.getText().toString();
                quan= Integer.parseInt(spinner.getSelectedItem().toString());
                Card_Data card_data = new Card_Data(food,amount,quan);
                dbHandler_card.add_data(card_data);
                Intent i =new Intent(getBaseContext(),Main_frame.class);
                i.putExtra("count",count);
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
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        textView = (TextView) findViewById(R.id.amount_text);
        int x = (i+1) * rup[position];
        //Toast.makeText(this,"Rupees is "+x,Toast.LENGTH_LONG).show();
        textView.setText("Rs ." +x);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_to_cart:
              item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                  @Override
                  public boolean onMenuItemClick(MenuItem menuItem) {
                      add();
                      return true;
                  }
              });

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void add(){
       // MyActivity myActivity = new MyActivity();
        dbHandler_card.getcount();
        Intent i = new Intent(this,MyActivity.class);
        startActivity(i);
    }
}
