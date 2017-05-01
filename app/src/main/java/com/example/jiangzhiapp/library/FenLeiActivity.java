package com.example.jiangzhiapp.library;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.library.fragment.BookFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccc on 2017/4/13.
 */
public class FenLeiActivity extends Activity implements View.OnClickListener {

    private ImageView fenlei_back_library;
    private ListView list;
    private List<String> str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initData() {
        str = new ArrayList<>();
        setString();

        fenlei_back_library.setOnClickListener(this);
        list.setAdapter(new MyApdater());
    }

    private void initView() {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.fenlei_layout);
        fenlei_back_library = (ImageView) findViewById(R.id.fenlei_back_library);
        list = (ListView) findViewById(R.id.feilei_list);


    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    private void setString() {
        str.add("文学");
        str.add("小说");
        str.add("励志");
        str.add("艺术");
        str.add("计算机");
        str.add("考试");
        str.add("教材");
        str.add("外语");
        str.add("法律");
        str.add("心理学");
        str.add("历史");
        str.add("医学");
        str.add("自然科学");
        str.add("科普读物");
        str.add("工具书");
        str.add("管理");
        str.add("经济");
        str.add("投资理财");
        str.add("哲学");
        str.add("文化");
        str.add("休闲娱乐");
        str.add("烹饪/美食");
        str.add("养生保健");
        str.add("体育/运动");
        str.add("旅游/地图");
        str.add("时尚/美妆");
        str.add("建筑");
        str.add("工业技术");

    }

    class MyApdater extends BaseAdapter {

        @Override
        public int getCount() {
            return str.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = null;
            final String name = str.get(i);

            if (v != null) {
                v = view;
            } else {
                v = View.inflate(FenLeiActivity.this, R.layout.library_fenlei_layout, null);
            }
            TextView t = (TextView) v.findViewById(R.id.lib_feilei_text);
            t.setText(name);
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(FenLeiActivity.this, MoreActivity.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }
            });
            return v;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
    }
}
