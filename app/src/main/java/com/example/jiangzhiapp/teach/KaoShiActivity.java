package com.example.jiangzhiapp.teach;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.jiangzhiapp.R;

/**
 * Created by ccc on 2017/4/13.
 */
public class KaoShiActivity extends Activity implements View.OnClickListener {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaoshi_layout);
        imageView = (ImageView) findViewById(R.id.ks_back_teach);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
