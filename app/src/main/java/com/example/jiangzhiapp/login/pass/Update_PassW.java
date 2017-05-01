package com.example.jiangzhiapp.login.pass;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.login.db.MyOpenHelper;


/**
 * Created by ccc on 2017/4/17.
 */
public class Update_PassW extends Activity implements View.OnClickListener {
    SQLiteDatabase db;
    MyOpenHelper oh;
    EditText pass1,pass2,pass3,xuehao;
    String mima1,mima2,mima3,id;
    Button btn_sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_pass);
        oh = new MyOpenHelper(this, "stu.db", null, 1);
        db = oh.getWritableDatabase();
        pass1 = (EditText) findViewById(R.id.mima1);
        pass2 = (EditText) findViewById(R.id.mima2);
        pass3 = (EditText) findViewById(R.id.mima3);
        xuehao = (EditText) findViewById(R.id.xuehao);
        btn_sub = (Button) findViewById(R.id.update_sub);
        btn_sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mima1 = pass1.getText().toString();
        mima2 = pass2.getText().toString();
        mima3 = pass3.getText().toString();
        id = xuehao.getText().toString();
        boolean id_flag = searchId(id);
        boolean passflag0 = passIsRight(mima1);
        boolean pass_flag1 = searchPassIsHas(mima1,mima2);
        boolean pass_flag2 = searchPassIsLike(mima2, mima3);
        if(pass_flag1&&pass_flag2&&passflag0&&id_flag){
            updatePassWord(mima2);
        }
    }

    private boolean passIsRight(String mima1) {
        Cursor cursor  = db.rawQuery("SELECT id FROM user WHERE id= '" + id + "' AND password= '" + mima1 + "'", null);
        if(cursor.moveToNext()){
            return true ;
        }else{
            Toast.makeText(getApplicationContext(),"原密码错误",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean searchId(String id) {
        Cursor cursor  = db.rawQuery("SELECT id FROM user WHERE id= '" + id + "'", null);
        if(cursor.moveToNext()){
            return true ;
        }else{
            Toast.makeText(getApplicationContext(),"id不存在",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean searchPassIsHas(String mima1,String mima2) {
        if(mima1.equals(mima2)){
            Toast.makeText(getApplicationContext(),"新密码不能与原密码相同",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private boolean searchPassIsLike(String mima2, String mima3) {
        if(!mima2.equals(mima3)){
            Toast.makeText(getApplicationContext(),"密码不一致",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private void updatePassWord(String mima2) {
        db.execSQL("update user set password = '" + mima2 + "' where id = '" + id + "'");
        Toast.makeText(getApplicationContext(),"密码修改完成",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
