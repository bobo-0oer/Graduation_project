package com.example.graduatioproject_android.LoginAndRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.graduatioproject_android.R;
import com.example.graduatioproject_android.tools.JSONTOOL;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

import static com.example.graduatioproject_android.tools.GlobalVariable.SERVERIP;

public class RegisterActivity extends AppCompatActivity {

    private EditText register_usernameET=null;
    private EditText register_passwordET=null;
    private EditText register_nicknameET=null;
    private EditText queryPasswordET=null;
    private EditText phoneET=null;
    private EditText emailET=null;
    private Button registerBtn=null;
    private MyListener myListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setStatusBarColor(0xffDADADA);

        myListener=new MyListener();

        register_usernameET=(EditText)findViewById(R.id.register_usernameET);
        register_nicknameET=(EditText)findViewById(R.id.register_nicknameET);
        register_passwordET=(EditText)findViewById(R.id.register_passwordET);
        queryPasswordET=(EditText)findViewById(R.id.queryPasswordET);
        phoneET=(EditText)findViewById(R.id.phoneET);
        emailET=(EditText)findViewById(R.id.emailET);
        registerBtn=(Button)findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(myListener);
    }

    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String result=checkInfo();
            switch (view.getId()){
                case(R.id.registerBtn):
                    if(result==null){
                        OkHttpUtils.post()
                                .url("http://"+SERVERIP+":8080/graduationproject/android/register")
                                . addParams("nickname", String.valueOf(register_nicknameET.getText()))
                                . addParams("username", String.valueOf(register_usernameET.getText()))
                                . addParams("password", String.valueOf(register_passwordET.getText()))
                                . addParams("phone", String.valueOf(phoneET.getText()))
                                . addParams("email", String.valueOf(emailET.getText()))
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e) {
                                        Toast.makeText(RegisterActivity.this, "服务器错误，请检查网络连接！", Toast.LENGTH_SHORT).show();
                                    }
                                    @Override
                                    public void onResponse(Call call, String s) {
                                        List<HashMap<String,String>> map = JSONTOOL.analyze_some_json("["+s+"]");
                                        if(map.get(0).get("state").equals("1")){
                                            /**
                                             * 注册事件处理
                                             * **/
                                            AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                                            builder.setTitle("提示");
                                            builder.setMessage("恭喜您，注册成功！");
                                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent jumpIntent=new Intent(RegisterActivity.this, LoginActivity.class);
                                                    startActivity(jumpIntent);
                                                    finish();
                                                }
                                            });
                                            builder.create().show();
                                        }else if(map.get(0).get("state").equals("0")){
                                            Toast.makeText(RegisterActivity.this, map.get(0).get("result"), Toast.LENGTH_SHORT).show();
                                            register_usernameET.setText("");
                                        }
                                    }
                                });
                    }else{
                        AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                        builder.setTitle("错误提示！");
                        builder.setMessage(result);
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.create().show();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 输入内容检测
     * */
    String checkInfo(){
        if (register_usernameET.getText().toString().trim()==null || register_usernameET.getText().toString().trim().equals("")){
            return "用户名不能为空!";
        }
        if(register_passwordET.getText().toString().trim().length()<6 ||register_passwordET.getText().toString().trim().length()>15 ){
            register_passwordET.setText("");
            queryPasswordET.setText("");
            return "密码长度有误！请重新输入6-15位之间！";
        }
        if(!queryPasswordET.getText().toString().trim().equals(register_passwordET.getText().toString().trim())){
            register_passwordET.setText("");
            queryPasswordET.setText("");
            return "两次输入的密码不一致！请重新输入！";
        }
        if(phoneET.getText().toString().trim().length()<11 ||phoneET.getText().toString().trim().length()>11 ){
            phoneET.setText("");
            return "联系方式输入有误！请重新输入11位联系方式！";
        }
        if(isEmail(emailET.getText().toString().trim())==false){
            emailET.setText("");
            return "邮箱格式有误！请重新输入！";
        }
        return null;
    }


    /**
     * 判断是否是邮箱.
     * @param str 指定的字符串
     * @return 是否是邮箱:是为true，否则false
     */
    public static Boolean isEmail(String str) {
        Boolean isEmail = false;
        String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }
}
