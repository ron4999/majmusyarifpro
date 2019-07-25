package com.asyabab.majmusyarifpro.activity.splascreen;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.util.Log;

import com.asyabab.majmusyarifpro.base.BasePresenter;
import com.asyabab.majmusyarifpro.database.DatabaseContract;
import com.asyabab.majmusyarifpro.database.DatabaseHelper;
import com.asyabab.majmusyarifpro.model.ModelAsmaulHusna;
import com.asyabab.majmusyarifpro.model.ModelNote;
import com.asyabab.majmusyarifpro.modelquran.ModelAyat;
import com.asyabab.majmusyarifpro.modelquran.ModelSurah;
import com.asyabab.majmusyarifpro.utils.RawParser;

import java.io.IOException;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by User on 01/05/2018.
 */

public class SplashscreenPresenter extends BasePresenter<SplashscreenView> {
    SplashscreenPresenter(SplashscreenView mView) {
        super.attach(mView);
    }

    void startPrepareData() {
        new PrepareData(mView).execute();
    }

    private static class PrepareData extends AsyncTask<Void, Integer, Void> {

        private SplashscreenView view;
        private SQLiteDatabase database;

        PrepareData(SplashscreenView view) {
            this.view = view;
            database = DatabaseHelper.getDatabase();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.onPrepare();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            int progress = 0;
            publishProgress(progress);

            try {
                database.beginTransaction();

                SQLiteStatement statement = database.compileStatement(DatabaseContract.TableSurah.QUERY_STATEMENT);
                List<ModelSurah> surahList = RawParser.getRawSurah();
                for (ModelSurah surah : surahList) {
                    statement.bindString(1, surah.getSurah());
                    statement.bindString(2, surah.getAyat());
                    statement.bindString(3, surah.getTerjemahanIndonesia());
                    statement.bindString(4, surah.getTerjemahanEnglish());
                    statement.bindString(5, surah.getJumlahAyat());
                    statement.execute();
                    statement.clearBindings();
                    progress++;
                    publishProgress(progress);
                }
                Thread.sleep(2000);

                statement = database.compileStatement(DatabaseContract.TableAyat.QUERY_STATEMENT);
                List<ModelAyat> ayatList = RawParser.getRawAyat();
                for (ModelAyat ayat : ayatList) {
                    statement.bindString(1, ayat.getSurah());
                    statement.bindString(2, ayat.getAyat());
                    statement.bindString(3, ayat.getArab());
                    statement.bindString(4, ayat.getTerjemahanIndonesia());
                    statement.bindString(5, ayat.getTerjemahanEnglish());
                    statement.execute();
                    statement.clearBindings();
                    progress++;
                    publishProgress(progress);
                }
                Thread.sleep(2000);


                statement = database.compileStatement(DatabaseContract.TableNote.QUERY_STATEMENT);
                List<ModelNote> noteList = RawParser.getRawNote();
                for (ModelNote  note: noteList) {
                    statement.bindString(1, note.getId());
                    statement.bindString(2, note.getNama());
                    statement.bindString(3, note.getStatus());
                    statement.execute();
                    statement.clearBindings();
                    progress++;
                    publishProgress(progress);
                }
                Thread.sleep(2000);


                statement = database.compileStatement(DatabaseContract.TableAsmaulHusna.QUERY_STATEMENT);
                List<ModelAsmaulHusna> asmalist = RawParser.getRawAsmaulHusna();
                for (ModelAsmaulHusna asma : asmalist) {
                    statement.bindString(1, asma.getNomer());
                    statement.bindString(2, asma.getLatin());
                    statement.bindString(3, asma.getArab());
                    statement.bindString(4, asma.getIndonesia());
                    statement.bindString(5, asma.getInggris());
                    statement.execute();
                    statement.clearBindings();
                    progress++;
                    publishProgress(progress);
                }

                Thread.sleep(2000);
                database.setTransactionSuccessful();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                database.endTransaction();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            view.onProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            view.onSuccess();
        }
    }
}
