package lxf.androiddemos.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lxf.androiddemos.R;

public class TransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
    }

    /**
     * 5.0之前的传统转场动画
     */
    public void tradAnim(View view) {
        Intent intent = new Intent(this, TransitionTargetActivity.class);
        startActivity(intent);

        //在startActivity（）或者finish()方法之后调用
        //1.作用于即将到来的Activity的动画  2.作用于此Activity的动画   0表示没有动画
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    //-------------------------------以下为5.0之后新增的转场动画-------------------------------------

    public void customAnim(View view) {
        Intent intent = new Intent(this, TransitionTargetActivity.class);
        //ActivityOptions要求api 16以上  因此使用ActivityOptionsCompat，配套需要使用ActivityCompat
        //使用方式类似于传统动画，效果也基本类似于传统动画
        ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeCustomAnimation(this, R.anim.fade_in, R.anim.fade_out).toBundle());
    }

    public void scaleUpAnim(View view) {
        Intent intent = new Intent(this, TransitionTargetActivity.class);
        View source = findViewById(R.id.button3);
        //新activity从某一个view开始放大
        //1.哪个view 2.3.从该view的哪个位置开始放大 4.5.新activity的初始尺寸
        Bundle bundle = ActivityOptionsCompat.makeScaleUpAnimation(source, 0, 0, 0, 0).toBundle();
        ActivityCompat.startActivity(this, intent, bundle);
    }

    public void thumbnailScaleUpAnim(View view) {
        Intent intent = new Intent(this, TransitionTargetActivity.class);
        View source = findViewById(R.id.button4);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //新activity从某一个view开始放大
        //1.哪个view 2.初始的缩略图 4.5.从该view的哪个位置开始放大
        Bundle bundle = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(source, bitmap, 0, 0).toBundle();
        ActivityCompat.startActivity(this, intent, bundle);
    }

    public void clipRevealAnim(View view) {
        Intent intent = new Intent(this, TransitionTargetActivity.class);
        View source = findViewById(R.id.button6);
        ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeClipRevealAnimation(source, 0, 0, 0, 0).toBundle());
    }

    public void sceneAnim(View view) {
        Intent intent = new Intent(this, TransitionTargetActivity.class);
        intent.putExtra("sceneAnim",true);
        View sharedElement = findViewById(R.id.button6);
        //sharedElement.setTransitionName("shareBtn");需要api 21以上
        ViewCompat.setTransitionName(sharedElement, "shareBtn");//设置共享元素的名字，也可以在xml文件中设置
        ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, sharedElement, "shareBtn").toBundle());
    }

    public void sceneMutiAnim(View view) {
        Intent intent = new Intent(this, TransitionTargetActivity.class);
        View sharedElement = findViewById(R.id.button7);
        //sharedElement.setTransitionName("shareBtn");需要api 21以上
        ViewCompat.setTransitionName(sharedElement, "shareBtn");//设置共享元素的名字，也可以在xml文件中设置
        View sharedElement2 = findViewById(R.id.imageView);
        ViewCompat.setTransitionName(sharedElement2, "shareImg");
        Pair pair = Pair.create(sharedElement, "shareBtn");
        Pair pair2 = Pair.create(sharedElement2, "shareImg");
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair, pair2).toBundle();
        ActivityCompat.startActivity(this, intent, bundle);
    }
}
