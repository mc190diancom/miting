package com.feidi.template.mvp.ui.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class HideTextView extends android.support.v7.widget.AppCompatTextView {
    public HideTextView(Context context) {
        super(context);
    }

    public HideTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HideTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int getAvailableWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    public boolean isOverFlowed() {
        Paint paint = getPaint();
        float width = paint.measureText(getText().toString());
        if (width > getAvailableWidth()) return true;
        return false;
    }

}
