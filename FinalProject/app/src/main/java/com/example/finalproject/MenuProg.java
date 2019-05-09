package com.example.finalproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MenuProg extends AppCompatActivity {

    Controllerdb db =new Controllerdb(this);
    SQLiteDatabase database;
    TextView labelPertanyaan, hitungLabelPertanyaan, labelHasil, NIM, nama, tema_prog, tgl;
    EditText jawaban;
    Button submit, back;
    ProgressBar progressBar;
    ArrayList<PertanyaanModel> arrayDaftarPertanyaan;
    String st_nim, st_nama, nim_new, nama_new, st_tema_prog;

    int pos = 0;
    int jumJawabBenar = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_prog);

        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        tgl = findViewById(R.id.tanggal);
        tgl.setText(date_n);
        NIM = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);
        tema_prog = findViewById(R.id.tema_prog);

        st_tema_prog = getIntent().getExtras().getString("Tema");
        tema_prog.setText(st_tema_prog);

        st_nim = getIntent().getExtras().getString("NIM");
        NIM.setText(st_nim);

        st_nama = getIntent().getExtras().getString("Nama");
        nama.setText(st_nama);

        hitungLabelPertanyaan = findViewById(R.id.noQuestion);
        labelPertanyaan = findViewById(R.id.question);
        labelHasil = findViewById(R.id.score);
        jawaban = findViewById(R.id.answer);
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.back);
        progressBar = findViewById(R.id.progress);

        arrayDaftarPertanyaan = new ArrayList<>();
        setPertanyaan();
        setData();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkJawaban();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu();
            }
        });

        jawaban.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                Log.e("event.getAction()",event.getAction()+"");
                Log.e("event.keyCode()",keyCode+"");
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    checkJawaban();
                    return true;
                }
                return false;
            }
        });

    }

    public void menu(){
        Intent myIntent = new Intent(MenuProg.this, MainActivity.class);
        nim_new = NIM.getText().toString();
        nama_new = nama.getText().toString();
        myIntent.putExtra("NIM", nim_new);
        myIntent.putExtra("Nama", nama_new);
        startActivity(myIntent);
    }

    public void checkJawaban(){
        String answerString  = jawaban.getText().toString().trim();

        if(answerString.equalsIgnoreCase(arrayDaftarPertanyaan.get(pos).getJawaban())) {
            jumJawabBenar ++;
            new SweetAlertDialog(MenuProg.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good job!")
                    .setContentText("Jawaban Benar")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            pos ++;
                            setData();
                            jawaban.setText("");
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();
        }
        else {

            new SweetAlertDialog(MenuProg.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Jawaban Salah")
                    .setContentText("Jawaban Yang Benar : "+arrayDaftarPertanyaan.get(pos).getJawaban())
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                            pos ++;
                            setData();
                            jawaban.setText("");
                        }
                    })
                    .show();
        }
        int x = ((pos+1) * 100) / arrayDaftarPertanyaan.size();
        progressBar.setProgress(x);
    }

    public void setPertanyaan(){
        arrayDaftarPertanyaan.add(new PertanyaanModel("Bahasa Pemograman Java adalah Bahasa Pemograman Tingkat ________. ","Tinggi"));
        arrayDaftarPertanyaan.add(new PertanyaanModel("Variabel ______ Merupakan Variabel Yang Bisa Digunakan atau Dipanggil Oleh Seluruh Struktur Program. ","Global"));
        arrayDaftarPertanyaan.add(new PertanyaanModel("Nama Lengkap Penemu Bahasa Pemograman Java ? ","James Gosling"));
        arrayDaftarPertanyaan.add(new PertanyaanModel("Benar atau Salah ? Method dan Properti dari sebuah kelas dapat diturunkan ke kelas yang lainnya. ","Benar"));
        arrayDaftarPertanyaan.add(new PertanyaanModel("__________ adalah method khusus yang akan dieksekusi pada saat pembuatan objek (instance). ","Constructor"));
    }

    public void setData(){

        labelHasil.setText( jumJawabBenar + "/" + arrayDaftarPertanyaan.size());

        if(arrayDaftarPertanyaan.size()>pos) {
            labelPertanyaan.setText(arrayDaftarPertanyaan.get(pos).getStringPertanyaan());
            hitungLabelPertanyaan.setText("Pertanyaan ke : " + (pos + 1));
        }
        else{
            new SweetAlertDialog(MenuProg.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Quiz Selesai")
                    .setContentText("Nilai : "+ jumJawabBenar + "/" + arrayDaftarPertanyaan.size())
                    .setConfirmText("Lagi ?")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            pos = 0;
                            jumJawabBenar = 0;
                            progressBar.setProgress(0);
                            simpanData();
                            setData();
                        }
                    })
                    .setCancelText("Kembali ke Menu")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            simpanData();
                            menu();
                            finish();
                        }
                    })
                    .show();
        }

    }

    public void simpanData(){
        database=db.getWritableDatabase();
        database.execSQL("INSERT INTO Score(nim, nama, tema, score, tgl)VALUES('"+NIM.getText()+"','"+nama.getText()+"','"+tema_prog.getText()+"','"+labelHasil.getText()+"','"+tgl.getText()+"')" );
        Toast.makeText(this,"Data Inserted To Sqlite Database",Toast.LENGTH_LONG).show();
    }
}

