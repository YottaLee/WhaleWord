package com.silence.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import com.silence.activity.CountActivity;
import com.silence.utils.FileUtils;
import com.silence.word.R;

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
        View contentView = inflater.inflate(R.layout.main, null);
        TextView todayCount = (TextView) contentView.findViewById(R.id.today_count);
        todayCount.setText(FileUtils.readFile(getActivity(), "today"));
        todayCount.setTextSize(100);

        TextView dayCount = (TextView) contentView.findViewById(R.id.day_count);
        dayCount.setText(FileUtils.readFile(getActivity(), "day"));
        dayCount.setTextSize(20);

        TextView wordCount = (TextView) contentView.findViewById(R.id.word_count);
        wordCount.setText(FileUtils.readFile(getActivity(), "word"));
        wordCount.setTextSize(20);
        return contentView;
    }
}
