package com.silence.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.silence.pojo.WordLast;
import com.silence.utils.DBOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Autumn on 2019/6/8 0008.
 */
public class WordDao {
    private DBOpenHelper mDBOpenHelper;

    //Word_Id, Word_Key, Word_Phono, Word_Trans, Word_Example, Word_Unit;
    public WordDao(Context context) {
        mDBOpenHelper = DBOpenHelper.getInstance(context);
    }

    public List<WordLast> getWords(String metaKey, int unitKey) {
        List<WordLast> wordLasts = null;
        String sql = "select Word_Id, Word_Key, Word_Phono, Word_Trans, Word_Example from " + metaKey + " where Word_Unit=?";
        SQLiteDatabase db = mDBOpenHelper.getDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(unitKey)});
        if (cursor.moveToFirst()) {
            wordLasts = new ArrayList<>(cursor.getCount());
            WordLast wordLast;
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Word_Id"));
                String key = cursor.getString(cursor.getColumnIndex("Word_Key"));
                String phono = cursor.getString(cursor.getColumnIndex("Word_Phono"));
                String trans = cursor.getString(cursor.getColumnIndex("Word_Trans"));
                String exam = cursor.getString(cursor.getColumnIndex("Word_Example"));
                wordLast = new WordLast(id, key, phono, trans, exam, unitKey);
                wordLasts.add(wordLast);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return wordLasts;
    }

    public ArrayList<WordLast> queryWords(String metaKey, String wordKey) {
        ArrayList<WordLast> wordLasts = null;
        String sql = "select Word_Id, Word_Key, Word_Phono, Word_Trans, Word_Example, Word_Unit from "
                + metaKey + " where Word_Key like ?;";
        SQLiteDatabase db = mDBOpenHelper.getDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{wordKey + "%"});
        if (cursor.moveToFirst()) {
            wordLasts = new ArrayList<>(cursor.getCount());
            WordLast wordLast;
            do {
                int id = cursor.getInt(cursor.getColumnIndex("Word_Id"));
                String key = cursor.getString(cursor.getColumnIndex("Word_Key"));
                String phono = cursor.getString(cursor.getColumnIndex("Word_Phono"));
                String trans = cursor.getString(cursor.getColumnIndex("Word_Trans"));
                String exam = cursor.getString(cursor.getColumnIndex("Word_Example"));
                int unitKey = cursor.getInt(cursor.getColumnIndex("Word_Unit"));
                wordLast = new WordLast(id, key, phono, trans, exam, unitKey);
                wordLasts.add(wordLast);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return wordLasts;
    }

}
