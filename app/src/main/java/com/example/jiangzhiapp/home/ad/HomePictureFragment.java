package com.example.jiangzhiapp.home.ad;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jiangzhiapp.R;

import java.util.ArrayList;
import java.util.List;


public class HomePictureFragment extends Fragment {

    private ViewPager vp;
    private List<AD> list = new ArrayList<AD>();
    private LinearLayout ll;
    private TextView tv;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            vp.setCurrentItem(vp.getCurrentItem()+1);

            handler.sendEmptyMessageDelayed(0,3000);//延时3秒发送一个空消息

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_activity,null);
        vp = (ViewPager) view.findViewById(R.id.viewpager);
        ll = (LinearLayout) view.findViewById(R.id.ll);
        tv = (TextView) view.findViewById(R.id.tv);

        initListener();//初始化页面改变监听
        initDate();  //初始化图片数据
        return view;
    }


    /**
     * 初始化页面改变监听
     */
    private void initListener() {
        //页面改变时调用
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //页面滑动
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updateDateAndDots();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 初始化图片数据
     */
    private void initDate() {
        list.add(new AD(R.drawable.a,"建团93周年表彰大会"));
        list.add(new AD(R.drawable.b,"第十一届校园文化技术节"));
        list.add(new AD(R.drawable.c,"社团招新"));
        list.add(new AD(R.drawable.d,"不知名的活动"));
        list.add(new AD(R.drawable.e,"义工活动"));

        initDots(); //初始化dot

        vp.setAdapter(new MyAdapter());

        int currentItem = Integer.MAX_VALUE/2;
        int v = currentItem%list.size();
        vp.setCurrentItem(currentItem-v); //设置当前选中的页数,并在第一张图片

        handler.sendEmptyMessageDelayed(0,5000);//延时3秒发送一个空消息

        updateDateAndDots();   //初始化TextView和dot
    }
    /**
     * 初始化点，在知道list的大小的时候就可以调用
     */
    private void initDots(){
        for (int i =0;i<list.size();i++) {
            View view = new View(getContext());
            LinearLayout.LayoutParams  lp = new LinearLayout.LayoutParams(20,20);
            if(i!=0){
                lp.leftMargin = 5;

            }
            view.setLayoutParams(lp);
            view.setBackgroundResource(R.drawable.selector);
            ll.addView(view);
        }
    }

    /**
     * 更新图片说明和点
     */
    private void updateDateAndDots() {
        int count =  vp.getCurrentItem()%list.size();  //得到当前的图片页数，取余，拿到0到4
        tv.setText(list.get(count).getData());

        for (int i=0;i<ll.getChildCount();i++) {
            ll.getChildAt(i).setEnabled(i==count);  //设置dot的状态
        }
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            //return list.size();//返回页数的数目
            return Integer.MAX_VALUE; //int的最大值，伪无限循环

        }

        //true:从缓存中读取，false：创建一个新的页
        //view：当前滑动的view，object：后来进来的view
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        //类似于BaseAdapter的getView方法，填充条目（页）
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = View.inflate(getActivity(),R.layout.home_picture,null);
            //由于position会很大，所以要取余，拿到0到4
            AD ad = list.get(position%list.size());
            ImageView iv = (ImageView) view.findViewById(R.id.iv);
            iv.setImageResource(ad.getImageId());
            container.addView(view); //把view添加到ViewPager里面
            return view;
        }

        /* PagerView有预加载机制，最多加载三个
         * 销毁page，position：当前要销毁的page的个数
         *          object：当前要销毁的page
        */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
