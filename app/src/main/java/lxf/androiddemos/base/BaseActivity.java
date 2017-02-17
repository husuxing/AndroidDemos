package lxf.androiddemos.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import lxf.widget.util.SharedPrefsUtil;
import lxf.widget.util.ToastUtils;
import lxf.androiddemos.net.NetWork;


/**
 * Activity基类，便于统一管理
 * <p>
 * Created by lxf on 2016/9/26.
 */
public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 订阅关系集合
     */
    protected CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowFlags();
        initVariables(savedInstanceState);
        setView();
        onActivityCreate();
    }

    protected void setWindowFlags() {

    }

    protected abstract void initVariables(Bundle savedInstanceState);

    protected abstract void setView();

    protected abstract void onActivityCreate();

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null)
            compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
        super.onDestroy();
    }


    //------------------------------------------辅助方法-----------------------------------------------//

    /**
     * 获取数据，返回为Object
     */
    protected <T> Observable<T> getObject(String code, String info, int userid, final Class<T> cls) {
        return NetWork.getInstance().getObject(code, info, userid, cls);
    }

    /**
     * 获取数据，返回为List
     */
    public static <T> Observable<List<T>> getList(String code, String info, int userid, final Class<T> cls) {
        return NetWork.getInstance().getList(code, info, userid, cls);
    }

    /**
     * 提交数据，返回为Object
     */
    protected <T> Observable<T> postObject(String code, String info, int userid, final Class<T> cls) {
        return NetWork.getInstance().postObject(code, info, userid, cls);
    }

    /**
     * 提交数据，返回为List
     */
    protected <T> Observable<List<T>> postList(String code, String info, int userid, final Class<T> cls) {
        return NetWork.getInstance().postList(code, info, userid, cls);
    }

    /**
     * 显示Toast
     */
    public void showToast(String content) {
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

    /**
     * 跳转到Activity，并请求返回结果
     */
    protected void startToActivityForResult(Class<?> cls, int requeseCode, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && bundle != null) {
            startActivityForResult(intent, requeseCode, bundle);
        } else
            startActivityForResult(intent, requeseCode);

    }

    /**
     * 字符串是否为空
     */
    protected boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

}
