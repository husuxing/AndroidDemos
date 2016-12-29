package lxf.androiddemos.model.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by lxf on 2016/12/28.
 */
public abstract class AppAdapter {

    @BindingAdapter(value = {"url","placeHolder"},requireAll = false)
    public abstract void setImage(ImageView imageView , String url, int placeHolder);
}
