package com.miu30.common.view;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.List;

/**
 * Created by admin on 2017/12/12.
 */

public class NavigationRadioGroup extends RadioGroup implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private static final String TAG = "NavigationRadioGroup";
    private int mScreenWidth;
    private int mScreenheight;
    private int mCountWidth;
    private int mCount = 4;
    private int mHeight = 50;
    private LinearLayout.LayoutParams layoutParams;

    public NavigationRadioGroup(Context context) {
        super(context);
        init();
    }

    public NavigationRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        mScreenWidth = size.x;

        mScreenheight = size.y;
        mCountWidth = mScreenWidth / mCount;
        getMyLayoutParams();
        this.setOnCheckedChangeListener(this);

    }

    /**
     * 导航栏显示的最大按钮的数量
     *
     * @param count
     */
    public void setShowMaxCountInScreen(int count) {
        mCount = count;
        mCountWidth = mScreenWidth / mCount;
        getMyLayoutParams();
    }

    /**
     * 设置宽度
     */
    public void setCountWidth(int count) {
        mCount = count;
        mCountWidth = mScreenWidth / 4;
        getMyLayoutParams();
    }

    private void getMyLayoutParams() {
        layoutParams = new LinearLayout.LayoutParams(mCountWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
    }

    public void addView(List<View> list) {
        for (View view : list) {
            view.setLayoutParams(layoutParams);
            addView(view);
            if (view instanceof Button) {
                view.setOnClickListener(this);
            }

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == -1) {
            return;
        }
        View viewById = findViewById(getCheckedRadioButtonId());
        if (viewById == null) {
            return;
        }
        Object tag = viewById.getTag();
        if (tag == null) {
            return;
        }
        String name = (String) tag;

    }

    @Override
    public void onClick(View v) {


    }
}
