package com.miu30.common.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private boolean isMain;

    public SpacesItemDecoration(int space) {
        this(space,false);
    }

    public SpacesItemDecoration(int space,boolean isMain) {
        this.space = space;
        this.isMain = isMain;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {

        if(isMain){
           if (parent.getChildAdapterPosition(view)%2 == 1 ){
                outRect.left = space;
                outRect.right = space;
                outRect.bottom = space;
            }else{
                outRect.left = space;
                outRect.right = space;
                outRect.bottom = space;
            }

        }else {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
        }

    }
}
