package com.silence.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
public class PlanActivity extends AppCompatActivity implements View.OnClickListener, CustomDatePicker.Callback {
    private LinearLayout ll_date;
    private TextView currentDate;
    private CustomDatePicker customDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        ll_date = (LinearLayout) findViewById(R.id.ll_date);
        currentDate = (TextView) findViewById(R.id.tv_selected_date);
        initDatePicker();
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.ll_date:
//                // 日期格式为yyyy-MM-dd
        customDatePicker.show(currentDate.getText().toString());
//                break;
//
//            case R.id.selectTime:
//                // 日期格式为yyyy-MM-dd HH:mm
//                customDatePicker2.show(currentTime.getText().toString());
//                break;
//        }


    }

    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        currentDate.setText(now.split(" ")[0]);
        CustomDatePicker.Callback callback = null;
        customDatePicker = new CustomDatePicker(this, callback, "2019-01-01 00:00", "2020-01-01 00:00");
//        CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
//            @Override
//            public void handle(String time) { // 回调接口，获得选中的时间
//                currentDate.setText(time.split(" ")[0]);
//            }
//        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
//        customDatePicker.showSpecificTime(false); // 不显示时和分
//        customDatePicker.setIsLoop(false); // 不允许循环滚动

    }

    @Override
    public void onTimeSelected(long timestamp) {

    }
}
