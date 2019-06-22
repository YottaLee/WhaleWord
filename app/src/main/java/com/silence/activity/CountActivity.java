package com.silence.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.silence.dao.Utils;
import com.silence.pojo.Word;
import com.silence.utils.FileUtils;
import com.silence.word.R;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019-06-22 10:40
 * @Version 1.0
 */
public class CountActivity extends Activity {
    private String wordCount = "UNDEFINED";

    private String dayCount = "UNDEFINED";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        TextView todayCount = (TextView)findViewById(R.id.today_count);
        todayCount.setText(FileUtils.readFile(CountActivity.this, "today"));
        todayCount.setTextSize(100);

        TextView dayCount = (TextView)findViewById(R.id.day_count);
        dayCount.setText(FileUtils.readFile(CountActivity.this, "day"));
        dayCount.setTextSize(20);

        TextView wordCount = (TextView) findViewById(R.id.word_count);
        wordCount.setText(FileUtils.readFile(CountActivity.this, "word"));
        wordCount.setTextSize(20);




    }

    private void initData() {
        try {
            wordCount = FileUtils.readFile(CountActivity.this, "word");
            System.out.println("WORD_COUNT: " + wordCount);
            dayCount = FileUtils.readFile(CountActivity.this, "day");
            System.out.println("DAY_COUNT: " + dayCount);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }



}
