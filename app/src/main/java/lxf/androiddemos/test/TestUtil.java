package lxf.androiddemos.test;

import android.view.View;
import android.widget.Toast;

public class TestUtil {

    public void onBtnClick(View view){
        Toast.makeText(view.getContext(),"onBtnClick",Toast.LENGTH_SHORT).show();
    }

    public void onTextChanged(CharSequence s, int start, int before, int count){
        System.out.println(s);
    }

//    @BindingAdapter(value = {"url","placeHolder"},requireAll = false)
//    public static void setImage(ImageView imageView , String url, int placeHolder){
//        ImgLoadUtil.load(imageView,url,placeHolder);
//    }

}
