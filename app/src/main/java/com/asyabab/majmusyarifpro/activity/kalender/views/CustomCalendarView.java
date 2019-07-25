package com.asyabab.majmusyarifpro.activity.kalender.views;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import com.asyabab.majmusyarifpro.R;
import com.asyabab.majmusyarifpro.activity.kalender.CalendarViewPagerAdapter;
import com.asyabab.majmusyarifpro.activity.kalender.interfaces.OnDateSelectedListener;
import com.asyabab.majmusyarifpro.activity.kalender.objects.CalendarMonth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CustomCalendarView extends FrameLayout implements View.OnClickListener {

    private TextView mPagerTextMonth;
    private ImageButton mButtonLeftArrow;
    private ImageButton mButtonRightArrow;
    private ViewPager mViewPager;
    private CalendarViewPagerAdapter mViewPagerAdapter;
    private OnDateSelectedListener mListener;
    Typeface typeface, facemedium;


    public CustomCalendarView(@NonNull Context context) {
        super(context);
        init();
        AssetManager am=context.getApplicationContext().getAssets();

        typeface= ResourcesCompat.getFont(context, R.font.visbydemibold);
        mPagerTextMonth.setTypeface(typeface);

    }

    public CustomCalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        AssetManager am=context.getApplicationContext().getAssets();
        facemedium= ResourcesCompat.getFont(context, R.font.visbycfmedium);

        typeface= ResourcesCompat.getFont(context, R.font.visbydemibold);
        mPagerTextMonth.setTypeface(typeface);
    }

    public CustomCalendarView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        AssetManager am=context.getApplicationContext().getAssets();

        typeface= ResourcesCompat.getFont(context, R.font.visbydemibold);
    }

    private void init() {
        inflate(getContext(), R.layout.view_custom_calendar, this);
        mViewPager = (ViewPager) findViewById(R.id.activity_main_view_pager);
        mPagerTextMonth = (TextView) findViewById(R.id.activity_main_pager_text_month);
        mButtonLeftArrow = (ImageButton) findViewById(R.id.activity_main_pager_button_left_arrow);
        mButtonRightArrow = (ImageButton) findViewById(R.id.activity_main_pager_button_right_arrow);
        mButtonLeftArrow.setOnClickListener(this);
        mButtonRightArrow.setOnClickListener(this);
        mPagerTextMonth.setTypeface(typeface);

        buildCalendarView();

    }

    public void setOnDateSelectedListener(OnDateSelectedListener listener) {
        mViewPagerAdapter.setOnDateSelectedListener(listener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_pager_button_right_arrow:
                int next = mViewPager.getCurrentItem() + 1;
                mViewPager.setCurrentItem(next, true);
                break;
            case R.id.activity_main_pager_button_left_arrow:
                int prev = mViewPager.getCurrentItem() - 1;
                mViewPager.setCurrentItem(prev, true);
                break;
        }
    }

    private void buildCalendarView() {
        List<CalendarMonth> list = new ArrayList<>();
        CalendarMonth today = new CalendarMonth(Calendar.getInstance());

        list.add(new CalendarMonth(today, -2));
        list.add(new CalendarMonth(today, -1));
        list.add(today);
        list.add(new CalendarMonth(today, 1));
        list.add(new CalendarMonth(today, 2));

        mViewPagerAdapter = new CalendarViewPagerAdapter(list, mViewPager);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(2);
        mPagerTextMonth.setText(mViewPagerAdapter.getItemPageHeader(2));

    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            int position = mViewPager.getCurrentItem();
            mPagerTextMonth.setText(mViewPagerAdapter.getItemPageHeader(position));

            // current item is the first item in the list
            if (state == ViewPager.SCROLL_STATE_IDLE && position == 1) {
                addPrev();
            }

            // current item is the last item in the list
            if (state == ViewPager.SCROLL_STATE_IDLE && position == mViewPagerAdapter.getCount() - 2) {
                addNext();
            }

        }
    };

    private void addNext() {
        CalendarMonth month = mViewPagerAdapter.getItem(mViewPagerAdapter.getCount() - 1);
        mViewPagerAdapter.addNext(new CalendarMonth(month, 1));
    }

    private void addPrev() {
        CalendarMonth month = mViewPagerAdapter.getItem(0);
        mViewPagerAdapter.addPrev(new CalendarMonth(month, -1));
    }

}
