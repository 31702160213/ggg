package com.example.administrator.shixun.db_util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.administrator.shixun.db_page.Infor;

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(@Nullable Context context) {
        super(context, Infor.DATABASE_NAME, null, Infor.VERSION);
    }
    /** 
    * @Description: 创建数据库表
    * @Param:  
    * @return:  
    * @Author: Mr.Yang 
    * @Date: 2019/1/6 
    */ 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Infor.CREATE_TABLE_USER);
        db.execSQL(Infor.CREATE_TABLE_CHAT);
        db.execSQL(Infor.CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
