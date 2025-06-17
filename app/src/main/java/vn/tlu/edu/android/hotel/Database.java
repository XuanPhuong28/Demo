package vn.tlu.edu.android.hotel;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
public class Database extends SQLiteOpenHelper {
    final static String D_NAME = "HOTEL_DATABASE";
    final static int D_VERSION = 1;
    Context c;

    public Database(Context context) {
        super(context, D_NAME, null, D_VERSION);
        c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table = "create table hotel_db(ID long,HotelName text,Name text,PhoneNum long,CheckIn text,CheckOut text,Amount long,Days long )";
        db.execSQL(Create_Table);

    }

    public void add(long i,String h,String n,long p,String ci,String co,long a,long d)
    {
        SQLiteDatabase sd = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ID",i);
        cv.put("HotelName",h);
        cv.put("Name",n);
        cv.put("PhoneNum",p);
        cv.put("CheckIn",ci);
        cv.put("CheckOut",co);
        cv.put("Amount",a);
        cv.put("Days",d);
        sd.insert("hotel_db",null,cv);
    }

    ArrayList viewBooking()
    {
        SQLiteDatabase sd = getWritableDatabase();
        String query = "select * from hotel_db";
        Cursor cursor = sd.rawQuery(query,null);
        ArrayList<String> arrayList = new ArrayList<>();
        while(cursor.moveToNext())
        {
            long id = cursor.getLong(0);
            String ho = cursor.getString(1);
            String na = cursor.getString(2);
            long ph = cursor.getLong(3);
            String cI = cursor.getString(4);
            String cO = cursor.getString(5);
            long am = cursor.getLong(6);
            long da = cursor.getLong(7);
            String addValue = "Booking ID :- "+id+"\n"+"\nHotel Name :- "+
                    ho+"\n"+"\nName :- "+na+"\n"+"\nPhone Number :- "+
                    ph+"\n"+"\nCheck-In Date : - "+cI+"\n"+
                    "\nCheck-Out Date :- "+cO+"\n"+"\nNo of Days :- "+da+
                    "\n"+"\nAmount Paid :- "+am+"\n";
            arrayList.add(addValue);
        }
        return arrayList;
    }

    void delete(long id)
    {
        SQLiteDatabase sd = getWritableDatabase();
        String s = "delete from hotel_db where ID = "+id;
        // String[] i = {Long.toString(id)};
        sd.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
