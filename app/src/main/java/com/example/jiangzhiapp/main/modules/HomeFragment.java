package com.example.jiangzhiapp.main.modules;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.home.ViewPagerIndicator;
import com.example.jiangzhiapp.home.fragment.CampusActivitiesFragment;
import com.example.jiangzhiapp.home.fragment.CampusNoticeFragment;
import com.example.jiangzhiapp.home.fragment.EducationalActivitiesFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment { //主页模块

    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;

    private List<Fragment> mContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, null);
        initViews(view);
        initDatas();

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                mIndicator.highLightTextView(position);
                //滑动 tabWidth*positionOffset + position * tabWidth
                mIndicator.scroll(position, positionOffset);
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

    private void initViews(View v) {
        mViewPager = (ViewPager) v.findViewById(R.id.id_view);
        mIndicator = (ViewPagerIndicator) v.findViewById(R.id.id_indicator);
    }

    private void initDatas() {

        CampusActivitiesFragment caf = new CampusActivitiesFragment();  //校园活动内容fragment
        CampusNoticeFragment cnf = new CampusNoticeFragment();   //校园通知内容fragment
        EducationalActivitiesFragment raf = new EducationalActivitiesFragment(); //教务通知内容fragment
        mContents.add(caf);
        mContents.add(cnf);
        mContents.add(raf);

        mIndicator.setItemClickEvent(mViewPager);

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
    }


}
