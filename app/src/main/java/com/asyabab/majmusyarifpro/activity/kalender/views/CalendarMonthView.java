package com.asyabab.majmusyarifpro.activity.kalender.views;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import com.asyabab.majmusyarifpro.R;
import com.asyabab.majmusyarifpro.activity.kalender.interfaces.OnDayViewClickListener;
import com.asyabab.majmusyarifpro.activity.kalender.objects.CalendarDate;
import com.asyabab.majmusyarifpro.activity.kalender.objects.CalendarMonth;
import com.asyabab.majmusyarifpro.utils.Utils;

import java.util.Calendar;
import java.util.Locale;


public class CalendarMonthView extends FrameLayout implements View.OnClickListener {

    private GridLayout mGridLayout;
    private ViewGroup mLayoutDays;
    private OnDayViewClickListener mListener;
    private CalendarDate mSelectedDate;
    Typeface typeface;
    public CalendarMonthView(Context context) {
        super(context);
        init();
        AssetManager am=context.getApplicationContext().getAssets();
        typeface= ResourcesCompat.getFont(context, R.font.visbydemibold);
    }

    public void setOnDayViewClickListener(OnDayViewClickListener listener) {
        mListener = listener;
    }

    public void setSelectedDate(CalendarDate selectedDate) {
        mSelectedDate = selectedDate;
    }

    private void init() {
        inflate(getContext(), R.layout.view_calendar_month, this);
        mGridLayout = (GridLayout) findViewById(R.id.view_calendar_month_grid);
        mLayoutDays = (ViewGroup) findViewById(R.id.view_calendar_month_layout_days);
    }

    public void buildView(CalendarMonth calendarMonth) {
        buildDaysLayout();
        buildGridView(calendarMonth);
    }

    private void buildDaysLayout() {
        String[] days;
        days = getResources().getStringArray(R.array.days_sunday_array);

        for (int i = 0; i < mLayoutDays.getChildCount(); i++) {
            TextView textView = (TextView) mLayoutDays.getChildAt(i);
            textView.setText(days[i]);
            textView.setTypeface(typeface);
        }
    }

    private void buildGridView(CalendarMonth calendarMonth) {
        int row = CalendarMonth.NUMBER_OF_WEEKS_IN_MONTH;
        int col = CalendarMonth.NUMBER_OF_DAYS_IN_WEEK;
        mGridLayout.setRowCount(row);
        mGridLayout.setColumnCount(col);

        int screenWidth = Utils.getScreenWidth(getContext());
        int width = (screenWidth-80) / col;

        for (CalendarDate date : calendarMonth.getDays()) {
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = LayoutParams.WRAP_CONTENT;
            params.height = LayoutParams.WRAP_CONTENT;

            CalendarDayView dayView = new CalendarDayView(getContext(), date);
            dayView.setContentDescription(date.toString());
            dayView.setLayoutParams(params);
            dayView.setOnClickListener(this);
            decorateDayView(dayView, date, calendarMonth.getMonth());
            mGridLayout.addView(dayView);

        }
    }

    private void decorateDayView(CalendarDayView dayView, CalendarDate day, int month) {

        if (day.getMonth() != month) {
            dayView.setOtherMothTextColor();
        } else {
            dayView.setThisMothTextColor();
            if(day.getDayOfWeek()== Calendar.FRIDAY){
                dayView.setMondayTextColor();
            }
            if(day.getDayOfWeek()== Calendar.SUNDAY){
                dayView.setMingguTextColor();
            }

            if(day.getDayOfWeek()== Calendar.MONDAY||day.getDayOfWeek()== Calendar.THURSDAY){
                dayView.setSeninKamisTextColor();
            }
        }

        if (day.getYear() == 2019) {
            if (day.getMonth()==0){
                if (day.getMonth() != month) {
                    dayView.setOtherMothTextColor();
                }else{
                    if(day.getDay()==19||day.getDay()==20||day.getDay()==21){
                        dayView.setdYaumulBidh();
                    }
                }

            }
            if (day.getMonth()==1){
                if (day.getMonth() != month) {
                    dayView.setOtherMothTextColor();
                }else{
                if(day.getDay()==18||day.getDay()==19||day.getDay()==20){
                    dayView.setdYaumulBidh();
                    }
                }
            }
            if (day.getMonth()==2){
                if (day.getMonth() != month) {
                    dayView.setOtherMothTextColor();
                }else{
                if(day.getDay()==20||day.getDay()==21||day.getDay()==22){
                    dayView.setdYaumulBidh();
                }}
            }
            if (day.getMonth()==3){
                if(day.getDay()==19||day.getDay()==20||day.getDay()==21){
                    dayView.setdYaumulBidh();
                }
            }
            if (day.getMonth()==4){
                if (day.getMonth() != month) {
                    dayView.setOtherMothTextColor();
                }else
                {
                for(int a=5;a<=31; a++){
                    if(day.getDay()==a){
                        dayView.setRamadhan();
                    }
                }}

            }
            if (day.getMonth()==5){
                if (day.getMonth() != month) {
                    dayView.setOtherMothTextColor();
                }else{
                if(day.getDay()==17||day.getDay()==18||day.getDay()==19){
                    dayView.setdYaumulBidh();
                }
                if(day.getDay()==5){
                    dayView.setHarampuasa();
                }
                for(int a=1;a<=4; a++){
                    if(day.getDay()==a){
                        dayView.setRamadhan();
                    }
                }}
            }
            if (day.getMonth()==6){
                if(day.getDay()==16||day.getDay()==17||day.getDay()==18){
                    dayView.setdYaumulBidh();
                }

            }
            if (day.getMonth()==7){
                if (day.getMonth() != month) {
                    dayView.setOtherMothTextColor();
                }else {
                    if (day.getDay() == 11 || day.getDay() == 12 || day.getDay() == 13 || day.getDay() == 14) {
                        dayView.setHarampuasa();
                    }
                    if (day.getDay() == 10) {
                        dayView.setArafah();
                    }
                    if (day.getDay() == 15 || day.getDay() == 16 || day.getDay() == 17) {
                        dayView.setdYaumulBidh();
                    }
                }

            }
            if (day.getMonth()==8){
                if(day.getDay()==8||day.getDay()==9){
                    dayView.setAsyura();
                }
                if (day.getDay() == 12 || day.getDay() == 13 || day.getDay() == 14) {
                    dayView.setdYaumulBidh();
                }

            }
            if (day.getMonth()==9){
                if (day.getMonth() != month) {
                    dayView.setOtherMothTextColor();
                }else{
                if (day.getDay() == 12 || day.getDay() == 13 || day.getDay() == 14) {
                    dayView.setdYaumulBidh();
                }}

            }
            if (day.getMonth()==10){

                if (day.getDay() == 10 || day.getDay() == 11 || day.getDay() == 12) {
                    dayView.setdYaumulBidh();
                }

            }
            if (day.getMonth()==11){
                if (day.getDay() == 10 || day.getDay() == 11 || day.getDay() == 12) {
                    dayView.setdYaumulBidh();
                }

            }
        }

        if (mSelectedDate != null && mSelectedDate.isDateEqual(day)) {
            dayView.setPurpleSolidOvalBackground();
        } else {
            dayView.unsetPurpleSolidOvalBackground();
        }
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onDayViewClick((CalendarDayView) view);
        }
    }
}
