package com.silence.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Autumn on 2019/6/8 0008.
 */
public class WordLast implements Parcelable {
    //Word_Id, Word_Key, Word_Phono, Word_Trans, Word_Example, Word_Unit;
    private int mId;
    private String mKey;
    private String mPhono;
    private String mTrans;
    private String mExample;
    private int mUnit;

    public WordLast() {
    }

    public WordLast(int id, String key, String phono, String trans, String example, int unit) {
        mId = id;
        mKey = key;
        mPhono = phono;
        mTrans = trans;
        mExample = example;
        mUnit = unit;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getPhono() {
        return mPhono;
    }

    public void setPhono(String phono) {
        mPhono = phono;
    }

    public String getTrans() {
        return mTrans;
    }

    public void setTrans(String trans) {
        mTrans = trans;
    }

    public String getExample() {
        return mExample;
    }

    public void setExample(String example) {
        mExample = example;
    }

    public int getUnit() {
        return mUnit;
    }

    public void setUnit(int unit) {
        mUnit = unit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mKey);
        dest.writeString(this.mPhono);
        dest.writeString(this.mTrans);
        dest.writeString(this.mExample);
        dest.writeInt(this.mUnit);
    }

    protected WordLast(Parcel in) {
        this.mId = in.readInt();
        this.mKey = in.readString();
        this.mPhono = in.readString();
        this.mTrans = in.readString();
        this.mExample = in.readString();
        this.mUnit = in.readInt();
    }

    public static final Parcelable.Creator<WordLast> CREATOR = new Parcelable.Creator<WordLast>() {
        public WordLast createFromParcel(Parcel source) {
            return new WordLast(source);
        }

        public WordLast[] newArray(int size) {
            return new WordLast[size];
        }
    };
}
