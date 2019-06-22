package com.silence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import com.silence.utils.Const;
import com.silence.word.R;

public class DicAcitivity extends AppCompatActivity implements View.OnClickListener {

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dic);
        findViewById(R.id.lv_all).setOnClickListener(this);
        findViewById(R.id.lv_studied).setOnClickListener(this);
        findViewById(R.id.lv_unfamiliar).setOnClickListener(this);
        findViewById(R.id.lv_handled).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.lv_all:
                intent.setClass(this, WordListActivity.class);
                intent.putExtra(Const.WORD_KEY, Const.DIC_ALL);
                break;
            case R.id.lv_studied:
                intent.setClass(this, WordListActivity.class);
                intent.putExtra(Const.WORD_KEY, Const.DIC_STUDIED);
                break;
            case R.id.lv_unfamiliar:
                intent.setClass(this, WordListActivity.class);
                intent.putExtra(Const.WORD_KEY, Const.DIC_UNFAMILIAR);
                break;
            case R.id.lv_handled:
                intent.setClass(this, WordListActivity.class);
                intent.putExtra(Const.WORD_KEY, Const.DIC_HANDLED);
                break;
        }
        startActivity(intent);
    }
}