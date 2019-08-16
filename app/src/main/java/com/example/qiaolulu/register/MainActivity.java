package com.example.qiaolulu.register;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;
/**
 *我修改了
 * @author:qiaolulu
 * @function:注册
 * @date:2019/06/25
 * @qq:2353695310
 * */

//输入用户名密码进行注册，将用户信息存入数据库
public class MainActivity extends AppCompatActivity {
    private EditText mAccount;
    private EditText mPwd;
    private EditText mPwdCheck;
    private Button mSureButton;
    UserManager userManager = new UserManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        initView();

    }

    public void initView() {
        mAccount = findViewById(R.id.resetpwd_edit_name);
        mPwd = findViewById(R.id.resetpwd_edit_pwd_old);
        mPwdCheck = findViewById(R.id.resetpwd_edit_pwd_new);
        mSureButton = findViewById(R.id.register_btn_sure);
        mSureButton.setOnClickListener(new Click());
    }


    private class Click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.register_btn_sure:
                    register_check();
                    break;
            }
        }
    }

    //对注册信息进行验证，用户名是否重复，两次密码输入是否一致
    private void register_check() {
        if(isUserNameAndPwdValid()){
            boolean flag;
            String name = mAccount.getText().toString().trim();
            String password = mPwd.getText().toString().trim();
            String rePassword = mPwdCheck.getText().toString().trim();
            flag = userManager.findUserByName(name);

            if(!flag){
                Log.d("tag","注册失败");
                Toast.makeText(this, "用户名已经存在",Toast.LENGTH_SHORT).show();
                return ;
            }
            if(password.equals(rePassword)==false){
                Toast.makeText(this, "输入的密码不同",Toast.LENGTH_SHORT).show();
                return ;

            }else {
                flag=userManager.insertUser(name,password);
                if(!flag){
                    Toast.makeText(this,"注册失败",Toast.LENGTH_LONG).show();
                    return ;
                }
                Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
                List<User> people = DataSupport.findAll(User.class);
                for(User person: people){
                    Log.d("MainActivity","person name is"+person.getUserName());
                    Log.d("MainActivity","person password is"+person.getUserPwd());
                }
                Intent intent_Register_to_Login = new Intent(MainActivity.this,Login.class) ;    //切换User Activity至Login Activity
                startActivity(intent_Register_to_Login);
                finish();

            }

        }



    }

    //判断输入的昵称，密码，确认密码是否为空

    public boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this,"用户名为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else if(mPwdCheck.getText().toString().trim().equals("")) {
            Toast.makeText(this, "再次输入密码不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
