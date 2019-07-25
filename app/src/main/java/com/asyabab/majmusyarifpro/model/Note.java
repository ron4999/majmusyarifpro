package com.asyabab.majmusyarifpro.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Note extends ModelNote{
    private String id;
    private String nama;
    private String status;

    public Note(String id, String nama, String status) {
        super(id, nama, status);
    }
    public String getId() {
        return super.getId();
    }

    public String getNama() {
        return super.getNama();
    }

    public String getStatus() {
        return super.getStatus();
    }
}
