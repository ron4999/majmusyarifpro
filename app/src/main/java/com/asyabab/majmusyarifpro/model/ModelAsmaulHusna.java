package com.asyabab.majmusyarifpro.model;


public class ModelAsmaulHusna  {

    private String nomer;
    private String latin;
    private String arab;
    private String indonesia;
    private String inggris;

    public ModelAsmaulHusna(String nomer, String latin, String arab, String indonesia,String inggris) {
        this.nomer = nomer;
        this.latin = latin;
        this.arab = arab;
        this.indonesia = indonesia;
        this.inggris = inggris;

    }

    public ModelAsmaulHusna(String nomer, String latin, String arab, String indonesia) {
        this.setNomer(nomer);
        this.setLatin(latin);
        this.setArab(arab);
        this.setIndonesia(indonesia);
    }


    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getArab() {
        return arab;
    }

    public void setArab(String arab) {
        this.arab = arab;
    }

    public String getIndonesia() {
        return indonesia;
    }

    public void setIndonesia(String indonesia) {
        this.indonesia = indonesia;
    }

    public String getInggris() {
        return inggris;
    }

    public void setInggris(String inggris) {
        this.inggris = inggris;
    }
}
