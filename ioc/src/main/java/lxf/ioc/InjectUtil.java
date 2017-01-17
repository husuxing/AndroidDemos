package lxf.ioc;

import android.app.Activity;

import java.lang.reflect.Field;

import lxf.ioc.annotation.ContentView;

/**
 * 依赖注入工具类：用于activity绑定布局、控件、事件。
 * Created by lxf on 2017/1/2.
 */
public class InjectUtil {

    public static void init(Activity activity){
        injectLayout(activity);
        injectField(activity);
    }

    private static void injectLayout(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        ContentView contentView = cls.getAnnotation(ContentView.class);
        int layoutId = contentView.value();
        activity.setContentView(layoutId);
    }

    private static void injectField(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields){

        }
    }
}
