package com.silence.fragment;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import com.silence.activity.CountActivity;
import com.silence.dao.CalendarDao;
import com.silence.utils.FileUtils;
import com.silence.utils.SDUtil;
import com.silence.word.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Autumn on 2019/6/22
 */
public class MainFragment extends Fragment {

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

        String wordStr = FileUtils.readFile(getActivity(), "word");

        String wordCountStr = wordStr + "/3233";
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(wordCountStr);
        wordCountStr = m.replaceAll("");
        Spannable wordCountStrSet = new SpannableString(wordCountStr);

        wordCountStrSet.setSpan(new ForegroundColorSpan(Color.WHITE), 0, wordCountStrSet.length() - 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordCountStrSet.setSpan(new AbsoluteSizeSpan(40, true), 0, wordStr.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        wordCountStrSet.setSpan(new AbsoluteSizeSpan(18, true), wordStr.length() - 1, wordCountStrSet.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        View contentView = inflater.inflate(R.layout.main, null);
        TextView todayCount = (TextView) contentView.findViewById(R.id.today_count);
        todayCount.setText(FileUtils.readFile(getActivity(), "today"));
        todayCount.setTextSize(100);

        String dayStr = FileUtils.readFile(getActivity(), "day");

        String dayCountStr = dayStr + "/180";

        m = p.matcher(dayCountStr);
        dayCountStr = m.replaceAll("");

        Spannable dayCountStrSet = new SpannableString(dayCountStr);
        dayCountStrSet.setSpan(new ForegroundColorSpan(Color.WHITE), 0, dayCountStrSet.length() - 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        dayCountStrSet.setSpan(new AbsoluteSizeSpan(40, true), 0, dayStr.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        dayCountStrSet.setSpan(new AbsoluteSizeSpan(18, true), dayStr.length() - 1, dayCountStrSet.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        TextView dayCount = (TextView) contentView.findViewById(R.id.day_count);
        dayCount.setText(dayCountStrSet);

        TextView wordCount = (TextView) contentView.findViewById(R.id.word_count);
        wordCount.setText(wordCountStrSet);

//        System.out.println("**********");
//        CalendarDao calendarDao = new CalendarDao();
//        String res = calendarDao.listDay("2019", "05", getContext());
//        System.out.println(res);
        SDUtil sdUtil = new SDUtil(getContext());
        sdUtil.verifyStoragePermissions(getActivity());
        try {
            sdUtil.savaFileToSD("day.txt", "2");
            Thread.sleep(2000);
            String content = sdUtil.readFromSD("day.txt");
            System.out.println( "ccccccontent" + content);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return contentView;
    }
}
