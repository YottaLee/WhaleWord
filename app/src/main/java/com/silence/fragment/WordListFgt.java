package com.silence.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andraskindler.quickscroll.QuickScroll;
import com.silence.adapter.WordAdapter;
import com.silence.dao.Utils;
import com.silence.dao.WordDao;
import com.silence.dao.WordUtils;
import com.silence.enums.Label;
import com.silence.pojo.Word;
import com.silence.pojo.WordLast;
import com.silence.utils.Const;
import com.silence.word.R;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Autumn on 2019/6/8 0008.
 */
public class WordListFgt extends Fragment implements AdapterView.OnItemClickListener {

    private onWordClickListener mOnWordClickListener;

    public static WordListFgt newInstance(String dicKey) {
        Bundle bundle = new Bundle();
        bundle.putString(Const.DIC_KEY, dicKey);
        WordListFgt wordListFgt = new WordListFgt();
        wordListFgt.setArguments(bundle);
        return wordListFgt;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onWordClickListener) {
            mOnWordClickListener = (onWordClickListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ListView listView = (ListView) view.findViewById(android.R.id.list);
        QuickScroll quickScroll = (QuickScroll) view.findViewById(R.id.quickscroll);
        WordDao wordDao = new WordDao(getActivity());
        Bundle bundle = getArguments();
        List<Word> wordList = new WordUtils().getWordByLabel(bundle.getString(Const.DIC_KEY), getActivity());//twordDao.getWords(bundle.getString(Const.META_KEY), bundle.getInt(Const.UNIT_KEY));
        WordAdapter wordAdapter = new WordAdapter(getActivity(), wordList);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(this);
        quickScroll.init(QuickScroll.TYPE_POPUP_WITH_HANDLE, listView, wordAdapter, QuickScroll.STYLE_HOLO);
        quickScroll.setFixedSize(1);
        quickScroll.setPopupColor(QuickScroll.BLUE_LIGHT, QuickScroll.BLUE_LIGHT_SEMITRANSPARENT, 1, Color.WHITE, 1);
        quickScroll.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 42);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnWordClickListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mOnWordClickListener != null) {
            mOnWordClickListener.getWordList(getArguments().getString(Const.META_KEY),
                    getArguments().getInt(Const.UNIT_KEY), position);
        }
    }

    public interface onWordClickListener {
        void getWordList(String metaKey, int unitKey, int wordKey);
    }
}
