package com.silence.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.silence.enums.Label;

import java.util.ArrayList;
import java.util.List;

public class Word implements Parcelable {

    private int mid;

    @SerializedName("word")
    private String mkey;


    @SerializedName("trans")
    private List<Trans> mtranslist;


    @SerializedName("label")
    private List<Label> labels;

    public Word(int mid, String mkey, List<Trans> mtranslist) {
        this.mid = mid;
        this.mkey = mkey;
        this.mtranslist = mtranslist;
        labels = new ArrayList<>();
    }

    public Word(int mid, String mkey, List<Trans> mtranslist, List<Label> labels) {
        this.mid = mid;
        this.mkey = mkey;
        this.mtranslist = mtranslist;
        this.labels = labels;
    }

    public List<Label> getLabels() {
        return labels;
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

    protected Word(Parcel in) {
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

    public static final Creator<Word> CREATOR = new Creator<Word>() {

        public Word createFromParcel(Parcel source) {
            return new Word(source);
        }

        public Word[] newArray(int size) {
            return new Word[size];
        }
    };
}
