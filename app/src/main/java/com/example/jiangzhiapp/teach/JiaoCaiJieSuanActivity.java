package com.example.jiangzhiapp.teach;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jiangzhiapp.R;
import com.example.jiangzhiapp.teach.teachBean.Teaching;
import com.example.jiangzhiapp.util.AnimationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JHO on 2017-04-25.
 */

public class JiaoCaiJieSuanActivity extends Activity implements View.OnClickListener{

    private static final String TAG = "JiaoCaiJieSuanActivity";
    private boolean isShowPopupListView = false;
    private String curTerm_Flag = Teaching.ALL_TERM;
    private float mTotalPrice = 0;

    private List<String> mDatas = new ArrayList<>();
    private List<Teaching> mTeachingList = new ArrayList<>();
    private List<Teaching> mTeachingTerm1 = new ArrayList<>();
    private List<Teaching> mTeachingTerm2 = new ArrayList<>();
    private List<Teaching> mTeachingTerm3 = new ArrayList<>();
    private List<Teaching> mTeachingTerm4 = new ArrayList<>();
    private List<Teaching> mTeachingTerm5 = new ArrayList<>();
    private List<Teaching> mTeachingTerm6 = new ArrayList<>();
    private List<Teaching> mTemp = new ArrayList<>();

    private ListView mPopupListView;
    private ImageView iv_down;
    private ImageView ks_back_teach;
    private TextView cur_term;
    private RelativeLayout rl_title;
    private RelativeLayout rl_popup;
    private ListView lv_teachings;
    private TextView tv_ex;

    private ArrayAdapter<String> mStringArrayAdapter;
    private TeachingAdapter mTeachingAdapter;
    private PopupWindow popupWindow;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //顶部状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);

        initDate();
        initView();
        initListener();
    }

    /**
     * 初始化监听事件
     */
    private void initListener() {
        ks_back_teach.setOnClickListener(this);
        iv_down.setOnClickListener(this);
        rl_popup.setOnClickListener(this);
        tv_ex.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        setContentView(R.layout.teach_jcjs_layout);
        iv_down = (ImageView) findViewById(R.id.iv_down);
        ks_back_teach = (ImageView) findViewById(R.id.ks_back_teach);
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        rl_popup = (RelativeLayout) findViewById(R.id.rl_popup);
        cur_term = (TextView) findViewById(R.id.cur_term);
        tv_ex = (TextView) findViewById(R.id.tv_ex);

        lv_teachings = (ListView) findViewById(R.id.lv_teachings);
        lv_teachings.setAdapter(mTeachingAdapter);
    }

    /**
     * 初始化数据
     */
    private void initDate() {
        mDatas.add(Teaching.ALL_TERM);
        mDatas.add(Teaching.TERM1);
        mDatas.add(Teaching.TERM2);
        mDatas.add(Teaching.TERM3);
        mDatas.add(Teaching.TERM4);
        mDatas.add(Teaching.TERM5);
        mDatas.add(Teaching.TERM6);

        mTeachingList.add(new Teaching("Java OOP案例开发", 58.0f, "2015-11-12", Teaching.TERM1));
        mTeachingList.add(new Teaching("高职数学（理工类）", 58.0f, "2015-11-12", Teaching.TERM1));
        mTeachingList.add(new Teaching("高职院校体育与健康教程", 58.0f, "2015-11-17", Teaching.TERM4));
        mTeachingList.add(new Teaching("高职院校学生职业发展案例精选", 58.0f, "2015-11-18", Teaching.TERM1));
        mTeachingList.add(new Teaching("计算机应用基础案例教程（windows7+office2010", 58.0f, "2015-11-18", Teaching.TERM1));
        mTeachingList.add(new Teaching("毛泽东思想和中国特色社会主义理论体系概论", 58.0f, "2015-11-18", Teaching.TERM1));
        mTeachingList.add(new Teaching("前景实用英语自主练习1", 58.0f, "2015-11-12", Teaching.TERM4));
        mTeachingList.add(new Teaching("前景实用英语综合教程1", 58.0f, "2015-11-12", Teaching.TERM1));
        mTeachingList.add(new Teaching("时事报告大学生版", 58.0f, "2015-11-12", Teaching.TERM2));
        mTeachingList.add(new Teaching("学生手册", 58.0f, "2015-11-12", Teaching.TERM1));
        mTeachingList.add(new Teaching("职业发展与素质训练教程", 58.0f, "2015-11-12", Teaching.TERM3));
        mTeachingList.add(new Teaching("MySQL入门很简单", 53.6f, "2016-03-31", Teaching.TERM2));
        mTeachingList.add(new Teaching("大学生心理健康教育", 53.6f, "2016-03-31", Teaching.TERM2));
        mTeachingList.add(new Teaching("前景实用英语自主练习2", 53.6f, "2016-03-31", Teaching.TERM4));
        mTeachingList.add(new Teaching("前景实用英语综合教程2", 53.6f, "2016-03-31", Teaching.TERM2));
        mTeachingList.add(new Teaching("时事报告大学生版", 53.6f, "2016-03-31", Teaching.TERM2));
        mTeachingList.add(new Teaching("数据结构（Java语言描述）", 53.6f, "2016-03-31", Teaching.TERM2));
        mTeachingList.add(new Teaching("思想道德修养与法律基础", 53.6f, "2016-03-31", Teaching.TERM3));
        mTeachingList.add(new Teaching("新编Linux系统基础教程", 53.6f, "2016-03-31", Teaching.TERM2));
        mTeachingList.add(new Teaching("Android移动开发项目教程", 53.6f, "2016-03-31", Teaching.TERM3));

        mTemp = mTeachingList;
        typeDatas();

        mStringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        mTeachingAdapter = new TeachingAdapter();
        initPopupListView();
    }

    /**
     * 数据分类
     */
    private void typeDatas() {
        for(Teaching t : mTeachingList) {
            switch (t.getTerm()) {
                case Teaching.TERM1:
                    mTeachingTerm1.add(t);
                    break;
                case Teaching.TERM2:
                    mTeachingTerm2.add(t);
                    break;
                case Teaching.TERM3:
                    mTeachingTerm3.add(t);
                    break;
                case Teaching.TERM4:
                    mTeachingTerm4.add(t);
                    break;
                case Teaching.TERM5:
                    mTeachingTerm5.add(t);
                    break;
                case Teaching.TERM6:
                    mTeachingTerm6.add(t);
                    break;
            }
        }
    }

    /**
     * 动态初始化ListView
     */
    private void initPopupListView() {
        mPopupListView = new ListView(this);

        mPopupListView.setBackgroundColor(Color.WHITE);
        mPopupListView.setDivider(null);

        mPopupListView.setAdapter(mStringArrayAdapter);
        mPopupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mTotalPrice = 0;
                cur_term.setText("当前学期： " + mDatas.get(position));
                curTerm_Flag = mDatas.get(position);
                switch (curTerm_Flag) {
                    case Teaching.ALL_TERM:
                        mTemp = mTeachingList;
                        break;
                    case Teaching.TERM1:
                        mTemp = mTeachingTerm1;
                        break;
                    case Teaching.TERM2:
                        mTemp = mTeachingTerm2;
                        break;
                    case Teaching.TERM3:
                        mTemp = mTeachingTerm3;
                        break;
                    case Teaching.TERM4:
                        mTemp = mTeachingTerm4;
                        break;
                    case Teaching.TERM5:
                        mTemp = mTeachingTerm5;
                        break;
                    case Teaching.TERM6:
                        mTemp = mTeachingTerm6;
                        break;
                }
                mTeachingAdapter.notifyDataSetChanged();
                popupWindow.dismiss();
            }
        });
    }

    private void showPopupListView() {
        if(popupWindow == null) {
            popupWindow = new PopupWindow(mPopupListView, rl_title.getWidth(), 750);
        }
        //要让view获取焦点，必须为true
        popupWindow.setFocusable(true);
        //设置背景图片
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击外部消失
        popupWindow.setOutsideTouchable(true);
        //显示在et下面
        popupWindow.showAsDropDown(rl_title, 0, 10);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                AnimationUtil.hintPopupListViewAnim(iv_down);
                isShowPopupListView = false;
            }
        });

    }

    /**
     * 点击监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_popup:
                if(isShowPopupListView) {
                    popupWindow.dismiss();
                } else {
                    showPopupListView();
                    AnimationUtil.showPopupListViewAnim(iv_down);
                }
                isShowPopupListView = !isShowPopupListView;
                break;
            case R.id.ks_back_teach:
                onBackPressed();
                finish();
                break;
            case R.id.tv_ex:
                Toast.makeText(JiaoCaiJieSuanActivity.this, "文件已保存（sdcard > jmpt.xls）", Toast.LENGTH_SHORT).show();
        }
    }

    class TeachingAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mTemp.size();
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
            Teaching teaching = mTemp.get(position);

            View view = null;
            if(convertView == null) {
                view = View.inflate(JiaoCaiJieSuanActivity.this, R.layout.teach_jcjs_layout_item, null);
            } else {
                view = convertView;
            }

            TextView tv_teaching_name = (TextView) view.findViewById(R.id.tv_teaching_name);
            TextView tv_teaching_price = (TextView) view.findViewById(R.id.tv_teaching_price);
            TextView tv_teaching_date = (TextView) view.findViewById(R.id.tv_teaching_date);

            tv_teaching_name.setText(teaching.getName());
            tv_teaching_price.setText(teaching.getPrice() + "");
            tv_teaching_date.setText(teaching.getDate());

            int[] colors = { Color.WHITE, Color.rgb(219, 238, 244) };//RGB颜色
            view.setBackgroundColor(colors[position % 2]);// 每隔item之间颜色不同

            return view;
        }
    }
}
