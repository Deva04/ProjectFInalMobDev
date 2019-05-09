package com.example.finalproject;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Score extends AppCompatActivity {

    Controllerdb controllerdb =  new Controllerdb(this);
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Nim = new ArrayList<String>();
    private ArrayList<String> Nama = new ArrayList<String>();
    private ArrayList<String> Tema = new ArrayList<String>();
    private ArrayList<String> Score = new ArrayList<String>();
    private ArrayList<String> Tgl = new ArrayList<String>();
    ListView lv;
    ImageButton Overflow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        lv = findViewById(R.id.lstvw);
    }

    @Override
    protected void onResume() {
        displayData();
        super.onResume();
    }
    private void displayData() {
        db = controllerdb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  Score",null);
        Id.clear();
        Nim.clear();
        Nama.clear();
        Tema.clear();
        Score.clear();
        Tgl.clear();
        if (cursor.moveToFirst()) {
            do {
                Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                Nim.add(cursor.getString(cursor.getColumnIndex("nim")));
                Nama.add(cursor.getString(cursor.getColumnIndex("nama")));
                Tema.add(cursor.getString(cursor.getColumnIndex("tema")));
                Score.add(cursor.getString(cursor.getColumnIndex("score")));
                Tgl.add(cursor.getString(cursor.getColumnIndex("tgl")));
            } while (cursor.moveToNext());
        }

        CustomAdapter ca = new CustomAdapter(Score.this,Id, Nim,Nama,Tema,Score,Tgl);
        lv.setAdapter(ca);
        //code to set adapter to populate list
        cursor.close();
    }



}
