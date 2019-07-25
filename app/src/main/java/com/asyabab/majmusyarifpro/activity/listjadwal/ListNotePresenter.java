package com.asyabab.majmusyarifpro.activity.listjadwal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.asyabab.majmusyarifpro.base.BasePresenter;
import com.asyabab.majmusyarifpro.database.DatabaseContract;
import com.asyabab.majmusyarifpro.database.DatabaseHelper;
import com.asyabab.majmusyarifpro.model.Jadwal;
import com.asyabab.majmusyarifpro.model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ListNotePresenter extends BasePresenter<ListNoteView> {ListNotePresenter(ListNoteView view) {
        super.attach(view);
    }


}
