package com.asyabab.majmusyarifpro.activity;

import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.asyabab.majmusyarifpro.R;

public class ArahKiblatActivity extends AppCompatActivity implements SensorEventListener {
    private ImageView image;

    // record the compass picture angle turned
    private float currentDegree = 0f;

    // device sensor manager
    private SensorManager mSensorManager;
    private static Typeface facebold, facemedium, facethin;

    TextView tvHeading;
    TextView Location;
    TextView textKiblat;
    TextView titlekiblat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compass_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //
        image = (ImageView) findViewById(R.id.imageViewCompass);

        // TextView that will tell the user what degree is he heading
        tvHeading = (TextView) findViewById(R.id.tvDerajat);
        Location = (TextView) findViewById(R.id.tvLokasiKompas);
        titlekiblat = (TextView) findViewById(R.id.titlekiblat);
        textKiblat = (TextView) findViewById(R.id.textKiblat);
        facebold= ResourcesCompat.getFont(getApplicationContext(), R.font.visbybold);
        facemedium= ResourcesCompat.getFont(getApplicationContext(), R.font.visbycfmedium);
        facethin= ResourcesCompat.getFont(getApplicationContext(), R.font.visbycf);
        // initialize your android device sensor capabilities
        tvHeading.setTypeface(facemedium);
        textKiblat.setTypeface(facemedium);
        titlekiblat.setTypeface(facemedium);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(getIntent().getExtras()!=null){
            Bundle bundle=getIntent().getExtras();
            Location.setText(bundle.getString("datalokasi"));
        }else
        {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]);

        tvHeading.setText(Float.toString(degree)+ (char)0x00B0);

        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        // how long the animation will take place
        ra.setDuration(210);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }



}
