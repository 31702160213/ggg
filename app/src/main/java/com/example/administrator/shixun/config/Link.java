package com.example.administrator.shixun.config;

/**
 * @program: shixun
 * @description: API链接库
 * @author: Mr.Yang
 * @create: 2019-01-05 16:12
 **/
public class Link {
    /** 
    * @Description: 登陆接口 
    * @Param:  user//用户名
     * @Param:  password//用户名
    * @return:  JSON
    * @Author: Mr.Yang 
    * @Date: 2019/1/6 
    */ 
    public static final String login="http://123.207.85.214/chat/login.php";
    /** 
    * @Description: 注册接口 
    * @Param:  name//用户名
     * @Param:  user//账号
     * @Param:  password//密码
    * @return:  JSON
    * @Author: Mr.Yang 
    * @Date: 2019/1/6 
    */ 
    public static final String register="http://123.207.85.214/chat/register.php";
    /** 
    * @Description: 聊天接口 
    * @Param:  user//密匙
     * @Param:  chat//聊天信息
    * @return:  JSON
    * @Author: Mr.Yang 
    * @Date: 2019/1/6 
    */ 
    public static final String chat="http://123.207.85.214/chat/chat1.php";
    /** 
    * @Description: 用户列表接口
    * @Param:  null
    * @return:  JSON
    * @Author: Mr.Yang 
    * @Date: 2019/1/6 
    */ 
    public static final String member="http://123.207.85.214/chat/member.php";
}
