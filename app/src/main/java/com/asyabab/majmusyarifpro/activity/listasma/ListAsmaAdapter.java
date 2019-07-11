package com.asyabab.majmusyarifpro.activity.listasma;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asyabab.majmusyarifpro.model.Asma;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import com.asyabab.majmusyarifpro.R;

/**
 * Created by User on 01/05/2018.
 */

public class ListAsmaAdapter extends RecyclerView.Adapter<ListAsmaAdapter.AsmaHolder> {

    private ArrayList<Asma> asmaList;
    private Context mContext;
    private static Typeface face;
    Typeface facemedium, facethin,facelight;

    ListAsmaAdapter(Context context, ArrayList<Asma> data) {
        this.asmaList = data;
        this.mContext=context;
        TextView rowAyata, rowArabica, rowTerjemahana;

        face= ResourcesCompat.getFont(context, R.font.aldabhi);

        facemedium= ResourcesCompat.getFont(context, R.font.visbycfmedium);
        facethin= ResourcesCompat.getFont(context, R.font.visbyoblique);
        facelight= ResourcesCompat.getFont(context, R.font.visbylight);

    }

    @Override
    public AsmaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_asmaulhusna, parent, false);
        return new AsmaHolder(view);

    }

    @Override
    public void onBindViewHolder(AsmaHolder holder, int position) {
        holder.setContent(asmaList.get(position));
    }

    @Override
    public int getItemCount() {
        return asmaList.size();
    }

    void refresh(ArrayList<Asma> fill) {
        asmaList = new ArrayList<>();
        asmaList.addAll(fill);

        notifyDataSetChanged();
    }

    class AsmaHolder extends RecyclerView.ViewHolder {

        @BindViews({R.id.rowNomer, R.id.rowLatin, R.id.rowArab, R.id.rowIndonesia})
        List<TextView> rowAsma;

        AsmaHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setContent(Asma asma) {

            rowAsma.get(0).setText(asma.getNomer());
            rowAsma.get(1).setText(asma.getLatin());
            rowAsma.get(2).setText(asma.getArab());
            rowAsma.get(3).setText(asma.getIndonesia());

            rowAsma.get(0).setTypeface(facemedium);
            rowAsma.get(1).setTypeface(facemedium);
            rowAsma.get(2).setTypeface(face);
            rowAsma.get(3).setTypeface(facethin);

        }
    }
}
