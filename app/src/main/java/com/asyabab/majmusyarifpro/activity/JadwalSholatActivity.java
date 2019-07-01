package com.asyabab.majmusyarifpro.activity;

import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asyabab.majmusyarifpro.R;

import butterknife.BindView;

public class JadwalSholatActivity extends AppCompatActivity {
    Typeface facebold, facemedium, facethin;
    TextView textashar;
    TextView textduhur;
    TextView textimsak;
    TextView textisya;
    TextView textmaghrib;
    TextView textterbit;
    TextView textsubuh;
    TextView tvj_jamsholat;
    TextView tvj_namawaktusholat;
    TextView tvj_waktumundursholat;
    TextView tvjamimsak;
    TextView tvjamashar;
    TextView tvjamduhur;
    TextView tvjamisya;
    TextView tvjammaghrib;
    TextView tvjamsubuh;
    TextView tvjamterbit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_sholat);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        facebold= ResourcesCompat.getFont(getApplicationContext(), R.font.visbybold);
        facemedium= ResourcesCompat.getFont(getApplicationContext(), R.font.visbycfmedium);
        facethin= ResourcesCompat.getFont(getApplicationContext(), R.font.visbycf);

        tvj_waktumundursholat=(TextView) findViewById(R.id.tvj_waktumundursholat);
        tvj_namawaktusholat=(TextView) findViewById(R.id.tvj_namawaktusholat);
        tvj_jamsholat=(TextView) findViewById(R.id.tvj_jamsholat);
        textterbit=(TextView) findViewById(R.id.textterbit);
        textsubuh=(TextView) findViewById(R.id.textsubuh);
        textmaghrib=(TextView) findViewById(R.id.textmaghrib);
        textisya=(TextView) findViewById(R.id.textisya);
        textimsak=(TextView) findViewById(R.id.textimsak);
        textduhur=(TextView) findViewById(R.id.textduhur);
        textashar=(TextView) findViewById(R.id.textashar);
        tvjamashar=(TextView) findViewById(R.id.tvjamashar);
        tvjamduhur=(TextView) findViewById(R.id.tvjamduhur);
        tvjamimsak=(TextView) findViewById(R.id.tvjamimsak);
        tvjamisya=(TextView) findViewById(R.id.tvjamisya);
        tvjammaghrib=(TextView) findViewById(R.id.tvjammaghrib);
        tvjamsubuh=(TextView) findViewById(R.id.tvjamsubuh);
        tvjamterbit=(TextView) findViewById(R.id.tvjamterbit);

        tvj_jamsholat.setTypeface(facemedium);
        tvj_namawaktusholat.setTypeface(facemedium);
        tvj_waktumundursholat.setTypeface(facemedium);
        textashar.setTypeface(facemedium);
        textduhur.setTypeface(facemedium);
        textimsak.setTypeface(facemedium);
        textisya.setTypeface(facemedium);
        textmaghrib.setTypeface(facemedium);
        textsubuh.setTypeface(facemedium);
        textterbit.setTypeface(facemedium);

    }
}
