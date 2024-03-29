
package com.asyabab.majmusyarifpro.activity.listjadwal;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.asyabab.majmusyarifpro.R;
import com.asyabab.majmusyarifpro.base.BaseActivity;
import com.asyabab.majmusyarifpro.database.DatabaseContract;
import com.asyabab.majmusyarifpro.database.DatabaseHelper;
import com.asyabab.majmusyarifpro.model.Jadwal;
import com.asyabab.majmusyarifpro.model.Note;
import com.asyabab.majmusyarifpro.utils.AppSettings;

import java.util.ArrayList;


public class JadwalSholatActivity extends BaseActivity<ListJadwalPresenter>  implements ListJadwalView, View.OnClickListener {
    AppSettings settings;
    String nilai;
    String id;
    private MediaPlayer mMediaPlayer;
    Typeface facebold, facemedium, facethin;
    TextView textsubuh;
    TextView tvj_jamsholat;
    TextView tvj_namawaktusholat;
    RadioButton radioButton;
    TextView tvj_waktumundursholat;
    TextView tvDialogNamaSholat, tvDialogJamSholat, tvtextperingatan, tvtextjenis;
    RecyclerView jadwalrecycle;
    RadioButton btepat, blimamenit, bsepuluhmenit, blimabelasmenit, badzan, bnotif, btidakada;
    ArrayList<Note> data;
    int value;
    RadioGroup radioperingatan;
    SwitchCompat switchImsak, switchSubuh, switchZuhur, switchAshar, switchMaghrib, switchIsya;
    private ListJadwalAdapter listJadwalAdapter;
    private ListNotePresenter listNotePresenter;

    private ImageButton mButtonLeftArrow;
    private ImageButton mButtonRightArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_sholat);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        facebold= ResourcesCompat.getFont(getApplicationContext(), R.font.visbybold);
        facemedium= ResourcesCompat.getFont(getApplicationContext(), R.font.visbycfmedium);
        facethin= ResourcesCompat.getFont(getApplicationContext(), R.font.visbycf);
        jadwalrecycle=(RecyclerView) findViewById(R.id.list_jadwalsholat);
        tvj_waktumundursholat=(TextView) findViewById(R.id.tvj_waktumundursholat);
        tvj_namawaktusholat=(TextView) findViewById(R.id.tvj_namawaktusholat);
        tvj_jamsholat=(TextView) findViewById(R.id.tvj_jamsholat);
        textsubuh=(TextView) findViewById(R.id.textsubuh);
        switchImsak=(SwitchCompat) findViewById(R.id.switchNotifimsak);
        switchSubuh=(SwitchCompat) findViewById(R.id.switchNotifsubuh);
        switchZuhur=(SwitchCompat) findViewById(R.id.switchNotifzuhur);
        switchAshar=(SwitchCompat) findViewById(R.id.switchNotifashar);
        switchMaghrib=(SwitchCompat) findViewById(R.id.switchNotifmaghrib);
        switchIsya=(SwitchCompat) findViewById(R.id.switchNotifisya);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listJadwalAdapter = new ListJadwalAdapter(getApplicationContext(),new ArrayList<Jadwal>());
        jadwalrecycle.setLayoutManager(layoutManager);
        jadwalrecycle.setHasFixedSize(true);
        jadwalrecycle.setAdapter(listJadwalAdapter);
        mButtonLeftArrow = (ImageButton) findViewById(R.id.left_arrow_jadwal);
        mButtonRightArrow = (ImageButton) findViewById(R.id.right_arrow_jadwal);
        mButtonLeftArrow.setOnClickListener(this);
        mButtonRightArrow.setOnClickListener(this);
        value=0;
        mPresenter.loadTanggal(String.valueOf(value));
        loadNote();

        check();
        click_switch(switchAshar);
        click_switch(switchIsya);
        click_switch(switchMaghrib);
        click_switch(switchImsak);
        click_switch(switchSubuh);
        click_switch(switchZuhur);

    }

    private void click_switch(SwitchCompat switchCompat){

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchImsak.isClickable()){
                    id="sImsak";

                }else if (switchZuhur.isClickable()){
                    id="sZuhur";
                }else if (switchAshar.isClickable()){
                    id="sAshar";
                }else if (switchMaghrib.isClickable()){
                    id="sMaghrib";
                }else if (switchSubuh.isClickable()){
                    id="sSubuh";
                }else if (switchIsya.isClickable()){
                    id="sIsya";
                }
                showCustomDialog();
            }
        });
    }


    private void click_menu(RadioButton radioButton, RadioButton false1, RadioButton false2){

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton.isChecked()){
                    false1.setChecked(false);
                    false2.setChecked(false);
                    if(bnotif.isChecked()){
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                        r.play();
                        Toast.makeText(JadwalSholatActivity.this, "Notifikasi Dering", Toast.LENGTH_SHORT).show();
                        nilai="dering";
                    }
                    else if (badzan.isChecked()){
                        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.adzancut);
                        mMediaPlayer.start();
                        Toast.makeText(JadwalSholatActivity.this, "Notifikasi Adzan", Toast.LENGTH_SHORT).show();
                        nilai="adzan";
                    }else{

                        nilai="0";
                    }
                    save1(id, nilai);

                }

            }
        });
    }
    private void click_waktu(RadioButton radioButton, RadioButton false1, RadioButton false2,RadioButton false3 ){
        if (data.get(0).getStatus().equals("tepat")){
            radioButton.setChecked(true);
        }else if(data.get(0).getStatus().equals("lima")){
            false1.setChecked(true);
        }else if(data.get(0).getStatus().equals("sepuluh")){
            false2.setChecked(true);
        }else if(data.get(0).getStatus().equals("limabelas")){
            false3.setChecked(true);
        }
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton.isChecked()){
                    false1.setChecked(false);
                    false2.setChecked(false);
                    false3.setChecked(false);

                    if (btepat.isChecked()){
                        nilai="tepat";
                    }else if(blimamenit.isChecked()){
                        nilai="lima";
                    }else if(bsepuluhmenit.isChecked()){
                        nilai="sepuluh";
                    }else if(blimabelasmenit.isChecked()){
                        nilai="limabelas";
                    }
                    save2(id, nilai);
                }
            }
        });
    }


    private void loadNote() {
        SQLiteDatabase database = DatabaseHelper.getDatabase();
        Cursor cursor = database.query(DatabaseContract.TableNote.TABLE_NOTE, null, null, null, null, null, null);

        data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableNote.ID));
                String nama = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableNote.NAMA));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableNote.STATUS));
                String status2 = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TableNote.STATUS2));
                data.add(new Note(id , nama , status));
            } while (cursor.moveToNext());
        }

        Log.d("test", "Coba"+data.get(1).getStatus());

        if (data.get(0).getStatus().equals("0")){
            switchImsak.setChecked(false);
        }else{
            switchImsak.setChecked(true);
        }
        if (data.get(1).getStatus().equals("0")){
            switchSubuh.setChecked(false);
        }else{
            switchSubuh.setChecked(true);
        }
        if (data.get(3).getStatus().equals("0")){
            switchAshar.setChecked(false);
        }else{
            switchAshar.setChecked(true);
        }
        if (data.get(2).getStatus().equals("0")){
            switchZuhur.setChecked(false);
        }else{
            switchZuhur.setChecked(true);
        }
        if (data.get(4).getStatus().equals("0")){
            switchMaghrib.setChecked(false);
        }else{
            switchMaghrib.setChecked(true);
        }
        if (data.get(5).getStatus().equals("0")){
            switchIsya.setChecked(false);
        }else{
            switchIsya.setChecked(true);
        }

        cursor.close();
        database.close();
    }


    private void save1(String id, String nilai){
        SQLiteDatabase database = DatabaseHelper.getDatabase();
        SQLiteStatement statement = database.compileStatement(DatabaseContract.TableNote.QUERY_UPDATE);
        statement.bindString(1, nilai);
        statement.bindString(2, id);
        statement.execute();
        statement.clearBindings();
        database.close();
    }
    private void save2(String id, String nilai){
        SQLiteDatabase database = DatabaseHelper.getDatabase();
        SQLiteStatement statement = database.compileStatement(DatabaseContract.TableNote.QUERY_UPDATE2);
        statement.bindString(1, nilai);
        statement.bindString(2, id);
        statement.execute();
        statement.clearBindings();
        database.close();

    }

    private void check() {
        //        if (data.get(1).getStatus().equals("0")){
//            switchSubuh.setChecked(false);
//        }else{
//            switchSubuh.setChecked(true);
//        }
//        if (data.get(3).getStatus().equals("0")){
//            switchAshar.setChecked(false);
//        }else{
//            switchAshar.setChecked(true);
//        }
//        if (data.get(2).getStatus().equals("0")){
//            switchZuhur.setChecked(false);
//        }else{
//            switchZuhur.setChecked(true);
//        }
//        if (data.get(4).getStatus().equals("0")){
//            switchMaghrib.setChecked(false);
//        }else{
//            switchMaghrib.setChecked(true);
//        }
//        if (data.get(5).getStatus().equals("0")){
//            switchIsya.setChecked(false);
//        }else{
//            switchIsya.setChecked(true);
//        }
    }

    private void showCustomDialog () {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialogprayer, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        facemedium= ResourcesCompat.getFont(getApplicationContext(), R.font.visbycfmedium);

        tvDialogNamaSholat=(TextView) dialogView.findViewById(R.id.dialog_namasholat);
        tvDialogJamSholat=(TextView) dialogView.findViewById(R.id.dialog_jamsholat);
        tvtextjenis=(TextView) dialogView.findViewById(R.id.textjenisalarm);
        tvtextperingatan=(TextView) dialogView.findViewById(R.id.textwaktusalarm);
//        int selected=radioperingatan.getCheckedRadioButtonId();
//        radioButton= (RadioButton) findViewById(selected);
        badzan=(RadioButton) dialogView.findViewById(R.id.buttonadzan);
        blimabelasmenit=(RadioButton) dialogView.findViewById(R.id.button15menit);
        bsepuluhmenit=(RadioButton) dialogView.findViewById(R.id.button10menit);
        bnotif=(RadioButton) dialogView.findViewById(R.id.buttondering);
        btepat=(RadioButton) dialogView.findViewById(R.id.buttontepat);
        btidakada=(RadioButton) dialogView.findViewById(R.id.buttondiam);
        blimamenit=(RadioButton) dialogView.findViewById(R.id.button5menit);

        tvDialogNamaSholat.setTypeface(facemedium);
        tvDialogJamSholat.setTypeface(facemedium);
        tvtextjenis.setTypeface(facemedium);
        tvtextperingatan.setTypeface(facemedium);

        click_menu(badzan, btidakada, bnotif);
        click_menu(btidakada, badzan, bnotif);
        click_menu(bnotif, badzan, btidakada);
        click_waktu(blimabelasmenit, blimamenit,bsepuluhmenit,btepat);
        click_waktu(blimamenit,blimabelasmenit, bsepuluhmenit,btepat);
        click_waktu(bsepuluhmenit,blimabelasmenit, blimamenit,btepat);
        click_waktu(btepat,blimabelasmenit, blimamenit,bsepuluhmenit);


        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public ListJadwalPresenter initPresenter() {
        return new ListJadwalPresenter( this);
    }


    public void onLoad(ArrayList<Jadwal> data) {
        listJadwalAdapter.refresh(data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_arrow_jadwal:
                if (value>0){
                    mPresenter.loadTanggal(String.valueOf(--value));
                }
                break;
            case R.id.right_arrow_jadwal:
                if (value<6){
                    mPresenter.loadTanggal(String.valueOf(++value));
                }
                break;
        }
    }
}
