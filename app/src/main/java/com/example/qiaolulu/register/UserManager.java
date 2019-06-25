package com.example.qiaolulu.register;



import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class UserManager {

    //插入用户数据
    public boolean insertUser(String name,String password){
        User user = new User();
        user.setUserName(name);
        user.setUserPwd(password);
        //创建数据库
        LitePal.getDatabase();
        return user.save();


    }


    //通过用户名查找用户是否存在
    public boolean findUserByName(String name){

        List<User> users = DataSupport.findAll(User.class);
        for(User person: users){
            if(name.trim().equals(person.getUserName())){
                return false;
            }
        }
        return true;
    }



}




