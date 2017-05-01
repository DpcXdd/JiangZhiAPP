package com.example.jiangzhiapp.main.modules;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jiangzhiapp.login.pass.LoginActivity;
import com.example.jiangzhiapp.teach.ChengJiActivity;
import com.example.jiangzhiapp.teach.Cla_TeachActivity;
import com.example.jiangzhiapp.teach.JiaoCaiJieSuanActivity;
import com.example.jiangzhiapp.teach.KaoShiActivity;
import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.teach.XuanKeActivity;
import com.example.jiangzhiapp.util.Util;

/**
 * Created by ccc on 2017/4/13.
 */
public class TeachFragment extends Fragment implements View.OnClickListener {
    private LinearLayout btn_kaishi, btn_chengji, btn_cla_teach, btn_xuanke,
            btn_js, btn_qj, btn_kq, btn_kb;
    private Intent intent;
    private SharedPreferences sp;

    private Boolean mboolean;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mboolean = sp.getBoolean("isLogined", false);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teach_layout, null);
        super.onCreate(savedInstanceState);

        sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        mboolean = sp.getBoolean("isLogined", false);

        initView(view);
        initListener();

        return view;
    }

    private void initListener() {
        btn_kaishi.setOnClickListener(this);
        btn_xuanke.setOnClickListener(this);
        btn_cla_teach.setOnClickListener(this);
        btn_chengji.setOnClickListener(this);
        btn_js.setOnClickListener(this);
        btn_qj.setOnClickListener(this);
        btn_kq.setOnClickListener(this);
        btn_kb.setOnClickListener(this);

    }

    private void initView(View view) {
        btn_kaishi = (LinearLayout) view.findViewById(R.id.teach_ks);
        btn_chengji = (LinearLayout) view.findViewById(R.id.teach_cj);
        btn_cla_teach = (LinearLayout) view.findViewById(R.id.teach_ktjx);
        btn_xuanke = (LinearLayout) view.findViewById(R.id.teach_xk);
        btn_js = (LinearLayout) view.findViewById(R.id.teach_jcjs);
        btn_qj = (LinearLayout) view.findViewById(R.id.teach_qj);
        btn_kq = (LinearLayout) view.findViewById(R.id.teach_kqcx);
        btn_kb = (LinearLayout) view.findViewById(R.id.teach_kbcx);

        Util.setThread(getActivity(), mHandler);

    }

    public void onClick(View v) {
        if (mboolean) {

            switch (v.getId()) {
                case R.id.teach_ks:
                    intent = new Intent(getContext(), KaoShiActivity.class);
                    startActivity(intent);
                    break;
                case R.id.teach_cj:
                    intent = new Intent(getContext(), ChengJiActivity.class);
                    startActivity(intent);
                    break;
                case R.id.teach_ktjx:
                    intent = new Intent(getContext(), Cla_TeachActivity.class);
                    startActivity(intent);
                    break;
                case R.id.teach_xk:
                    intent = new Intent(getContext(), XuanKeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.teach_jcjs:
                    intent = new Intent(getContext(), JiaoCaiJieSuanActivity.class);
                    startActivity(intent);
                    break;
            }
        } else {
            startActivity(new Intent(getContext(), LoginActivity.class));
        }


    }
}
