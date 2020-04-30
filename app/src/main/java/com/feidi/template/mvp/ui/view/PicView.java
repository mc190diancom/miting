package com.feidi.template.mvp.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tencent.liteav.demo.trtc.widget.videolayout.Utils;

public class PicView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();

    public PicView(Context context) {
        super(context);
        init();
    }

    public PicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        paint.setColor(Color.parseColor("#FFFFFF"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dip2px(getContext(), 3));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        int w = this.getWidth();
        int h = this.getHeight();
        float left = w * 0.5f;
        path.lineTo(left, 0);
//        path.moveTo(w - left, 0);
//        path.lineTo(w, 0);
//        path.moveTo(0, h);
//        path.lineTo(left, h);
        path.moveTo(w - left, h);
        path.lineTo(w, h);
        //-----竖线
        path.moveTo(0, 0);
        path.lineTo(0, left);
//        path.moveTo(w, 0);
//        path.lineTo(w, left);
//
//        path.moveTo(0, h - left);
//        path.lineTo(0, h);
        path.moveTo(w, h - left);
        path.lineTo(w, h);

        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }
}
