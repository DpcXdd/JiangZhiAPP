package com.example.jiangzhiapp.my;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.login.pass.ResetPass;
import com.example.jiangzhiapp.login.pass.UpdPassWord;

public class MySettingActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;
    private RelativeLayout update;
    private TextView my_passwd,my_foundpasswd;
    private TextView my_exit;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_setting_layout);

        initView();
        initListener();

    }
    private void initListener() {
        imageView.setOnClickListener(this);
        update.setOnClickListener(this);
        my_passwd.setOnClickListener(this);
        my_foundpasswd.setOnClickListener(this);
        my_exit.setOnClickListener(this);

    }

    private void initView() {
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);

        imageView = (ImageView) findViewById(R.id.my_back);
        update = (RelativeLayout) findViewById(R.id.my_update);
        my_passwd = (TextView) findViewById(R.id.my_passwd);
        my_foundpasswd = (TextView) findViewById(R.id.my_foundpasswd);
        my_exit = (TextView) findViewById(R.id.my_exit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_back:
                onBackPressed();
                break;
            case R.id.my_update:
                Toast.makeText(this, "已经是最新版本！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_passwd:
                Intent intent = new Intent(MySettingActivity.this, ResetPass.class);
                startActivity(intent);
                break;
            case R.id.my_foundpasswd:
                Intent intent2 = new Intent(MySettingActivity.this,UpdPassWord.class);
                startActivity(intent2);
                break;
            case R.id.my_exit:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("消息");
        builder.setMessage("确认退出登录吗?");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sp.edit().putBoolean("isLogined", false).commit();
                onBackPressed();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        builder.show();

    }
}
