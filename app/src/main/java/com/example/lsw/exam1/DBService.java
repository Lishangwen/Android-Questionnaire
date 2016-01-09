package com.example.lsw.exam1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsw on 2016/1/4.
 */
public class DBService {
    private SQLiteDatabase db;

    public DBService()
    {
        db=SQLiteDatabase.openDatabase("/data/data/com.example.lsw.exam1/databases/tinnitus_question.db",null,SQLiteDatabase.OPEN_READWRITE);
    }

    public List<Question> getQuestions()
    {
        List<Question> list=new ArrayList<Question>();
        Cursor cursor=db.rawQuery("select * from teq_question",null);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            int count=cursor.getCount();
            for(int i = 0;i<count;i++)
            {
                cursor.moveToPosition(i);
                Question question=new Question();
                question.question=cursor.getString(cursor.getColumnIndex("question"));
                question.answerA=cursor.getString(cursor.getColumnIndex("answerA"));
                question.answerB=cursor.getString(cursor.getColumnIndex("answerB"));
                question.answerC=cursor.getString(cursor.getColumnIndex("answerC"));
                question.answerD=cursor.getString(cursor.getColumnIndex("answerD"));
                question.ID=cursor.getInt(cursor.getColumnIndex("ID"));
                question.select_answer=-1;
                list.add(question);
            }
        }
        return list;
    }

}
