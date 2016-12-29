package lxf.androiddemos.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;

public class UserEntity extends BaseObservable{
    private String name;
    private String sex;
    private int age;
    private int type;
    public ObservableField<String> address = new ObservableField<>();
    private String img;

    public ObservableField<Boolean> checked = new ObservableField<>();
    public ObservableField<String> color = new ObservableField<>();

    public String initType(int type) {
        String result;
        switch (type) {
            case 1:
                result = "程序猿";
                break;
            case 2:
                result = "程序猿的天敌";
                break;
            default:
                result = "无业游民";
                break;
        }
        return result;
    }

    public void changeAddress(View view){
        address.set("change:" + address.get());
    }

    public void addAge(View view) {
        setAge(getAge() + 1);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
//        notifyChange();//刷新所有可刷新数据
        notifyPropertyChanged(BR.age);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}