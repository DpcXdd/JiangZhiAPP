package com.example.jiangzhiapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.library.fragment.borrow.Book;


/**
 * Created by 浅墨留痕 on 2017/4/26.
 */
public class Util {

    public static final int CODE_LOGIN = 0;
    public static final int CODE_NO_LOGIN = 1;

    //设置一个子线程监听登录的状态
    public static void setThread(Activity activity, final Handler mHandler) {
        final SharedPreferences sp = activity.getSharedPreferences("config", Context.MODE_PRIVATE);
        final boolean [] mboolean ={ sp.getBoolean("isLogined", false)};

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    Boolean b = sp.getBoolean("isLogined", false);
                    if (mboolean[0] != b) {
                        Log.e("哈哈哈哈或或或或或", "jjjjjjjj");
                        if (b) {
                            mboolean[0] = true;
                            mHandler.sendEmptyMessageDelayed(CODE_LOGIN, 0);
                        } else {
                            mboolean[0] = false;
                            mHandler.sendEmptyMessageDelayed(CODE_NO_LOGIN, 0);
                        }
                    }
                }
            }
        }.start();
    }

    /**
     * 设置一本书的3个属性
     */
    public static void setValue(View v, Book book) {
        ImageView image = (ImageView) v.findViewById(R.id.book_image);
        TextView bookname = (TextView) v.findViewById(R.id.book_bookname);
        TextView author = (TextView) v.findViewById(R.id.book_author);

        image.setImageResource(book.getImageId());
        bookname.setText(book.getBookName());
        author.setText(book.getAuthor());

    }

    /**
     * 设置一本书的4个属性
     */
    public static void setValue2(View v, Book book) {
        ImageView image = (ImageView) v.findViewById(R.id.book_image);
        TextView bookname = (TextView) v.findViewById(R.id.book_bookname);
        TextView author = (TextView) v.findViewById(R.id.book_author);
        TextView days = (TextView) v.findViewById(R.id.book_days);

        image.setImageResource(book.getImageId());
        bookname.setText(book.getBookName());
        author.setText(book.getAuthor());
        days.setText(book.getDays() + "");

    }
}
