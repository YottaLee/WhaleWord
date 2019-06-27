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
import java.util.Arrays;
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
        return mTransList != null ? mTransList.size() : 0;
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
        ViewHolder viewHolder;
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
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Trans trans = mTransList.get(position);
        String kaofa = trans.getKaofa();

        if(kaofa != "null"){
            List<String> kaofalist = Arrays.asList(kaofa.split("："));
            String res_kaofa = "";
            if(kaofalist != null && kaofalist.size() != 0 ){
                if(kaofalist.size() == 1){
                    res_kaofa = kaofalist.get(0);
                }
                else {
                    for(int i = 0; i< kaofalist.size()-1; i++){
                        res_kaofa += (kaofalist.get(i)+"\r\n");
                    }
                    res_kaofa += kaofalist.get(kaofalist.size()-1);
                }
                viewHolder.trans_kaofa.setText(res_kaofa);
            }
        }
        else {
            viewHolder.trans_kaofa.setHeight(0);
        }



        List<String>  eList =new ArrayList<>();
        eList = trans.getExampleSentences();
        String res = "";
        if(eList != null && eList.size() != 0){

            if(eList.size() == 1){
                res = eList.get(0);
            }
            else {
                for(int i = 0; i< eList.size()-1; i++){
                    res += (eList.get(i)+"\r\n");
                }
                res += eList.get(eList.size()-1);
            }
            res = "例："+res;
            viewHolder.trans_example.setText(res);
        }
        else {
            viewHolder.trans_example.setHeight(0);
        }

        if(trans.getSynonym()!= null && !trans.getSynonym().isEmpty() && trans.getSynonym().length() !=0 && trans.getSynonym()!= "null" &&trans.getSynonym()!= "null"&& trans.getSynonym()!= ""){
            viewHolder.trans_synonym.setText("近："+trans.getSynonym());
        }
        else{
            viewHolder.trans_synonym.setHeight(0);
        }
        if(trans.getAntonym()!= null && !trans.getAntonym().isEmpty() && trans.getAntonym().length() != 0&& trans.getAntonym()!= "null" && trans.getAntonym()!= ""){
            viewHolder.trans_antonym.setText("反："+trans.getAntonym());
        }
        else{
            viewHolder.trans_antonym.setHeight(0);
        }
        if(trans.getDerivative()!= null && !trans.getDerivative().isEmpty()&& trans.getDerivative().length() != 0 && trans.getDerivative()!= "null" && trans.getDerivative()!= ""){
            viewHolder.trans_derivative.setText("派："+trans.getDerivative());
//            System.out.println(3+"????"+trans.getDerivative());
        }
        else{
            viewHolder.trans_derivative.setHeight(1);
//            System.out.println(3+"!!!!!");
        }
        return convertView;
    }

    @Override
    public String getIndicatorForPosition(int childposition, int groupposition) {
        return String.valueOf(mTransList.get(childposition).getKaofa().charAt(0));
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
