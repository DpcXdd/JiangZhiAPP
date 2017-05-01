package com.example.jiangzhiapp.library;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.library.fragment.BookFragment;
import com.example.jiangzhiapp.library.fragment.borrow.Book;
import com.example.jiangzhiapp.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浅墨留痕 on 2017/4/23.
 */
public class MoreActivity extends Activity {

    private ImageView image;
    private TextView text;
    private GridView grid;
    private List<Book> books;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.library_more);
        image = (ImageView) findViewById(R.id.more_image);
        text = (TextView) findViewById(R.id.more_text);
        grid = (GridView) findViewById(R.id.more_grid);
    }

    private void initData() {

        setBooks(23);

        grid.setAdapter(new MyApdater());

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        text.setText(name);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    public void setBooks(int ii) {
        books = new ArrayList<>();

        Book book;
        //拿到book的信息
        for (int i = 1; i < ii; i++) {
            if ((i % 5) == 0) {
                book = new Book(R.drawable.book1, "三少爷的剑", "古龙", (4 * i >= 100) ? i / 4 : i * 4);
            } else if ((i % 5) == 1) {
                book = new Book(R.drawable.book2, "三体(合集)", "刘慈欣", (2 * i >= 100) ? i / 2 : i * 2);
            } else if ((i % 5) == 2) {
                book = new Book(R.drawable.book3, "玩命爱一个姑娘", "宋小君", (4 * i >= 100) ? i / 4 : i * 4);
            } else if ((i % 5) == 3) {
                book = new Book(R.drawable.book4, "最初不过你", "鹿人三千", (2 * i >= 100) ? i / 2 : i * 2);
            } else if ((i % 5) == 4) {
                book = new Book(R.drawable.book5, "假面饭店", "东野圭吾", (4 * i >= 100) ? i / 4 : i * 4);
            } else {
                book = new Book(R.drawable.book4, "最初不过你", "鹿人三千", (2 * i >= 100) ? i / 2 : i * 2);
            }
            books.add(book);

        }
    }

    class MyApdater extends BaseAdapter{

        @Override
        public int getCount() {
            return books.size();
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
                v = View.inflate(MoreActivity.this, R.layout.library_book2, null);
            }

            Util.setValue(v,books.get(i));

            return v;
        }
    }
}
