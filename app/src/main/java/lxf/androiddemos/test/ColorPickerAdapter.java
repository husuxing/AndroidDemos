package lxf.androiddemos.test;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;

/**
 * 测试用
 * Created by lxf on 2016/12/29.
 */
//@InverseBindingMethods({
//        @InverseBindingMethod(type = ColorPicker.class,attribute = "color")
//})
public class ColorPickerAdapter {

    @InverseBindingAdapter(attribute = "color")
    public static String getColor(ColorPicker picker){
        return picker.getColor();
    }

    @BindingAdapter(value = {"colorAttrChanged"},requireAll = false)
    public static void setListener(ColorPicker picker, final InverseBindingListener attrChange){
        if (attrChange!=null){
            picker.setOnColorChangeListener(new ColorPicker.OnColorChangeListener() {
                @Override
                public void onColorChange(ColorPicker picker, String color) {
                    //...

                    attrChange.onChange();
                }
            });
        }
    }
}
