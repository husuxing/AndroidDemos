package lxf.androiddemos.model.databinding;

import android.databinding.BindingConversion;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by lxf on 2016/12/29.
 */

public class AppConversion {
    @BindingConversion
    public static Drawable colorToDrawable(String color){

        return new ColorDrawable(Color.parseColor(color));
    }
 }
