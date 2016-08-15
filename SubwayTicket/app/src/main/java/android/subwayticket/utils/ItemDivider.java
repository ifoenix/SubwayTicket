package android.subwayticket.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Zach on 2016/8/13.
 */
public class ItemDivider extends RecyclerView.ItemDecoration {
    //默认分割条Drawable资源的id
    private static  final int[] ATTRS={ android.R.attr.listDivider};

    //分隔条Drawable对象
    private Drawable mDivider;

    public ItemDivider(Context context) {
        TypedArray a=context.obtainStyledAttributes(ATTRS);
        //获取系统提供的分隔条Drawable对象
        mDivider=a.getDrawable(0);
        //回收typedarray所占用的空间
        a.recycle();
    }

    //在该方法中绘制了所有列表项之间的分隔条
    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        super.onDraw(c, parent);
        //获取列表项距离左边缘的距离
        int left=parent.getPaddingLeft();
        //获取列表项距离右边缘的距离
        int right=parent.getWidth() - parent.getPaddingRight();
        //获取列表项的总数
        int chileCount=parent.getChildCount();

        //开始绘制所有列表项之间的分隔线

        for (int i=0;i<chileCount; i++){
            //获取当前的列表项
            View child=parent.getChildAt(i);
            //获取当前列表项的布局参数信息
            RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) child.
                    getLayoutParams();
            //计算分割线左上角的纵坐标
            int top=child.getBottom() + params.bottomMargin;
            //计算分隔线右下角的纵坐标
            int bottom=top+mDivider.getIntrinsicHeight();
            //设置分隔条绘制的位置
            mDivider.setBounds(left,top,right,bottom);
            //开始绘制当前列表项下方的分割条
            mDivider.draw(c);
        }

    }
}
