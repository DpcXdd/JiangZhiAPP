package com.example.jiangzhiapp.login.pass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiangzhiapp.R;


/**
 * Created by ccc on 2017/4/16.
 */
public class YZMActivity extends Activity implements View.OnClickListener{
    TextView tv_phone ;
    EditText et_yzm ;
    Button btn_yes;
    String phoneNum;
    String yzm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getyzm);
        tv_phone = (TextView) findViewById(R.id.yzm_textview);
        et_yzm = (EditText) findViewById(R.id.yzm_editext);
        btn_yes = (Button) findViewById(R.id.yzm_btn_yes);
        btn_yes.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        phoneNum = bundle.getString("PHONE_NUM");
        yzm = bundle.getString("YZM");
        tv_phone.setText(phoneNum);
        Toast.makeText(getApplicationContext(),"phoneNum:"+phoneNum +"yzm" +yzm ,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if(yanZheng()){
//            Toast.makeText(getApplicationContext(),"验证码正确",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),ResetPass.class);
            startActivity(intent);
        }
    }

    private boolean yanZheng() {
        if(et_yzm.getText().toString().equals(yzm)){
            return true;
        }else{
            Toast.makeText(getApplicationContext(),"验证码错误",Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
