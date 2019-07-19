package com.asyabab.majmusyarifpro.model;


public class Jadwal extends  ModelJadwal{

    public Jadwal(String mtanggal, String msubuh, String mzuhur, String mashar, String mmaghrib, String misya) {
        super(mtanggal, msubuh, mzuhur, mashar, mmaghrib, misya);
    }

    public String getTanggal() {
        return super.getTanggal();
    }

    public String getFajar() {
        return super.getFajar();
    }

    public String getSubuh() {
        return super.getSubuh();
    }

    public String getZuhur() {
        return super.getZuhur();
    }

    public String getAshar() {
        return super.getAshar();
    }

    public String getMaghrib() {
        return super.getMaghrib();
    }

    public String getIsya() {
        return super.getIsya();
    }
}
