package com.example.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Controllerdb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="SqliteListviewDB";
    public Controllerdb(Context applicationcontext) {
        super(applicationcontext, DATABASE_NAME, null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table to insert data
        String query;
        query = "CREATE TABLE IF NOT EXISTS Score(Id INTEGER PRIMARY KEY AUTOINCREMENT,nim VARCHAR,nama VARCHAR,tema VARCHAR,score VARCHAR,tgl VARCHAR);";
        db.execSQL(query);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query ;
        query = "DROP TABLE IF EXISTS Score";
        db.execSQL(query);
        onCreate(db);
    }

}
