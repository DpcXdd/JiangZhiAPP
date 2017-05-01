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
public class ResetPass extends Activity implements View.OnClickListener{
    SQLiteDatabase db;
    MyOpenHelper oh;
    EditText id,pass,passAgain;
    Button finish;
    String passW;
    String Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_pass);
        oh = new MyOpenHelper(this, "stu.db", null, 1);
        db = oh.getWritableDatabase();
        finish = (Button) findViewById(R.id.reset_finish);
        id = (EditText) findViewById(R.id.reset_id);
        pass = (EditText) findViewById(R.id.reset_pass);
        passAgain = (EditText) findViewById(R.id.reset_pass_again);
        finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Id = id.getText().toString();
        passW = pass.getText().toString();
        boolean id_flag = searchId(Id);
        boolean pass_flag = searchPass(passW, passAgain.getText().toString());
        if(id_flag && pass_flag){
         resetPass(passW);
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

    private boolean searchPass(String p1,String p2) {
        if(!p1.equals(p2)){
            Toast.makeText(getApplicationContext(),"密码不一致",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    private void resetPass(String pass) {
        db.execSQL("update user set password = '" + pass + "' where id = '" + Id + "'");
        Toast.makeText(getApplicationContext(),"密码找回完成",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
    @Override
      protected void onDestroy() {
        super.onDestroy();
    }
}
