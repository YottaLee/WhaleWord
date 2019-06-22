package com.silence.dao;

import android.content.Context;
import com.silence.enums.Label;
import com.silence.pojo.Word;
import com.silence.utils.Const;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Autumn on 2019/6/22
 */
public class WordUtils {

    public List<Word> getWordByLabel(String value, Context context) {
        try {
            List<Word> allWords = new Utils().getJsonWords(context);
            Label label = Label.Studied;
            switch (value) {
                case Const.DIC_ALL:
                    return allWords;
                case Const.DIC_STUDIED:
                    label = Label.Studied;
                    break;
                case Const.DIC_HANDLED:
                    label = Label.Handled;
                    break;
                case Const.DIC_UNFAMILIAR:
                    label = Label.Unfamiliar;
                    break;
            }
            List<Word> filterWords = new ArrayList<>();
            for (Word word : allWords)
                for (Label wordLabel : word.getLabels())
                    if (wordLabel.equals(label)) {
                        filterWords.add(word);
                        break;
                    }
            return filterWords;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
