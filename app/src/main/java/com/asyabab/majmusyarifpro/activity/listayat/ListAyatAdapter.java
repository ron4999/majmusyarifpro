package com.asyabab.majmusyarifpro.activity.listayat;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asyabab.majmusyarifpro.modelquran.Ayat;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import com.asyabab.majmusyarifpro.R;

/**
 * Created by User on 01/05/2018.
 */

public class ListAyatAdapter extends RecyclerView.Adapter<ListAyatAdapter.AyatHolder> {

    private ArrayList<Ayat> ayatList;
    private Context mContext;
    private static Typeface face;
    ListAyatAdapter(Context context, ArrayList<Ayat> data) {
        this.ayatList = data;
        this.mContext=context;
        TextView rowAyata, rowArabica, rowTerjemahana;

        face= ResourcesCompat.getFont(context, R.font.arabic);
//        rowAyata =(TextView) findViewById(R.id.rowAyat);
//        rowArabica=(TextView) findViewById(R.id.rowArabic);
//        rowTerjemahana=(TextView) findViewById(R.id.rowTerjemahan);


    }

    @Override
    public AyatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_ayat, parent, false);
        return new AyatHolder(view);

    }

    @Override
    public void onBindViewHolder(AyatHolder holder, int position) {
        holder.setContent(ayatList.get(position));
    }

    @Override
    public int getItemCount() {
        return ayatList.size();
    }

    void refresh(ArrayList<Ayat> fill) {
        ayatList = new ArrayList<>();
        ayatList.addAll(fill);

        notifyDataSetChanged();
    }

    class AyatHolder extends RecyclerView.ViewHolder {

        @BindViews({R.id.rowAyat, R.id.rowArabic, R.id.rowTerjemahan})
        List<TextView> rowAyat;

        AyatHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setContent(Ayat ayat) {

            rowAyat.get(0).setText(ayat.getAyat());
            rowAyat.get(1).setText(ayat.getArab());
            rowAyat.get(2).setText(ayat.getTerjemahan());

            rowAyat.get(0).setTypeface(face);
            rowAyat.get(1).setTypeface(face);
            rowAyat.get(2).setTypeface(face);

        }
    }
}
