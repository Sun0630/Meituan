package com.sunxin.meituan;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] titles = {"美食", "电影", "酒店住宿", "休闲娱乐", "外卖", "自助餐", "KTV", "机票/火车票", "周边游", "美甲美睫",
            "火锅", "生日蛋糕", "甜品饮品", "水上乐园", "汽车服务", "美发", "丽人", "景点", "足疗按摩", "运动健身", "健身", "超市", "买菜",
            "今日新单", "小吃快餐", "面膜", "洗浴/汗蒸", "母婴亲子", "生活服务", "婚纱摄影", "学习培训", "家装", "结婚", "全部分配"};


    //ViewPager
    private ViewPager mViewPager;
    //点
    private LinearLayout mLlDot;
    private List<Model> mDatas;
    private LayoutInflater mInflater;
    private List<GridView> mPagerList;
    //规定每页的大小为 10
    private int pageSize = 10;
    //页数
    private int mPageCount;
    //当前是第几页
    private int mCurrIndex;
    private GridView mGridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        //初始化数据
        initData();

        //设置圆点
        setOvlDot();

    }


    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mLlDot = (LinearLayout) findViewById(R.id.ll_dot);
    }

    private void initData() {
        //数据源,把数据封装到集合
        mDatas = new ArrayList<>();
        //存的是GridView
        mPagerList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            //动态获取图片的资源id
            int imageId = getResources().getIdentifier("ic_category_" + i, "mipmap", getPackageName());
            mDatas.add(new Model(titles[i], imageId));
        }
        //填充器
        mInflater = LayoutInflater.from(this);
        //为GridView分页
        //总的页数 = 总的条目数  /  每页的条目数，并取整
        mPageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);

        for (int i = 0; i < mPageCount; i++) {
            mGridView = (GridView) mInflater.inflate(R.layout.gridview, mViewPager, false);
            mGridView.setAdapter(new GridViewAdapter(this, mDatas, i, pageSize));
            mPagerList.add(mGridView);
            initListener(i);
        }

        //为ViewPager设置适配器
        mViewPager.setAdapter(new ViewPagerAdapter(mPagerList));

    }

    private void initListener(final int currIndex) {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = position + currIndex * pageSize;
                Toast.makeText(MainActivity.this, mDatas.get(pos).appName + "---" + pos, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setOvlDot() {
        //有几页就有几个点
        for (int i = 0; i < mPageCount; i++) {
            mLlDot.addView(mInflater.inflate(R.layout.dot, null));
        }
        //默认选中第一个点
        mLlDot.getChildAt(0).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);

        //当ViewPager滑动的时候换点
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //先取消选中一个点
                mLlDot.getChildAt(mCurrIndex).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_normal);
                //在选中
                mLlDot.getChildAt(position).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);
                mCurrIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
