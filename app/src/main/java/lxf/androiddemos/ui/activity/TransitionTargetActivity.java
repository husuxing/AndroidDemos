package lxf.androiddemos.ui.activity;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;

import lxf.androiddemos.R;

public class TransitionTargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_transition_target);

        setTitle("目标页面");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP
                && getIntent().getBooleanExtra("sceneAnim", false)) {
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.addTarget(R.id.imageView);
//            slide.excludeTarget();
            slide.setDuration(500);
            getWindow().setEnterTransition(slide);
        }

        View sharedElement = findViewById(R.id.button);
        ViewCompat.setTransitionName(sharedElement, "shareBtn");

        View sharedElement2 = findViewById(R.id.imageView);
        ViewCompat.setTransitionName(sharedElement2, "shareImg");

        sharedElement2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = ViewAnimationUtils.createCircularReveal(v, v.getWidth() / 2, v.getHeight() / 2, 0, (float) Math.hypot(v.getWidth() / 2, v.getHeight() / 2));
                    animator.setDuration(1500);
                    animator.start();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}
