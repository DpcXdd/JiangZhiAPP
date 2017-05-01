package com.example.jiangzhiapp.login.pass;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.login.db.MyOpenHelper;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private MyOpenHelper oh;
    private EditText login_id, login_pass;
    private ImageView eye;

    private SharedPreferences sp;

    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        oh = new MyOpenHelper(this, "stu.db", null, 1);
        db = oh.getWritableDatabase();

        sp = getSharedPreferences("config", Context.MODE_PRIVATE);

//        db.execSQL("insert into user (id,password) values('p201501','01')");
//        db.execSQL("insert into user (id,password) values('p201502','02')");
//        db.execSQL("insert into user (id,password) values('p201503','03')");
//        db.execSQL("insert into user (id,password) values('p201504','04')");
        login_id = (EditText) findViewById(R.id.ligin_id);
        login_pass = (EditText) findViewById(R.id.login_pass);
        eye = (ImageView) findViewById(R.id.login_eye);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = !flag;
                eye.setSelected(flag);
                if (flag)
                    login_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                else
                    login_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
    }

    public boolean findLogin(String s1, String s2) throws Exception {
        boolean flag = false;
        Cursor cursor = db.rawQuery("SELECT id FROM user WHERE id= '" + s1 + "' AND password= '" + s2 + "'", null);
        if (cursor.moveToNext()) {
            flag = true;
        }
        return flag;
    }

    public void login(View view) {
        String id = login_id.getText().toString();
        String pass = login_pass.getText().toString();
        List<String> info = new ArrayList<String>();    // 收集错误
        if (id == null || "".equals(id)) {
            info.add("用户名不能为空！");
            Toast.makeText(getApplicationContext(), "用户名不能为空！", Toast.LENGTH_SHORT).show();
        }
        if (pass == null || "".equals(pass)) {
            Toast.makeText(getApplicationContext(), "密码不能为空！", Toast.LENGTH_SHORT).show();
            info.add("密码不能为空！");
        }
        if (info.size() == 0) {    // 里面没有记录任何的错误

            try {
                if (findLogin(id, pass)) {
                    Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                    sp.edit().putBoolean("isLogined", true).commit();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "登陆失败,错误的用户名或密码", Toast.LENGTH_SHORT).show();
                    info.add("用户登陆失败，错误的用户名和密码！");
                    login_pass.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void login_no(View view) {
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

}
