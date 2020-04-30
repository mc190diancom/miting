package com.feidi.template.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feidi.template.R;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

public class HomeAdaper extends DefaultAdapter<String> {
    //先定义两个ItemViewType，0代表头，1代表表格中间的部分
    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;
    //数据源
    private List<String> dataList;

    public HomeAdaper(List<String> infos) {
        super(infos);
    }

    /**
     * 判断当前position是否处于第一个
     * @param position
     * @return
     */
    public boolean isHeader(int position) {
        return position == 0;
    }

    @Override
    public BaseHolder<String> getHolder(View v, int viewType) {
        return new BaseHolder<String>(v) {
            @Override
            public void setData(String data, int position) {

            }
        };
    }

    @Override
    public int getLayoutId(int viewType) {
        if (viewType == ITEM_VIEW_TYPE_HEADER) {
            //对于Header，我们应该返回填充有Header对应布局文件的ViewHolder（再次我们返回的都是一个布局文件，请根据不同的需求做相应的改动）
            return R.layout.layout_home_head_item;
        } else {
            //对于Body中的item，我们也返回所对应的ViewHolder
            return R.layout.layout_home_item;
        }
    }

    /**
     *
     * 复用getItemViewType方法，根据位置返回相应的ViewType
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        //如果是0，就是头，否则则是其他的item
        return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
    }

}
