package com.asyabab.majmusyarifpro.model;


public class Jadwal extends  ModelJadwal{

    public Jadwal(String id, String mtanggal, String imsak, String msubuh, String mzuhur, String mashar, String mmaghrib, String misya) {
        super(id, mtanggal, imsak, msubuh, mzuhur, mashar, mmaghrib, misya);
    }
    public String getImsak() {
        return super.getImsak();
    }

    public String getId() {
        return super.getId();
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
