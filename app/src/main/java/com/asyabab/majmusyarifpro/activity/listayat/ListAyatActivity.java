package com.asyabab.majmusyarifpro.activity.listayat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.asyabab.majmusyarifpro.R;
import com.asyabab.majmusyarifpro.base.BaseActivity;
import com.asyabab.majmusyarifpro.modelquran.Ayat;

public class ListAyatActivity extends BaseActivity<ListAyatPresenter> implements ListAyatView {
    public static final String KEY_SURAH = "surah";
    public static final String KEY_AYAT = "ayat";
    public static final String KEY_TERJEMAHAN = "terjemahan";
    public static final String KEY_LOAD_TERJEMAHAN = "load_terjemahan";
    @BindView(R.id.list_ayat)
    RecyclerView listAyat;
   /* @BindView(R.id.toolbar)
    Toolbar toolbar;*/
    private ListAyatAdapter listAyatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ayat);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String surah = getIntent().getStringExtra(KEY_SURAH);
        String ayat = getIntent().getStringExtra(KEY_AYAT);
        String terjemahan = getIntent().getStringExtra(KEY_TERJEMAHAN);
        String loadTerjemahan = getIntent().getStringExtra(KEY_LOAD_TERJEMAHAN);

       // setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(ayat);
            getSupportActionBar().setSubtitle(terjemahan);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



        listAyatAdapter = new ListAyatAdapter(getApplicationContext(),new ArrayList<Ayat>());

        listAyat.setHasFixedSize(true);
        listAyat.setLayoutManager(new LinearLayoutManager(this));
        listAyat.setAdapter(listAyatAdapter);

        mPresenter.loadAyat(surah, loadTerjemahan);
    }

    @Override
    public ListAyatPresenter initPresenter() {
        return new ListAyatPresenter(this);
    }

    @Override
    public void onLoad(ArrayList<Ayat> data) {
        listAyatAdapter.refresh(data);
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
