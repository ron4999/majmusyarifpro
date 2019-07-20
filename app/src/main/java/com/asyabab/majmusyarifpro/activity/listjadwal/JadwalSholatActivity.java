package com.asyabab.majmusyarifpro.activity.listjadwal;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.asyabab.majmusyarifpro.R;
import com.asyabab.majmusyarifpro.base.BaseActivity;
import com.asyabab.majmusyarifpro.model.Jadwal;
import java.util.ArrayList;


public class JadwalSholatActivity extends BaseActivity<ListJadwalPresenter>  implements ListJadwalView {

    Typeface facebold, facemedium, facethin;
    TextView textsubuh;
    TextView tvj_jamsholat;
    TextView tvj_namawaktusholat;
    TextView tvj_waktumundursholat;
    RecyclerView jadwalrecycle;
    private ListJadwalAdapter listJadwalAdapter;

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
        LinearLayoutManager layoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listJadwalAdapter = new ListJadwalAdapter(getApplicationContext(),new ArrayList<Jadwal>());
        jadwalrecycle.setLayoutManager(layoutManager);
        jadwalrecycle.setHasFixedSize(true);
        jadwalrecycle.setAdapter(listJadwalAdapter);

        mPresenter.loadTanggal("2019-7-20");


//        scimsak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (scimsak.isChecked()==true){
//                    showCustomDialog();
//                }
//            }
//        });



    }

    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialogprayer, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
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

}
