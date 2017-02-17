package lxf.widget.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lxf.widget.R;

/**
 * 流布局
 * Created by lxf on 2017/2/7/007.
 */
public class FlowLayout extends ViewGroup {


    private static final String TAG = "FlowLayout";
    private static final int LEFT = -1;
    private static final int CENTER = 0;
    private static final int RIGHT = 1;

    /**
     * 存储所有的View，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<>();
    /**
     * 记录每一行的最大高度
     */
    private List<Integer> mLineHeight = new ArrayList<>();
    protected List<Integer> mLineWidth = new ArrayList<>();
    private int mGravity;
    /**
     * 存储每一行所有的childView
     */
    private List<View> lineViews = new ArrayList<>();

    public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TagFlowLayout);
        mGravity = ta.getInt(R.styleable.TagFlowLayout_gravity, LEFT);
        ta.recycle();
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context) {
        this(context, null);
    }

    /**
     * 负责设置子控件的测量模式和大小 根据所有子控件设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        Log.e(TAG, sizeWidth + "," + sizeHeight);

        // 如果是warp_content情况下，记录宽和高
        int width = 0;
        int height = 0;
        /**
         * 记录每一行的宽度，width不断取最大宽度
         */
        int lineWidth = 0;
        /**
         * 每一行的高度，累加至height
         */
        int lineHeight = 0;

        int cCount = getChildCount();

        // 遍历每个子元素
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            // 测量每一个child的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 得到child的lp
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();
            // 当前子空间实际占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin
                    + lp.rightMargin;
            // 当前子空间实际占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin
                    + lp.bottomMargin;
            /**
             * 如果加入当前child，则超出最大宽度，则的到目前最大宽度给width，类加height 然后开启新行
             */
            if (lineWidth + childWidth > sizeWidth) {
                width = Math.max(lineWidth, childWidth);// 取最大的
                lineWidth = childWidth; // 重新开启新行，开始记录
                // 叠加当前高度，
                height += lineHeight;
                // 开启记录下一行的高度
                lineHeight = childHeight;
            } else
            // 否则累加值lineWidth,lineHeight取最大高度
            {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较
            if (i == cCount - 1) {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }

        }
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth
                : width, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight
                : height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();
        mLineWidth.clear();
        lineViews.clear();

        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;
        int cCount = getChildCount();
        // 遍历所有的孩子
        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == View.GONE) continue;
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            // 如果已经需要换行
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - getPaddingLeft() - getPaddingRight()) {
                // 记录这一行所有的View以及最大高度
                mLineHeight.add(lineHeight);
                // 将当前行的childView保存，然后开启新的ArrayList保存下一行的childView
                mAllViews.add(lineViews);
                mLineWidth.add(lineWidth);

                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                lineViews = new ArrayList<>();
            }
            /**
             * 如果不需要换行，则累加
             */
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin
                    + lp.bottomMargin);
            lineViews.add(child);
        }
        // 记录最后一行
        mLineHeight.add(lineHeight);
        mLineWidth.add(lineWidth);
        mAllViews.add(lineViews);

        int left = getPaddingLeft();
        int top = getPaddingTop();
        // 得到总行数
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++) {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            // 当前行的最大高度
            lineHeight = mLineHeight.get(i);

            Log.e(TAG, "第" + i + "行 ：" + lineViews.size() + " , " + lineViews);
            Log.e(TAG, "第" + i + "行， ：" + lineHeight);

            // set gravity
            int currentLineWidth = this.mLineWidth.get(i);
            switch (this.mGravity) {
                case LEFT:
                    left = getPaddingLeft();
                    break;
                case CENTER:
                    left = (width - currentLineWidth) / 2 + getPaddingLeft();
                    break;
                case RIGHT:
                    left = width - currentLineWidth + getPaddingLeft();
                    break;
            }


            // 遍历当前行所有的View
            for (int j = 0; j < lineViews.size(); j++) {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child
                        .getLayoutParams();

                //计算childView的left,top,right,bottom
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                Log.e(TAG, child + " , l = " + lc + " , t = " + t + " , r ="
                        + rc + " , b = " + bc);

                child.layout(lc, tc, rc, bc);

                left += child.getMeasuredWidth() + lp.rightMargin
                        + lp.leftMargin;
            }
            top += lineHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }


//    /**
//     * resort child elements to use lines as few as possible
//     */
//    public void relayoutToCompress() {
//        post(new Runnable() {
//            @Override
//            public void run() {
//                compress();
//            }
//        });
//    }
//    private void compress() {
//        int childCount = this.getChildCount();
//        if (0 == childCount) {
//            //no need to sort if flowlayout has no child view
//            return;
//        }
//        int count = 0;
//        for (int i = 0; i < childCount; i++) {
//            View v = getChildAt(i);
//            if (v instanceof BlankView) {
//                //BlankView is just to make childs look in alignment, we should ignore them when we relayout
//                continue;
//            }
//            count++;
//        }
//        View[] childs = new View[count];
//        int[] spaces = new int[count];
//        int n = 0;
//        for (int i = 0; i < childCount; i++) {
//            View v = getChildAt(i);
//            if (v instanceof BlankView) {
//                //BlankView is just to make childs look in alignment, we should ignore them when we relayout
//                continue;
//            }
//            childs[n] = v;
//            LayoutParams childLp = v.getLayoutParams();
//            int childWidth = v.getMeasuredWidth();
//            if (childLp instanceof MarginLayoutParams) {
//                MarginLayoutParams mlp = (MarginLayoutParams) childLp ;
//                spaces[n] = mlp.leftMargin + childWidth + mlp.rightMargin;
//            } else {
//                spaces[n] = childWidth;
//            }
//            n++;
//        }
//        int[] compressSpaces = new int[count];
//        for (int i = 0; i < count; i++) {
//            compressSpaces[i] = spaces[i] > usefulWidth ? usefulWidth : spaces[i];
//        }
//        sortToCompress(childs, compressSpaces);
//        this.removeAllViews();
//        for (View v : childList) {
//            this.addView(v);
//        }
//        childList.clear();
//    }
//
//    private void sortToCompress(View[] childs, int[] spaces) {
//        int childCount = childs.length;
//        int[][] table = new int[childCount + 1][usefulWidth + 1];
//        for (int i = 0; i < childCount +1; i++) {
//            for (int j = 0; j < usefulWidth; j++) {
//                table[i][j] = 0;
//            }
//        }
//        boolean[] flag = new boolean[childCount];
//        for (int i = 0; i < childCount; i++) {
//            flag[i] = false;
//        }
//        for (int i = 1; i <= childCount; i++) {
//            for (int j = spaces[i-1]; j <= usefulWidth; j++) {
//                table[i][j] = (table[i-1][j] > table[i-1][j-spaces[i-1]] + spaces[i-1]) ? table[i-1][j] : table[i-1][j-spaces[i-1]] + spaces[i-1];
//            }
//        }
//        int v = usefulWidth;
//        for (int i = childCount ; i > 0 && v >= spaces[i-1]; i--) {
//            if (table[i][v] == table[i-1][v-spaces[i-1]] + spaces[i-1]) {
//                flag[i-1] =  true;
//                v = v - spaces[i - 1];
//            }
//        }
//        int rest = childCount;
//        View[] restArray;
//        int[] restSpaces;
//        for (int i = 0; i < flag.length; i++) {
//            if (flag[i] == true) {
//                childList.add(childs[i]);
//                rest--;
//            }
//        }
//
//        if (0 == rest) {
//            return;
//        }
//        restArray = new View[rest];
//        restSpaces = new int[rest];
//        int index = 0;
//        for (int i = 0; i < flag.length; i++) {
//            if (flag[i] == false) {
//                restArray[index] = childs[i];
//                restSpaces[index] = spaces[i];
//                index++;
//            }
//        }
//        table = null;
//        childs = null;
//        flag = null;
//        sortToCompress(restArray, restSpaces);
//    }
//
//    /**
//     * add some blank view to make child elements look in alignment
//     */
//    public void relayoutToAlign() {
//        post(new Runnable() {
//            @Override
//            public void run() {
//                align();
//            }
//        });
//    }
//
//    private void align() {
//        int childCount = this.getChildCount();
//        if (0 == childCount) {
//            //no need to sort if flowlayout has no child view
//            return;
//        }
//        int count = 0;
//        for (int i = 0; i < childCount; i++) {
//            View v = getChildAt(i);
//            if (v instanceof BlankView) {
//                //BlankView is just to make childs look in alignment, we should ignore them when we relayout
//                continue;
//            }
//            count++;
//        }
//        View[] childs = new View[count];
//        int[] spaces = new int[count];
//        int n = 0;
//        for (int i = 0; i < childCount; i++) {
//            View v = getChildAt(i);
//            if (v instanceof BlankView) {
//                //BlankView is just to make childs look in alignment, we should ignore them when we relayout
//                continue;
//            }
//            childs[n] = v;
//            LayoutParams childLp = v.getLayoutParams();
//            int childWidth = v.getMeasuredWidth();
//            if (childLp instanceof MarginLayoutParams) {
//                MarginLayoutParams mlp = (MarginLayoutParams) childLp ;
//                spaces[n] = mlp.leftMargin + childWidth + mlp.rightMargin;
//            } else {
//                spaces[n] = childWidth;
//            }
//            n++;
//        }
//        int lineTotal = 0;
//        int start = 0;
//        this.removeAllViews();
//        for (int i = 0; i < count; i++) {
//            if (lineTotal + spaces[i] > usefulWidth) {
//                int blankWidth = usefulWidth - lineTotal;
//                int end = i - 1;
//                int blankCount = end - start;
//                if (blankCount >= 0) {
//                    if (blankCount > 0) {
//                        int eachBlankWidth = blankWidth / blankCount;
//                        MarginLayoutParams lp = new MarginLayoutParams(eachBlankWidth, 0);
//                        for (int j = start; j < end; j++) {
//                            this.addView(childs[j]);
//                            BlankView blank = new BlankView(mContext);
//                            this.addView(blank, lp);
//                        }
//                    }
//                    this.addView(childs[end]);
//                    start = i;
//                    i --;
//                    lineTotal = 0;
//                } else {
//                    this.addView(childs[i]);
//                    start = i + 1;
//                    lineTotal = 0;
//                }
//            } else {
//                lineTotal += spaces[i];
//            }
//        }
//        for (int i = start; i < count; i++) {
//            this.addView(childs[i]);
//        }
//    }
//
//    /**
//     * use both of relayout methods together
//     */
//    public void relayoutToCompressAndAlign(){
//        post(new Runnable() {
//            @Override
//            public void run() {
//                compress();
//                align();
//            }
//        });
//    }
//
//    /**
//     * cut the flowlayout to the specified num of lines
//     * @param line_num_now
//     */
//    public void specifyLines(final int line_num_now) {
//        post(new Runnable() {
//            @Override
//            public void run() {
//                int line_num = line_num_now;
//                int childNum = 0;
//                if (line_num > lineNumList.size()) {
//                    line_num = lineNumList.size();
//                }
//                for (int i = 0; i < line_num; i++) {
//                    childNum += lineNumList.get(i);
//                }
//                List<View> viewList = new ArrayList<>();
//                for (int i = 0; i < childNum; i++) {
//                    viewList.add(getChildAt(i));
//                }
//                removeAllViews();
//                for (View v : viewList) {
//                    addView(v);
//                }
//            }
//        });
//    }
//
//    class BlankView extends View {
//
//        public BlankView(Context context) {
//            super(context);
//        }
//    }
}
