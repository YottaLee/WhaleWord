package com.silence.dao;

//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.stream.JsonReader;
import com.silence.pojo.Trans;
import com.silence.pojo.Word;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public List<String> ReadFile(String Path){
        BufferedReader reader = null;
        List<String> laststr = new ArrayList<>();
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
//                System.out.println("in");
                laststr.add(tempString);
//                System.out.println(tempString);
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

    public List<Word> getJsonWords() throws UnsupportedEncodingException, JSONException {
        List<String> JsonContext = new Utils().ReadFile("app\\src\\main\\res\\raw\\wordlist.json");
//        System.out.println(JsonContext.get(0));


        List<Word> wordList = null;
        for(int j = 0; j<JsonContext.size(); j++){
            String s = JsonContext.get(j);
            JSONObject dataJson = new JSONObject(s);
            JSONArray meaningList = dataJson.getJSONArray("trans");
            String wordtext = dataJson.getString("word");

            List<Trans> transList = null;

            if (meaningList!=null || meaningList.length() != 0) {
                for (int i = 0; i < meaningList.length(); i++) {
                    JSONObject jsonObject = meaningList.getJSONObject(i);
                    if (jsonObject != null) {
                        String kaofa = jsonObject.optString("\u8003\u6cd5");
//                        String exampleSentences = jsonObject.optString("\u4f8b");

                        JSONArray exampleList = jsonObject.optJSONArray("\u4f8b");
                        List<String> eList = null;
                        if(exampleList != null || exampleList.length() !=0){
                            for(int k =0; k<exampleList.length(); k++){
                                JSONObject exampleobject = exampleList.getJSONObject(k);
                                String e = exampleobject.toString();
                                eList.add(e);
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
