package lxf.androiddemos.ui;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lxf.androiddemos.R;
import lxf.androiddemos.base.BaseActivity;


public class BehaviorActivity extends BaseActivity {

    private List<Integer> datas;
    private NestedScrollView scrollView;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_behavior;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add(i + 1);
        }
    }

    @Override
    protected void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    //                        .setAction("Action", null).show();
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(scrollView);
                    bottomSheetBehavior.setState(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED ? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_COLLAPSED);
                }
            });
        }

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MyAdapter());
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView tv = new TextView(parent.getContext());
            tv.setGravity(Gravity.CENTER);
            return new MyViewHolder(tv);
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(String.valueOf(datas.get(position)));
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView;
            }
        }
    }
}
