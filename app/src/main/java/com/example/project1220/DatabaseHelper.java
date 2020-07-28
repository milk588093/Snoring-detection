package com.example.project1220;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "RawData.db";
    public static final String TABLE_NAME = "data_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "DATA";
    public static final String COL_4 = "number";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table if not exists "+TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT, DATA TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String date, String data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,data);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
        {
            return false;
        }else
            {
            return true;
        }
    }

    public Cursor getData(String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select DATA from " + TABLE_NAME + " WHERE DATE = " +"'" +date+"'" + "LIMIT 1", null);
        return result;
    }

    public Cursor getLatestData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery("select DATA from " + TABLE_NAME + " WHERE ID = (SELECT MAX(ID) FROM "+TABLE_NAME+")", null);
        return result;
    }

    public Cursor number(String number )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select number from " + TABLE_NAME + " WHERE DATE = " +"'" +number+"'" + "LIMIT 1", null);
        return result;
    }
    public Cursor getDateList()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME,null);
        return result;
    }

    public Integer deleteData(String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, " DATE = ?", new String[]{date});
    }



}
