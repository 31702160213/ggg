package com.example.administrator.shixun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import com.example.administrator.shixun.Adapter.ChatAdapter;
import com.example.administrator.shixun.R;
import com.example.administrator.shixun.bean.MainBean;
import com.example.administrator.shixun.business.MainBse;
import com.example.administrator.shixun.business.RegisterBes;
import com.example.administrator.shixun.dao.MainDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends AppCompatActivity {
    private EditText et_content;
    private ListView listView;
    private String key;
    private String name;
    private MainDao mainDao;
    /** 
    * @Description: 界面数据同步 
    * @Param:  
    * @return:  
    * @Author: Mr.Yang 
    * @Date: 2019/1/6 
    */ 
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            List<MainBean> list= getData((List<MainBean>) msg.obj);
            switch (msg.what) {
                case 100:
//                    SimpleAdapter simpleAdapter=new SimpleAdapter(getApplicationContext(),
//                            getData(list),
//                            R.layout.wechat_list_item,
//                            new String[]{"name","chat","time"},
//                            new int[]{R.id.list_name,R.id.list_content,R.id.time});
//                    listView.setAdapter(simpleAdapter);
                    ChatAdapter chatAdapter=new ChatAdapter(getApplicationContext(),list,name);
                    System.out.println(name);
                    listView.setAdapter(chatAdapter);
                    et_content.setText("");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化控件
        init();//初始化数据
        //发送点击事件
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();//发送消息并同步到界面
            }
        });
        findViewById(R.id.iv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passulist();
            }
        });

    }
    /**
    * @Description:  初始化控件
    * @Param:
    * @return:
    * @Author: Mr.Yang
    * @Date: 2019/1/6
    */
    void initView(){
        et_content=findViewById(R.id.et_text);
        listView=findViewById(R.id.list);
        Intent intent=getIntent();
        key=intent.getStringExtra("key");
        name=intent.getStringExtra("name");
    }
    /**
    * @Description: 初始化界面数据,从数据库里获取
    * @Param:
    * @return:
    * @Author: Mr.Yang
    * @Date: 2019/1/6
    */
    void init(){
        mainDao=new MainDao(getApplicationContext());
        new Thread(){
            @Override
            public void run() {
                if (mainDao.find()!=null){
                    List<MainBean> daoList= mainDao.find();
                    Message message=new Message();
                    message.what=100;
                    message.obj=daoList;
                    handler.sendMessage(message);
                }
            }
        }.start();
    }
    /**
    * @Description: 发送信息，同时接收信息
    * @Param:
    * @return:
    * @Author: Mr.Yang
    * @Date: 2019/1/6
    */
    void send(){
        new Thread(){
            @Override
            public void run() {
                String content=et_content.getText().toString().trim();
                MainBse mainBse=new MainBse();
                List<MainBean> list=mainBse.send(key,content);
                Message message=new Message();
                message.what=100;
                message.obj=list;
                handler.sendMessage(message);
                setdb(list);//当前数据存入数据库
            }
        }.start();
    }
    /**
    * @Description: 把当前最新聊天记录存进数据库，同时删掉旧数据
    * @Param:
    * @return:
    * @Author: Mr.Yang
    * @Date: 2019/1/6
    */
    void setdb(List<MainBean> list){
        if(mainDao.find()!=null){
            mainDao.delete();
        }
        mainDao.insert(list);
    }
    /**
    * @Description: 进入用户列表
    * @Param:
    * @return:
    * @Author: Mr.Yang
    * @Date: 2019/1/6
    */
    private void passulist() {
        Intent intent=new Intent(getApplicationContext(),User_List.class);
        startActivityForResult(intent,1);
    }
    /**
    * @Description: 获取数据，用于Sim适配器调用
    * @Param:
    * @return:
    * @Author: Mr.Yang
    * @Date: 2019/1/6
    */
//    List<Map<String,String>> getData(List<MainBean> list){
//        List<Map<String,String>> list_map=new ArrayList<Map<String,String>>();
//        Map<String,String> map;
//        for (int i=list.size()-1;i>=0;i--){
//            map=new HashMap<>();
//            map.put("name",list.get(i).getName());
//            map.put("chat",list.get(i).getChat());
//            map.put("time",list.get(i).getTime());
//            list_map.add(map);
//        }
//        return list_map;
//    }
    List<MainBean> getData(List<MainBean> list){
        List<MainBean> list_=new ArrayList<MainBean>();
        for (int i=0;i<list.size();i++){
            list_.add(list.get(list.size()-i-1));
        }
        return list_;
    }
}
