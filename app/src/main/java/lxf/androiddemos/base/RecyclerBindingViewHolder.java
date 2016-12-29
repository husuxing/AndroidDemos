package lxf.androiddemos.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

class RecyclerBindingViewHolder extends RecyclerView.ViewHolder {
    ViewDataBinding binding;

    private RecyclerBindingViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    static RecyclerBindingViewHolder createViewHolder(ViewGroup parent, int layoutId) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),layoutId,parent,false);
        return new RecyclerBindingViewHolder(binding);
    }
}
