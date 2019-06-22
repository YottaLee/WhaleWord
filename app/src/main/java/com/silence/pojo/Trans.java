package com.silence.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Trans implements Parcelable {

    private String kaofa;
    private List<String> exampleSentences;
    private String synonym;
    private String antonym;
    private String derivative;

    public Trans(String kaofa, List<String> exampleSentences, String synonym, String antonym, String derivative) {
        this.kaofa = kaofa;
        this.exampleSentences = exampleSentences;
        this.synonym = synonym;
        this.antonym = antonym;
        this.derivative = derivative;
    }


    public String getKaofa() {
        return kaofa;
    }

    public void setKaofa(String kaofa) {
        this.kaofa = kaofa;
    }

    public List<String> getExampleSentences() {
        return exampleSentences;
    }

    public void setExampleSentences(List<String> exampleSentences) {
        this.exampleSentences = exampleSentences;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getAntonym() {
        return antonym;
    }

    public void setAntonym(String antonym) {
        this.antonym = antonym;
    }

    public String getDerivative() {
        return derivative;
    }

    public void setDerivative(String derivative) {
        this.derivative = derivative;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.kaofa);
        parcel.writeList(this.exampleSentences);
        parcel.writeString(this.synonym);
        parcel.writeString(this.antonym);
        parcel.writeString(this.derivative);
    }

    protected Trans(Parcel in) {
        this.kaofa = in.readString();
        this.exampleSentences = new ArrayList<String>();
        in.readList(this.exampleSentences, String.class.getClassLoader());
        this.synonym = in.readString();
        this.antonym = in.readString();
        this.derivative = in.readString();
    }

    public static final Creator<Trans> CREATOR = new Creator<Trans>() {
        public Trans createFromParcel(Parcel source) {
            return new Trans(source);
        }

        public Trans[] newArray(int size) {
            return new Trans[size];
        }
    };
}
