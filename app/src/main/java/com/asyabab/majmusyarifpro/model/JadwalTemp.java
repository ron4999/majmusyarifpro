package com.asyabab.majmusyarifpro.model;


import com.google.gson.annotations.SerializedName;

public class JadwalTemp{

    @SerializedName("date_for")
    public String tanggal;
    @SerializedName("fajr")
    public String subuh;
    @SerializedName("shurooq")
    public String terbit;
    @SerializedName("dhuhr")
    public String zuhur;
    @SerializedName("asr")
    public String ashar;
    @SerializedName("maghrib")
    public String maghrib;
    @SerializedName("isha")
    public String isya;

    public JadwalTemp(String mtanggal, String msubuh, String mzuhur, String mashar, String mmaghrib, String misya) {
        this.tanggal = mtanggal;
        this.subuh = msubuh;
        this.zuhur = mzuhur;
        this.ashar = mashar;
        this.maghrib = mmaghrib;
        this.isya = misya;
    }


    public String getTanggal() {
        return tanggal;
    }

    public String getFajar() {
        return terbit;
    }

    public String getSubuh() {
        return subuh;
    }

    public String getZuhur() {
        return zuhur;
    }

    public String getAshar() {
        return ashar;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public String getIsya() {
        return isya;
    }
}
