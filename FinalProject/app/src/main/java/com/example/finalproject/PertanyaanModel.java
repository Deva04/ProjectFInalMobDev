package com.example.finalproject;

public class PertanyaanModel {

    public PertanyaanModel(String pertanyaan, String jawaban) {

        StringPertanyaan = pertanyaan;
        Jawaban = jawaban;
    }

    public String getStringPertanyaan() {

        return StringPertanyaan;
    }

    public void setStringPertanyaan(String stringPertanyaan) {

        StringPertanyaan = stringPertanyaan;
    }

    public String getJawaban() {

        return Jawaban;
    }

    public void setJawaban(String jawab) {

        Jawaban = jawab;
    }

    private String StringPertanyaan;
    private String Jawaban;


}
