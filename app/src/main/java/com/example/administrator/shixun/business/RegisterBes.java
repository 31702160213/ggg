package com.example.administrator.shixun.business;

import com.example.administrator.shixun.http.LoginHttp;
import com.example.administrator.shixun.http.RegisterHttp;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @program: shixun
 * @description: 注册页面业务
 * @author: Mr.Yang
 * @create: 2019-01-05 19:16
 **/
public class RegisterBes {
    /**
    * @Description: 注册用户
    * @Param:  name//用户名
     * @Param:  username//账号
     * @Param:  password//密码
    * @return:
    * @Author: Mr.Yang
    * @Date: 2019/1/6
    */
    public boolean doRegister(String name,String username,String password){
        try {
            RegisterHttp registerBes=new RegisterHttp();
            String res=registerBes.post(name,username,password);
            JSONObject jsonObject=new JSONObject(res);
            String islog=jsonObject.getString("status");
            if ("注册成功".equals(islog)){
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
