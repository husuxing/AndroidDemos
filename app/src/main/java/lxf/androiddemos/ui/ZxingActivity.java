package lxf.androiddemos.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import lxf.androiddemos.R;
import lxf.androiddemos.base.BaseBindingActivity;
import lxf.androiddemos.databinding.ActivityZxingBinding;
import lxf.androiddemos.model.ZxingModel;

public class ZxingActivity extends BaseBindingActivity {
    private ZxingModel model;

    @Override
    protected void initBinding() {
        ActivityZxingBinding binding =  DataBindingUtil.setContentView(this,R.layout.activity_zxing);
        binding.setZxing(model);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        model = new ZxingModel();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
            model.content.set(data.getDataString());
    }
}
