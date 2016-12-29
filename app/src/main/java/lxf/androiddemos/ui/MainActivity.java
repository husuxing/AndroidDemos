package lxf.androiddemos.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import lxf.androiddemos.BR;
import lxf.androiddemos.R;
import lxf.androiddemos.base.BaseActivity;
import lxf.androiddemos.base.BaseRecyclerBindingAdapter;
import lxf.androiddemos.databinding.ActivityMainBinding;
import lxf.androiddemos.model.MainRecyclerHeader;
import lxf.androiddemos.model.MainRecyclerItem;
import lxf.widget.recyclerview.itemtouchhelper.MyItemTouchHelperCallback;
import lxf.widget.util.RecyclerViewUtils;

public class MainActivity extends BaseActivity {

    private List<Object> datas;
    private ActivityMainBinding binding;

    @Override
    protected void setView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        datas = new ArrayList<>();
        MainRecyclerHeader header = new MainRecyclerHeader();
        header.setHeader("我是头部文件");
        datas.add(header);
        for (int i = 0; i < MainRecyclerItem.items.length; i++) {
            MainRecyclerItem item = new MainRecyclerItem();
            item.setContent(MainRecyclerItem.items[i]);
            datas.add(item);
        }
    }

    @Override
    protected void onActivityCreate() {
        BaseRecyclerBindingAdapter bindingAdapter = new BaseRecyclerBindingAdapter(datas) {
            @Override
            public int getItemLayoutId(int viewType) {
                return viewType;
            }

            @Override
            public int getItemVariableId() {
                return BR.item;//对应item布局里面data标签中的name
            }

            @Override
            public int getItemViewType(int position) {
                if (position == 0)
                    return R.layout.header_recycler_main;
                else
                    return R.layout.item_recycler_main;
            }
        };
        RecyclerViewUtils.setLinearManagerAndAdapter(binding.recyclerview, bindingAdapter);

        //item可以长按拖动进行位置交换
        ItemTouchHelper.Callback callBack = new MyItemTouchHelperCallback(bindingAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBack);
        itemTouchHelper.attachToRecyclerView(binding.recyclerview);
    }

}
