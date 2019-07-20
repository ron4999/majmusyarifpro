package com.asyabab.majmusyarifpro.activity.listjadwal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.asyabab.majmusyarifpro.base.BasePresenter;
import com.asyabab.majmusyarifpro.database.DatabaseContract;
import com.asyabab.majmusyarifpro.database.DatabaseHelper;
import com.asyabab.majmusyarifpro.model.Jadwal;
import com.asyabab.majmusyarifpro.modelquran.Surah;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by User on 01/05/2018.
 */

public class ListJadwalPresenter extends BasePresenter<ListJadwalView> {
    ListJadwalPresenter(ListJadwalView view) {
        super.attach(view);
    }

    void loadTanggal(String loadtanggal) {
        SQLiteDatabase database = DatabaseHelper.getDatabase();
        Cursor cursor = database.query(DatabaseContract.TableJadwalSholat.TABLE_SHOLAT, null, DatabaseContract.TableJadwalSholat.TANGGAL + " LIKE '" + loadtanggal + "'", null, null, null, null);

        ArrayList<Jadwal> data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String tanggal = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableJadwalSholat.TANGGAL));
                String subuh = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableJadwalSholat.SUBUH));
                String duhur = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableJadwalSholat.DUHUR));
                String ashar = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableJadwalSholat.ASHAR));
                String maghrib = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableJadwalSholat.MAGHRIB));
                String isya = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableJadwalSholat.ISYA));
                String imsak="";
                String menit="00:10";
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");

                Date d1 = null;
                Date d2 = null;

                try {
                    d1 = format.parse(subuh);
                    d2 = format.parse(menit);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                    long diff = d1.getTime()-d2.getTime();

                    long diffMinutes = (diff / (60 * 1000) % 60);
                    long diffHours = diff / (60 * 60 * 1000) % 24;

                    imsak= ("0"+diffHours+":"+diffMinutes);
                    Log.d("hoursc",diffHours + " hours, ");
                    Log.d("minutesc",diffMinutes + " minutes, ");

                data.add(new Jadwal(tanggal, imsak, subuh , duhur , ashar, maghrib,isya));
            } while (cursor.moveToNext());
        }
        mView.onLoad(data);

        cursor.close();
        database.close();
    }
}
