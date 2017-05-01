package com.example.jiangzhiapp.login.pass;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Random;
import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.login.db.MyOpenHelper;


/**
 * Created by ccc on 2017/4/16.
 */
public class UpdPassWord extends Activity implements View.OnClickListener {
    SQLiteDatabase db;
    MyOpenHelper oh;
    ViewPager pager;
    View view1, view2;
    Button btn_next1, btn_next2;
    EditText ed_phoneNum;
    NotificationManager manger;
    int notification_id;
    String[] titles = {"手机号码", "电子邮箱"};
    int yzm;

    Handler handler = new Handler() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(Message msg) {
           // Toast.makeText(getApplicationContext(), "通知", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), UpdPassWord.class);
            PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
            Notification.Builder builder = new Notification.Builder(getApplicationContext());
            builder.setTicker("你好！");
            builder.setSmallIcon(R.drawable.app_icon);
            builder.setWhen(System.currentTimeMillis());
            builder.setContentTitle("通知栏通知");
            builder.setContentText("验证码是：" + yzm + ", 请不要轻易告诉他人");
            builder.setContentIntent(pIntent);
            builder.setDefaults(Notification.DEFAULT_ALL);
            Notification notification = builder.build();
            manger.notify(notification_id, notification);
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upd_pass);
        oh = new MyOpenHelper(this, "stu.db", null, 1);
        db = oh.getWritableDatabase();

//        db.execSQL("insert into user (id,password) values('p201501','01')");
//        db.execSQL("insert into user (id,password) values('p201502','02')");
//        db.execSQL("insert into user (id,password) values('p201503','03')");
//        db.execSQL("insert into user (id,password) values('p201504','04')");
        Random ran = new Random();
        yzm = ran.nextInt(1000000);
        manger = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        pager = (ViewPager) findViewById(R.id.upd_viewpager);
        view1 = View.inflate(getApplicationContext(), R.layout.from_phonenum, null);
        view2 = View.inflate(getApplicationContext(), R.layout.from_email, null);
        pager.setAdapter(new MainAdapter());
        btn_next1 = (Button) view1.findViewById(R.id.next_fromP);
        ed_phoneNum = (EditText) view1.findViewById(R.id.phonenum);
        btn_next2 = (Button) view2.findViewById(R.id.next_fromE);
        btn_next1.setOnClickListener(this);
        btn_next2.setOnClickListener(this);
    }

    class MainAdapter extends PagerAdapter {
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = getViewFromPosition(position);
            container.addView(view);
            return view;
        }

        private View getViewFromPosition(int position) {
            if (position == 1) {
                return view2;
            } else {
                return view1;
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @Override
    public void onClick(View v) {
        if (formatIsOk()) {
            handler.sendEmptyMessageDelayed(0, 1500);
            Intent intent = new Intent(UpdPassWord.this, YZMActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("PHONE_NUM", ed_phoneNum.getText().toString());
            bundle.putString("YZM", yzm + "");
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

    private boolean formatIsOk() {
        String str = ed_phoneNum.getText().toString();
        if (!str.matches("\\d{11}")) {
            Toast.makeText(getApplicationContext(), "手机号码格式错误或不存在", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;

        }
    }
    @Override
    protected void onDestroy() {
//        Toast.makeText(getApplicationContext(), "dddd", Toast.LENGTH_SHORT).show();
//        db.execSQL("drop database stu");
        super.onDestroy();
    }

}
