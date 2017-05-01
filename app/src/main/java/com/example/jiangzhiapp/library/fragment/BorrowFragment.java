package com.example.jiangzhiapp.library.fragment;

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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.library.fragment.borrow.Book;
import com.example.jiangzhiapp.login.pass.LoginActivity;
import com.example.jiangzhiapp.main.MainActivity;
import com.example.jiangzhiapp.splash.SplashActivity;
import com.example.jiangzhiapp.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浅墨留痕 on 2017/4/16.
 */
public class BorrowFragment extends Fragment {

    private ListView list;
    private List<Book> books;
    private int index;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_borrow, null);
        list = (ListView) view.findViewById(R.id.lib_borrow_list);
        SharedPreferences sp =
                getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);

        if(sp.getBoolean("isLogined", false)) {
            initData();
            list.setAdapter(new MyApdate());
        } else {
            View v = inflater.inflate(R.layout.no_login_layout, null);
            initNoLogin(v);
            return v;
        }

        return view;
    }

    private void initNoLogin(View v) {
        Button b = (Button) v.findViewById(R.id.no_login_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 初始化数据
     */
    private void initData() {

        books = new ArrayList<>();

        Book book;
        //拿到book的信息
        for (int i = 1; i < 6; i++) {
            if ((i % 5) == 0) {
                book = new Book(R.drawable.book1, "三少爷的剑", "古龙", (3 * i >= 100) ? i / 4 : i * 4);
            } else if ((i % 5) == 1) {
                book = new Book(R.drawable.book2, "三体(合集)", "刘慈欣", (2 * i >= 100) ? i / 2 : i * 2);
            } else if ((i % 5) == 2) {
                book = new Book(R.drawable.book3, "玩命爱一个姑娘", "宋小君", (1 * i >= 100) ? i / 4 : i * 4);
            } else if ((i % 5) == 3) {
                book = new Book(R.drawable.book4, "最初不过你", "鹿人三千", (5 * i >= 100) ? i / 2 : i * 2);
            } else if ((i % 5) == 4) {
                book = new Book(R.drawable.book5, "假面饭店", "东野圭吾", (4 * i >= 100) ? i / 4 : i * 4);
            } else {
                book = new Book(R.drawable.book4, "最初不过你", "鹿人三千", (2 * i >= 100) ? i / 2 : i * 2);
            }
            books.add(book);

        }

    }

    /**
     * 创建ListView的适配器
     */
    class MyApdate extends BaseAdapter {
        @Override
        public int getCount() {
            int size = books.size();
            int y = size % 3;
            int i = size / 3;
            return (y == 0) ? i : i + 1;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //创建LinearLayout对象，并存入三个book布局

            LinearLayout threeBook;
            LinearLayout oneBook;

            //读取缓存
            if (view != null) {
                threeBook = (LinearLayout) view;

            } else {
                threeBook = (LinearLayout) View.inflate(getContext(), R.layout.library_bookparent, null);
            }

            //拿到books的下标
            //是否是倒数第二行，
            if (i == (getCount() - 1)) {
                index = books.size();
                //得出剩下的书本数
                int needInt = 3 - ((i * 3 + 3) - index);

                //最后一行是否为两本书
                if (needInt == 2) {
                    oneBook = (LinearLayout) threeBook.getChildAt(0);
                    Util.setValue2(oneBook, books.get(index - 2));
                    oneBook = (LinearLayout) threeBook.getChildAt(1);
                    Util.setValue2(oneBook, books.get(index - 1));
                    threeBook.getChildAt(2).setVisibility(View.INVISIBLE);

                    //最后一行是否为一本书
                } else if (needInt == 1) {
                    oneBook = (LinearLayout) threeBook.getChildAt(0);
                    Util.setValue2(oneBook, books.get(index - 1));
                    threeBook.getChildAt(1).setVisibility(View.INVISIBLE);
                    threeBook.getChildAt(2).setVisibility(View.INVISIBLE);
                }

                return threeBook;
            } else {
                index = 3 * i + 3;

            }

            //一行为3本书执行
            for (int j = 0; j < threeBook.getChildCount(); j++) {
                oneBook = (LinearLayout) threeBook.getChildAt(j);
                oneBook.setVisibility(View.VISIBLE);
                Util.setValue2(oneBook, books.get(index - getInt(j)));
            }

            return threeBook;
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

    private int getInt(int i) {

        return (i == 0) ? 3 : ((i == 1) ? 2 : 1);
    }
}
