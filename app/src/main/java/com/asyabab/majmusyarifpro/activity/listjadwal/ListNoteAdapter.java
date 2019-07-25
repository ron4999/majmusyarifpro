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
import com.asyabab.majmusyarifpro.model.Note;
import com.asyabab.majmusyarifpro.modelquran.Surah;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by User on 01/05/2018.
 */


public class ListNoteAdapter extends RecyclerView.Adapter<ListNoteAdapter.NoteHolder> {
    private ArrayList<Note> noteList;
    private OnSurahItemClick click;


    private static Typeface facemedium, facethin,facelight;
    @BindView(R.id.tvj_namawaktusholat)
    TextView namawaktu;

    ListNoteAdapter(Context context, ArrayList<Note> jadwalList, OnSurahItemClick click) {
        this.noteList = jadwalList;
        this.click = click;
        facemedium= ResourcesCompat.getFont(context, R.font.visbycfmedium);
        facethin= ResourcesCompat.getFont(context, R.font.visbyoblique);
        facelight= ResourcesCompat.getFont(context, R.font.visbylight);
    }

    ListNoteAdapter(Context context, ArrayList<Note> jadwals) {
        this.noteList = jadwals;
        facemedium= ResourcesCompat.getFont(context, R.font.visbycfmedium);
        facethin= ResourcesCompat.getFont(context, R.font.visbyoblique);
        facelight= ResourcesCompat.getFont(context, R.font.visbylight);
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_listjadwalsholat, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        holder.setContent(noteList.get(position), click);
    }

    void refresh(ArrayList<Note> fill) {
        noteList = new ArrayList<>();
        noteList.addAll(fill);

        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return noteList.size();
    }


    class NoteHolder extends RecyclerView.ViewHolder {

        @BindViews({R.id.switchNotifimsak, R.id.switchNotifsubuh, R.id.switchNotifzuhur, R.id.switch_notifashar, R.id.switch_notifmaghrib,R.id.switchNotifisya})
        List<TextView> rowJadwal;


        NoteHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setContent(final Note jadwal, OnSurahItemClick click) {

            rowJadwal.get(0).setText(jadwal.getStatus());

        }

    }

    interface OnSurahItemClick {
        void onCLick(Surah surah);
    }
}
