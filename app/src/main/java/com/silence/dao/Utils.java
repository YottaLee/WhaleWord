package com.silence.dao;

//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.stream.JsonReader;
import android.content.Context;

import com.silence.pojo.Trans;
import com.silence.pojo.Word;
import com.silence.word.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> ReadFile(Context context){
        BufferedReader reader = null;
        List<String> laststr = new ArrayList<>();
        try{

            String tempString = null;
            InputStream inputStream ;
            inputStream = context.getResources().openRawResource(R.raw.wordlist);
            reader=new BufferedReader(new InputStreamReader(inputStream));
            while((tempString = reader.readLine()) != null){
                laststr.add(tempString);
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }

    public static List<Word> getJsonWords(Context context) throws UnsupportedEncodingException, JSONException {
        List<String> JsonContext = new Utils().ReadFile(context);
//        System.out.println(JsonContext.get(0));

        List<Word> wordList = new ArrayList<>();
        for(int j = 0; j<JsonContext.size(); j++){
            String s = JsonContext.get(j);
            System.out.println("string:"+s);
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
                                System.out.println("k: "+k);
                                String e  =(String) exampleList.getString(k);
                                System.out.println("s: "+e);
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


//    public static void main(String[] args) throws UnsupportedEncodingException, JSONException {
//        getJsonWords();
//    }


}
