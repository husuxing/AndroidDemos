package lxf.androiddemos.model.databinding;

/**
 * Created by lxf on 2016/12/29.
 */

public class MyComponent implements android.databinding.DataBindingComponent {
    @Override
    public AppAdapter getAppAdapter() {
        return new ImgAdapter();
    }
}
