package com.asyabab.majmusyarifpro.activity.kalender.views;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.asyabab.majmusyarifpro.R;
import com.asyabab.majmusyarifpro.activity.kalender.objects.CalendarDate;

import java.util.Locale;


public class CalendarDayView extends LinearLayout {

    private CalendarDate mCalendarDate;
    private TextView mTextDay;
    private View mLayoutBackground;
    Typeface typeface;
    public CalendarDayView(Context context, CalendarDate calendarDate) {
        super(context);
        mCalendarDate = calendarDate;
        init();
        AssetManager am=context.getApplicationContext().getAssets();

        typeface= ResourcesCompat.getFont(context, R.font.visbydemibold);

    }

    private void init() {
        inflate(getContext(), R.layout.view_calendar_day, this);
        mLayoutBackground = findViewById(R.id.view_calendar_day_layout_background);
        mTextDay = (TextView) findViewById(R.id.view_calendar_day_text);
        mTextDay.setText("" + mCalendarDate.getDay());

    }

    public CalendarDate getDate() {
        return mCalendarDate;
    }

    public void setThisMothTextColor() {
        mTextDay.setTextColor(ContextCompat.getColor(getContext(), R.color.title));
        mTextDay.setTypeface(typeface);

    }

    public void setOtherMothTextColor() {
        mTextDay.setTextColor(ContextCompat.getColor(getContext(), R.color.subtitle));
        mTextDay.setTypeface(typeface);

    }

    public void setMondayTextColor() {
        mTextDay.setTextColor(ContextCompat.getColor(getContext(), R.color.dategreen));

    }
    public void setSeninKamisTextColor() {
        mTextDay.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.oval_bluesolid));

    }
    public void setMingguTextColor() {
        mTextDay.setTextColor(ContextCompat.getColor(getContext(), R.color.datepink));

    }

    public void setPurpleSolidOvalBackground() {
        mLayoutBackground.setBackgroundResource(R.drawable.oval_purple_solid);
    }
    public void setdYaumulBidh() {
        mTextDay.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.oval_orange_solid));
        mTextDay.setTextColor(ContextCompat.getColor(getContext(), R.color.title));

    }

    public void setHarampuasa() {
        mTextDay.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.oval_merah_solid));
        mTextDay.setTextColor(ContextCompat.getColor(getContext(), R.color.title));

    }
    public void setRamadhan() {
        mTextDay.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.oval_green_solid));
        mTextDay.setTextColor(ContextCompat.getColor(getContext(), R.color.title));

    }
    public void setArafah() {
        mTextDay.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.oval_pink_solid));
        mTextDay.setTextColor(ContextCompat.getColor(getContext(), R.color.title));

    }

    public void setAsyura() {
        mTextDay.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.oval_ungu_solid));
        mTextDay.setTextColor(ContextCompat.getColor(getContext(), R.color.title));

    }

    public void unsetPurpleSolidOvalBackground() {
        mLayoutBackground.setBackgroundResource(R.drawable.oval_black_solid);
    }

}
