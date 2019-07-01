package com.asyabab.majmusyarifpro.activity.listasma;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.asyabab.majmusyarifpro.base.BaseActivity;
import com.asyabab.majmusyarifpro.model.Asma;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.asyabab.majmusyarifpro.R;

import org.w3c.dom.Text;

public class AsmaulHusna extends BaseActivity<ListAsmaPresenter> implements ListAsmaView{


    @BindView(R.id.list_surah)
    RecyclerView recyclerView;
    @BindView(R.id.titlekumpulansurah)
    TextView titlekumpulansurah;
    private ListAsmaAdapter listAsmaAdapter;
    private String loadASMA = "latin";
    Typeface facemedium, facethin,facelight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);
        ButterKnife.bind(this);
        facemedium= ResourcesCompat.getFont(getApplicationContext(), R.font.visbycfmedium);
        facethin= ResourcesCompat.getFont(getApplicationContext(), R.font.visbyoblique);
        facelight= ResourcesCompat.getFont(getApplicationContext(), R.font.visbylight);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listAsmaAdapter = new ListAsmaAdapter(getApplicationContext(),new ArrayList<Asma>());


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAsmaAdapter);

        mPresenter.loadAsma();
        titlekumpulansurah.setTypeface(facemedium);

    }

    @Override
    public ListAsmaPresenter initPresenter() {
        return new ListAsmaPresenter(this);
    }


    public void onLoad(ArrayList<Asma> data) {
        listAsmaAdapter.refresh(data);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
