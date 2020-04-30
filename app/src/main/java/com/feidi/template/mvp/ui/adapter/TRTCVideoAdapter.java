package com.feidi.template.mvp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.feidi.template.mvp.ui.activity.TRTCVedioRoomActivity;

import java.util.ArrayList;
import java.util.List;

public class TRTCVideoAdapter extends RecyclerView.Adapter<TRTCVideoAdapter.VH> {
    List<TRTCVedioRoomActivity.PlayerViewParams> viewList = new ArrayList<>();


    public void notifyAdapter(List<TRTCVedioRoomActivity.PlayerViewParams> list) {
        viewList.clear();
        viewList.addAll(list);
        notifyDataSetChanged();
    }

    public void deleteAll() {
        for (int i = 0; i < viewList.size(); i++) {
            notifyItemRemoved(i + 1);
        }
        viewList.clear();
    }

    public void insertView(TRTCVedioRoomActivity.PlayerViewParams viewParams) {
        viewList.add(viewParams);

        notifyItemRangeInserted(getItemCount()+1, 1);
    }

    public int deleteAdapter(TRTCVedioRoomActivity.PlayerViewParams view) {
        if (viewList.size() != 0) {
            int i = viewList.indexOf(view);
            viewList.remove(view);
            if (i != -1) {
                notifyItemRemoved(i);

            }
            return i;
        }
        return -1;

    }

    public void notifyMy(TRTCVedioRoomActivity.PlayerViewParams view) {
        viewList.clear();
        viewList.add(view);
        notifyDataSetChanged();
    }

    //② 创建ViewHolder
    public static class VH extends RecyclerView.ViewHolder {

        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new VH(viewList.get(i).txCloudVideoView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return viewList.size();
    }
}
