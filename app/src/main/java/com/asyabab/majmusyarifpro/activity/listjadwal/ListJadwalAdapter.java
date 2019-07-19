package com.asyabab.majmusyarifpro.activity.listjadwal;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asyabab.majmusyarifpro.R;
import com.asyabab.majmusyarifpro.model.Jadwal;
import com.asyabab.majmusyarifpro.modelquran.Ayat;
import com.asyabab.majmusyarifpro.modelquran.Surah;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by User on 01/05/2018.
 */

public class ListJadwalAdapter extends RecyclerView.Adapter<ListJadwalAdapter.JadwalHolder> {
    private ArrayList<Jadwal> jadwalList;
    private OnSurahItemClick click;
    private static Typeface facemedium, facethin,facelight;


    ListJadwalAdapter(Context context, ArrayList<Jadwal> jadwalList, OnSurahItemClick click) {
        this.jadwalList = jadwalList;
        this.click = click;
        facemedium= ResourcesCompat.getFont(context, R.font.visbycfmedium);
        facethin= ResourcesCompat.getFont(context, R.font.visbyoblique);
        facelight= ResourcesCompat.getFont(context, R.font.visbylight);
    }

    ListJadwalAdapter(Context applicationContext, ArrayList<Jadwal> jadwals) {
        this.jadwalList = jadwals;
    }

    @Override
    public JadwalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_jadwal_sholat, parent, false);
        return new JadwalHolder(view);
    }

    @Override
    public void onBindViewHolder(JadwalHolder holder, int position) {
        holder.setContent(jadwalList.get(position), click);
    }

    void refresh(ArrayList<Jadwal> fill) {
        jadwalList = new ArrayList<>();
        jadwalList.addAll(fill);

        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return jadwalList.size();
    }

    class JadwalHolder extends RecyclerView.ViewHolder {

        @BindViews({R.id.tvjamashar, R.id.tvjamduhur, R.id.tvjamsubuh, R.id.tvjamisya, R.id.tvjammaghrib,R.id.tvjamimsak})
        List<TextView> rowJadwal;

        JadwalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setContent(final Jadwal jadwal, OnSurahItemClick click) {
            rowJadwal.get(0).setText(jadwal.getAshar());
            rowJadwal.get(1).setText(jadwal.getZuhur());
            rowJadwal.get(2).setText(jadwal.getSubuh());
            rowJadwal.get(3).setText(jadwal.getIsya());
            rowJadwal.get(4).setText(jadwal.getMaghrib());
            rowJadwal.get(5).setText("AA");

        }

    }

    interface OnSurahItemClick {
        void onCLick(Surah surah);
    }
}