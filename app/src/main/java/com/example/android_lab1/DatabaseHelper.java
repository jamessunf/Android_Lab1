package com.example.android_lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "chat.db";
    private static final String TABLE_NAME = "massage_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "SEND";
    private static final String COL_3 = "MASSAGE";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, SEND INTEGER, MASSAGE TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,person.getSend());
        contentValues.put(COL_3,person.getMassage());
        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
            return false;  //
        else
            return  true; //


    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;

    }

    public Integer getV(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.getVersion();
    }




    public boolean updateData(String id, int send, String massage){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,send);
        contentValues.put(COL_3,massage);
        db.update(TABLE_NAME, contentValues,"ID=?",new String[]{ id });
        return true;


    }

    public Integer deleteData (Person p){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"MASSAGE=?",new String[]{p.getMassage()});


    }


    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);

    }
}
