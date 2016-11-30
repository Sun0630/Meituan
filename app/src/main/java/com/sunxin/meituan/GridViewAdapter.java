package com.sunxin.meituan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunxin on 2016/10/25.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Model> mDatas = new ArrayList<>();
    //当前页的下标
    private int currIndex;
    //每页的条目数
    private int pageSize;

    public GridViewAdapter(Context context, List<Model> mDatas, int currIndex, int pageSize) {
        mContext = context;
        this.mDatas = mDatas;
        this.currIndex = currIndex;
        this.pageSize = pageSize;
    }

    /**
     * 先判断数据集的大小是否足够显示满本页？mDatas.size() > (curIndex+1)*pageSize,
     * 如果够，则直接返回每一页显示的最大条目个数pageSize,
     * 如果不够，则有几项返回几,(mDatas.size() - curIndex * pageSize);(也就是最后一页的时候就显示剩余item)
     */
    @Override
    public int getCount() {
//        return mDatas.size() > (currIndex + 1) * pageSize ? pageSize : (mDatas.size() - currIndex * pageSize);
        return mDatas.size() > (currIndex + 1) * pageSize ? pageSize : (mDatas.size() - currIndex * pageSize);
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position + currIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + currIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_gridview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        //计算位置
        int pos = position + currIndex * pageSize;
        holder.mImageView.setImageResource(mDatas.get(pos).iconRes);
        holder.mTextView.setText(mDatas.get(pos).appName);
        return convertView;
    }

    class ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View view) {
            mTextView = (TextView) view.findViewById(R.id.textView);
            mImageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }
}
