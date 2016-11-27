package com.example.android.gocrazy;

/**
 * Created by sanjaypradeep on 06-10-2016.
 */
public class Card_Data {
    private static final String TAG = "Myactivity";
    public static int id;
    public static String food;
    public static String amt;
    public static  int quan;
    public Card_Data(){}
    public Card_Data(int i){
      id=i;
    }
    public Card_Data(String f,String a,int q){
        food=f;
        amt=a;
        quan=q;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuan() {
        return quan;
    }

    public void setQuan(int quan) {
        this.quan = quan;
    }
}
