package com.example.xuchen.zhbj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.xuchen.zhbj.utils.PrefUtils;

/**
* 闪屏页
 * */

public class SplashActivity extends Activity {

    RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        startAnim();
    }

    /**
     * 开启动画
     *
     * */

    private void startAnim() {
        //动画集合
        AnimationSet set = new AnimationSet(false);

        //旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //动画时间
        rotateAnimation.setDuration(1000);
        //保持动画状态
        rotateAnimation.setFillAfter(true);

        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);

        //渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);

        //动画放入集合中
        set.addAnimation(rotateAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);

        //设置动画监听
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //动画执行结束
            @Override
            public void onAnimationEnd(Animation animation) {
                jumpNextPage();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rlRoot.startAnimation(set);

    }

    /**
     * 跳转下一个页面*/
    private void jumpNextPage() {
        // 判断之前有没有显示过新手引导
        boolean userGuide = PrefUtils.getBoolean(this, "is_user_guide_showed",
                false);

        if (!userGuide) {
            // 跳转到新手引导页
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }

        finish();
    }
}
