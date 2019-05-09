package com.example.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Shree on 10/22/2016.
 */
public class CustomAdapter extends BaseAdapter
{
    private Context mContext;
    Controllerdb controldb;
    SQLiteDatabase db;

    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Nim = new ArrayList<String>();
    private ArrayList<String> Nama = new ArrayList<String>();
    private ArrayList<String> Tema = new ArrayList<String>();
    private ArrayList<String> Score = new ArrayList<String>();
    private ArrayList<String> Tgl = new ArrayList<String>();
    public CustomAdapter(Context  context,ArrayList<String> Id,ArrayList<String> Nim,ArrayList<String> Nama, ArrayList<String> Tema,ArrayList<String> Score, ArrayList<String> Tgl)
    {
        this.mContext = context;
        this.Id = Id;
        this.Nim = Nim;
        this.Nama = Nama;
        this.Tema = Tema;
        this.Score = Score;
        this.Tgl = Tgl;
    }


    @Override
    public int getCount() {
        return Id.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final viewHolder holder;
        controldb = new Controllerdb(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_score, parent, false);
            holder = new viewHolder();
            holder.nim = (TextView) convertView.findViewById(R.id.tvnim);
            holder.nama = (TextView) convertView.findViewById(R.id.tvnama);
            holder.tema = (TextView) convertView.findViewById(R.id.tvtema);
            holder.score = (TextView) convertView.findViewById(R.id.tvscore);
            holder.tgl = (TextView) convertView.findViewById(R.id.tvtgl);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.nim.setText(Nim.get(position));
        holder.nama.setText(Nama.get(position));
        holder.tema.setText(Tema.get(position));
        holder.score.setText(Score.get(position));
        holder.tgl.setText(Tgl.get(position));

        return convertView;
    }
    public class viewHolder {
        TextView nim;
        TextView nama;
        TextView tema;
        TextView score;
        TextView tgl;
    }
}
