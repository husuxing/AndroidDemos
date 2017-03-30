package lxf.androiddemos.ui.activity;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.widget.LinearLayout;

import lxf.androiddemos.R;

public class LoginTargetActivity extends AppCompatActivity {
    private int screenW;
    private int screenH;
    private LinearLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_target);
        
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screenW =  outMetrics.widthPixels;
        screenH =  outMetrics.heightPixels;

        rootView = (LinearLayout) findViewById(R.id.rootView);

        rootView.post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int[] location = getIntent().getIntArrayExtra("location");
                    Animator animator = ViewAnimationUtils.createCircularReveal(rootView, location[0], location[1], 0,
                            (float) Math.hypot(Math.max(location[0], screenW - location[0]), Math.max(location[1], screenH - location[1])));
                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            //// TODO: 业务逻辑 
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    animator.setDuration(500).start();
                } 
            }
        });
    }
}
