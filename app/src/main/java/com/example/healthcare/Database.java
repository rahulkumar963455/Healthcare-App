package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qr1 = "CREATE TABLE users(username TEXT, email TEXT, password TEXT)";
        db.execSQL(qr1);

        String qr2 = "CREATE TABLE cart(username TEXT, product TEXT, price FLOAT, otype TEXT)";
        db.execSQL(qr2);

        String qr3 = "CREATE TABLE orderplace(username TEXT, fullname TEXT, address TEXT, pin INTEGER, contactNo TEXT, amount FLOAT, date TEXT, time TEXT, otype TEXT)";
        db.execSQL(qr3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement schema upgrade logic if needed
    }

    public void registerUser(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        SQLiteDatabase database = getWritableDatabase();
        database.insert("users", null, cv);
        database.close();
    }

    public int loginUser(String username, String password) {
        String[] args = {username, password};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", args);

        boolean isLoggedIn = cursor != null && cursor.moveToFirst();
        if (cursor != null) cursor.close();
        db.close();

        return isLoggedIn ? 1 : 0;
    }

    public void addCart(String username, String product, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }

    public int checkCart(String username, String product) {
        String[] args = {username, product};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cart WHERE username = ? AND product = ?", args);

        boolean exists = cursor != null && cursor.moveToFirst();
        if (cursor != null) cursor.close();
        db.close();

        return exists ? 1 : 0;
    }

    public void removeCart(String username, String otype) {
        String[] args = {username, otype};
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "username = ? AND otype = ?", args);
        db.close();
    }

    public ArrayList<String> getCartData(String username, String otype) {
        ArrayList<String> cartData = new ArrayList<>();
        String[] args = {username, otype};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cart WHERE username = ? AND otype = ?", args);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String product = cursor.getString(1);
                String price = String.valueOf(cursor.getFloat(2));
                cartData.add(product + " - $" + price);
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        db.close();

        return cartData;
    }

    public void addOrder(String username, String fullname, String address, int pin, String contactNo, float amount, String date, String time, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("pin", pin);
        cv.put("contactNo", contactNo);
        cv.put("amount", amount);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("otype", otype);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();
    }

    public  ArrayList getOrderData(String username){
        ArrayList<String> arr=new ArrayList<>();
        String[] str = new String[1];
        str[0] = username;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM orderplace WHERE username = ?", str);
        if(cursor.moveToFirst()){
            do{
                arr.add(cursor.getString(1)+"$"+cursor.getString(2)+"$"+cursor.getString(3)+"$"+cursor.getString(4)+"$"+cursor.getString(5)+"$"+cursor.getString(6)+"$"+cursor.getString(7)+"$"+cursor.getString(8));
            }while (cursor.moveToNext());
        }
        db.close();
        return arr;
    }

    public  int checkAppointmentExists(String username,String fullname, String address, String contact,String date,String time){
        int result=0;
        String str[]=new String[6];
        str[0]=username;
        str[1]=fullname;
        str[2]=address;
        str[3]=contact;
        str[5]=date;
        str[6]=time;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM orderplace WHERE username = ? AND fullname = ? AND address = ? AND contact = ? AND date = ? AND time = ?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }
}
