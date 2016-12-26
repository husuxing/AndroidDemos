package lxf.androiddemos.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lxf.androiddemos.R;
import lxf.androiddemos.base.BaseActivity;
import lxf.widget.recyclerview.LoadMoreRecyclerView;
import lxf.widget.recyclerview.adapter.BaseRecyclerAdapter;
import lxf.widget.recyclerview.adapter.RecyclerViewHolder;
import lxf.widget.recyclerview.itemtouchhelper.MyItemTouchHelperCallback;

public class MainActivity extends BaseActivity{

    private List<String> datas;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        datas = new ArrayList<>();
        datas.add("ViewDragHelper");
        datas.add("自定义Behavior");
    }

    @Override
    protected void initViews() {
        LoadMoreRecyclerView recyclerView = (LoadMoreRecyclerView) findViewById(R.id.recyclerview);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            BaseRecyclerAdapter<String> adapter = new BaseRecyclerAdapter<String>(datas) {
                @Override
                public int getItemLayoutId(int viewType) {
                    return R.layout.item_recycler_main;
                }

                @Override
                public void bindData(RecyclerViewHolder holder, int position, String item) {
                    holder.setText(R.id.tv_content,item);
                }
            };
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int pos) {
                    switch (pos){
                        case 0:
                            startToActivity(ViewDragHelperActivity.class);
                            break;
                        case 1:
                            startToActivity(BehaviorActivity.class);
                            break;
                    }
                }
            });
            recyclerView.setAdapter(adapter);

            ItemTouchHelper.Callback callBack = new MyItemTouchHelperCallback(adapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBack);
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);//加载menu文件到布局
        return true;
    }

}
