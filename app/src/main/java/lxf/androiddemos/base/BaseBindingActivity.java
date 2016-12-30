package lxf.androiddemos.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lxf on 2016/12/30.
 */
public abstract class BaseBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables(savedInstanceState);
        initBinding();
    }

    protected abstract void initVariables(Bundle savedInstanceState);

    protected abstract void initBinding();


}
