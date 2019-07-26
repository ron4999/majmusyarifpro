package com.asyabab.majmusyarifpro.database;

import android.provider.BaseColumns;


public class DatabaseContract {

    static final String DATABASE_NAME = "majmu.db";
    static final int DATABASE_VERSION = 2;

    public static final String LOAD_TERJEMEMAHAN_INDONESIA = "TerjemahanIndonesia";
    public static final String LOAD_TERJEMEMAHAN_ENGLISH = "TerjemahanEnglish";

    public static class TableSurah implements BaseColumns {
        public static final String TABLE_SURAH = "table_surah";

        public static final String SURAH = "Surah";
        public static final String AYAT = "Ayat";

        public static final String TERJEMAHAN_INDONESIA = LOAD_TERJEMEMAHAN_INDONESIA;
        public static final String TERJEMAHAN_ENGLISH = LOAD_TERJEMEMAHAN_ENGLISH;
        public static final String JUMLAH_AYAT = "Jumlah_Ayat";

        static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_SURAH + "("
                + SURAH + " TEXT,"
                + AYAT + " TEXT,"
                + TERJEMAHAN_INDONESIA + " TEXT,"
                + TERJEMAHAN_ENGLISH + " TEXT,"
                + JUMLAH_AYAT + " TEXT)";
        public static final String QUERY_STATEMENT = "INSERT INTO " + TABLE_SURAH + "(" + SURAH + "," + AYAT + "," + TERJEMAHAN_INDONESIA + "," + TERJEMAHAN_ENGLISH + "," + JUMLAH_AYAT + ") VALUES (?,?,?,?,?)";
    }

    public static class TableAyat implements BaseColumns {
        public static final String TABLE_AYAT = "table_ayat";

        public static final String SURAH = "Surah";
        public static final String AYAT = "Ayat";
        public static final String ARAB = "Arab";
        public static final String TERJEMAHAN_INDONESIA = LOAD_TERJEMEMAHAN_INDONESIA;
        public static final String TERJEMAHAN_ENGLISH = LOAD_TERJEMEMAHAN_ENGLISH;

        static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_AYAT + "("
                + SURAH + " TEXT,"
                + AYAT + " TEXT,"
                + ARAB + " TEXT,"
                + TERJEMAHAN_INDONESIA + " TEXT,"
                + TERJEMAHAN_ENGLISH + " TEXT)";

        public static final String QUERY_STATEMENT = "INSERT INTO " + TABLE_AYAT + "(" + SURAH + "," + AYAT + "," + ARAB + "," + TERJEMAHAN_INDONESIA + "," + TERJEMAHAN_ENGLISH + ") VALUES (?,?,?,?,?)";
    }

    public static class TableAsmaulHusna implements BaseColumns {
        public static final String TABLE_ASMA = "table_asmaulhusna";

        public static final String NO = "no";
        public static final String LATIN = "latin";
        public static final String ARAB = "arab";
        public static final String INDONESIA = "indonesia";
        public static final String INGGRIS = "inggris";

        static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_ASMA + "("
                + NO + " TEXT,"
                + LATIN + " TEXT,"
                + ARAB + " TEXT,"
                + INDONESIA + " TEXT,"
                + INGGRIS + " TEXT)";

        public static final String QUERY_STATEMENT = "INSERT INTO " + TABLE_ASMA + "(" + NO + "," + LATIN + "," + ARAB + "," + INDONESIA + "," + INGGRIS + ") VALUES (?,?,?,?,?)";
    }

    public static class TableNote implements BaseColumns {
        public static final String TABLE_NOTE = "table_note";

        public static final String ID = "no";
        public static final String NAMA = "nama";
        public static final String STATUS = "status";
        public static final String STATUS2 = "status2";


        static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTE + "("
                + ID + " TEXT,"
                + NAMA + " TEXT,"
                + STATUS + " TEXT,"
                + STATUS2 + " TEXT)";
        public static final String QUERY_UPDATE= "UPDATE "+TABLE_NOTE+" SET " + STATUS + "=(?) WHERE "+NAMA+"= (?)";
        public static final String QUERY_UPDATE2= "UPDATE "+TABLE_NOTE+" SET " + STATUS2 + "=(?) WHERE "+NAMA+"= (?)";

        public static final String QUERY_STATEMENT = "INSERT INTO " + TABLE_NOTE + "(" + ID + "," + NAMA + "," + STATUS + ") VALUES (?,?,?)";
    }

    public static class TableJadwalSholat implements BaseColumns {
        public static final String TABLE_SHOLAT = "table_jadwalsholat";
        public static final String ID = "no";
        public static final String TANGGAL = "tanggal";
        public static final String SUBUH = "subuh";
        public static final String DUHUR = "duhur";
        public static final String ASHAR = "ashar";
        public static final String MAGHRIB = "maghrib";
        public static final String ISYA = "isya";


        static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_SHOLAT + "("
                + ID + " TEXT,"
                + TANGGAL + " TEXT,"
                + SUBUH + " TEXT,"
                + DUHUR + " TEXT,"
                + ASHAR + " TEXT,"
                + MAGHRIB + " TEXT,"
                + ISYA + " TEXT)";

        public static final String DELETE = "DELETE FROM "+TABLE_SHOLAT;

        public static final String QUERY_STATEMENT = "INSERT INTO " + TABLE_SHOLAT + "(" + ID + ","+ TANGGAL +"," + SUBUH + "," + DUHUR +","+ASHAR+","+MAGHRIB+","+ISYA+") VALUES (?,?,?,?,?,?,?)";
    }
}
