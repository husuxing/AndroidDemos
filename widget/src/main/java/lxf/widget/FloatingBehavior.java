package lxf.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

public class FloatingBehavior extends FloatingActionButton.Behavior {

    private boolean isAniming;

    public FloatingBehavior(Context context, AttributeSet attrs) {

    }

    /******************************************监听view位置、状态*****************************************************/

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency) || dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
        if (dependency instanceof NestedScrollView) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            int fabBottomMargin = lp.bottomMargin;
            int distanceToScroll = fab.getHeight() + fabBottomMargin;
            fab.setY(dependency.getY() - distanceToScroll);
        }
        return super.onDependentViewChanged(parent, fab, dependency);
    }

    /*******************************************监听滑动****************************************************/

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyConsumed > 0 && !isAniming && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else if (dyConsumed < 0 && !isAniming && child.getVisibility() == View.INVISIBLE) {
            show(child);
        }
    }

    private void show(final View view){
        ValueAnimator animator = ValueAnimator.ofFloat(0,1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (float) animation.getAnimatedValue();
                view.setScaleX(f);
                view.setScaleY(f);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
                isAniming = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAniming = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAniming = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private void hide(final View view){
        ValueAnimator animator = ValueAnimator.ofFloat(1,0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f = (float) animation.getAnimatedValue();
                view.setScaleX(f);
                view.setScaleY(f);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAniming = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.INVISIBLE);
                isAniming = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAniming = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
