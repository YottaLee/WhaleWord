package com.silence.signcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.silence.word.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class I8ShowSignCalendarActivity extends Activity {
    private SignCalendar calendar;
    private String date;
    private TextView btn_sign;
    private TextView tv_sign_year_month;
    private SignCalendarReq signCalendarReq;
    private SignCalendarReq.DataBean dataBean;
    List<String> list = new ArrayList<>();//list中存储的格式为2019-06-02
    private int month;
    private int year;
    private RelativeLayout rlGetGiftData;
    private TextView tvGetSunValue;
    private ImageView ivSun;
    private ImageView ivSunBg;
    private RelativeLayout rlQuedingBtn;
    private RelativeLayout rlBtnSign;
    private ImageView signBack;
    private boolean isSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_calendar);

        //接收传递过来的数据
        final SignCalendarReq signCalendarReq = (SignCalendarReq) getIntent().getSerializableExtra("userInfos");

        //获取当前的月份
        month = Calendar.getInstance().get(Calendar.MONTH);
        //获取当前的年份
        year = Calendar.getInstance().get(Calendar.YEAR);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        date = formatter.format(curDate);

        calendar = (SignCalendar) findViewById(R.id.sc_main);  //com.silence.signcalendar.SignCalendar
        btn_sign = (TextView) findViewById(R.id.btn_sign);
        tv_sign_year_month = (TextView) findViewById(R.id.tv_sign_year_month);
        rlGetGiftData = (RelativeLayout) findViewById(R.id.rl_get_gift_view);
        tvGetSunValue = (TextView) findViewById(R.id.tv_text_one);
        ivSun = (ImageView) findViewById(R.id.iv_sun);        ivSunBg = (ImageView) findViewById(R.id.iv_sun_bg);
        signBack = (ImageView) findViewById(R.id.i8show_attention_back);
        rlQuedingBtn = (RelativeLayout) findViewById(R.id.rl_queding_btn);
        rlBtnSign = (RelativeLayout) findViewById(R.id.rl_btn_sign);

        //设置当前日期
        tv_sign_year_month.setText(year + "年" + (month + 1) + "月");

        if (signCalendarReq != null) {
            if (signCalendarReq.getState().getCode() == 1) {//1成功，0失败
                dataBean = signCalendarReq.getData();
                //获取当月已签到的日期
                String signDay = dataBean.getSignDay();
                String[] splitDay = signDay.split(",");

                //list中存储的格式为2019-06-02
                for (int i = 0; i < splitDay.length; i++) {
                    if (Integer.parseInt(splitDay[i]) < 10) {
                        if (month < 10) {
                            list.add(year + "-0" + (month + 1) + "-0" + splitDay[i]);
                        } else {
                            list.add(year + "-" + (month + 1) + "-0" + splitDay[i]);
                        }

                    } else {
                        if (month < 10) {
                            list.add(year + "-0" + (month + 1) + "-" + splitDay[i]);
                        } else {
                            list.add(year + "-" + (month + 1) + "-" + splitDay[i]);
                        }
                    }
                }


                calendar.addMarks(list, 0);

                if (dataBean.getIsSign() == 1) {//1是已签到，0是未签到
                    rlBtnSign.setBackgroundResource(R.drawable.btn_sign_calendar_no);
                    btn_sign.setText("已签到");
                    rlBtnSign.setClickable(false);
                } else {
                    rlBtnSign.setBackgroundResource(R.drawable.btn_sign_calendar);
                    btn_sign.setText("签 到");
                }
            }
        }

        btn_sign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isSign) {
                    signCalendarData();
                }

            }
        });

        rlQuedingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlGetGiftData.setVisibility(View.GONE);
            }
        });

        signBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void signCalendarData() {
        //模拟请求后台数据签到已成功

        rlGetGiftData.setVisibility(View.VISIBLE);
        rlBtnSign.setBackgroundResource(R.drawable.btn_sign_calendar_no);
        btn_sign.setText("已签到");
        isSign = true;//模拟当天已签到


        ivSun.setImageResource(R.drawable.i8live_sun);
        tvGetSunValue.setText("恭喜获得10个阳光值");


        Animation operatingAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anim_online_gift);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        ivSunBg.startAnimation(operatingAnim);

        //list.add("2017-11-18");
        list.add(date);
        calendar.addMarks(list, 0);

    }

}
