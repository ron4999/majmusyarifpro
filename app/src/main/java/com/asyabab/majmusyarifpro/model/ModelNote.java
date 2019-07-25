package com.asyabab.majmusyarifpro.model;

public class ModelNote {
    private String id;
    private String nama;
    private String status;

    public ModelNote(String id, String nama, String status) {
        this.id = id;
        this.nama = nama;
        this.status = status;
    }
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getStatus() {
        return status;
    }
}
