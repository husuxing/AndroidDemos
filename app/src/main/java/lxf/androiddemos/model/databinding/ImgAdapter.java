package lxf.androiddemos.model.databinding;

import android.widget.ImageView;

import lxf.widget.util.ImgLoadUtil;

/**
 * Created by lxf on 2016/12/29.
 */

public class ImgAdapter extends AppAdapter {
    @Override
    public void setImage(ImageView imageView, String url, int placeHolder) {
        ImgLoadUtil.load(imageView,url,placeHolder);
    }
}
