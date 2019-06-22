package com.silence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andraskindler.quickscroll.Scrollable;
import com.silence.pojo.Trans;
import com.silence.pojo.Unit;
import com.silence.utils.DateUtils;
import com.silence.word.R;

import java.util.ArrayList;
import java.util.List;

public class TransAdapter extends BaseAdapter implements Scrollable {

    private Context mContext;
    private List<Trans> mTransList;

    public TransAdapter(Context mContext, List<Trans> mTransList) {
        this.mContext = mContext;
        this.mTransList = mTransList;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mTransList != null ? mTransList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TransAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_trans, parent, false);
            viewHolder = new TransAdapter.ViewHolder();
            viewHolder.trans_kaofa = (TextView) convertView.findViewById(R.id.trans_kaofa);
            viewHolder.trans_example = (TextView) convertView.findViewById(R.id.trans_example);
            viewHolder.trans_synonym = (TextView) convertView.findViewById(R.id.trans_synonym);
            viewHolder.trans_antonym = (TextView) convertView.findViewById(R.id.trans_antonym);
            viewHolder.trans_derivative = (TextView) convertView.findViewById(R.id.trans_derivative);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TransAdapter.ViewHolder) convertView.getTag();
        }
        Trans trans = mTransList.get(position);
        viewHolder.trans_kaofa.setText("考法："+String.valueOf(trans.getKaofa()));
        List<String>  eList =new ArrayList<>();
        eList = trans.getExampleSentences();
        String res = "";
        if(eList != null){
            for(String e: eList){
                res+= (e+"\r\n");
            }
        }
        viewHolder.trans_example.setText(res);
        viewHolder.trans_synonym.setText("近："+String.valueOf(trans.getSynonym()));
        viewHolder.trans_antonym.setText("反："+String.valueOf(trans.getAntonym()));
        viewHolder.trans_synonym.setText("派："+String.valueOf(trans.getDerivative()));
        return convertView;
    }

    @Override
    public String getIndicatorForPosition(int childposition, int groupposition) {
        return String.valueOf(mTransList.indexOf(childposition));
    }

    @Override
    public int getScrollPosition(int childposition, int groupposition) {
        return childposition;
    }


    static class ViewHolder {
        TextView trans_kaofa;
        TextView trans_example;
        TextView trans_synonym;
        TextView trans_antonym;
        TextView trans_derivative;
    }
}
