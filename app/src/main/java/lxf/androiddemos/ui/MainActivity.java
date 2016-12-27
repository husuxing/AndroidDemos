package lxf.androiddemos.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lxf.androiddemos.R;
import lxf.androiddemos.base.BaseActivity;
import lxf.androiddemos.databinding.ActivityMainBinding;
import lxf.widget.recyclerview.adapter.BaseRecyclerAdapter;
import lxf.widget.recyclerview.adapter.RecyclerViewHolder;
import lxf.widget.recyclerview.itemtouchhelper.MyItemTouchHelperCallback;
import lxf.widget.util.RecyclerViewUtils;

public class MainActivity extends BaseActivity {

    private List<String> datas;
    private ActivityMainBinding binding;

    @Override
    protected void setView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        datas = new ArrayList<>();
        datas.add("ViewDragHelper");
        datas.add("自定义Behavior");
        datas.add("二维码");
        datas.add("DataBinding");
    }

    @Override
    protected void onActivityCreate() {
        BaseRecyclerAdapter<String> adapter = new BaseRecyclerAdapter<String>(datas) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_recycler_main;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, String item) {
                holder.setText(R.id.tv_content, item);
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                switch (pos) {
                    case 0:
                        startToActivity(ViewDragHelperActivity.class);
                        break;
                    case 1:
                        startToActivity(BehaviorActivity.class);
                        break;
                    case 2:
                        startToActivity(ZxingActivity.class);
                        break;
                    case 3:
                        startToActivity(DatabindingActivity.class);
                        break;
                }
            }
        });
        RecyclerViewUtils.setLinearManagerAndAdapter(binding.recyclerview,adapter);


        ItemTouchHelper.Callback callBack = new MyItemTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBack);
        itemTouchHelper.attachToRecyclerView(binding.recyclerview);
    }

}
