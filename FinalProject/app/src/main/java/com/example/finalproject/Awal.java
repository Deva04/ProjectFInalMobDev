package com.example.finalproject;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Awal extends AppCompatActivity {

    TextInputEditText textInputNIM;
    TextInputEditText textInputUsername;
    Button btn;
    String nama;
    String NIM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);

        textInputNIM = findViewById(R.id.nim);
        textInputUsername = findViewById(R.id.username);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInput();
            }
        });
    }

    private boolean validateUsername() {
        String usernameInput = textInputUsername.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputUsername.setError("Nama Tidak Boleh Kosong");
            return false;
        } else if (usernameInput.length() > 15) {
            textInputUsername.setError("Nama Terlalu Panjang");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }

    private boolean validateNIM() {
        String usernameInput = textInputNIM.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputUsername.setError("NIM Tidak Boleh Kosong");
            return false;
        } else if (usernameInput.length() > 12) {
            textInputUsername.setError("NIM Terlalu Panjang");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }

    public void confirmInput() {
        if (!validateNIM() | !validateUsername()) {
            return;
        }
        else{
            Intent myIntent = new Intent(Awal.this, MainActivity.class);
            NIM = textInputNIM.getText().toString();
            nama = textInputUsername.getText().toString();
            myIntent.putExtra("NIM", NIM);
            myIntent.putExtra("Nama", nama);
            startActivity(myIntent);
            finish();
        }
        String input = "NIM: " + textInputNIM.getText().toString();
        input += "\n";
        input += "Nama: " + textInputUsername.getText().toString();
        input += "\n";

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }


}
