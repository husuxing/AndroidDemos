package lxf.androiddemos.ui.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;

import lxf.androiddemos.R;
import lxf.androiddemos.databinding.ActivityLottieBinding;
import lxf.androiddemos.model.LottieModel;

public class LottieActivity extends AppCompatActivity {
    private LottieModel data;
    private ActivityLottieBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lottie);
        data = new LottieModel();
        binding.setData(data);

        binding.checkboxLoop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                binding.lottieView.loop(isChecked);
            }
        });

        binding.lottieView.setAnimation("data.json");
        binding.lottieView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        binding.lottieView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });

    }

    public void startAnimation(View view) {
        binding.lottieView.playAnimation();
    }

    public void pauseAnimation(View view) {
        binding.lottieView.pauseAnimation();
    }

    public void resumeAnimation(View view) {
        binding.lottieView.resumeAnimation();
    }

    public void stopAnimation(View view) {
        binding.lottieView.cancelAnimation();
    }
}
