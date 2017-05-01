package com.example.jiangzhiapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.jiangzhiapp.R;

/**
 * Created by JHO on 2017-04-18.
 */

public class WebViewActivity extends AppCompatActivity {

    private ImageView my_back;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus_activity_webview);

        my_back = (ImageView) findViewById(R.id.my_back);
        my_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        WebView wb = (WebView) findViewById(R.id.wb);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.loadUrl(url);
    }
}
