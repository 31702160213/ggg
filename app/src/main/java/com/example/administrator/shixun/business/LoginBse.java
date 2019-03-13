package com.example.administrator.shixun.business;

import com.example.administrator.shixun.http.LoginHttp;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: shixun
 * @description: 登陆页业务
 * @author: Mr.Yang
 * @create: 2019-01-05 16:41
 **/
public class LoginBse {
    /**
    * @Description: 登陆
    * @Param:  username//账号
     * @Param:  password//密码
     * @return:
    * @Author: Mr.Yang
    * @Date: 2019/1/6
    */
    public Map<String,String> doLogin(String username,String password){
        try {
            LoginHttp loginHttp=new LoginHttp();
            String res=loginHttp.post(username,password);
            JSONObject jsonObject=new JSONObject(res);
            Map<String,String> map=new HashMap<String,String>();
            map.put("status",jsonObject.getString("status"));
            map.put("key",jsonObject.getString("user"));
            if ("登陆成功".equals(jsonObject.getString("status"))){
                map.put("name",jsonObject.getString("name"));
            }
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
