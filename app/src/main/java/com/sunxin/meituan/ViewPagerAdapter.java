package com.sunxin.meituan;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunxin on 2016/10/25.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private List<GridView> mList = new ArrayList<>();

    public ViewPagerAdapter(List<GridView> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
        if (mList == null){
            return 0;
        }
        return mList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        GridView gridView = mList.get(position);
        container.addView(gridView);
        return gridView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
