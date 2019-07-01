package com.asyabab.majmusyarifpro.activity.listasma;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.asyabab.majmusyarifpro.base.BasePresenter;
import com.asyabab.majmusyarifpro.database.DatabaseContract;
import com.asyabab.majmusyarifpro.database.DatabaseHelper;
import com.asyabab.majmusyarifpro.model.Asma;
import com.asyabab.majmusyarifpro.modelquran.Ayat;

import java.util.ArrayList;

/**
 * Created by User on 01/05/2018.
 */

public class ListAsmaPresenter extends BasePresenter<ListAsmaView> {
    ListAsmaPresenter(ListAsmaView view) {
        super.attach(view);
    }

    void loadAsma() {
        SQLiteDatabase database = DatabaseHelper.getDatabase();
        Cursor cursor = database.query(DatabaseContract.TableAsmaulHusna.TABLE_ASMA, null,  null,null, null, null, null);

        ArrayList<Asma> data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String nomer = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableAsmaulHusna.NO));
                String latin = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableAsmaulHusna.LATIN));
                String arab = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableAsmaulHusna.ARAB));
                String indonesia = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableAsmaulHusna.INDONESIA));
                String inggris = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableAsmaulHusna.INGGRIS));

                data.add(new Asma(nomer , latin , arab , indonesia, inggris));
            } while (cursor.moveToNext());
        }
        mView.onLoad(data);

        cursor.close();
        database.close();
    }
}
