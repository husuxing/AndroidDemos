package lxf.widget;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class ViewDragHelperTest extends LinearLayout {

    private ViewDragHelper mDragger;

    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;

    private Point mAutoBackOriginPos = new Point();

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {//返回true表示可以捕获该view
            return true;
        }

        /**
         * 可以在该方法中对child移动的边界进行控制
         * @param child 正在被拖拽的子view
         * @param left 即将移动到的位置
         * @param dx 打算移动的距离
         * @return 实际被移动到的位置
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {

            return top;
        }

        /**
         * 当child不再被拖拽，手指松开时回调；也包括fling状态，速度由系统的最大和最小值决定
         *
         * 调用这个方法后需要确定进入fling状态或者让child移动到某个位置。
         * 需要使用flingCapturedView(int, int, int, int)或者settleCapturedViewAt(int, int)方法。
         * 如果调用了这两个方法之一，则child进入STATE_SETTLING状态(自动滚动状态)；否则进入STATE_IDLE状态
         *
         * @param releasedChild 处于释放状态的被捕获到的子view
         * @param xvel x方向的速度  px/s
         * @param yvel y方向的速度  px/s
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //mAutoBackView手指释放时可以自动回去
            if (releasedChild == mAutoBackView) {
                /**
                 * 设置捕获的view在指定的(left, top)位置。
                 * 移动到指定位置时会有一个适当的速度
                 *
                 * 如果这个方法返回true，需要调用continueSettling(boolean)在随后的每一祯（因为内部使用的Scroller）直到返回false。
                 * 如果返回false，则不会有后续的动作。（看源码貌似指定位置和现在位置重合才会返回false）
                 */
                mDragger.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);

                /**
                 * 设定child在fling状态的x、y方向的最大最小边界，依然要不断调用continueSettling(boolean)。
                 */
//                mDragger.flingCapturedView(releasedChild.getLeft() + (int)xvel /10,releasedChild.getTop()  + (int)yvel/10,releasedChild.getLeft() + (int)xvel/5,releasedChild.getTop()  + (int)yvel/5);
                invalidate();
            }
        }

        /**
         * 当用户从父布局被订阅的边界拖拽，且没有被捕获的子view时调用。
         * 需配合mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);//检测左边界
         *
         * @param edgeFlags EDGE_LEFT   EDGE_TOP  EDGE_RIGHT  EDGE_BOTTOM  EDGE_ALL
         * @param pointerId ID of the pointer touching the described edge(s)
         */
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            /**
             * 捕获一个特殊的child，会绕过tryCaptureView()方法
             * 第一个参数为想要捕获的view
             */
            mDragger.captureChildView(mEdgeTrackerView, pointerId);
        }

        /**
         * 只有在child消费事件时，才需要进行这个判断
         * 返回水平方向的拖拽距离的范围（px），返回0表示水平方向不能拖拽
         * @param child 要检测的view
         * @return 水平方向的拖拽距离的范围（px）
         */
        @Override
        public int getViewHorizontalDragRange(View child)
        {
            return getMeasuredWidth()-child.getMeasuredWidth();
        }

        @Override
        public int getViewVerticalDragRange(View child)
        {
            return getMeasuredHeight()-child.getMeasuredHeight();
        }
    };

    public ViewDragHelperTest(Context context) {
        super(context);
        init();
    }

    public ViewDragHelperTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewDragHelperTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDragger = ViewDragHelper.create(this, callback);
        mDragger = ViewDragHelper.create(this, 1.0f, callback);
        mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);//检测左边界
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragger.shouldInterceptTouchEvent(ev);//判断是不是需要拦截这个触摸事件
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);//处理触摸事件
        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
    }
}
