package com.silence.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.*;

import com.andraskindler.quickscroll.QuickScroll;
import com.silence.adapter.TransAdapter;
import com.silence.dao.Utils;
import com.silence.dao.WordUtils;
import com.silence.pojo.Trans;
import com.silence.pojo.Word;
import com.silence.pojo.WordLast;
import com.silence.utils.Const;
import com.silence.word.R;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Autumn on 2019/6/9 0009.
 */
public class DetailFgt extends Fragment{
    private onSpeechListener mOnSpeechListener;
    private ImageView mImageView;
    private ListView trans_list;

    private Word word;


    /* 签到提示
       声明与layout相关的变量
     */
    private RelativeLayout rlGetGiftData;
    private TextView tvGetSunValue;
    private ImageView ivSun;
    private ImageView ivSunBg;

    private int todaycount;
    private int plancount;


    public static DetailFgt newInstance(Word word) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Const.WORD_KEY, word);
        DetailFgt detailFgt = new DetailFgt();
        detailFgt.setArguments(bundle);

        return detailFgt;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onSpeechListener) {
            mOnSpeechListener = (onSpeechListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_detail, container, false);

        /* 签到提示
           初始化与layout相关的变量
        */
        rlGetGiftData = (RelativeLayout) view.findViewById(R.id.rl_get_gift_view);  //弹出签到提示
        tvGetSunValue = (TextView) view.findViewById(R.id.tv_text_one);
        ivSun = (ImageView) view.findViewById(R.id.iv_sun);
        ivSunBg = (ImageView) view.findViewById(R.id.iv_sun_bg);


        TextView tvKey = (TextView) view.findViewById(R.id.tv_key);
//        TextView tvPhono = (TextView) view.findViewById(R.id.tv_phono);
        ListView trans_list = (ListView)view.findViewById(R.id.trans_list);
        if(trans_list != null){
            System.out.println("1 not null");
        }
        else {
            System.out.println("1 is null");
        }
        if(tvKey != null){
            System.out.println("key not null");
        }
        else {
            System.out.println("key is null");
        }

        QuickScroll quickScroll = (QuickScroll) view.findViewById(R.id.quickscroll);
        Bundle bundle = getArguments();
        word = bundle.getParcelable(Const.WORD_KEY);
        tvKey.setText(String.valueOf(word.getMkey()));
        System.out.println("The word is :"+word.getMkey());
//        tvPhono.setText(String.valueOf("[,æbə'reiʃən]"));
        List<Trans> translist = new ArrayList<>();
        translist = word.getMtranslist();
        if(translist != null || translist.size() != 0){
            System.out.println("2 not null, "+translist);
        }
        else {
            System.out.println("2 is null");
        }

        TransAdapter transAdapter = new TransAdapter(getActivity(), translist);
        trans_list.setAdapter(transAdapter);
        if(transAdapter != null ){
            System.out.println("3 not null, ");
        }
        else {
            System.out.println("3 is null");
        }


        if(isfinishplan()){
            signPrompt();
        }

//        quickScroll.init(QuickScroll.TYPE_POPUP_WITH_HANDLE, trans_list, transAdapter, QuickScroll.STYLE_HOLO);
//        quickScroll.setFixedSize(1);
//        quickScroll.setPopupColor(QuickScroll.BLUE_LIGHT, QuickScroll.BLUE_LIGHT_SEMITRANSPARENT, 1, Color.WHITE, 1);
//        quickScroll.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 42);
        return view;

    }

    private boolean isfinishplan(){


        return false;
    }

    public void setSpeakImg(int resId) {
        mImageView.setImageResource(resId);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnSpeechListener = null;
    }

    public interface onSpeechListener {
        void speech(Word word);
    }

    /**
     *  签到提示
     *  使用：调用 signPrompt()方法即可
     */
    //设置可见性，显示几秒后自动隐藏
    public void signPrompt() {
        showSignPrompt();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rlGetGiftData.setVisibility(View.GONE);
            }
        }, 400);
    }

    //显示签到提示：会自动被signPrompt()方法调用
    private void showSignPrompt(){

        rlGetGiftData.setVisibility(View.VISIBLE);
        String text = "恭喜你已完成今日计划";
        ivSun.setImageResource(R.drawable.i8live_sun);
        tvGetSunValue.setText(text);
        Animation operatingAnim = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.rotate_anim_online_gift);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        ivSunBg.startAnimation(operatingAnim);
    }



}
