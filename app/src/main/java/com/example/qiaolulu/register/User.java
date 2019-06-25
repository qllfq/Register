package com.example.qiaolulu.register;

import org.litepal.crud.DataSupport;

import java.util.UUID;

public class User extends DataSupport {
    private String userName;
    private String userPwd;
    private String userId;
    public int pwdresetFlag=0;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }



    public int getPwdresetFlag() {
        return pwdresetFlag;
    }

    public void setPwdresetFlag(int pwdresetFlag) {
        this.pwdresetFlag = pwdresetFlag;
    }

    public User(){
        super();
        userId = UUID.randomUUID().toString();
    }

}

