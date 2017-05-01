package com.example.jiangzhiapp.main.modules;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.jiangzhiapp.library.FenLeiActivity;
import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.library.LibraryLinearLayout;
import com.example.jiangzhiapp.library.SearchActivity;
import com.example.jiangzhiapp.library.fragment.BookFragment;
import com.example.jiangzhiapp.library.fragment.BorrowFragment;
import com.example.jiangzhiapp.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccc on 2017/4/13.
 */
public class LibraryFragment extends Fragment implements View.OnClickListener {

    private ViewPager mViewPager;
    private LibraryLinearLayout linearLayout;

    private List<Fragment> mContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;

    private LinearLayout imageV_fenlei;
    private LinearLayout search_book;
    private Intent intent;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setApdater();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.library_layout, null);

        initViews(view);
        initDatas();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                linearLayout.highLightTextView(position);
                //滑动 tabWidth*positionOffset + position * tabWidth
                linearLayout.scroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    private void initDatas() {

        imageV_fenlei.setOnClickListener(this);
        search_book.setOnClickListener(this);

        BorrowFragment bf = new BorrowFragment();
        BookFragment bookf = new BookFragment();
        mContents.add(bookf);
        mContents.add(bf);

        linearLayout.setItemClickEvent(mViewPager);

        mAdapter = new FragmentPagerAdapter(getFragmentManager()) {  //得到fragment的管理器
            @Override
            public Fragment getItem(int position) {

                return mContents.get(position);
            }

            @Override
            public int getCount() {
                return mContents.size();

            }
        };

        mViewPager.setAdapter(mAdapter);

        Util.setThread(getActivity(), mHandler);
    }

    private void initViews(View view) {
        imageV_fenlei = (LinearLayout) view.findViewById(R.id.lib_fenlei);
        search_book = (LinearLayout) view.findViewById(R.id.lib_search);

        mViewPager = (ViewPager) view.findViewById(R.id.lib_viewpager);
        linearLayout = (LibraryLinearLayout) view.findViewById(R.id.lib_linear);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lib_fenlei:
                intent = new Intent(getContext(), FenLeiActivity.class);
                startActivity(intent);
                break;
            case R.id.lib_search:
                intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void setApdater() {
        mContents.clear();
        BorrowFragment bf = new BorrowFragment();
        BookFragment bookf = new BookFragment();
        mContents.add(bookf);
        mContents.add(bf);

        mViewPager.setAdapter(mAdapter);
    }


}
