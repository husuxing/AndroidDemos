package lxf.androiddemos.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lxf.widget.recyclerview.itemtouchhelper.ChangeDataLinstener;

public abstract class BaseRecyclerBindingAdapter extends RecyclerView.Adapter<RecyclerBindingViewHolder> implements ChangeDataLinstener{
    protected List<Object> mData;

    public BaseRecyclerBindingAdapter(List<Object> list) {
        mData = (list != null) ? list : new ArrayList<>();
    }

    @Override
    public RecyclerBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecyclerBindingViewHolder.createViewHolder(parent,getItemLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(RecyclerBindingViewHolder holder, int position) {
        holder.binding.setVariable(getItemVariableId(),mData.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setmData(List<Object> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void add(int pos, Object item) {
        mData.add(pos, item);
        notifyItemInserted(pos);
    }

    public void delete(int pos) {
        mData.remove(pos);
        notifyItemRemoved(pos);
    }

    //item布局id
    public abstract int getItemLayoutId(int viewType);

    //对应item布局里面data标签中的name，会自动生成一个BR.xxx属性，类似于R文件
    public abstract int getItemVariableId();


    @Override
    public void swap(int from, int to) {
        //交换list中两个对象的位置
        Collections.swap(mData, from, to);
        notifyItemMoved(from, to);
    }

    @Override
    public void del(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}