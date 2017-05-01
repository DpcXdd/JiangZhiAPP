package com.example.jiangzhiapp.library;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.library.fragment.BookFragment;
import com.example.jiangzhiapp.library.fragment.borrow.Book;
import com.example.jiangzhiapp.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccc on 2017/4/13.
 */
public class SearchActivity extends Activity implements View.OnClickListener {

    private TextView btn_search, t1, t2, t3;
    private EditText search_value;
    private ImageView back_library;
    private LinearLayout linear;
    private GridView grid;

    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initData() {
        books = new ArrayList<>();

        books.add(new Book(R.drawable.book1, "三少爷的剑", "古龙"));
        books.add(new Book(R.drawable.book2, "三体(合集)", "刘慈欣"));
        books.add(new Book(R.drawable.book3, "玩命爱一个姑娘", "宋小君"));
        books.add(new Book(R.drawable.book4, "最初不过你", "鹿人三千"));
        books.add(new Book(R.drawable.book5, "假面饭店", "东野圭吾"));

        btn_search.setOnClickListener(this);
        back_library.setOnClickListener(this);

        t1.setOnClickListener(new MyOnClickListener());
        t2.setOnClickListener(new MyOnClickListener());
        t3.setOnClickListener(new MyOnClickListener());
    }

    private void initView() {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.textv_search_layout);

        btn_search = (TextView) findViewById(R.id.btn_search);
        t1 = (TextView) findViewById(R.id.search_t1);
        t2 = (TextView) findViewById(R.id.search_t2);
        t3 = (TextView) findViewById(R.id.search_t3);

        search_value = (EditText) findViewById(R.id.search_value);
        back_library = (ImageView) findViewById(R.id.search_back_library);
        linear = (LinearLayout) findViewById(R.id.search_linear);
        grid = (GridView) findViewById(R.id.search_grid);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_search:

                String n = search_value.getText().toString();
                if (n.equals(""))
                    Toast.makeText(SearchActivity.this, "请输入书名或作者", Toast.LENGTH_SHORT).show();
                else
                    setContainsBooks(n);
                break;

            case R.id.search_back_library:
                onBackPressed();
                break;

        }
    }

    private void setContainsBooks(String name) {
        List<Book> bs = new ArrayList<>();

        for (Book b : books) {
            //判断是否包含所需内容
            if (b.getBookName().contains(name) || b.getAuthor().contains(name)) {
                bs.add(b);
            }
        }
        if (bs.size() > 0) {
            linear.setVisibility(View.GONE);
            grid.setAdapter(new MyAdapter(bs));
        } else {
            Toast.makeText(getApplicationContext(),
                    "您要找的书不存在", Toast.LENGTH_SHORT).show();
        }


    }


    class MyOnClickListener implements View.OnClickListener {


        @Override
        public void onClick(View view) {
            TextView t = (TextView) view;
            setContainsBooks(t.getText().toString());

        }
    }

    class MyAdapter extends BaseAdapter {

        private List<Book> bs;

        public MyAdapter(List<Book> bs) {
            this.bs = bs;
        }

        @Override
        public int getCount() {
            return bs.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = null;

            if (v != null) {
                v = view;
            } else {
                v = View.inflate(SearchActivity.this, R.layout.library_book2, null);
            }

            Util.setValue(v, bs.get(i));

            return v;
        }
    }
}
