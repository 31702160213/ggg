package com.example.administrator.shixun.db_util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.shixun.db_util.MyHelper;

public class Util {
    private static MyHelper mHelper=null;
    /** 
    * @Description: 打开数据库写
    * @Param:  
    * @return:  
    * @Author: Mr.Yang 
    * @Date: 2019/1/6 
    */ 
    public static SQLiteDatabase getWriteDb(Context context){
        if (mHelper==null){
            mHelper=new MyHelper(context);
        }
        SQLiteDatabase db=mHelper.getWritableDatabase();
        return db;
    }
    /** 
    * @Description: 打开数据库读 
    * @Param:  
    * @return:  
    * @Author: Mr.Yang 
    * @Date: 2019/1/6 
    */ 
    public static SQLiteDatabase getReadDb(Context context){
        if (mHelper==null){
            mHelper=new MyHelper(context);
        }
        SQLiteDatabase db=mHelper.getReadableDatabase();
        return db;
    }
}
