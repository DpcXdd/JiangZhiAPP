package com.example.jiangzhiapp.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.home.WebViewActivity;
import com.example.jiangzhiapp.home.dao.EduAct;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浅墨留痕 on 2017/4/15.
 */
public class EducationalActivitiesFragment extends Fragment {

    private ListView lv_edu_Act;
    private List<EduAct> eduActList = new ArrayList<EduAct>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initData();
        View view = inflater.inflate(R.layout.layout_three,null);
        lv_edu_Act = (ListView) view.findViewById(R.id.lv_edu_Act);
        lv_edu_Act.setAdapter(new EduActAdapter());

        lv_edu_Act.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = eduActList.get(position).getUrl();
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initData() {
        eduActList.add(new EduAct(R.drawable.a,
                "学校简介",
                "江门职业技术学院是一所综合性的公办全日制普通高等学校。",
                "http://mp.weixin.qq.com/s/KXcJYQoytD_dco4imeWFqg"));
        eduActList.add(new EduAct(R.drawable.b,
                "依据学业水平考试 || 江职录取结果出来啦，还不赶紧点开",
                "我校2017广东依据普通高中学业水平考试已经完满结束，心急如焚的各位考生是否想尽快得知自己有被录取没。",
                "http://mp.weixin.qq.com/s/anQ_pVX8DCC0EfxDfJ-SHw"));
        eduActList.add(new EduAct(R.drawable.c,
                "好美|江职花儿都开好了！木棉花儿开起来简直是肆无忌惮",
                "你肯定注意到了吧？校园里的一棵棵木棉开的正是茂盛",
                "http://mp.weixin.qq.com/s/eccW5fIQvB2fDHDIys-VGA"));
        eduActList.add(new EduAct(R.drawable.d,
                "前方一堆技能学分来袭！你准备好了吗?",
                "第十三届校园技术节要来啦！",
                "http://mp.weixin.qq.com/s/h6K6ogzb9phdbYvssCb7fA"));
        eduActList.add(new EduAct(R.drawable.e,
                "厉害了word大江职",
                "特等奖排名第一，总分全省第一，我校2016年校园文化建设大丰收",
                "http://mp.weixin.qq.com/s/_QDe-6ew5rtITjXtTnow2A"));
    }

    private class EduActAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return eduActList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getContext(), R.layout.campus_activities_item, null);
            ImageView iv = (ImageView) view.findViewById(R.id.iv);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            TextView tv_detail = (TextView) view.findViewById(R.id.tv_detail);
            iv.setImageResource(eduActList.get(position).getImgId());
            tv_title.setText(eduActList.get(position).getTitle());
            tv_detail.setText(eduActList.get(position).getDtaile());
            return view;
        }
    }
}
