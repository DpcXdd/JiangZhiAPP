package com.example.jiangzhiapp.main.modules;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.login.pass.LoginActivity;
import com.example.jiangzhiapp.my.MyClassActivity;
import com.example.jiangzhiapp.my.MyDataActivity;
import com.example.jiangzhiapp.my.MySettingActivity;
import com.example.jiangzhiapp.util.Util;

/**
 * Created by 浅墨留痕 on 2017/4/13.
 */
public class MyFragment extends Fragment implements View.OnClickListener { //我的模块

    private ImageView my_image, my_sex;
    private TextView my_name;
    private LinearLayout my_information, my_class,
            my_club, my_proofread, my_query, setting;

    private Intent intent;

    private SharedPreferences sp;
    private Boolean mboolean;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mboolean = sp.getBoolean("isLogined", false);
            initData();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_layout, null);

        sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        mboolean = sp.getBoolean("isLogined", false);

        initView(view);
        initListener();
        initData();

        Util.setThread(getActivity(),mHandler);
        return view;
    }

    private void initData() {
        if (mboolean) logined();
        else noLogin();
    }

    private void initListener() {
        my_image.setOnClickListener(this);
        my_name.setOnClickListener(this);
        my_sex.setOnClickListener(this);

        my_information.setOnClickListener(this);
        my_class.setOnClickListener(this);
        my_club.setOnClickListener(this);
        my_proofread.setOnClickListener(this);
        my_query.setOnClickListener(this);
        setting.setOnClickListener(this);

    }

    private void initView(View view) {
        my_image = (ImageView) view.findViewById(R.id.my_image);
        my_name = (TextView) view.findViewById(R.id.my_name);
        my_sex = (ImageView) view.findViewById(R.id.my_sex);

        my_information = (LinearLayout) view.findViewById(R.id.my_information);
        my_class = (LinearLayout) view.findViewById(R.id.my_class);
        my_club = (LinearLayout) view.findViewById(R.id.my_club);
        my_proofread = (LinearLayout) view.findViewById(R.id.my_proofread);
        my_query = (LinearLayout) view.findViewById(R.id.my_query);
        setting = (LinearLayout) view.findViewById(R.id.my_setting);
    }

    @Override
    public void onClick(View view) {

        if (mboolean) {
            switch (view.getId()) {

                case R.id.my_information:
                    intent = new Intent(getContext(), MyDataActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_class:
                    intent = new Intent(getContext(), MyClassActivity.class);
                    startActivity(intent);
                    break;
                case R.id.my_club:
                    break;
                case R.id.my_proofread:
                    break;
                case R.id.my_query:
                    break;
                case R.id.my_setting:
                    intent = new Intent(getContext(), MySettingActivity.class);
                    startActivity(intent);
                    break;
            }
        } else {
            startActivity(new Intent(getContext(), LoginActivity.class));
        }

    }

    private void noLogin() {
            my_image.setImageResource(R.drawable.my_no_login);
            my_name.setTextColor(Color.parseColor("#736c6c"));
            my_name.setText("请点击登录");
            my_sex.setVisibility(View.GONE);
    }

    private void logined() {
            my_image.setImageResource(R.drawable.my_head);
            my_name.setTextColor(Color.parseColor("#000000"));
            my_name.setText("许集泓");
            my_sex.setVisibility(View.VISIBLE);
    }
}
