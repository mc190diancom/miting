package com.feidi.template.mvp.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.feidi.template.R;
import com.feidi.template.di.component.DaggerMeetRoomComponent;
import com.feidi.template.mvp.contract.MeetRoomContract;
import com.feidi.template.mvp.model.entity.LiveChatRoomBean;
import com.feidi.template.mvp.presenter.MeetRoomPresenter;
import com.feidi.template.mvp.ui.activity.TRTCVedioRoomActivity;
import com.feidi.template.mvp.ui.adapter.LiveRoomChatAdaper;
import com.feidi.template.mvp.util.GlideImageLoader;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.miu30.common.base.BaseMvpFragment;
import com.miu30.common.config.Config;
import com.miu30.common.util.SpacesItemDecoration;
import com.miu30.common.util.UIUtils;
import com.tencent.liteav.debug.GenerateTestUserSig;
import com.tencent.liteav.demo.trtc.TRTCVideoRoomActivity;
import com.tencent.liteav.demo.trtc.customcapture.utils.MediaUtils;
import com.tencent.liteav.liveroom.ui.widget.SpaceDecoration;
import com.tencent.trtc.TRTCCloudDef;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.tencent.liteav.demo.trtc.TRTCVideoRoomActivity.KEY_AUDIO_EARPIECEMODE;
import static com.tencent.liteav.demo.trtc.TRTCVideoRoomActivity.KEY_AUDIO_VOLUMETYOE;
import static com.tencent.liteav.demo.trtc.TRTCVideoRoomActivity.KEY_RECEIVED_AUDIO;
import static com.tencent.liteav.demo.trtc.TRTCVideoRoomActivity.KEY_RECEIVED_VIDEO;
import static com.tencent.liteav.demo.trtc.utils.Utils.getPath;
import static com.tencent.liteav.demo.trtc.utils.Utils.getRealPathFromURI;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/23/2020 18:47
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class MeetRoomFragment extends BaseMvpFragment<MeetRoomPresenter> implements MeetRoomContract.View {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_room_chat)
    RecyclerView rvRoomChat;

    @Inject
    LiveRoomChatAdaper mAdapter;


    public static MeetRoomFragment newInstance() {
        MeetRoomFragment fragment = new MeetRoomFragment();
        return fragment;
    }


    /**
     * 0 视频通话，1在线直播
     */
    public static final int TRTC_VOICECALL = 0;
    public static final int TRTC_LIVE = 1;
    private LiveChatRoomBean mLiveChatRoomBean;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMeetRoomComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meet_room, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mLiveChatRoomBean = self.getIntent().getParcelableExtra("livechat");
        initBanner();
        initRecyclerView();
        mPresenter.getRoomChatData(mLiveChatRoomBean);

    }

    private void initRecyclerView() {
        rvRoomChat.setAdapter(mAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(self, 2);
        rvRoomChat.setLayoutManager(layoutManager);
        rvRoomChat.addItemDecoration(new SpacesItemDecoration(UIUtils.dip2px(self,5)));
        mAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                Intent intent = new Intent(self,TRTCVedioRoomActivity.class);
                intent.putExtra("liveChatRoom",(LiveChatRoomBean.NegotiateBean)data);
                startActivity(intent);
            }
        });
    }

    @Override
    public void Notify(){
        mAdapter.notifyDataSetChanged();
    }

    private void initBanner() {

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        List<String> images = mPresenter.getBanner(mLiveChatRoomBean.getBanner());
        if(images == null || images.isEmpty()){
            return;
        }
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private String mVideoFile = "";
    private int mCurrentType = 0;
    private String mRoomId = "1";
    private final String mUserId = "520";
    private void startJoinRoom() {
        // 这里对房间号和用户名的有效性进行校验
        int roomId = 123;
        try {
            roomId = Long.valueOf(mRoomId).intValue();
        } catch (Exception e) {
            Toast.makeText(self, "请输入有效的房间号", Toast.LENGTH_SHORT).show();
            return;
        }
        final String userId = mUserId;//暂时固定成520
        if (TextUtils.isEmpty(userId)) {
            Toast.makeText(self, "请输入有效的用户名", Toast.LENGTH_SHORT).show();
            return;
        }

      //  startJoinRoomInternal(roomId, userId);
    }


    private Handler mUiHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean needDelay = false;
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if ("file".equalsIgnoreCase(uri.getScheme())) {//使用第三方应用打开
                mVideoFile = uri.getPath();
            } else {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                    mVideoFile = getPath(self, uri);
                } else {//4.4以下下系统调用方法
                    mVideoFile = getRealPathFromURI(self, uri);
                }
            }

            try {
                MediaFormat mediaFormat = MediaUtils.retriveMediaFormat(mVideoFile, false);
                int sampleRate = mediaFormat.getInteger(MediaFormat.KEY_SAMPLE_RATE);
                int channelCount = mediaFormat.getInteger(MediaFormat.KEY_CHANNEL_COUNT);
                if (sampleRate != 48000 || channelCount != 1) {
                    Toast.makeText(self, "音频仅支持采样率48000、单声道，请重新选择！", Toast.LENGTH_SHORT).show();
                    mVideoFile = null;
                    needDelay = true;
                }
            } catch (Exception e) {
                Log.e(TAG, "Failed to open file " + mVideoFile);
                Toast.makeText(self, "打开文件失败!", Toast.LENGTH_LONG).show();
                mVideoFile = null;
                needDelay = true;
            }
        }

        if (needDelay) {
            // 有错误出现时，延时一点再进房，防止错误提示被冲掉
            mUiHandler.postDelayed(mDelayStartJoinRoom, 2000);
        } else {
            startJoinRoom();
        }
    }


    private void saveUserInfo(String roomId, String userId) {
        try {
            SharedPreferences shareInfo = self.getSharedPreferences("per_data", 0);
            SharedPreferences.Editor editor = shareInfo.edit();
            editor.putString("userId", userId);
            editor.putString("roomId", roomId);
            editor.commit();
        } catch (Exception e) {

        }
    }


    @Override
    public void onStop() {
        super.onStop();
        //mUiHandler.removeCallbacks(mDelayStartJoinRoom);
    }

    private Runnable mDelayStartJoinRoom = new Runnable() {
        @Override
        public void run() {
            startJoinRoom();
        }
    };

}
