package lxf.widget.flowlayout;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 流布局适配器
 * Created by lxf on 2017/2/8.
 */
public abstract class TagAdapter<T>
{
    private List<T> mTagDatas;
    private OnDataChangedListener mOnDataChangedListener;

    public TagAdapter(List<T> datas)
    {
        mTagDatas = datas;
    }

    public TagAdapter(T[] datas)
    {
        mTagDatas = new ArrayList<>(Arrays.asList(datas));
    }

    interface OnDataChangedListener
    {
        void onChanged();
    }

    void setOnDataChangedListener(OnDataChangedListener listener)
    {
        mOnDataChangedListener = listener;
    }

    public int getCount()
    {
        return mTagDatas == null ? 0 : mTagDatas.size();
    }

    public void notifyDataChanged()
    {
        mOnDataChangedListener.onChanged();
    }

    public T getItem(int position)
    {
        return mTagDatas.get(position);
    }

    public abstract View getView(FlowLayout parent, int position, T t);

}