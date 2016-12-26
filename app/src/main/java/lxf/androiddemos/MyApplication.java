package lxf.androiddemos;

import android.app.Application;
import android.content.Context;

/**
 * 自定义Application
 * Created by lxf2016 on 2016/8/3.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return getInstance().getApplicationContext();
    }

    /**
     * 在内存低时,发送广播可以释放一些内存
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
