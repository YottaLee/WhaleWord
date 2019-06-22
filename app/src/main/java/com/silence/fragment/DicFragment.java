package com.silence.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.silence.activity.WordListActivity;
import com.silence.utils.Const;
import com.silence.word.R;

/**
 * Created by Autumn on 2019/6/21
 */
public class DicFragment extends Fragment {

    private long exitTime = 0;
    private View.OnClickListener listener;

    public static DicFragment newInstance(){
        Bundle arguments = new Bundle();
        DicFragment tabContentFragment = new DicFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.activity_dic, null);
        LinearLayout lv_all = (LinearLayout) contentView.findViewById(R.id.lv_all);
        LinearLayout lv_studied = (LinearLayout) contentView.findViewById(R.id.lv_studied);
        LinearLayout lv_unfamiliar = (LinearLayout) contentView.findViewById(R.id.lv_unfamiliar);
        LinearLayout lv_handled = (LinearLayout) contentView.findViewById(R.id.lv_handled);
        listener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                switch (v.getId()) {
                    case R.id.lv_all:
                        intent.setClass(getActivity(), WordListActivity.class);
                        intent.putExtra(Const.DIC_KEY, Const.DIC_ALL);
                        break;
                    case R.id.lv_studied:
                        intent.setClass(getActivity(), WordListActivity.class);
                        intent.putExtra(Const.DIC_KEY, Const.DIC_STUDIED);
                        break;
                    case R.id.lv_unfamiliar:
                        intent.setClass(getActivity(), WordListActivity.class);
                        intent.putExtra(Const.DIC_KEY, Const.DIC_UNFAMILIAR);
                        break;
                    case R.id.lv_handled:
                        intent.setClass(getActivity(), WordListActivity.class);
                        intent.putExtra(Const.DIC_KEY, Const.DIC_HANDLED);
                        break;
                }
                startActivity(intent);
            }
        };
        lv_all.setOnClickListener(listener);
        lv_studied.setOnClickListener(listener);
        lv_handled.setOnClickListener(listener);
        lv_unfamiliar.setOnClickListener(listener);
        return contentView;
    }
}
