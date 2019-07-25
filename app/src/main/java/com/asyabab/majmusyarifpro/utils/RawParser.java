package com.asyabab.majmusyarifpro.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.asyabab.majmusyarifpro.App;
import com.asyabab.majmusyarifpro.R;
import com.asyabab.majmusyarifpro.model.ModelAsmaulHusna;
import com.asyabab.majmusyarifpro.model.ModelNote;
import com.asyabab.majmusyarifpro.modelquran.ModelAyat;
import com.asyabab.majmusyarifpro.modelquran.ModelSurah;

import static com.android.volley.VolleyLog.TAG;


public class RawParser {

    public static List<ModelAyat> getRawAyat() throws IOException {
        BufferedReader reader = App.getRawResources(R.raw.ayat);
        List<ModelAyat> ayatList = new ArrayList<>();

        String rawAyat;
        while ((rawAyat = reader.readLine()) != null) {
            String[] rawAyats = rawAyat.split("//");
            ayatList.add(new ModelAyat(rawAyats[0], rawAyats[1], rawAyats[2], rawAyats[3], rawAyats[4]));
        }

        return ayatList;
    }

    public static List<ModelSurah> getRawSurah() throws IOException {
        BufferedReader reader = App.getRawResources(R.raw.surah);
        List<ModelSurah> surahList = new ArrayList<>();

        String rawSurah;
        while ((rawSurah = reader.readLine()) != null) {
            String[] rawSurahs = rawSurah.split("//");

            if (rawSurahs.length < 5){
                continue;
            }
            surahList.add(new ModelSurah(rawSurahs[0], rawSurahs[1], rawSurahs[2], rawSurahs[3], rawSurahs[4]));
        }

        return surahList;
    }


    public static List<ModelAsmaulHusna> getRawAsmaulHusna() throws IOException {
        BufferedReader reader = App.getRawResources(R.raw.asmaulhusna);
        List<ModelAsmaulHusna> asmalist = new ArrayList<>();

        String rawAsma;
        while ((rawAsma = reader.readLine()) != null) {
            String[] rawAsmas = rawAsma.split("//");

            if (rawAsmas.length < 5){
                continue;
            }
            asmalist.add(new ModelAsmaulHusna(rawAsmas[0], rawAsmas[1], rawAsmas[2], rawAsmas[3], rawAsmas[4]));
        }

        return asmalist;
    }

    public static List<ModelNote> getRawNote() throws IOException {
        BufferedReader reader = App.getRawResources(R.raw.notes);
        List<ModelNote> noteList = new ArrayList<>();
        String rawNote;
        while ((rawNote = reader.readLine()) != null) {
            String[] rawNotes = rawNote.split("//");

            noteList.add(new ModelNote(rawNotes[0], rawNotes[1], rawNotes[2]));
        }

        return noteList;
    }
}
