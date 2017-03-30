package lxf.androiddemos.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lxf.androiddemos.R;
import lxf.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(final View view) {
        LoginButton lb = (LoginButton) view;
        final int[] location = new int[2];
        lb.getLocationInWindow(location);
        location[0] = location[0] + lb.getWidth() / 2;
        location[1] = location[1] + lb.getHeight() / 2;
        lb.setLoading(new LoginButton.Loading() {
            @Override
            public void startLoading() {
                loading(view, location);
            }
        });
        lb.click();
    }

    private void loading(final View view, final int[] location) {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        Intent intent = new Intent(view.getContext(), LoginTargetActivity.class);
                        intent.putExtra("location", location);
                        view.getContext().startActivity(intent);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ((Activity) view.getContext()).overridePendingTransition(0, 0);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_SHORT).show();
                        ((LoginButton) view).reset();
                    }

                    @Override
                    public void onComplete() {
                        finish();
                    }
                });
    }
}
