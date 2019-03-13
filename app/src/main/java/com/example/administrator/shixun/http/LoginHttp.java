package com.example.administrator.shixun.http;

import com.example.administrator.shixun.config.Link;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @program: shixun
 * @description: 登陆页Http请求
 * @author: Mr.Yang
 * @create: 2019-01-05 15:57
 **/
public class LoginHttp {
    OkHttpClient client=null;
    /**
    * @Description: POST请求
    * @Param:
    * @return: true/false
    * @Author: Mr.Yang
    * @Date: 2019/1/5
    */
    public String post(String username,String password) throws IOException {
        client = new OkHttpClient();
        FormBody formBody=new FormBody.Builder()
                .add("user",username)
                .add("password",password)
                .build();
        final Request request = new Request.Builder().post(formBody).url(Link.login).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            return null;
        }
    }
}
