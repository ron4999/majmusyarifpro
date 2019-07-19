package com.asyabab.majmusyarifpro.activity.listjadwal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.asyabab.majmusyarifpro.base.BasePresenter;
import com.asyabab.majmusyarifpro.database.DatabaseContract;
import com.asyabab.majmusyarifpro.database.DatabaseHelper;
import com.asyabab.majmusyarifpro.model.Jadwal;
import com.asyabab.majmusyarifpro.modelquran.Surah;

import java.util.ArrayList;

/**
 * Created by User on 01/05/2018.
 */

public class ListJadwalPresenter extends BasePresenter<ListJadwalView> {
    ListJadwalPresenter(ListJadwalView view) {
        super.attach(view);
    }

    void loadTanggal(String loadtanggal) {
        SQLiteDatabase database = DatabaseHelper.getDatabase();
        Cursor cursor = database.query(DatabaseContract.TableJadwalSholat.TABLE_SHOLAT, null, null, null, null, null, null);

        ArrayList<Jadwal> data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String tanggal = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableJadwalSholat.TANGGAL));
                String subuh = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableJadwalSholat.SUBUH));
                String duhur = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableJadwalSholat.DUHUR));
                String ashar = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableJadwalSholat.ASHAR));
                String maghrib = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableJadwalSholat.MAGHRIB));
                String isya = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableJadwalSholat.ISYA));

                data.add(new Jadwal(tanggal , subuh , duhur , ashar, maghrib,isya));
            } while (cursor.moveToNext());
        }
        mView.onLoad(data);

        cursor.close();
        database.close();
    }
}
