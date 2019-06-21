package com.silence.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.silence.activity.UnitListActivity;
import com.silence.activity.WordListActivity;
import com.silence.utils.Const;
import com.silence.word.R;

/**
 * Created by Autumn on 2019/6/21
 */
public class OriginalFragment extends Fragment {
    private long exitTime = 0;
    private View.OnClickListener listener;

    public static OriginalFragment newInstance() {
        Bundle arguments = new Bundle();
        OriginalFragment tabContentFragment = new OriginalFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.activity_main, null);
        ImageView iv_cet4 = (ImageView) contentView.findViewById(R.id.iv_cet4);
        ImageView iv_cet6 = (ImageView) contentView.findViewById(R.id.iv_cet6);
        ImageView iv_gre = (ImageView) contentView.findViewById(R.id.iv_gre);
        ImageView iv_ietsl = (ImageView) contentView.findViewById(R.id.iv_ietsl);
        ImageView iv_nmet = (ImageView) contentView.findViewById(R.id.iv_nmet);
        listener = new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                switch (v.getId()) {
                    case R.id.iv_nmet:
                        intent.setClass(getActivity(), UnitListActivity.class);
                        intent.putExtra(Const.META_KEY, Const.WORDS_NMET);
                        break;
                    case R.id.iv_cet4:
                        intent.setClass(getActivity(), UnitListActivity.class);
                        intent.putExtra(Const.META_KEY, Const.WORDS_CET4);
                        break;
                    case R.id.iv_cet6:
                        intent.setClass(getActivity(), UnitListActivity.class);
                        intent.putExtra(Const.META_KEY, Const.WORDS_CET6);
                        break;
                    case R.id.iv_ietsl:
                        intent.setClass(getActivity(), UnitListActivity.class);
                        intent.putExtra(Const.META_KEY, Const.WORDS_IETSL);
                        break;
                    case R.id.iv_gre:
                        intent.setClass(getActivity(), UnitListActivity.class);
                        intent.putExtra(Const.META_KEY, Const.WORDS_GRE);
                        break;
                }
                startActivity(intent);
            }
        };
        iv_nmet.setOnClickListener(listener);
        iv_ietsl.setOnClickListener(listener);
        iv_gre.setOnClickListener(listener);
        iv_cet4.setOnClickListener(listener);
        iv_cet6.setOnClickListener(listener);
        return contentView;
    }

}
