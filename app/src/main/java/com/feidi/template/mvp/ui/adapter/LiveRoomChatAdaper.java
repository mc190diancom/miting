package com.feidi.template.mvp.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feidi.template.R;
import com.feidi.template.TRTCApplication;
import com.feidi.template.mvp.model.LiveChatRoomDataBean;
import com.feidi.template.mvp.model.entity.LiveChatRoomBean;
import com.feidi.template.mvp.model.entity.bannerBean;
import com.google.gson.reflect.TypeToken;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.miu30.common.base.BaseData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LiveRoomChatAdaper extends DefaultAdapter<LiveChatRoomBean.NegotiateBean> {
    public LiveRoomChatAdaper(List<LiveChatRoomBean.NegotiateBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<LiveChatRoomBean.NegotiateBean> getHolder(View v, int viewType) {
        return new BaseHolder<LiveChatRoomBean.NegotiateBean>(v) {
            @Override
            public void setData(LiveChatRoomBean.NegotiateBean data, int position) {
                ImageView ivHomeItem = v.findViewById(R.id.iv_home_item);
                TextView tvLiveRoomItemTitle = v.findViewById(R.id.tv_live_room_item_title);
                TextView tvLiveRoomItemContent = v.findViewById(R.id.tv_live_room_item_content);
                TextView tvLiveRoomItemStatus = v.findViewById(R.id.tv_live_room_item_status);
                if(!TextUtils.isEmpty(data.getBanner())){
                    Glide.with(TRTCApplication.self).load(getBanner(data.getBanner())).into(ivHomeItem);
                }
                tvLiveRoomItemTitle.setText(data.getTitle());
                tvLiveRoomItemContent.setText(data.getDesc());
                try {
                    if(1 == Integer.valueOf(data.getState())){
                        tvLiveRoomItemStatus.setText(TRTCApplication.self.getResources().getString(R.string.live_room_state));
                    }else{
                        tvLiveRoomItemStatus.setText(TRTCApplication.self.getResources().getString(R.string.live_room_state));
                    }
                }catch (NumberFormatException e){
                    tvLiveRoomItemStatus.setText(TRTCApplication.self.getResources().getString(R.string.live_room_state));
                }

            }
        };
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.layout_live_room_item;
    }

    private String getBanner(String banner) {
        List<bannerBean> banners;
        try {
            banners = BaseData.gson.fromJson(banner, new TypeToken<List<bannerBean>>() {
            }.getType());
            return banners.get(0).getUrl();
        }catch (Exception e){
            return "";
        }
    }
}
