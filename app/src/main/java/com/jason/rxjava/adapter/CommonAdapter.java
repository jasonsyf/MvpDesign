package com.jason.rxjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 太能 on 2016/11/10.
 */
public abstract class CommonAdapter<T> extends BaseAdapter implements OnClickBack {
    private List<T> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private int mLayoutId;

    public CommonAdapter(Context context, List<T> data, int layoutId) {
        if (data != null) {
            mData.addAll(data);
        }
        mLayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        T t = null;
        if (position >= 0 && position < getCount()) {
            t = mData.get(position);
        }
        return t;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder;
        if (convertView != null) {
            holder = (BaseViewHolder) convertView.getTag();
            setViewDimen(convertView);
        } else {
            convertView = mInflater.inflate(mLayoutId, parent, false);
            holder = new BaseViewHolder(convertView,this);
            setViewDimen(convertView);
            setListeners(holder,convertView,position);
        }
        holder.setPosition(position);
        setViewData(position,holder,getItem(position));
        return convertView;
    }

    public void update(List<T> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public List<T> getData(){
        return mData;
    }

    public void append(T response){
        if (response!=null){
            mData.add(response);
        }
        notifyDataSetChanged();
    }

    protected abstract void setListeners(BaseViewHolder holder,View view,int position);

    protected abstract void setViewDimen(View convertView);

    protected abstract void setViewData(int position, BaseViewHolder holder, T item);
}
