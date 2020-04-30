package com.miu30.common.ui.view.tab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.miu360.library.R;

/**
 * 作者：wang.
 * 邮箱：forwlwork@gmail.com
 */
public class CustomTabItem extends View {

    final CharSequence mText;
    final Drawable mIcon;
    final int mCustomLayout;

    public CustomTabItem(Context context) {
        this(context, null);
    }

    public CustomTabItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomTabItem, 0, 0);
        mText = a.getText(R.styleable.CustomTabItem_android_text);
        mIcon = a.getDrawable(R.styleable.CustomTabItem_android_icon);
        mCustomLayout = a.getResourceId(R.styleable.CustomTabItem_android_layout, 0);
        a.recycle();
    }

}
