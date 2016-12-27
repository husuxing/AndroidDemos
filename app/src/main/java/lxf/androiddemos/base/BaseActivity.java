package lxf.androiddemos.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import lxf.widget.util.SharedPrefsUtil;
import lxf.widget.util.ToastUtils;


/**
 * Activity基类
 * Created by lxf2016 on 2016/8/5.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowFlags();
        setView();
        initVariables(savedInstanceState);
        onActivityCreate();
    }

    /**
     * 设置窗体属性
     */
    protected void setWindowFlags() {
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 设置布局
     *
     */
    protected abstract void setView();

    /**
     * 初始化成员变量
     *
     * @param savedInstanceState activity被系统回收时保存的数据
     */
    protected abstract void initVariables(Bundle savedInstanceState);

    protected abstract void onActivityCreate();

    /**
     * 显示Toast
     */
    public  void showToast(String content) {
        ToastUtils.showShort(getApplicationContext(), content);
    }

    /**
     * 通过SharePre保存数据到本地
     */
    protected void putValue(String key, Object value) {
        if (value instanceof Integer) {
            SharedPrefsUtil.putValue(getApplicationContext(), key, (Integer) value);
        } else if (value instanceof Boolean) {
            SharedPrefsUtil.putValue(getApplicationContext(), key, (Boolean) value);
        } else if (value instanceof String) {
            SharedPrefsUtil.putValue(getApplicationContext(), key, (String) value);
        }
    }

    /**
     * 通过SharePre获取本地数据
     */
    protected Object getValue(String key, Object value) {
        if (value instanceof Integer) {
            return SharedPrefsUtil.getValue(getApplicationContext(), key, (Integer) value);
        } else if (value instanceof Boolean) {
            return SharedPrefsUtil.getValue(getApplicationContext(), key, (Boolean) value);
        } else if (value instanceof String) {
            return SharedPrefsUtil.getValue(getApplicationContext(), key, (String) value);
        }
        return null;
    }

    /**
     * 清除SharePre的本地数据
     */
    protected void removeValue(String key) {
        SharedPrefsUtil.removeValue(getApplicationContext(), key);
    }

    /**
     * 跳转到Activity，不携带数据
     */
    protected void startToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * 跳转到Activity，携带数据
     */
    protected void startToActivity(Class<?> cls, String key, @Nullable Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(key, bundle);
        startActivity(intent);
    }
}
