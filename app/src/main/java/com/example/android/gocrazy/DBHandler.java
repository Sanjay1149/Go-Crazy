package com.example.android.gocrazy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.UnicodeSetSpanner;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sanjaypradeep on 28-09-2016.
 */
public class DBHandler extends SQLiteOpenHelper  {

    private static final String TAG = "Myactivity";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "acc.db";
    public static final String TABLE_NAME = "account2";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "pass";
    Context mcomtext;
 // Forgot_Password forpass;


    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME, factory,DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS Account");
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+"(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COLUMN_NAME + " VARCHAR(255),"+
                COLUMN_EMAIL + " VARCHAR(255),"+
                COLUMN_PASS + " VARCHAR(255)"+
                ");";
        db.execSQL(query);
    }

    public void addAccount(Account_Data acc){

        String n = acc.getFname();
        String e = acc.getEmail();
        String p = acc.getPass();
        Log.d(TAG,"name  "+n + "  email  "+e+ "  pass  "+ p+ "\n\n\n\n");

        if(n.equals("")||e.equals("")||p.equals("")){
            Toast.makeText(mcomtext,"Please Fill all the fields",Toast.LENGTH_LONG).show();
            return;
        }

        SQLiteDatabase db = getWritableDatabase();
        String query = "INSERT INTO "+TABLE_NAME+ " ("+COLUMN_NAME+","+COLUMN_EMAIL+","+COLUMN_PASS+") VALUES('"+n+"','"+e+"','"
                +p+"');";
        db.execSQL(query);
        /*ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,acc.getFname());
        values.put(COLUMN_EMAIL,acc.getEmail());
        values.put(COLUMN_PASS,acc.getPass());
        db.insert(TABLE_NAME,null,values);*/
        db.close();
    }

    public String  viewalldata(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        int count=1;
        String dbstring = "" ;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
            while (c.moveToNext()) {
                dbstring += "ID no .. "+count+++"\n";
                dbstring +="Name : "+ c.getString(c.getColumnIndex(COLUMN_NAME)) +"\n";
                dbstring +="Email :"+ c.getString(c.getColumnIndex(COLUMN_EMAIL)) +"\n";
                dbstring +="Password :"+ c.getString(c.getColumnIndex(COLUMN_PASS)) +"\n\n\n";

              //  forpass.text.setText(dbstring);
            }

        c.close();

        return dbstring;
    }

    public String getpass(String user){
        String data="";
        SQLiteDatabase db = getWritableDatabase();
        try {
            String query = "SELECT " + COLUMN_PASS + " FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + " = '" + user + "'";

            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();

            data = c.getString(c.getColumnIndex(COLUMN_PASS));
            Log.d(TAG, "data Value is " + data);

            c.close();
        }
        catch (NullPointerException e){
          //  Toast.makeText(mcomtext,"EmailID does not exist",Toast.LENGTH_LONG).show();
        }
        catch (CursorIndexOutOfBoundsException e){
         //   Toast.makeText(mcomtext,"EmailID does not exist",Toast.LENGTH_LONG).show();
        }
        return data;
    }

    public boolean check_Account(Account_Data acc) {
        Log.d(TAG,"S Value is ");
        boolean bool = false;

       // Log.d(TAG, "IT reached here though");

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT " + COLUMN_PASS + ","+COLUMN_EMAIL+ " FROM " + TABLE_NAME;

        Cursor c = db.rawQuery(query, null);


        //+ " WHERE " + COLUMN_EMAIL + " = \"" + acc.getEmail() + "\""
        //String m = c.getString(c.getColumnIndex(COLUMN_PASS));

        c.moveToFirst();
            while (c.moveToNext()) {
            String s = c.getString(c.getColumnIndex(COLUMN_PASS));
                String m = c.getString(c.getColumnIndex(COLUMN_EMAIL));

                Log.d(TAG,"S value "+s+" and m value "+m);

            if (s.equals(acc.getPass())&&m.equals(acc.getEmail())) {

                Log.d(TAG,"S value "+s+" and m value "+m);
                bool = true;
                break;
            }
        }

        c.close();

        return bool;
    }

}
