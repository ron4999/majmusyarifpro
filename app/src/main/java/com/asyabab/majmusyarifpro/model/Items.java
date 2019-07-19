package com.asyabab.majmusyarifpro.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Items {
    @SerializedName("items")
    public List<JadwalTemp> items;
    String status_valid;

    public String getStatus_valid() {
        return status_valid;
    }

    public void setStatus_valid(String status_valid) {
        this.status_valid = status_valid;
    }

    public Items(List<JadwalTemp> items) {
        this.items = items;
    }

    public List<JadwalTemp> getItems() {
        return items;
    }

    public void setItems(List<JadwalTemp> items) {
        this.items = items;
    }
}
