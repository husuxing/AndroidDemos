package lxf.androiddemos.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import lxf.androiddemos.base.BaseActivity;

import lxf.androiddemos.R;

/**
 * View学习
 */
public class ViewActivity extends BaseActivity {

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }

    @Override
    protected void setView() {
        DataBindingUtil.setContentView(this, R.layout.activity_view);
    }

    @Override
    protected void onActivityCreate() {

    }
}
