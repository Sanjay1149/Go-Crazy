package com.example.android.gocrazy;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sanjaypradeep on 06-10-2016.
 */
public class DBHandler_Card extends SQLiteOpenHelper {

    private static final String TAG = "MyDatabase";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "a.db";
    public static final String TABLE_NAME = "carde";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FOOD = "food";
    public static final String COLUMN_AMT = "amt";
    public static final String COLUMN_QUAN = "quan";
    Context mcomtext;


    public DBHandler_Card(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
                COLUMN_FOOD + " VARCHAR(255),"+
                COLUMN_AMT + " VARCHAR(255),"+
                COLUMN_QUAN + " INTEGER"+
                ");";
        db.execSQL(query);
    }

    public void delete(){

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DROP TABLE "+TABLE_NAME);
        onCreate(db);
    }

    public void add_data(Card_Data car){

        String f = car.getFood();
        String a = car.getAmt();
        int q = car.getQuan();
       // Log.d(TAG,"name  "+n + "  email  "+e+ "  pass  "+ p+ "\n\n\n\n");

        SQLiteDatabase db = getWritableDatabase();
        String query = "INSERT INTO "+TABLE_NAME+ " ("+COLUMN_FOOD+","+COLUMN_AMT+","+COLUMN_QUAN+") VALUES('"+f+"','"+a+"'," +q+");";
        db.execSQL(query);

        db.close();
    }

    public void input(int i){
        String f = "", a = "";
        int q = 0;
        SQLiteDatabase db = getWritableDatabase();
        try {
            String query = "SELECT " + COLUMN_FOOD + "," + COLUMN_AMT + "," + COLUMN_QUAN + " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + i;
            //int count=0;
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            //while (c.moveToNext()) {

                Log.d(TAG,"food "+f+"amount "+a+"and quan is "+q+"where id = "+i);
                f = c.getString(c.getColumnIndex(COLUMN_FOOD));
                a = c.getString(c.getColumnIndex(COLUMN_AMT));
                q = c.getInt(c.getColumnIndex(COLUMN_QUAN));

          //  }
            Card_Data card_data = new Card_Data(f, a, q);
            c.close();
        }
        catch (CursorIndexOutOfBoundsException c){

        }
        }

    public void getcount(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            String query = "SELECT "+COLUMN_ID+" FROM "+TABLE_NAME;
            int count,co=0;

            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            while (c.moveToNext()) {
                count=c.getInt(c.getColumnIndex(COLUMN_ID));
                co++;
            }
            Card_Data card_data = new Card_Data(co);
            c.close();
        }
        catch (CursorIndexOutOfBoundsException c){

        }
    }
}
