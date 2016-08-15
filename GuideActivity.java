package com.example.xuchen.zhbj;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**新手引导
 *
 * Created by xuchen on 2016/8/10.
 */
public class GuideActivity extends Activity{
    private static final int[] mImageIds=new int[]{R.mipmap.guide_1,R.mipmap.guide_2,R.mipmap.guide_3};

    private ViewPager vpGuide;
    private Button btnStart;
    private LinearLayout llPointGroup;
    private View viewRedPoint;
    private ArrayList<ImageView> mImageViewList;
    private int mPointWidth;// 圆点间的距离

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        vpGuide = (ViewPager) findViewById(R.id.vp_guide);
        btnStart = (Button) findViewById(R.id.btn_start);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_PointGroup);
        viewRedPoint = findViewById(R.id.view_redPoint);

        initViews();
        vpGuide.setAdapter(new GuideAdapter());
        vpGuide.addOnPageChangeListener(new GuidePageListener());
    }


        /**
         * 初始化界面*/

    private void initViews() {
        mImageViewList = new ArrayList<ImageView>();
        //初始化引导页3个页面
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(mImageIds[i]);//设置引导页背景
            mImageViewList.add(image);
        }

        //初始化引导页的小圆点
        for (int i = 0; i < mImageIds.length; i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);//设置默认圆点
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            if (i > 0) {
                params.leftMargin = 10;//设置圆点间隔
            }
            point.setLayoutParams(params);//设置圆点大小
            llPointGroup.addView(point);//将圆点添加到线性布局
        }
        // 获取视图树, 对layout结束事件进行监听
        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    // 当layout执行结束后回调此方法
                    @Override
                    public void onGlobalLayout() {
                        System.out.println("layout 结束");
                        llPointGroup.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        mPointWidth = llPointGroup.getChildAt(1).getLeft()
                                - llPointGroup.getChildAt(0).getLeft();
                        System.out.println("圆点距离:" + mPointWidth);
                    }
                });
        
    }

    /**
     * Viewpager数据适配器*/
    class GuideAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * ViewPager滑动监听*/
    class GuidePageListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            int len = (int) (mPointWidth * positionOffset) + position
                    * mPointWidth;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewRedPoint
                    .getLayoutParams();// 获取当前红点的布局参数
            params.leftMargin = len;// 设置左边距

            viewRedPoint.setLayoutParams(params);// 重新给小红点设置布局参数
        }

        @Override
        public void onPageSelected(int position) {
            if (position == mImageIds.length - 1) {// 最后一个页面
                btnStart.setVisibility(View.VISIBLE);// 显示开始体验的按钮
            } else {
                btnStart.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
