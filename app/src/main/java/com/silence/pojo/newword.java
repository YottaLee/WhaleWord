package com.silence.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import com.silence.enums.Label;

import java.util.ArrayList;
import java.util.List;

public class newword implements Parcelable {

    private int mid;
    private String mkey;
    private List<Trans> mtranslist;
    private List<Label> labels;

    public newword(int mid, String mkey, List<Trans> mtranslist) {
        this.mid = mid;
        this.mkey = mkey;
        this.mtranslist = mtranslist;
        labels = new ArrayList<>();
    }

    /**
     * 掌握该单词
     */
    public void handle(){
        if(!labels.contains(Label.Studied))
            labels.add(Label.Studied);
        if(!labels.contains(Label.Handled)) {
            if(labels.contains(Label.Unfamiliar))
                labels.remove(Label.Unfamiliar);
            labels.add(Label.Handled);
        }
    }

    /**
     * 忘记该单词
     */
    public void forget(){
        if(!labels.contains(Label.Studied))
            labels.add(Label.Studied);
        if(!labels.contains(Label.Unfamiliar)) {
            if(labels.contains(Label.Handled))
                labels.remove(Label.Handled);
            labels.add(Label.Unfamiliar);
        }
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

    public List<Trans> getMtranslist() {
        return mtranslist;
    }

    public void setMtranslist(List<Trans> mtranslist) {
        this.mtranslist = mtranslist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected newword(Parcel in) {
        this.mid = in.readInt();
        this.mkey = in.readString();
        this.mtranslist = new ArrayList<Trans>();
        in.readList(mtranslist, Trans.class.getClassLoader());
//        this.mtranslist = in.readList(mtranslist, Trans.CREATOR );

    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(this.mid);
        parcel.writeString(this.mkey);
        parcel.writeList(this.mtranslist);
//        parcel.writeList(this.mtranslist);
    }

    public static final Creator<newword> CREATOR = new Creator<newword>() {

        public newword createFromParcel(Parcel source) {
            return new newword(source);
        }

        public newword[] newArray(int size) {
            return new newword[size];
        }
    };
}
