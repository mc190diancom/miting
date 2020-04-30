package com.tencent.liteav.demo.trtc.widget.videolayout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.liteav.demo.trtc.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{
    private List<TRTCVideoLayoutManager.TRTCLayoutEntity> mFruitList;

    //内部类，继承自RecyclerView.ViewHolder
    //接收一个View ，通常就是RecyclerView子项的最外层布局，
    //所以就可以用findViewById 来获取控件
    static class  ViewHolder extends RecyclerView.ViewHolder{
        TextView TextName;
        public ViewHolder(View View) {
            super(View);

            TextName = (TextView)View.findViewById(R.id.tv_video);
        }
    }
    //将要展示的数据传递进来，
    VideoAdapter(List<TRTCVideoLayoutManager.TRTCLayoutEntity> list){
        this.mFruitList = list ;
    }
    //将fruit_item 布局加载进来，然后创建一个ViewHolder实例，
    //将加载的布局传给ViewHolder的构造函数中。就可以获取布局中的控件
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    //这个方法会在屏幕滚动的时候执行
    // 通过position获取到Fruit 的实例，然后给布局上的控件进行赋值，
    @Override
    public void onBindViewHolder(VideoAdapter.ViewHolder holder, int position) {

        TRTCVideoLayoutManager.TRTCLayoutEntity layout = mFruitList.get(position);
        holder.TextName.setText(layout.userId);
    }
    //他用于告诉RecyclerView有多少子项，直接返回数据源的长度就行了
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}