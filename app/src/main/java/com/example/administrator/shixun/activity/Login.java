package com.example.administrator.shixun.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import com.example.administrator.shixun.R;
import com.example.administrator.shixun.bean.MainBean;
import com.example.administrator.shixun.bean.UserBean;
import com.example.administrator.shixun.business.LoginBse;
import com.example.administrator.shixun.dao.MainDao;
import com.example.administrator.shixun.dao.User_ListDao;
import com.example.administrator.shixun.db_util.Util;
import com.example.administrator.shixun.http.LoginHttp;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {
    private Button btn_login;
    private EditText username;
    private EditText password;
    private Button zhuce;
    /**
     * @Description: 提示
     * @Param:
     * @return:
     * @Author: Mr.Yang
     * @Date: 2019/1/6
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    Toast.makeText(getApplicationContext(), msg.obj.toString().trim(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passlogin();

            }
        });
        findViewById(R.id.zhuce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passzhuce();
            }
        });
    }

    /**
     * @Description: 初始化控件
     * @Param:
     * @return:
     * @Author: Mr.Yang
     * @Date: 2019/1/5
     */
    void init() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    /**
     * @Description: 登陆
     * @Param:
     * @return:
     * @Author: Mr.Yang
     * @Date: 2019/1/5
     */
    public void passlogin() {
        new Thread() {
            @Override
            public void run() {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                LoginBse loginBse = new LoginBse();
                Map<String,String> map=loginBse.doLogin(user, pass);
                if ("登陆成功".equals(map.get("status"))) {
                    Message msg=new Message();
                    msg.what=100;
                    msg.obj="登陆成功";
                    handler.sendMessage(msg);
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    intent.putExtra("key",map.get("key"));
                    intent.putExtra("name",map.get("name"));
                    startActivity(intent);
                    finish();
                } else {
                    Message msg=new Message();
                    msg.what=100;
                    msg.obj="登陆失败";
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    /**
     * @Description: 注册
     * @Param:
     * @return:
     * @Author: Mr.Yang
     * @Date: 2019/1/5
     */
    public void passzhuce() {
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }
}
