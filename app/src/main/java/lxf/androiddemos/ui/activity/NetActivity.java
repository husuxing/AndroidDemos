package lxf.androiddemos.ui.activity;

import android.os.Bundle;
import android.view.View;

import io.reactivex.Observable;
import lxf.androiddemos.base.BaseActivity;
import lxf.androiddemos.model.UserEntity;
import lxf.androiddemos.net.NetWork;
import lxf.androiddemos.net.ProgressSubscriber;

public class NetActivity extends BaseActivity {

    @Override
    protected void setView() {
        setContentView(lxf.androiddemos.R.layout.activity_net);
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }

    @Override
    protected void onActivityCreate() {

    }

    public void get(View view){
        getObject("010102", "{\"root\":[{\"userid\":" + 11078 + "}]}", 11078, UserEntity.class)
                .subscribe(new ProgressSubscriber<UserEntity>(this) {
                    @Override
                    public void _onNext(UserEntity userEntity) {
                        showToast(userEntity.toString());
                    }

                    @Override
                    public void _onError(String error) {
                        showToast(error);
                    }
                });
    }

    public void post(View view){

    }

    /**
     * 获取数据，返回为Object
     */
    protected <T> Observable<T> getObject(String code, String info, int userid, final Class<T> cls) {
        return NetWork.getInstance().getObject(code, info, userid, cls);
    }
}
