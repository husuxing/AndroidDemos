package lxf.widget.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImgLoadUtil {
    public static void load(ImageView imageView,String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public static void load(ImageView imageView,String url,int placeHolder){
        Glide.with(imageView.getContext()).load(url).placeholder(placeHolder).into(imageView);
    }


}
