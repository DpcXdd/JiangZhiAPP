package com.example.jiangzhiapp.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.main.modules.HomeFragment;
import com.example.jiangzhiapp.main.modules.LibraryFragment;
import com.example.jiangzhiapp.main.modules.MyFragment;
import com.example.jiangzhiapp.main.modules.TeachFragment;

/**
 * Created by 浅墨留痕 on 2017/4/14.
 */
public class MainActivity extends AppCompatActivity {

    private LinearLayout tv1; //主页
    private LinearLayout tv2; //图书馆
    private LinearLayout tv3; //教学
    private LinearLayout tv4; //我的

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;


    private FrameLayout frameLayout;

    private HomeFragment homeFragment;  //主页模块
    private MyFragment myFragment;      //我的模块
    private LibraryFragment libraryFragment; //图书馆模块
    private TeachFragment teachFragment;   //教学模块

    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.main_layout);
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        super.onCreate(savedInstanceState);

        tv1 = (LinearLayout) findViewById(R.id.main_tv1);
        tv2 = (LinearLayout) findViewById(R.id.main_tv2);
        tv3 = (LinearLayout) findViewById(R.id.main_tv3);
        tv4 = (LinearLayout) findViewById(R.id.main_tv4);

        img1 = (ImageView) findViewById(R.id.main_img1);
        img2 = (ImageView) findViewById(R.id.main_img2);
        img3 = (ImageView) findViewById(R.id.main_img3);
        img4 = (ImageView) findViewById(R.id.main_img4);

        text1 = (TextView) findViewById(R.id.main_text1);
        text2 = (TextView) findViewById(R.id.main_text2);
        text3 = (TextView) findViewById(R.id.main_text3);
        text4 = (TextView) findViewById(R.id.main_text4);

        frameLayout = (FrameLayout) findViewById(R.id.main_frame);

        //创建fragment对象
        myFragment = new MyFragment();
        homeFragment = new HomeFragment();
        libraryFragment = new LibraryFragment();
        teachFragment = new TeachFragment();


        //获取fragment管理器
        fm = getSupportFragmentManager();
        //打开事务
        ft = fm.beginTransaction();
        //把内容显示至帧布局
        ft.add(R.id.main_frame, homeFragment);
        //提交
        ft.commit();

        setHomeChecked();

        /**
         * 设置TextView的点击监听
         */
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!homeFragment.isVisible()) {

                    ft = fm.beginTransaction();

                    //判断homeFragment是否被添加过
                    if (!homeFragment.isAdded()) {
                        ft.hide(myFragment)
                                .hide(libraryFragment)
                                .hide(teachFragment); //隐藏
                        ft.add(R.id.main_frame, homeFragment).show(homeFragment).commit();
                    } else {
                        ft.hide(myFragment)
                                .hide(libraryFragment)
                                .hide(teachFragment);
                        ft.show(homeFragment).commit(); //显示

                    }

                    setHomeChecked();
                }
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!libraryFragment.isVisible()) {

                    ft = fm.beginTransaction();

                    //判断homeFragment是否被添加过
                    if (!libraryFragment.isAdded()) {
                        ft.hide(myFragment)
                                .hide(homeFragment)
                                .hide(teachFragment); //隐藏
                        ft.add(R.id.main_frame, libraryFragment).show(libraryFragment).commit();
                    } else {
                        ft.hide(myFragment)
                                .hide(homeFragment)
                                .hide(teachFragment);
                        ft.show(libraryFragment).commit(); //显示

                    }

                    setLibraryChecked();
                }
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!teachFragment.isVisible()) {

                    ft = fm.beginTransaction();

                    //判断homeFragment是否被添加过
                    if (!teachFragment.isAdded()) {
                        ft.hide(myFragment)
                                .hide(homeFragment)
                                .hide(libraryFragment); //隐藏
                        ft.add(R.id.main_frame, teachFragment).show(teachFragment).commit();
                    } else {
                        ft.hide(myFragment)
                                .hide(homeFragment)
                                .hide(libraryFragment);
                        ft.show(teachFragment).commit(); //显示

                    }

                    setTeachChecked();
                }
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!myFragment.isVisible()) {

                    ft = fm.beginTransaction();

                    if (!myFragment.isAdded()) {
                        ft.hide(homeFragment)
                                .hide(libraryFragment)
                                .hide(teachFragment); //隐藏
                        ft.add(R.id.main_frame, myFragment).show(myFragment).commit();

                    } else {
                        ft.hide(homeFragment)
                                .hide(libraryFragment)
                                .hide(teachFragment);
                        ft.show(myFragment).commit(); //显示

                    }

                    setMyChecked();
                }

            }
        });

    }

    /**
     * 设置选中的状态
     */
    private void setHomeChecked() {
        img1.setImageResource(R.drawable.main_home_checked);
        img2.setImageResource(R.drawable.main_library);
        img3.setImageResource(R.drawable.main_teach);
        img4.setImageResource(R.drawable.main_my);

        text1.setTextColor(Color.parseColor("#3da4e9"));
        text2.setTextColor(Color.parseColor("#736c6c"));
        text3.setTextColor(Color.parseColor("#736c6c"));
        text4.setTextColor(Color.parseColor("#736c6c"));

    }

    private void setLibraryChecked() {
        img1.setImageResource(R.drawable.main_home);
        img2.setImageResource(R.drawable.main_library_checked);
        img3.setImageResource(R.drawable.main_teach);
        img4.setImageResource(R.drawable.main_my);

        text2.setTextColor(Color.parseColor("#3da4e9"));
        text1.setTextColor(Color.parseColor("#736c6c"));
        text3.setTextColor(Color.parseColor("#736c6c"));
        text4.setTextColor(Color.parseColor("#736c6c"));
    }

    private void setTeachChecked() {
        img1.setImageResource(R.drawable.main_home);
        img2.setImageResource(R.drawable.main_library);
        img3.setImageResource(R.drawable.main_teach_checked);
        img4.setImageResource(R.drawable.main_my);

        text3.setTextColor(Color.parseColor("#3da4e9"));
        text2.setTextColor(Color.parseColor("#736c6c"));
        text1.setTextColor(Color.parseColor("#736c6c"));
        text4.setTextColor(Color.parseColor("#736c6c"));
    }

    private void setMyChecked() {
        img1.setImageResource(R.drawable.main_home);
        img2.setImageResource(R.drawable.main_library);
        img3.setImageResource(R.drawable.main_teach);
        img4.setImageResource(R.drawable.main_my_checked);

        text4.setTextColor(Color.parseColor("#3da4e9"));
        text2.setTextColor(Color.parseColor("#736c6c"));
        text3.setTextColor(Color.parseColor("#736c6c"));
        text1.setTextColor(Color.parseColor("#736c6c"));
    }

    /**
     * 返回状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        //该方式初始化时拿不到数据，只能在页面加载之后用按钮才能拿到高度
        /*Rect rectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        return rectangle.top;*/

        /**获取status_bar_height资源的ID*/
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值  
            return context.getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

}

