package com.example.jiangzhiapp.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.main.MainActivity;
import com.example.jiangzhiapp.util.Util;

/**
 * Created by JHO on 2017-04-17.
 */

public class SplashActivity extends AppCompatActivity {

    public SharedPreferences sp;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        sp = getSharedPreferences("config", MODE_PRIVATE);

        //检测已有登录账户
        if (sp.getBoolean("isLogined", false)) {
            mHandler.sendEmptyMessageDelayed(Util.CODE_LOGIN, 2000);
        } else {
            mHandler.sendEmptyMessageDelayed(Util.CODE_NO_LOGIN, 2000);
        }
    }

}
