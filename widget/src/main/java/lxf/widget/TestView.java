package lxf.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * 测试用的自定义view
 * Created by lxf on 2017/2/17.
 */
public class TestView extends View implements GestureDetector.OnGestureListener {
    private final String TAG = "TestView";
    private GestureDetector gestureDetector;//手势检测
    private Scroller scroller;//弹性滑动

    public TestView(Context context) {
        super(context);
        init();
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        gestureDetector = new GestureDetector(getContext(), this);
        scroller = new Scroller(getContext());

//        TranslateAnimation animation = new TranslateAnimation(0,100,0,0);
//        setAnimation(animation);
//        animation.start();

//        ObjectAnimator.ofInt(targetView,"translationX",0,100).setDuration(500).start();

//        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLayoutParams();
//        params.leftMargin += 100;
//        setayoutParams(params);
////      或requestLayout();

//        ValueAnimator animator = ValueAnimator.ofFloat(0,100).setDuration(1000);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float flag = animation.getAnimatedFraction();
//                scrollTo(startX + (int)(delayX * flag),0);
//            }
//        });
//        animator.start();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        /**
//         * 某个触摸时间的速度测量：
//         * 1.通过obtain()方法获得velocityTracker变量，并添加需要测量的事件
//         * 2.通过computeCurrentVelocity计算速度（参数单位为ms），再通过getter方法获取速度值(右下滑为正数，反之为负数)
//         * 3.释放velocityTracker对象。
//         */
//        VelocityTracker velocityTracker = VelocityTracker.obtain();
//        velocityTracker.addMovement(event);
//
//        velocityTracker.computeCurrentVelocity(1000);//秒速
//        int v_x = (int) velocityTracker.getXVelocity();
//        int v_y = (int) velocityTracker.getYVelocity();
//
//        LogUtils.i(TAG,"\t\tX:" + v_x + "\t\tY:" + v_y);
//
//        velocityTracker.recycle();
//
//        return true;
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    private void smoothScrollTo(int x, int y) {
        int scrollX = getScrollX();
        int delayX = x - scrollX;
        scroller.startScroll(scrollX, 0, delayX, 0, 1000);//保存相应参数
        invalidate();//onDraw方法中会调用computeScroll
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }


}
