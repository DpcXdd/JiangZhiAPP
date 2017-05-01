package com.example.jiangzhiapp.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.jiangzhiapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MyClassActivity extends AppCompatActivity {
    private ImageView cla_back_my;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_class);
        cla_back_my = (ImageView) findViewById(R.id.cla_back_my);
        listView = (ListView) findViewById(R.id.cla_listv);
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for (int i = 1;i <20;i++){
            HashMap<String,Object> map = new HashMap<>();
            map.put("name","许集泓");
            map.put("gender","男");
            map.put("phone","1342743148"+i);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,list,
                R.layout.class_list_item,new String[]{"name","gender","phone"},
                new int[]{R.id.mycla_name,R.id.mycla_gender,R.id.mycla_phone});
        listView.setAdapter(adapter);
        cla_back_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
