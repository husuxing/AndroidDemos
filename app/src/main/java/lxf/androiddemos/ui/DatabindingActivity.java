package lxf.androiddemos.ui;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import lxf.androiddemos.R;
import lxf.androiddemos.BR;
import lxf.androiddemos.databinding.DataBingMain;
import lxf.androiddemos.model.UserEntity;
import lxf.androiddemos.test.TestUtil;
import lxf.androiddemos.model.databinding.MyComponent;



public class DatabindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1.获取ViewDataBinding对象
//        DataBingMain dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
//        DataBindingUtil.setDefaultComponent(new MyComponent());
        DataBingMain dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding,new MyComponent());

        //2.获取数据
        UserEntity user = new UserEntity();
//        user.setName("lxf");
        user.name="lxf";
        user.setSex("man");
        user.setAge(25);
        user.setType(1);
        user.address.set("长沙");
        user.setImg("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
        user.checked.set(true);
        user.color.set("#de3654");

        //3.绑定数据
        dataBinding.setUser(user);
        dataBinding.setUtil(new TestUtil());
        //dataBinding.setVariable(BR.user,user);

        user.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == BR.age){
                    Toast.makeText(getApplicationContext(),"age刷新了",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
