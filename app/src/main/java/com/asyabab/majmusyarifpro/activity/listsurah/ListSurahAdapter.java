package com.asyabab.majmusyarifpro.activity.listsurah;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asyabab.majmusyarifpro.modelquran.Surah;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import com.asyabab.majmusyarifpro.R;

/**
 * Created by User on 01/05/2018.
 */

public class ListSurahAdapter extends RecyclerView.Adapter<ListSurahAdapter.SurahHolder> {
    private ArrayList<Surah> surahList;
    private OnSurahItemClick click;
    private static Typeface facemedium, facethin,facelight;


    ListSurahAdapter(Context context, ArrayList<Surah> surahList, OnSurahItemClick click) {
        this.surahList = surahList;
        this.click = click;
        facemedium= ResourcesCompat.getFont(context, R.font.visbycfmedium);
        facethin= ResourcesCompat.getFont(context, R.font.visbyoblique);
        facelight= ResourcesCompat.getFont(context, R.font.visbylight);
    }

    @Override
    public SurahHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_surah, parent, false);
        return new SurahHolder(view);
    }

    @Override
    public void onBindViewHolder(SurahHolder holder, int position) {
        holder.setContent(surahList.get(position), click);
    }

    @Override
    public int getItemCount() {
        return surahList.size();
    }

    void refresh(ArrayList<Surah> fill){
        surahList = new ArrayList<>();
        surahList.addAll(fill);

        notifyDataSetChanged();
    }

    class SurahHolder extends RecyclerView.ViewHolder {

        @BindViews({R.id.rowSurah, R.id.rowAyat, R.id.rowTerjemahanSurah, R.id.rowJumlahAyat, R.id.tulisanayat})
        List<TextView> rowSurah;

        SurahHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setContent(final Surah surah, final OnSurahItemClick click) {
            rowSurah.get(0).setText(surah.getSurah());
            rowSurah.get(1).setText(surah.getAyat());
            rowSurah.get(2).setText(surah.getTerjemahan());
            rowSurah.get(3).setText(surah.getJumlahAyat());

            rowSurah.get(0).setTypeface(facemedium);
            rowSurah.get(2).setTypeface(facethin);
            rowSurah.get(3).setTypeface(facemedium);
            rowSurah.get(1).setTypeface(facemedium);
            rowSurah.get(4).setTypeface(facelight);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onCLick(surah);
                }
            });

        }

    }

    interface OnSurahItemClick {
        void onCLick(Surah surah);
    }
}
