package com.silence.dao;

import android.content.Context;
import com.silence.enums.RecordType;
import com.silence.pojo.Trans;
import com.silence.pojo.Word;
import com.silence.utils.GsonUtil;
import com.silence.utils.SDUtil;
import com.silence.utils.WRUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: py
 * @Date: 2019-06-25 15:34
 * @Version 1.0
 */
public class WordListDao {
    SDUtil sdUtil = new SDUtil();

    public List<String> listWord() {
        List<String> wordList = new ArrayList<>();
        try {
            String wordStr = sdUtil.readFromSD(RecordType.WORD_LIST.getPath());
            String[] sigWordArr = wordStr.split("\n");

            Collections.addAll(wordList, sigWordArr);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    public  List<Word> listJsonWords(Context context) throws UnsupportedEncodingException, JSONException {
        List<String> JsonContext = listWord();
        System.out.println(JsonContext.get(0));

        List<Word> wordList = new ArrayList<>();
        for(int j = 0; j<JsonContext.size(); j++){
            String s = JsonContext.get(j);
//            System.out.println("string:"+s);
            JSONObject dataJson = new JSONObject(s);
            JSONArray meaningList = dataJson.getJSONArray("trans");
            String wordtext = dataJson.getString("word");

            List<Trans> transList = new ArrayList<>();

            if (meaningList!=null || meaningList.length() != 0) {
                for (int i = 0; i < meaningList.length(); i++) {
                    JSONObject jsonObject = meaningList.getJSONObject(i);
                    if (jsonObject != null) {
                        String kaofa = jsonObject.optString("\u8003\u6cd5");
//                        String exampleSentences = jsonObject.optString("\u4f8b");

                        JSONArray exampleList = jsonObject.optJSONArray("\u4f8b");
//                        System.out.println("List:"+exampleList.getString(0));
//                        System.out.println("length: "+exampleList.length());
                        List<String> eList = new ArrayList<>();
                        if(exampleList != null && exampleList.length() !=0){
                            for(int k =0; k<exampleList.length(); k++){
//                                System.out.println("k: "+k);
                                String e  =(String) exampleList.getString(k);
//                                System.out.println("s: "+e);
                                if(e != null) {
                                    boolean b = eList.add(e);
                                }
                            }
                        }
                        String synonym = jsonObject.optString("\u8fd1");
                        String antonym = jsonObject.optString("\u53cd");
                        String derivative = jsonObject.optString("\u6d3e");

                        Trans trans = new Trans(kaofa,eList, synonym, antonym, derivative);
                        transList.add(trans);
                    }
                }
            }

            Word word = new Word(j, wordtext, transList);
            wordList.add(word);
        }
        return wordList;
    }

    public void updateWordList(Word word, Context context) {
        try {
            String resStr = "";
            List<Word> wordList = listJsonWords(context);
            List<Word> resList = new ArrayList<>();
            for(Word tmp: wordList) {
                if (tmp.getMkey().equals(word.getMkey())) {
                    resList.add(word);
                } else {
                    resList.add(tmp);
                }
            }

            WRUtil wrUtil = new WRUtil();
            wrUtil.writeFile(context, resStr, RecordType.WORD_LIST);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
