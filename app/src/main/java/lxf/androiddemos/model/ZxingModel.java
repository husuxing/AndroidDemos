package lxf.androiddemos.model;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import cn.izis.zxing.util.QRCodeUtil;
import lxf.androiddemos.R;


/**
 * 二维码model
 * Created by lxf on 2016/12/30.
 */
public class ZxingModel extends BaseObservable{
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<Bitmap> zxing = new ObservableField<>();

    public void getZxing(View view){
        try {
            zxing.set(QRCodeUtil.createQRCode(content.get()));
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void getZxingWithImg(View view){
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(view.getResources(),R.mipmap.ic_launcher);
            zxing.set(QRCodeUtil.createCode(content.get(),bitmap, BarcodeFormat.QR_CODE));
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void scanQR(View view){
        QRCodeUtil.ScanQRCode((Activity) view.getContext(),1);
    }

    @BindingAdapter(value = {"zxing"}, requireAll = false)
    public static void setImg(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
