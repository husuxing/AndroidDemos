package lxf.androiddemos.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

/**
 *
 * Created by lxf on 2017/3/13.
 */
public class LottieModel extends BaseObservable{
    public ObservableBoolean loop = new ObservableBoolean(true);
}
