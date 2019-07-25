package com.asyabab.majmusyarifpro.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.asyabab.majmusyarifpro.R;
import com.asyabab.majmusyarifpro.activity.kalender.Kalender;
import com.asyabab.majmusyarifpro.activity.listasma.AsmaulHusna;
import com.asyabab.majmusyarifpro.activity.listjadwal.JadwalSholatActivity;
import com.asyabab.majmusyarifpro.activity.listsurah.QuranActivity;
import com.asyabab.majmusyarifpro.base.HttpHandler;
import com.asyabab.majmusyarifpro.database.DatabaseContract;
import com.asyabab.majmusyarifpro.database.DatabaseHelper;
import com.asyabab.majmusyarifpro.model.Items;
import com.asyabab.majmusyarifpro.model.JadwalTemp;
import com.asyabab.majmusyarifpro.model.ModelJadwal;
import com.asyabab.majmusyarifpro.network.ApiClient;
import com.asyabab.majmusyarifpro.network.ApiInterface;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private SQLiteDatabase database;


    String zuhur, ashar, magrib, isya, subuh, tanggalmasehi, tanggalhijriyah, subuhbesok, tanggalSekarang, tanggalBesok;
    List<JadwalTemp> jadwaltemp;
    List<ModelJadwal> jadwalList = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    String date=new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(new Date());
    private static Typeface facebold, facemedium, facethin;

    Location location;
    String lokasi;
    ProgressDialog pd;
    @BindView(R.id.tv_tanggalmasehi)
    TextView tvTanggalmasehi;
    @BindView(R.id.tv_tanggalhijriah)
    TextView tvTanggalhijriah;
    @BindView(R.id.tv_lokasi)
    TextView tvLokasi;
    @BindView(R.id.tv_jam)
    TextView tvJam;
    @BindView(R.id.tv_namawaktusholat)
    TextView tvWaktusholat;
    @BindView(R.id.tv_waktumundursholat)
    TextView tvWaktumundursholat;
    @BindView(R.id.tv_jamsholat)
    TextView tvJamsholat;
    @BindView(R.id.textasmaul)
    TextView textasmaul;
    @BindView(R.id.textdoa)
    TextView textdoa;
    @BindView(R.id.textjadwalsholat)
    TextView textjadwalsholat;
    @BindView(R.id.textkiblat)
    TextView textkiblat;
    @BindView(R.id.textlainnya)
    TextView textlainnya;
    @BindView(R.id.textsurah)
    TextView textsurah;
    @BindView(R.id.texttahlil)
    TextView texttahlil;
    @BindView(R.id.textsholawat)
    TextView textsholawat;
    @BindView(R.id.textkalenderpuasa)
    TextView textkalenderpuasa;
    SQLiteDatabase db;
    @BindView(R.id.menukiblat)
    LinearLayout imgArahKabah;
    @BindView(R.id.menudoa)
    LinearLayout imgAlQuran;
    @BindView(R.id.menutahlil)
    LinearLayout imgCatatanRamadhan;
    NestedScrollView idHomeActivity;
    private Handler handler = new Handler();
    String dateFormat=new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault()).format(new Date());
    String url="http://api.aladhan.com/v1/gToH?date="+dateFormat;
    private String TAG = HomeActivity.class.getSimpleName();
    private  TimerClass timerClass;
    final String[] hijriah = new String[1];
    Calendar c1 = Calendar.getInstance();
    long waktumundur;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.subtitle, R.color.colorPrimary);
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        facebold= ResourcesCompat.getFont(getApplicationContext(), R.font.visbybold);
        facemedium= ResourcesCompat.getFont(getApplicationContext(), R.font.visbycfmedium);
        facethin= ResourcesCompat.getFont(getApplicationContext(), R.font.visbycf);
        database = DatabaseHelper.getDatabase();

        tvJam.setTypeface(facebold);
        tvLokasi.setTypeface(facemedium);
        tvWaktusholat.setTypeface(facemedium);
        tvWaktumundursholat.setTypeface(facemedium);
        textasmaul.setTypeface(facethin);
        textdoa.setTypeface(facethin);
        textjadwalsholat.setTypeface(facethin);
        textkalenderpuasa.setTypeface(facethin);
        textkiblat.setTypeface(facethin);
        textlainnya.setTypeface(facethin);
        textsholawat.setTypeface(facethin);
        textsurah.setTypeface(facethin);
        texttahlil.setTypeface(facethin);

        try {
            tanggalmasehi=dateFormat.format(df.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        handler.postDelayed(runnable, 1000);

        refresh();
        koneksi();


    }
    private void koneksi(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            SQLiteStatement statement = database.compileStatement(DatabaseContract.TableJadwalSholat.DELETE);
            statement.execute();

            actionLoad();
            new GetContacts().execute();
            pd = new ProgressDialog(HomeActivity.this);
            pd.setTitle("Loading . . . ");
            pd.setMessage("Waiting . . .");
            pd.setCancelable(false);
            pd.show();
            connected = true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Tidak Ada Koneksi Internet",Toast.LENGTH_SHORT).show();

            connected = false;
        }
    }

    //Membuat InnerClass untuk konfigurasi Countdown Time
    public class TimerClass extends CountDownTimer {

        TimerClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //Method ini berjalan saat waktu/timer berubah
        @Override
        public void onTick(long millisUntilFinished) {
            //Kenfigurasi Format Waktu yang digunakan
            @SuppressLint("DefaultLocale") String waktu = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

            //Menampilkannya pada TexView

            tvWaktumundursholat.setText("(-" +waktu+")");
        }

        @Override
        public void onFinish() {
            ///Berjalan saat waktu telah selesai atau berhenti
            Toast.makeText(HomeActivity.this, "Waktu Telah Berakhir", Toast.LENGTH_LONG).show();


        }
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(HomeActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObject = new JSONObject(jsonStr);
                    String result = jsonObject.getString("code");
                    String msg = jsonObject.getString("status");

                    if (result.equalsIgnoreCase("200")) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONObject hijri = data.getJSONObject("hijri");
                        JSONObject month = hijri.getJSONObject("month");
                        hijriah[0] = month.getString("number")+" "+month.getString("en")+" "+hijri.getString("year");

                        Log.d("Coba", "Response from url: " +hijriah[0]);

                    }

                } catch (final JSONException e) {

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            tvTanggalhijriah.setText(hijriah[0]);

        }

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Calendar c3 = Calendar.getInstance();

                    SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
                    String strdate1 = sdf1.format(c3.getTime());
                    handler.postDelayed(this, 1000);
                    tvJam.setText(strdate1);
        }

    };

    private void refresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        actionLoad();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });

    }

    private void actionLoad() {

        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (!EasyPermissions.hasPermissions(getApplicationContext(), perms)) {
            EasyPermissions.requestPermissions(HomeActivity.this, "Butuh Permission Location", 10, perms);
        } else {
            FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        // Do it all with location
                        Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                        // Display in Toast
                        Geocoder gcd3 = new Geocoder(getBaseContext(), Locale.getDefault());
                        List<Address> addresses;

                        try {
                            addresses = gcd3.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (addresses.size() > 0)

                            {
                                //while(locTextView.getText().toString()=="Location") {
                                Log.d("Cek lokasi", "1 :" + addresses.get(0).toString());
                                lokasi = addresses.get(0).getSubAdminArea().toString();

                                if (lokasi != null) {

                                    Log.d("location", "locatin :" + lokasi);

                                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                                    Call<Items> call = apiInterface.getJadwalSholat(lokasi);
                                    call.enqueue(new Callback<Items>() {
                                        @Override
                                        public void onResponse(Call<Items> call, Response<Items> response) {
                                            Log.d("Data ", " respon" + response.body().getItems());
                                            jadwaltemp = response.body().getItems();
                                            Log.d("respon data ", "" + new Gson().toJson(jadwaltemp));

                                            if (jadwaltemp != null) {
                                                int value=0;
                                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                                String tanggal=format.format(new Date());
                                                int hasila=converta(jadwaltemp.get(value).getTanggal()).compareTo(converta(tanggal));
                                                Log.d("hasila :", "" + hasila);

                                                if (hasila!=0){
                                                    value++;
                                                }

                                                zuhur = converttime(jadwaltemp.get(value).getZuhur());
                                                ashar = converttime(jadwaltemp.get(value).getAshar());
                                                magrib = converttime(jadwaltemp.get(value).getMaghrib());
                                                isya = converttime(jadwaltemp.get(value).getIsya());
                                                subuh = converttime(jadwaltemp.get(value).getSubuh());
                                                subuhbesok = converttime(jadwaltemp.get(value+1).getSubuh());

                                                //insert to database
                                                for (int i=0; i<7; i++){
                                                    jadwalList.add(new ModelJadwal(String.valueOf(i),jadwaltemp.get(i).getTanggal(), converttime(jadwaltemp.get(i).getSubuh()), converttime(jadwaltemp.get(i).getZuhur()), converttime(jadwaltemp.get(i).getAshar()), converttime(jadwaltemp.get(i).getMaghrib()), converttime(jadwaltemp.get(i).getIsya())));
                                                }

                                                SQLiteStatement statement = database.compileStatement(DatabaseContract.TableJadwalSholat.QUERY_STATEMENT);
                                                List<ModelJadwal> jadwallist = jadwalList;

                                                //masukkan ke database
                                                for (ModelJadwal jadwal : jadwallist) {
                                                    statement.bindString(1, jadwal.getId());
                                                    statement.bindString(2, jadwal.getTanggal());
                                                    statement.bindString(3, jadwal.getSubuh());
                                                    statement.bindString(4, jadwal.getZuhur());
                                                    statement.bindString(5, jadwal.getAshar());
                                                    statement.bindString(6, jadwal.getMaghrib());
                                                    statement.bindString(7, jadwal.getIsya());
                                                    statement.execute();
                                                    statement.clearBindings();
                                                }
                                                //konvert tanggal besok dan waktu sholat besok (baru subuh)
                                                tanggalSekarang = jadwaltemp.get(value).getTanggal();
                                                tanggalBesok = jadwaltemp.get(value+1).getTanggal();

                                                Log.d("respon :", "" + zuhur);

                                                String waktusubuh=tanggalSekarang+" "+subuh;
                                                String waktuduhur=tanggalSekarang+" "+zuhur;
                                                String waktuashar=tanggalSekarang+" "+ashar;
                                                String waktumaghrib=tanggalSekarang+" "+magrib;
                                                String waktuisya=tanggalSekarang+" "+isya;
                                                String waktusubuhbesok=tanggalBesok+" "+subuhbesok;
                                                Log.d("subuhbesok :", "" + waktusubuhbesok);

                                                int hasil=convert(waktusubuh).compareTo(new Date());
                                                Log.d("Hasil", "hasilsubuh" + hasil);
                                                if (hasil>0){
                                                    tvWaktusholat.setText("Subuh");
                                                    statusTv(convert(waktusubuh));
                                                    tvJamsholat.setText(subuh);
                                                }else{
                                                    hasil=convert(waktuduhur).compareTo(new Date());
                                                    Log.d("Hasil", "hassiilduhur" + hasil);
                                                    if (hasil>0){
                                                        tvWaktusholat.setText("Dhuhur");
                                                        statusTv(convert(waktuduhur));
                                                        tvJamsholat.setText(zuhur);
                                                    }else{
                                                        hasil=convert(waktuashar).compareTo(new Date());
                                                        Log.d("Hasil", "hasilashar" + hasil);

                                                        if (hasil>0){

                                                            tvWaktusholat.setText("Ashar");
                                                            statusTv(convert(waktuashar));
                                                            tvJamsholat.setText(ashar);
                                                        }else{
                                                            hasil=convert(waktumaghrib).compareTo(new Date());
                                                            Log.d("Hasil", "hasilmaghrib" + hasil);
                                                            if (hasil>0){
                                                                tvWaktusholat.setText("Maghrib");
                                                                statusTv(convert(waktumaghrib));
                                                                tvJamsholat.setText(magrib);
                                                            }else {
                                                                hasil = convert(waktuisya).compareTo(new Date());                                                Log.d("Hasil", "hasilsubuh" + hasil);
                                                                Log.d("Hasil", "hasilisya" + hasil);
                                                                if (hasil > 0) {
                                                                    tvWaktusholat.setText("Isya");
                                                                    statusTv(convert(waktuisya));
                                                                    tvJamsholat.setText(isya);
                                                                } else {
                                                                    hasil = convert(waktusubuhbesok).compareTo(new Date());
                                                                    Log.d("Hasil", "hasilsubuh" + hasil);
                                                                    if (hasil > 0) {
                                                                        tvWaktusholat.setText("Subuh");
                                                                        statusTv(convert(waktusubuhbesok));
                                                                        tvJamsholat.setText(subuhbesok);
                                                                    }

                                                                }
                                                            }


                                                        }
                                                    }
                                                }
                                                Log.d("Hasil", "hasilsubuh" + hasil);

                                                Log.d("Test", "lokasi" + lokasi);
                                                tvLokasi.setText(lokasi);
                                                tvTanggalmasehi.setText(tanggalmasehi);
//                                                swipeId.setRefreshing(false);
                                                pd.dismiss();
                                            } else {
                                                Toast.makeText(HomeActivity.this, "Error Network", Toast.LENGTH_SHORT).show();
//                                                swipeId.setRefreshing(false);
                                            }
                                            //  loadData(jadwal);
                                        }

                                        @Override
                                        public void onFailure(Call<Items> call, Throwable t) {
                                            Log.d("Data ", "" + t.getMessage());
//                                            swipeId.setRefreshing(false);
                                            pd.dismiss();
                                        }
                                    });

                                }
                            }

                        } catch (NullPointerException e) {
                            e.printStackTrace();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });


        }
    }
    public Date converta(String tanggal){
        SimpleDateFormat stfirst = new SimpleDateFormat("yyyy-MM-dd");
        Date tanggala= null;
        try {
            tanggala = stfirst.parse(tanggal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tanggala;
    }

    public Date convert(String waktu){
        SimpleDateFormat stfirst = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date waktusholat= null;
        try {
            waktusholat = stfirst.parse(waktu);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return waktusholat;
    }

    public String converttime(String waktu){
        SimpleDateFormat st24 = new SimpleDateFormat("HH:mm");
        SimpleDateFormat st12 = new SimpleDateFormat("hh:mm a");

        String a= null;
        try {
            a = st24.format(st12.parse(waktu));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return a;
    }



    public void statusTv(Date waktu){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String strdate1 = format.format(new Date());
        String stpdate1 = format.format(waktu);

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(strdate1);
            d2 = format.parse(stpdate1);

//in milliseconds
            Log.d("awal",d1 + " hours, ");
            Log.d("akhir",d2 + " hours, ");

            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            Log.d("days",diffDays + " days, ");
            Log.d("hours",diffHours + " hours, ");
            Log.d("minutes",diffMinutes + " minutes, ");
            Log.d("seconds",diffSeconds + " seconds.");

            waktumundur=(diffHours*3600)+(diffMinutes*60)+diffSeconds;
            timerClass = new TimerClass(1000 * waktumundur, 1000);
            timerClass.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == 10) {
            actionLoad();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @OnClick(R.id.menukiblat)
    public void onImgArahKabahClicked() {
        Bundle bundle=new Bundle();
        bundle.putString("datalokasi", lokasi);
        Intent intent=new Intent(HomeActivity.this, ArahKiblatActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick(R.id.menujadwalsholat)
    public void onMnJadwalSholat() {
        startActivity(new Intent(HomeActivity.this, JadwalSholatActivity.class));
    }

    @OnClick(R.id.menuasmaulhusna)
    public void onMnasmaulhusna() {
        startActivity(new Intent(HomeActivity.this, AsmaulHusna.class));
    }
    @OnClick(R.id.menukalenderpuasa)
    public void onKalenderPuasa() {
        startActivity(new Intent(HomeActivity.this, Kalender.class));
    }


    @OnClick(R.id.menusurah)
    public void onImgAlQuranClicked() {
        startActivity(new Intent(this, QuranActivity.class));
    }

    @OnClick(R.id.menulainnya)
    public void onLainnya() {
        startActivity(new Intent(this, Setting.class));
    }


}
