package com.asyabab.majmusyarifpro.activity.kalender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;
import com.asyabab.majmusyarifpro.R;
import com.asyabab.majmusyarifpro.activity.kalender.interfaces.OnDateSelectedListener;
import com.asyabab.majmusyarifpro.activity.kalender.objects.CalendarDate;
import com.asyabab.majmusyarifpro.activity.kalender.views.CustomCalendarView;


public class Kalender extends AppCompatActivity implements OnDateSelectedListener {

    private TextView mTextDay;
    private TextView mTextDayOfWeek;
    private CustomCalendarView mCustomCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mTextDay = (TextView) findViewById(R.id.activity_main_text_day_of_month);
        mTextDayOfWeek = (TextView) findViewById(R.id.activity_main_text_day_of_week);
        mCustomCalendar = (CustomCalendarView) findViewById(R.id.activity_main_view_custom_calendar);
        mCustomCalendar.setOnDateSelectedListener(this);
    }

    @Override
    public void onDateSelected(CalendarDate date) {
        mTextDay.setText(date.dayToString());
        mTextDayOfWeek.setText(date.dayOfWeekToStringName());
    }
}
