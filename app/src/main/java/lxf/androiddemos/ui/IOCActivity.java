package lxf.androiddemos.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import lxf.androiddemos.R;
import lxf.ioc.InjectUtil;
import lxf.ioc.annotation.ContentView;

@ContentView(R.layout.activity_ioc)
public class IOCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InjectUtil.init(this);
    }
}
