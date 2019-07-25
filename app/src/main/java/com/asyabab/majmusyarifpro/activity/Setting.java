package com.asyabab.majmusyarifpro.activity;

import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.asyabab.majmusyarifpro.R;

public class Setting extends AppCompatActivity {
    private static Typeface facebold, facemedium, facethin;

    TextView textTentang, textUlas, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        facebold= ResourcesCompat.getFont(getApplicationContext(), R.font.visbybold);
        facemedium= ResourcesCompat.getFont(getApplicationContext(), R.font.visbycfmedium);
        facethin= ResourcesCompat.getFont(getApplicationContext(), R.font.visbycf);
        textTentang=(TextView) findViewById(R.id.textTentang);
        textUlas=(TextView) findViewById(R.id.textUlas);
        title=(TextView) findViewById(R.id.titleLainnya);

        textTentang.setTypeface(facemedium);
        textUlas.setTypeface(facemedium);
        title.setTypeface(facemedium);

    }
}
