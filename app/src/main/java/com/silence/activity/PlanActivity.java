package com.silence.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.silence.studyplan.CustomDatePicker;
import com.silence.word.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Autumn on 2019/6/22
 */
public class PlanActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_date;
    private TextView currentDate;
    private TextView endDate;
    private CustomDatePicker customDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle("单词计划");
        ll_date = (LinearLayout) findViewById(R.id.ll_date);
        currentDate = (TextView) findViewById(R.id.tv_current_date);
        endDate = (TextView) findViewById(R.id.tv_selected_date);
        ll_date.setOnClickListener(this);
        initDatePicker();
    }

    @Override
    public void onClick(View v) {
        customDatePicker.show(endDate.getText().toString());
    }

    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        currentDate.setText(now.split(" ")[0]);
        endDate.setText(now.split(" ")[0]);
        customDatePicker =
                new CustomDatePicker(this, new CustomDatePicker.Callback() {
                    @Override
                    public void onTimeSelected(long timestamp) { // 回调接口，获得选中的时间
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date(timestamp);
                        endDate.setText(simpleDateFormat.format(date));
                    }
                }, now, "2030-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.setCanShowPreciseTime(false);
        customDatePicker.setScrollLoop(false);
    }
}
