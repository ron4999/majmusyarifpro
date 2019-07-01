package com.asyabab.majmusyarifpro.activity.listsurah;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.asyabab.majmusyarifpro.activity.listayat.ListAyatActivity;
import com.asyabab.majmusyarifpro.base.BaseActivity;
import com.asyabab.majmusyarifpro.modelquran.Surah;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.asyabab.majmusyarifpro.R;

import org.w3c.dom.Text;

public class QuranActivity extends BaseActivity<ListSurahPresenter> implements ListSurahView, ListSurahAdapter.OnSurahItemClick {


    @BindView(R.id.list_surah)
    RecyclerView recyclerView;
    @BindView(R.id.titlekumpulansurah)
    TextView titlekumpulansurah;
    private ListSurahAdapter listSurahAdapter;
    private String loadTerjemahan = LOAD_INDONESIA;
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
        listSurahAdapter = new ListSurahAdapter(getApplicationContext(),new ArrayList<Surah>(), this);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listSurahAdapter);

        mPresenter.loadSurah(loadTerjemahan);
        titlekumpulansurah.setTypeface(facemedium);

    }

    @Override
    public ListSurahPresenter initPresenter() {
        return new ListSurahPresenter(this);
    }


    public void onLoad(ArrayList<Surah> data) {
        listSurahAdapter.refresh(data);
    }

    @Override
    public void onCLick(Surah surah) {
        Intent ayat = new Intent(QuranActivity.this, ListAyatActivity.class);
        ayat.putExtra(ListAyatActivity.KEY_AYAT, surah.getAyat());
        ayat.putExtra(ListAyatActivity.KEY_SURAH, surah.getSurah());
        ayat.putExtra(ListAyatActivity.KEY_TERJEMAHAN, surah.getTerjemahan());
        ayat.putExtra(ListAyatActivity.KEY_LOAD_TERJEMAHAN, loadTerjemahan);
        startActivity(ayat);
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
