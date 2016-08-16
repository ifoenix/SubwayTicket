package android.subwayticket.view;

import android.app.Activity;
import android.os.Bundle;
import android.subwayticket.R;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zach on 2016/8/12.
 */
public class OperGuideActivity extends Activity {

    private ViewPager vp;
    private List<ImageView> imgs=new ArrayList<ImageView>();
    private List<String> titles=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operguide);
        vp=(ViewPager)this.findViewById(R.id.viewPager);
        addImags();
        MyViewPagerAdapter adapter=new MyViewPagerAdapter();
        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageSelected(int arg0) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }
    LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    public void addImags(){
        ImageView iv1=new ImageView(this);
        iv1.setLayoutParams(params);
        iv1.setImageResource(R.drawable.pic1);

        ImageView iv2=new ImageView(this);
        iv2.setLayoutParams(params);
        iv2.setImageResource(R.drawable.pic1);

        imgs.add(iv1);
        imgs.add(iv2);

        titles.add("页面一");
        titles.add("页面二");
    }

    class MyViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv=imgs.get(position);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView iv=imgs.get(position);
            container.removeView(iv);
        }
    }
}
