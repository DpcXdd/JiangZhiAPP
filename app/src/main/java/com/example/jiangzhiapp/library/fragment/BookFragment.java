package com.example.jiangzhiapp.library.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.library.MoreActivity;
import com.example.jiangzhiapp.library.fragment.borrow.Book;
import com.example.jiangzhiapp.util.Util;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浅墨留痕 on 2017/4/16.
 */
public class BookFragment extends Fragment {

    private GridView grid1,grid2,grid3,grid4,grid5;
    private TextView t1,t2,t3,t4,t5;

    private List<GridView> grids;
    private List<String> texts;
    private List<Book> books;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.library_lib, null);

        initView(view);
        initData();
        return view;
    }

    private void initData() {
        grids = new ArrayList<>();
        texts = new ArrayList<>();
        books = new ArrayList<>();

        grids.add(grid1);
        grids.add(grid2);
        grids.add(grid3);
        grids.add(grid4);
        grids.add(grid5);

        books.add(new Book(R.drawable.book1, "三少爷的剑", "古龙"));
        books.add(new Book(R.drawable.book2, "三体(合集)", "刘慈欣"));
        books.add(new Book(R.drawable.book3, "玩命爱一个姑娘", "宋小君"));
        books.add(new Book(R.drawable.book4, "最初不过你", "鹿人三千"));
        books.add(new Book(R.drawable.book5, "假面饭店", "东野圭吾"));
        books.add(new Book(R.drawable.book1, "三少爷的剑", "古龙"));

        texts.add("新书推荐");
        texts.add("经典名著");
        texts.add("专业教材");
        texts.add("成功励志");
        texts.add("大家都在看");

        t1.setOnClickListener(new MyOnClickListener(0));
        t2.setOnClickListener(new MyOnClickListener(1));
        t3.setOnClickListener(new MyOnClickListener(2));
        t4.setOnClickListener(new MyOnClickListener(3));
        t5.setOnClickListener(new MyOnClickListener(4));

        for (GridView g : grids) {
            g.setAdapter(new MyApdater());
        }

    }

    private void initView(View view) {
        grid1 = (GridView) view.findViewById(R.id.lib_grid1);
        grid2 = (GridView) view.findViewById(R.id.lib_grid2);
        grid3 = (GridView) view.findViewById(R.id.lib_grid3);
        grid4 = (GridView) view.findViewById(R.id.lib_grid4);
        grid5 = (GridView) view.findViewById(R.id.lib_grid5);

        t1 = (TextView) view.findViewById(R.id.lib_t1);
        t2 = (TextView) view.findViewById(R.id.lib_t2);
        t3 = (TextView) view.findViewById(R.id.lib_t3);
        t4 = (TextView) view.findViewById(R.id.lib_t4);
        t5 = (TextView) view.findViewById(R.id.lib_t5);


    }

    class MyApdater extends BaseAdapter{

        @Override
        public int getCount() {
            return books.size();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = null;

            if (v != null) {
                v = view;
            } else {
                v = View.inflate(getContext(), R.layout.library_book2, null);
            }

            Util.setValue(v,books.get(i));

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


    class MyOnClickListener implements View.OnClickListener {

        private int i;

        public MyOnClickListener(int i) {
            this.i = i;
        }

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(getContext(), MoreActivity.class);
            intent.putExtra("name", texts.get(i));
            startActivity(intent);
        }
    }

}
