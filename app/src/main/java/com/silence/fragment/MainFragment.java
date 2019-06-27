package com.silence.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import com.silence.activity.CountActivity;
import com.silence.activity.DetailActivity;
import com.silence.activity.PlanActivity;
import com.silence.activity.WordListActivity;
import com.silence.dao.CalendarDao;
import com.silence.dao.WordDao;
import com.silence.dao.WordListDao;
import com.silence.dao.WordUtils;
import com.silence.enums.RecordType;
import com.silence.utils.*;
import com.silence.word.R;
import org.json.JSONException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Autumn on 2019/6/22
 */
public class MainFragment extends Fragment {

    private View.OnClickListener listener;

    public static MainFragment newInstance() {
        Bundle arguments = new Bundle();
        MainFragment tabContentFragment = new MainFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        WordUtils wutils = new WordUtils();

        String wordStr = wutils.getWordSizeByLabel(Const.DIC_STUDIED, getContext()) + "";

        String wordCountStr = wordStr + "/3233";
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(wordCountStr);
        wordCountStr = m.replaceAll("");
        Spannable wordCountStrSet = new SpannableString(wordCountStr);

        wordCountStrSet.setSpan(new ForegroundColorSpan(Color.WHITE), 0, wordCountStrSet.length() ,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordCountStrSet.setSpan(new AbsoluteSizeSpan(40, true), 0, wordStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        wordCountStrSet.setSpan(new AbsoluteSizeSpan(18, true), wordStr.length() , wordCountStrSet.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SDUtil sdUtil = new SDUtil();
        View contentView = inflater.inflate(R.layout.main, null);
        TextView todayCount = (TextView) contentView.findViewById(R.id.today_count);
        try {
            todayCount.setText(sdUtil.readFromSD(RecordType.TODAY.getPath())+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        todayCount.setTextSize(100);

        WRUtil wrUtil = new WRUtil();
        wrUtil.writeFile(getContext(), "20", RecordType.DAY);
        try {
            sdUtil.saveFileToSD(RecordType.CALENDAR.getPath(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sdUtil.saveFileToSD(RecordType.CALENDAR.getPath(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        wrUtil.writeFile(getContext(), "2019-06-20", RecordType.CALENDAR);
        wrUtil.writeFile(getContext(), "2019-06-21", RecordType.CALENDAR);
        wrUtil.writeFile(getContext(), "2019-06-22", RecordType.CALENDAR);
        wrUtil.writeFile(getContext(), "2019-06-23", RecordType.CALENDAR);
        wrUtil.writeFile(getContext(), "2019-06-24", RecordType.CALENDAR);
        String dayStr = null;
        try {
            dayStr = sdUtil.readFromSD(RecordType.DAY.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dayCountStr = dayStr + "/180";

        m = p.matcher(dayCountStr);
        dayCountStr = m.replaceAll("");

        Spannable dayCountStrSet = new SpannableString(dayCountStr);
        dayCountStrSet.setSpan(new ForegroundColorSpan(Color.WHITE), 0, dayCountStrSet.length() - 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        dayCountStrSet.setSpan(new AbsoluteSizeSpan(40, true), 0, dayStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        dayCountStrSet.setSpan(new AbsoluteSizeSpan(18, true), dayStr.length(), dayCountStrSet.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        String plan = "预计" + DateUtil.getMinutes() + "分钟";
        TextView planCount = (TextView) contentView.findViewById(R.id.plan);
        planCount.setText(plan);

        TextView dayCount = (TextView) contentView.findViewById(R.id.day_count);
        dayCount.setText(dayCountStrSet);

        wrUtil = new WRUtil();
        wrUtil.writeFile(getContext(), "16", RecordType.TODAY);
        TextView wordCount = (TextView) contentView.findViewById(R.id.word_count);
        wordCount.setText(wordCountStrSet);

        Button changePlan = (Button) contentView.findViewById(R.id.plan_change);
        initListener();
        changePlan.setOnClickListener(listener);

        Button btn_xueci = (Button) contentView.findViewById(R.id.btnExitSys);
        initListener();
        btn_xueci.setOnClickListener(listener);


        sdUtil = new SDUtil(getContext());
        wrUtil = new WRUtil();

        System.out.println("-------------");
        initWordList();

        WordListDao wordListDao = new WordListDao();
        try {
            wordListDao.listJsonWords(getContext());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /**
         * 这个try先执行一次，然后注释掉
         */

        try {
            sdUtil.saveFileToSD(RecordType.CALENDAR.getPath(),"");
            sdUtil.saveFileToSD(RecordType.WORD.getPath(),"");
        } catch (Exception e) {
            System.out.println("ERROR1");
            e.printStackTrace();
        }
        sdUtil.initFile();
        wordListDao = new WordListDao();
        wordListDao.listWord();

        try {
            String res = sdUtil.readFromSD("calendar.txt");
            System.out.println(res);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("**********");
        CalendarDao calendarDao = new CalendarDao();
//        String res = calendarDao.listDay("2019", "05", getContext());
 //       System.out.println(res);
        sdUtil = new SDUtil(getContext());
        sdUtil.verifyStoragePermissions(getActivity());
        try {
            sdUtil.saveFileToSD("day.txt", "2");
            Thread.sleep(2000);
            String content = sdUtil.readFromSD("day.txt");
            System.out.println( "ccccccontent" + content);
        } catch (Exception ex) {
            ex.printStackTrace();
//
        }

       // wrUtil.writeFile(getContext(), wordStr.toString(), RecordType.WORD_LIST);

        return contentView;

    }

    /**
     * 监听器初始化
     */
    private void initListener() {
        listener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                switch (v.getId()) {
                    case R.id.plan_change:
                        intent.setClass(getActivity(), PlanActivity.class);
                        break;
                    case R.id.btnExitSys:
                        intent.setClass(getActivity(), DetailActivity.class);
                        intent.putExtra(Const.DIC_KEY, Const.DIC_UNFAMILIAR);
                        System.out.println("I click xueci!");
                        break;
                }
                startActivity(intent);
            }
        };
    }

    public List<String> initWordList() {
        Resources resources = this.getResources();
        InputStream is;
        List<String> wordList = new ArrayList<>();
        try {
            is = resources.openRawResource(R.raw.wordlist);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder wordStr = new StringBuilder();
            while ( (line = reader.readLine()) != null ) {
                // System.out.println(line);
//                wordList.add(line);
                wordStr.append(line + "\n");
            }
            System.out.println("---------");
//            System.out.println(wordList.size());


            WRUtil wrUtil = new WRUtil();

            SDUtil sdUtil = new SDUtil();
            String fileStr = sdUtil.readFromSD("wordlist.txt");
            if ( fileStr == null || fileStr.isEmpty()) {
                wrUtil.writeFile(getContext(), wordStr.toString(), RecordType.WORD_LIST);
                String[] wordArr = fileStr.split("\n");
                System.out.println("LEN: " + wordArr.length);
            } else {
                System.out.println("WORDLIST FILE ALREADY EXIST...........");
            }



          //  System.out.println(fileStr);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return wordList;
    }

//    public void refresh() {
//        onCreate(null);
//    }


//    @Override
//    protected void onRestart() {
//        super.o
//    }
//


}
