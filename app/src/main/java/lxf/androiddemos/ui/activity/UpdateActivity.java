package lxf.androiddemos.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import lxf.androiddemos.R;
import lxf.androiddemos.base.BaseActivity;
import lxf.androiddemos.ui.service.MyIntentService;

public class UpdateActivity extends BaseActivity {

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_update);
    }

    @Override
    protected void onActivityCreate() {

    }

    public void startDownload(View view){
        String url = "http://www.izis.cn/mygoedu/yztv_1.apk";
        String apkPath = Environment.getExternalStorageDirectory().getPath() + "/yzkj.apk";
        MyIntentService.startUpdateService(this,url,apkPath);
    }
}
