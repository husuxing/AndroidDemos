package lxf.androiddemos.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * 测试自定义双向绑定
 * Created by lxf on 2016/12/29.
 */

public class ColorPicker extends View {
    private final String[] colors = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private OnColorChangeListener onColorChangeListener;
    private String mColor;
    private Paint paint;

    public String getColor() {
        return mColor;
    }

    public void setColor(String mColor) {
        this.mColor = mColor;
        paint.setColor(Color.parseColor(mColor));
        invalidate();
    }

    public void setOnColorChangeListener(OnColorChangeListener onColorChangeListener) {
        this.onColorChangeListener = onColorChangeListener;
    }

    public ColorPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorPicker(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                String color = "#";
                do {
                    color += colors[random.nextInt(colors.length)];
                } while (color.length() < 7);
                mColor = color;
                paint.setColor(Color.parseColor(mColor));
                invalidate();

                if (onColorChangeListener!=null)
                    onColorChangeListener.onColorChange(ColorPicker.this,mColor);
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(50, 50, 50, paint);
    }

    public interface OnColorChangeListener {
        //ColorPicker picker, String color
        void onColorChange(ColorPicker picker, String color);
    }

}
