package com.asyabab.majmusyarifpro;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.asyabab.majmusyarifpro.utils.PreferenceApp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.asyabab.majmusyarifpro.database.DatabaseHelper;


public class App extends Application {
    private static Context sContext;

    private static Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        resources = getResources();
        DatabaseHelper.initDatabase(this);
        PreferenceApp.initPreferences(this);
    }

    public static BufferedReader getRawResources(int res){
        InputStream streamReader = resources.openRawResource(res);
        return new BufferedReader(new InputStreamReader(streamReader));
    }

    public static Context getAppContext() {
        return sContext;
    }


}
