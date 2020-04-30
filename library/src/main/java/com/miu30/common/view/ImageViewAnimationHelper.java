package com.miu30.common.view;

import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by Murphy on 2018/7/16.
 */

public class ImageViewAnimationHelper {
    private Context context;
    private float lineWith;
    private float moveNum;
    private float oldMoveNum;
    private float distance;
    private float fromXDelta;
    private float toXDelta;
    private float oldXDelta;
    private ImageView imageView;
    private boolean isInit = true;
    private int index = 0;

    /**
     * @param context    上下文
     * @param imageView  视图控件
     * @param moveNum    一共需要移动几次
     * @param lineWithdp 下划线的宽度，单位dp
     */
    public ImageViewAnimationHelper(Context context, ImageView imageView, float moveNum, float lineWithdp, int index) {
        this.context = context;
        this.moveNum = moveNum;
        this.imageView = imageView;
        this.lineWith = lineWithdp * context.getResources().getDisplayMetrics().density;
        this.index = index;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
        //每段间隔距离
        float surplus = context.getResources().getDisplayMetrics().widthPixels - (lineWith * moveNum);
        distance = surplus / (moveNum * 2);
        params.width = (int) lineWith;
        imageView.setLayoutParams(params);
        //刷新
        imageView.requestLayout();
        startAnimation(index);
    }

    /***
     * @param moveNum 移动第几个 从0开始下标、不计间隔
     */
    public void startAnimation(int moveNum) {

        if (moveNum > this.moveNum) {
            throw new RuntimeException("IndexOutOf Exception ");
        }

        //计算fromXDelta toXDelta
        if (oldMoveNum == 0 & isInit) {
            isInit = false;
            fromXDelta = oldXDelta;
            toXDelta = distance * (2 * moveNum) + lineWith * moveNum + distance;
            oldXDelta = toXDelta;
            startAnima(0);
        } else if (oldMoveNum < moveNum | moveNum == 0) {//右移、初始化
            fromXDelta = oldXDelta;
            toXDelta = distance * (2 * moveNum) + lineWith * moveNum + distance;
            oldXDelta = toXDelta;
            startAnima(300);
        } else {//左移
            fromXDelta = oldXDelta;
            toXDelta = distance * (2 * moveNum) + lineWith * moveNum + distance;
            oldXDelta = toXDelta;
            startAnima(300);
        }

        oldMoveNum = moveNum;
    }

    /**
     * float fromXDelta 动画开始的点离当前View X坐标上的差值
     * float toXDelta 动画结束的点离当前View X坐标上的差值
     * float fromYDelta 动画开始的点离当前View Y坐标上的差值
     * float toYDelta 动画开始的点离当前View Y坐标上的差值
     */
    private void startAnima(int time) {
        Animation animation = new TranslateAnimation(fromXDelta, toXDelta, 0f, 0f);
        animation.setDuration(time);
        animation.setFillAfter(true);
        imageView.startAnimation(animation);
    }
}
