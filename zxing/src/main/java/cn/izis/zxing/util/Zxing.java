package cn.izis.zxing.util;

import android.app.Application;
import android.content.Context;
import cn.izis.zxing.camera.CameraManager;

public class Zxing {

    public static void init(Application application){
        Context context = application.getApplicationContext();
        CameraManager.MIN_FRAME_HEIGHT = (int) dp2px(context,180);
        CameraManager.MIN_FRAME_WIDTH = (int) dp2px(context,180);
        CameraManager.MAX_FRAME_WIDTH = (int) dp2px(context,240);
        CameraManager.MAX_FRAME_HEIGHT = (int) dp2px(context,240);
    }

    /**
     * dp转px
     */
    public static float dp2px(Context context, float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return dpValue*scale+0.5f;
    }
}
