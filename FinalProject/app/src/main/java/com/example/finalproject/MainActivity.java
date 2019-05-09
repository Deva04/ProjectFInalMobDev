package com.example.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    CardView nextMath, nextProg, nextScore, nextDosen, nextAbout, exit;
    TextView NIM, nama, tema_math, tema_prog, tema_dosen;
    String st_nim, st_nama, nim_new, nama_new, st_tema_math, st_tema_prog, st_tema_dosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NIM = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);
        tema_math = findViewById(R.id.tema_math);
        tema_prog = findViewById(R.id.tema_prog);
        tema_dosen = findViewById(R.id.tema_dosen);
        st_nim = getIntent().getExtras().getString("NIM");
        NIM.setText(st_nim);

        st_nama = getIntent().getExtras().getString("Nama");
        nama.setText(st_nama);

        nextAbout = findViewById(R.id.img_menu5);
        nextMath = findViewById(R.id.img_menu1);
        nextProg = findViewById(R.id.img_menu2);
        nextScore = findViewById(R.id.img_menu3);
        nextDosen = findViewById(R.id.img_menu4);
        exit = findViewById(R.id.img_menu6);
        nextMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MenuMath.class);
                nim_new = NIM.getText().toString();
                nama_new = nama.getText().toString();
                st_tema_math = tema_math.getText().toString();
                myIntent.putExtra("NIM", nim_new);
                myIntent.putExtra("Nama", nama_new);
                myIntent.putExtra("Tema", st_tema_math);
                startActivity(myIntent);
            }
        });
        nextProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MenuProg.class);
                nim_new = NIM.getText().toString();
                nama_new = nama.getText().toString();
                st_tema_prog = tema_prog.getText().toString();
                myIntent.putExtra("NIM", nim_new);
                myIntent.putExtra("Nama", nama_new);
                myIntent.putExtra("Tema", st_tema_prog);
                startActivity(myIntent);
            }
        });nextDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent( MainActivity.this, MenuDosen.class);
                nim_new = NIM.getText().toString();
                nama_new = nama.getText().toString();
                st_tema_dosen = tema_dosen.getText().toString();
                myIntent.putExtra("NIM", nim_new);
                myIntent.putExtra("Nama", nama_new);
                myIntent.putExtra("Tema", st_tema_dosen);
                startActivity(myIntent);
            }
        });

        nextScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent( MainActivity.this, Score.class);
                startActivity(myIntent);
            }
        });

        nextAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent ( MainActivity.this, About.class);
                startActivity(myIntent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Ingin Keluar ?")
                        .setConfirmText("Ya")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                finish();
                                System.exit(0);
                            }
                        })
                        .setCancelText("Tidak")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismiss();
                            }
                        })
                        .setCancelText("Kembali ke Login")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                Intent myIntent = new Intent(MainActivity.this, Awal.class);
                                startActivity(myIntent);
                            }
                        })

                        .show();
            }
        });
    }
}
