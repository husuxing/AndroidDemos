package lxf.androiddemos.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import lxf.androiddemos.R;
import lxf.androiddemos.databinding.DataBingMain;
import lxf.androiddemos.model.UserEntity;


public class DatabindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1.获取ViewDataBinding对象
        DataBingMain dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);

        //2.获取数据
        UserEntity user = new UserEntity();
        user.setName("lxf");
        user.setSex("man");
        user.setAge(25);
        user.setType(1);

        //3.绑定数据
        dataBinding.setUser(user);
    }

}
