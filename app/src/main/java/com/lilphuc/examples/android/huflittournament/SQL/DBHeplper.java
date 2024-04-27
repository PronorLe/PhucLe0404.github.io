package com.lilphuc.examples.android.huflittournament.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHeplper extends SQLiteOpenHelper {

    public DBHeplper (@Nullable Context context) {
        super(context,"SQLAppDatTour.sqlite", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE Account(userName text primary key, passWord text," +
                "gmail text,phoneNumber text,sex text)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Account VALUES('admin','1','1','1','1')";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE Bill(billId int primary key,  userName text REFERENCES Account(userName))";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO Bill VALUES('1','admin')";
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE Cart(billId int REFERENCES Bill(billId),bookId int,bookName text,numberOfSelect int,price int,primary key (billId,bookId))";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
