package com.feidi.template.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.feidi.template.R;
import com.feidi.template.di.component.DaggerTRTCVedioRoomActivityComponent;
import com.feidi.template.mvp.contract.TRTCVedioRoomActivityContract;
import com.feidi.template.mvp.model.entity.LiveChatRoomBean;
import com.feidi.template.mvp.presenter.TRTCVedioRoomActivityPresenter;
import com.feidi.template.mvp.ui.adapter.TRTCVideoAdapter;
import com.jess.arms.di.component.AppComponent;
import com.miu30.common.base.BaseMvpActivity;
import com.miu30.common.config.Config;
import com.miu30.common.util.StatusBarUtils;
import com.miu30.common.util.UIUtils;
import com.tencent.liteav.debug.GenerateTestUserSig;
import com.tencent.liteav.demo.trtc.customcapture.TestRenderVideoFrame;
import com.tencent.liteav.demo.trtc.customcapture.TestSendCustomData;
import com.tencent.liteav.demo.trtc.sdkadapter.ConfigHelper;
import com.tencent.liteav.demo.trtc.sdkadapter.TRTCCloudListenerImpl;
import com.tencent.liteav.demo.trtc.sdkadapter.TRTCCloudManagerListener;
import com.tencent.liteav.demo.trtc.sdkadapter.feature.AudioConfig;
import com.tencent.liteav.demo.trtc.sdkadapter.feature.PkConfig;
import com.tencent.liteav.demo.trtc.sdkadapter.feature.VideoConfig;
import com.tencent.liteav.demo.trtc.sdkadapter.remoteuser.RemoteUserConfig;
import com.tencent.liteav.demo.trtc.sdkadapter.remoteuser.RemoteUserConfigHelper;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLog;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.trtc.TRTCCloud;
import com.tencent.trtc.TRTCCloudDef;
import com.tencent.trtc.TRTCStatistics;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

import static com.tencent.trtc.TRTCCloudDef.TRTC_VIDEO_STREAM_TYPE_BIG;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/27/2020 17:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class TRTCVedioRoomActivity extends BaseMvpActivity<TRTCVedioRoomActivityPresenter> implements TRTCVedioRoomActivityContract.View, TRTCCloudManagerListener, View.OnClickListener {


    @BindView(R.id.rlGrid)
    RecyclerView rlGrid;
    //@BindView(R.id.trtc_cdn_play_view)
    TXCloudVideoView trtcCdnPlayView;
    @BindView(R.id.flVideo)
    FrameLayout flVideo;
    @BindView(R.id.flContent)
    RelativeLayout flContent;
    @BindView(R.id.llVideo)
    LinearLayout llVideo;
    List<PlayerViewParams> list = new ArrayList<>();
    @BindView(R.id.ibSwitch)
    ImageButton ibSwitch;
    @BindView(R.id.ibBack)
    ImageView ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.ibSpeak)
    ImageButton ibSpeak;
    @BindView(R.id.ibVoice)
    ImageButton ibVoice;
    @BindView(R.id.ibCamera)
    ImageButton ibCamera;
    @BindView(R.id.hsVideo)
    HorizontalScrollView hsVideo;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.viewBg)
    View viewBg;
    private TRTCVideoAdapter trtcVideoAdapter;
    private TRTCCloud mTRTCCloud;
    public static final String KEY_SDK_APP_ID = "sdk_app_id";
    public static final String KEY_ROOM_ID = "room_id";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_SIG = "user_sig";
    public static final String KEY_APP_SCENE = "app_scene";
    public static final String KEY_ROLE = "role";
    public static final String KEY_CUSTOM_CAPTURE = "custom_capture";
    public static final String KEY_VIDEO_FILE_PATH = "file_path";
    public static final String KEY_RECEIVED_VIDEO = "auto_received_video";
    public static final String KEY_RECEIVED_AUDIO = "auto_received_audio";
    public static final String KEY_SCREEN_CAPTURE = "screen_capture";
    public static final String KEY_AUDIO_VOLUMETYOE = "auto_audio_volumeType";
    public static final String KEY_AUDIO_EARPIECEMODE = "EarpieceMode";
    private static final String KEY_TITLE = "title";
    private int mAppScene;                  // 推流模式，文件头第一点注释

    private boolean mIsCustomCaptureAndRender = false;
    private String mVideoFilePath;             // 视频文件路径
    private TestSendCustomData mCustomCapture;             // 外部采集
    private TestRenderVideoFrame mCustomRender;              // 外部渲染
    private Group mRoleAudienceGroup;
    private ImageView mIvMoreTrtc;
    private boolean mReceivedVideo = true;
    private boolean mReceivedAudio = true;
    private int mVolumeType = -1;
    private boolean mIsAudioEarpieceMode = false;
    // 录屏相关
    private boolean mIsScreenCapture = false;
    private int sdkAppId = GenerateTestUserSig.SDKAPPID;
    private int roomId = 12548;
    private String userId = "252549";
    private String userSig = GenerateTestUserSig.genTestUserSig(userId);
    private int role = TRTCCloudDef.TRTCRoleAnchor;
    private TRTCCloudDef.TRTCParams mTRTCParams;
    private ArrayList<TRTCVideoStream> mTRTCVideoStreams = new ArrayList<>();
    private int width = -2;
    private int audioPlayoutVolume;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerTRTCVedioRoomActivityComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    private boolean isGrid = false;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_trtcvedio_room; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (initTitleAndRoomId()) return;
        rlGrid.setLayoutManager(new GridLayoutManager(this, 2));
        trtcVideoAdapter = new TRTCVideoAdapter();
        rlGrid.setAdapter(trtcVideoAdapter);
        initBarMargin();
        initTRTCSDK();
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.white));
        audioPlayoutVolume = mTRTCCloud.getAudioPlayoutVolume();

    }

    /**
     * 初始化会议房间信息
     */
    private boolean initTitleAndRoomId() {
        LiveChatRoomBean.NegotiateBean bean = getIntent().getParcelableExtra("liveChatRoom");
        if (bean == null) {
            UIUtils.toast(self, "信息获取失败", Toast.LENGTH_SHORT);
            return true;
        }
        tvTitle.setText(bean.getTitle());
        try {
            roomId = Integer.valueOf(bean.getRoomNumber());
        } catch (NumberFormatException e) {

        }
        String userid= SPUtils.getInstance(Config.SP_USERINFO).getString(Config.USER_ID);
        if(!TextUtils.isEmpty(userid)){
            userId = userid;
            userSig = GenerateTestUserSig.genTestUserSig(userId);
        }
        return false;
    }

    @Override
    protected void initBar() {
        statusBarUtils = StatusBarUtils.with(this);
        statusBarUtils.init();
    }

    private void initBarMargin() {
        int statusBarHeight = StatusBarUtils.getStatusBarHeight(this);
        MarginLayoutParams layoutParams = (MarginLayoutParams) rlTitle.getLayoutParams();
        layoutParams.topMargin += statusBarHeight;
        rlTitle.setLayoutParams(layoutParams);
        rlTitle.measure(0, 0);
        int topMargin = layoutParams.topMargin + UIUtils.dip2px(this, 20) + rlTitle.getMeasuredHeight();
        layoutParams = (MarginLayoutParams) rlGrid.getLayoutParams();
        layoutParams.topMargin = topMargin;
        rlGrid.setLayoutParams(layoutParams);
    }


    @OnClick(R.id.ibSwitch)
    public void onViewClicked() {
        if (isGrid) {
            isGrid = false;
            line.setVisibility(View.GONE);
            if (list.size() > 1) {
                viewBg.setVisibility(View.VISIBLE);
            } else {
                viewBg.setVisibility(View.INVISIBLE);
            }
            ibSwitch.setBackgroundResource(R.drawable.grid_video);
            trtcVideoAdapter.deleteAll();
            for (PlayerViewParams playerViewParams : list) {
                UIUtils.removeView(playerViewParams.txCloudVideoView);
            }
            flVideo.setVisibility(View.VISIBLE);
            rlGrid.setAdapter(trtcVideoAdapter);
            for (PlayerViewParams view : list) {
                MarginLayoutParams layoutParams;
                if (!view.isMySelf) {
                    llVideo.addView(view.txCloudVideoView);
                    layoutParams = (MarginLayoutParams) view.txCloudVideoView.getLayoutParams();
                    initBottomGraidLayoutParams(layoutParams);
                } else {
                    flVideo.addView(view.txCloudVideoView);

                    layoutParams = (MarginLayoutParams) view.txCloudVideoView.getLayoutParams();
                    initLocalLayoutParams(layoutParams);
                }
                view.txCloudVideoView.setLayoutParams(layoutParams);

            }
            trtcCdnPlayView.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
            rlGrid.setVisibility(View.GONE);
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.white));

        } else {
            viewBg.setVisibility(View.INVISIBLE);
            line.setVisibility(View.VISIBLE);
            ibSwitch.setBackgroundResource(R.drawable.full_video);
            isGrid = true;
            flVideo.setVisibility(View.GONE);
            flVideo.removeAllViews();
            llVideo.removeAllViews();
            for (PlayerViewParams playerViewParams : list) {
                UIUtils.removeView(playerViewParams.txCloudVideoView);
                trtcVideoAdapter.insertView(playerViewParams);

            }


            for (PlayerViewParams playerViewParams : list) {
                MarginLayoutParams layoutParams = (MarginLayoutParams) playerViewParams.txCloudVideoView.getLayoutParams();
                initGraidLayoutParams(layoutParams);
                playerViewParams.txCloudVideoView.setLayoutParams(layoutParams);

            }
            rlGrid.setVisibility(View.VISIBLE);
            getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.color333));
        }

    }

    private void initLocalLayoutParams(MarginLayoutParams marginLayoutParams) {
        marginLayoutParams.width = -1;
        marginLayoutParams.height = -1;
        marginLayoutParams.topMargin = 0;
        marginLayoutParams.bottomMargin = 0;
        marginLayoutParams.leftMargin = 0;
        marginLayoutParams.rightMargin = 0;
    }

    private void initGraidLayoutParams(MarginLayoutParams marginLayoutParams) {
        if (width == -2) {
            width = flVideo.getWidth();
            width = width - (UIUtils.dip2px(this, 25) * 2) - UIUtils.dip2px(this, 10);
            width = width / 2;
        }
        marginLayoutParams.width = width;
        marginLayoutParams.height = width;
        marginLayoutParams.bottomMargin = UIUtils.dip2px(this, 6);
        marginLayoutParams.leftMargin = UIUtils.dip2px(this, 10);


    }

    private void initBottomGraidLayoutParams(MarginLayoutParams marginLayoutParams) {
        marginLayoutParams.width = UIUtils.dip2px(this, 63);
        marginLayoutParams.height = UIUtils.dip2px(this, 63);
        marginLayoutParams.leftMargin = 10;


    }

    private void initTRTCSDK() {
        //  getParams();
        mTRTCCloud = TRTCCloud.sharedInstance(this);
        mTRTCParams = new TRTCCloudDef.TRTCParams(sdkAppId, userId, userSig, roomId, "", "");
        mTRTCParams.role = role;
        mTRTCCloud.setListener(new TRTCCloudListenerImpl(this));
        trtcCdnPlayView = new TXCloudVideoView(this);
        flVideo.addView(trtcCdnPlayView);
        initLocalLayoutParams((MarginLayoutParams) trtcCdnPlayView.getLayoutParams());
        enableANS(true);
        // 开始进房
        enterRoom();
    }

    boolean isSpeak = true;
    boolean isVoice = true;
    boolean isCamera = true;

    @OnClick({R.id.ibSpeak, R.id.ibVoice, R.id.ibCamera, R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibSpeak:
                isSpeak = !isSpeak;
                if (isSpeak) {
                    mTRTCCloud.startLocalAudio();
                } else {
                    mTRTCCloud.stopLocalAudio();
                }
                ibSpeak.setBackgroundResource(isSpeak ? R.drawable.mk_open : R.drawable.mk_close);
                break;
            case R.id.ibVoice:
                isVoice = !isVoice;
                mTRTCCloud.setAudioPlayoutVolume(isVoice ? audioPlayoutVolume : 0);
                ibVoice.setBackgroundResource(isVoice ? R.drawable.voice_open : R.drawable.voice_close);
                break;
            case R.id.ibCamera:
                isCamera = !isCamera;
                if (isCamera) {
                    mTRTCCloud.startLocalPreview(true, trtcCdnPlayView);
                } else {
                    mTRTCCloud.stopLocalPreview();
                }

                ibCamera.setBackgroundResource(isCamera ? R.drawable.camera_open : R.drawable.camera_close);
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (isGrid) {
            if (list.size() > 1) {
                viewBg.setVisibility(View.VISIBLE);
            }
            isGrid = false;
            flVideo.removeAllViews();
            llVideo.removeAllViews();
            trtcVideoAdapter.deleteAll();
            for (PlayerViewParams playerViewParams : list) {
                UIUtils.removeView(playerViewParams.txCloudVideoView);
            }
            line.setVisibility(View.GONE);
            rlGrid.setVisibility(View.GONE);
            for (PlayerViewParams playerViewParams : list) {
                if (playerViewParams.txCloudVideoView == v) {
                    flVideo.setVisibility(View.VISIBLE);
                    flVideo.addView(v);
                    MarginLayoutParams layoutParams = (MarginLayoutParams) v.getLayoutParams();
                    initLocalLayoutParams(layoutParams);
                    v.setLayoutParams(layoutParams);

                } else {
                    llVideo.addView(playerViewParams.txCloudVideoView);
                    MarginLayoutParams layoutParams = (MarginLayoutParams) playerViewParams.txCloudVideoView.getLayoutParams();
                    initBottomGraidLayoutParams(layoutParams);
                    playerViewParams.txCloudVideoView.setLayoutParams(layoutParams);

                }
            }
        } else {

            MarginLayoutParams layoutParams = null;
            View view = null;

            for (PlayerViewParams playerViewParams : list) {
                if (v == playerViewParams.txCloudVideoView) {
                    layoutParams = (MarginLayoutParams) v.getLayoutParams();
                }
                ViewGroup.LayoutParams layoutParams1 = playerViewParams.txCloudVideoView.getLayoutParams();
                if (layoutParams1.width == -1) {
                    view = playerViewParams.txCloudVideoView;

                }
            }
            if (layoutParams == null || layoutParams.width == -1 || view == null) {
                return;
            }

            layoutParams = (MarginLayoutParams) view.getLayoutParams();
            initBottomGraidLayoutParams(layoutParams);
            view.setLayoutParams(layoutParams);
            MarginLayoutParams layoutParams1 = (MarginLayoutParams) v.getLayoutParams();
            initLocalLayoutParams(layoutParams1);
            v.setLayoutParams(layoutParams1);
            int pos = 0;
            for (int i = 0; i < llVideo.getChildCount(); i++) {
                View childAt = llVideo.getChildAt(i);
                if (childAt == v) {
                    pos = i;
                }
            }
            flVideo.removeAllViews();
            llVideo.removeView(v);
            flVideo.addView(v);
            llVideo.addView(view, pos);
        }
    }


    public static class PlayerViewParams {
        public TXCloudVideoView txCloudVideoView;
        public boolean isMySelf = false;
        public String userId;
    }

    private void enterRoom() {
        VideoConfig videoConfig = ConfigHelper.getInstance().getVideoConfig();
        AudioConfig audioConfig = ConfigHelper.getInstance().getAudioConfig();
        mTRTCCloud.setSystemVolumeType(mVolumeType);
        // 如果当前角色是主播, 才能打开本地摄像头
        if (mTRTCParams.role == TRTCCloudDef.TRTCRoleAnchor) {
            // 开启本地预览
            startLocalPreview();
            videoConfig.setEnableVideo(true);
            videoConfig.setPublishVideo(true);
            // 开始采集声音
            mTRTCCloud.startLocalAudio();
            audioConfig.setEnableAudio(true);
        } else {
            videoConfig.setEnableVideo(false);
            audioConfig.setEnableAudio(false);
            videoConfig.setPublishVideo(false);
        }
        // 耳返
        mTRTCCloud.enableAudioEarMonitoring(audioConfig.isEnableEarMonitoring());

        mTRTCCloud.enterRoom(mTRTCParams, mAppScene);

    }

    private void startLocalPreview() {
        // 开启本地预览
        trtcCdnPlayView.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
        mTRTCCloud.startLocalPreview(true, trtcCdnPlayView);
        PlayerViewParams mySelfplayerViewParams = new PlayerViewParams();
        mySelfplayerViewParams.userId = userId;
        mySelfplayerViewParams.isMySelf = true;
        mySelfplayerViewParams.txCloudVideoView = trtcCdnPlayView;
        list.add(mySelfplayerViewParams);
        trtcCdnPlayView.setOnClickListener(this);
    }

    private void getParams() {
        Intent intent = getIntent();
        // sdkAppId = intent.getIntExtra(KEY_SDK_APP_ID, 0);
        roomId = intent.getIntExtra(KEY_ROOM_ID, 0);
        userId = intent.getStringExtra(KEY_USER_ID);
        userSig = intent.getStringExtra(KEY_USER_SIG);
        String title = intent.getStringExtra(KEY_TITLE);
        if (TextUtils.isEmpty(title)) {
            tvTitle.setText("");
        } else {
            tvTitle.setText(title);
        }
        //   mAppScene = intent.getIntExtra(KEY_APP_SCENE, TRTCCloudDef.TRTC_APP_SCENE_VIDEOCALL);

        //  role = intent.getIntExtra(KEY_ROLE, TRTCCloudDef.TRTCRoleAnchor);
        //    mIsCustomCaptureAndRender = intent.getBooleanExtra(KEY_CUSTOM_CAPTURE, false);
        //  mIsScreenCapture = intent.getBooleanExtra(KEY_SCREEN_CAPTURE, false);
        //   mReceivedVideo = intent.getBooleanExtra(KEY_RECEIVED_VIDEO, true);
        // mVolumeType = intent.getIntExtra(KEY_AUDIO_VOLUMETYOE, 0);
        //  mIsAudioEarpieceMode = intent.getBooleanExtra(KEY_AUDIO_EARPIECEMODE, false);
        Timber.tag(TAG).d("onCreate, intent.getIntExtra  mVolumeType " + mVolumeType);

    }

    @Override
    public void onEnterRoom(long elapsed) {
        if (elapsed >= 0) {
            showMessage("加入房间成功");
            // 发起云端混流
            updateCloudMixtureParams();
        } else {
            showMessage("加入房间失败");
            exitRoom();
            finish();
        }
    }

    /**
     * 退房
     */
    private void exitRoom() {
        mTRTCCloud.stopLocalPreview();
        // 退房设置为非录制状态
        ConfigHelper.getInstance().getAudioConfig().setRecording(false);
        if (mTRTCCloud != null) {
            mTRTCCloud.exitRoom();
        }

    }

    /**
     * 更新混流参数
     */
    public void updateCloudMixtureParams() {
        int mode = ConfigHelper.getInstance().getVideoConfig().getCloudMixtureMode();
        switch (mode) {
            case TRTCCloudDef.TRTC_TranscodingConfigMode_Unknown:
                ConfigHelper.getInstance().getVideoConfig().setCurIsMix(false);
                mTRTCCloud.setMixTranscodingConfig(null);
                break;
            case TRTCCloudDef.TRTC_TranscodingConfigMode_Manual:
                updateCloudMixtureManual();
                ConfigHelper.getInstance().getVideoConfig().setCurIsMix(true);
                break;
            case TRTCCloudDef.TRTC_TranscodingConfigMode_Template_PureAudio:
                updateCloudMixturePureAudio();
                ConfigHelper.getInstance().getVideoConfig().setCurIsMix(true);
                break;
            case TRTCCloudDef.TRTC_TranscodingConfigMode_Template_PresetLayout:
                updateCloudMixturePresetLayout();
                ConfigHelper.getInstance().getVideoConfig().setCurIsMix(true);
                break;
        }
    }

    private void updateCloudMixtureManual() {
        ArrayList<TRTCVideoStream> local = new ArrayList<>();
        TRTCVideoStream user = new TRTCVideoStream();
        user.userId = mTRTCParams.userId;
        user.streamType = TRTC_VIDEO_STREAM_TYPE_BIG;
        local.add(user);
        updateCloudMixture(TRTCCloudDef.TRTC_TranscodingConfigMode_Manual, local, mTRTCVideoStreams);
    }

    private void updateCloudMixturePresetLayout() {
        ArrayList<TRTCVideoStream> local = new ArrayList<>();
        TRTCVideoStream user = new TRTCVideoStream();
        user.userId = "$PLACE_HOLDER_LOCAL_MAIN$";
        user.streamType = TRTC_VIDEO_STREAM_TYPE_BIG;
        local.add(user);

        user = new TRTCVideoStream();
        user.userId = "$PLACE_HOLDER_LOCAL_SUB$";
        user.streamType = TRTCCloudDef.TRTC_VIDEO_STREAM_TYPE_SUB;
        local.add(user);

        ArrayList<TRTCVideoStream> remote = new ArrayList<>();
        for (int index = 0; index < 6; ++index) {
            TRTCVideoStream stream = new TRTCVideoStream();
            stream.userId = "$PLACE_HOLDER_REMOTE$";
            stream.streamType = TRTC_VIDEO_STREAM_TYPE_BIG;
            remote.add(stream);
        }
        updateCloudMixture(TRTCCloudDef.TRTC_TranscodingConfigMode_Template_PresetLayout, local, remote);
    }


    private void updateCloudMixturePureAudio() {
        TRTCCloudDef.TRTCTranscodingConfig config = new TRTCCloudDef.TRTCTranscodingConfig();
        config.mode = TRTCCloudDef.TRTC_TranscodingConfigMode_Template_PureAudio;
        ///【字段含义】腾讯云直播 AppID
        ///【推荐取值】请在 [实时音视频控制台](https://console.cloud.tencent.com/rav) 选择已经创建的应用，单击【帐号信息】后，在“直播信息”中获取
        config.appId = sdkAppId;
        ///【字段含义】腾讯云直播 bizid
        ///【推荐取值】请在 [实时音视频控制台](https://console.cloud.tencent.com/rav) 选择已经创建的应用，单击【帐号信息】后，在“直播信息”中获取
        config.bizId = 3891;
        config.audioSampleRate = 48000;
        config.audioBitrate = 64;
        config.audioChannels = 1;

        mTRTCCloud.setMixTranscodingConfig(config);
    }

    private void updateCloudMixture(int mode, ArrayList<TRTCVideoStream> localList, ArrayList<TRTCVideoStream> remoteList) {
        // 背景大画面宽高
        int videoWidth = 720;
        int videoHeight = 1280;

        // 小画面宽高
        int subWidth = 180;
        int subHeight = 320;

        int offsetX = 5;
        int offsetY = 50;

        int bitrate = 200;
        VideoConfig videoConfig = ConfigHelper.getInstance().getVideoConfig();
        int resolution = videoConfig.getVideoResolution();
        switch (resolution) {
            case TRTCCloudDef.TRTC_VIDEO_RESOLUTION_160_160: {
                videoWidth = 160;
                videoHeight = 160;
                subWidth = 32;
                subHeight = 48;
                offsetY = 10;
                bitrate = 200;
                break;
            }
            case TRTCCloudDef.TRTC_VIDEO_RESOLUTION_320_180: {
                videoWidth = 192;
                videoHeight = 336;
                subWidth = 54;
                subHeight = 96;
                offsetY = 30;
                bitrate = 400;
                break;
            }
            case TRTCCloudDef.TRTC_VIDEO_RESOLUTION_320_240: {
                videoWidth = 240;
                videoHeight = 320;
                subWidth = 54;
                subHeight = 96;
                offsetY = 30;
                bitrate = 400;
                break;
            }
            case TRTCCloudDef.TRTC_VIDEO_RESOLUTION_480_480: {
                videoWidth = 480;
                videoHeight = 480;
                subWidth = 72;
                subHeight = 128;
                bitrate = 600;
                break;
            }
            case TRTCCloudDef.TRTC_VIDEO_RESOLUTION_640_360: {
                videoWidth = 368;
                videoHeight = 640;
                subWidth = 90;
                subHeight = 160;
                bitrate = 800;
                break;
            }
            case TRTCCloudDef.TRTC_VIDEO_RESOLUTION_640_480: {
                videoWidth = 480;
                videoHeight = 640;
                subWidth = 90;
                subHeight = 160;
                bitrate = 800;
                break;
            }
            case TRTCCloudDef.TRTC_VIDEO_RESOLUTION_960_540: {
                videoWidth = 544;
                videoHeight = 960;
                subWidth = 160;
                subHeight = 288;
                bitrate = 1000;
                break;
            }
            case TRTCCloudDef.TRTC_VIDEO_RESOLUTION_1280_720: {
                videoWidth = 720;
                videoHeight = 1280;
                subWidth = 192;
                subHeight = 336;
                bitrate = 1500;
                break;
            }
        }

        TRTCCloudDef.TRTCTranscodingConfig config = new TRTCCloudDef.TRTCTranscodingConfig();
        config.mode = mode;
        ///【字段含义】腾讯云直播 AppID
        ///【推荐取值】请在 [实时音视频控制台](https://console.cloud.tencent.com/rav) 选择已经创建的应用，单击【帐号信息】后，在“直播信息”中获取
        config.appId = sdkAppId;
        ///【字段含义】腾讯云直播 bizid
        ///【推荐取值】请在 [实时音视频控制台](https://console.cloud.tencent.com/rav) 选择已经创建的应用，单击【帐号信息】后，在“直播信息”中获取
        config.bizId = 3891;
        config.videoWidth = videoWidth;
        config.videoHeight = videoHeight;
        config.videoGOP = 1;
        config.videoFramerate = 15;
        config.videoBitrate = bitrate;
        config.audioSampleRate = 48000;
        config.audioBitrate = 64;
        config.audioChannels = 1;

        config.mixUsers = new ArrayList<>();

        for (TRTCVideoStream userStream : localList) {
            // 设置混流后主播的画面位置
            TRTCCloudDef.TRTCMixUser mixUser = new TRTCCloudDef.TRTCMixUser();
            mixUser.userId = userStream.userId;
            mixUser.streamType = userStream.streamType;
            mixUser.zOrder = 1;
            mixUser.x = 0;
            mixUser.y = 0;
            mixUser.width = videoWidth;
            mixUser.height = videoHeight;

            config.mixUsers.add(mixUser);
        }


        // 设置混流后各个小画面的位置
        int index = 0;
        TXLog.i(TAG, "updateCloudMixtureParams " + remoteList.size());
        for (TRTCVideoStream userStream : remoteList) {
            TRTCCloudDef.TRTCMixUser _mixUser = new TRTCCloudDef.TRTCMixUser();
            PkConfig pkConfig = ConfigHelper.getInstance().getPkConfig();
            if (pkConfig.isConnected() && userStream.userId.equalsIgnoreCase(pkConfig.getConnectUserName())) {
                _mixUser.roomId = pkConfig.getConnectRoomId();
            }

            _mixUser.userId = userStream.userId;
            _mixUser.streamType = userStream.streamType;
            _mixUser.zOrder = 2 + index;
            if (index < 3) {
                // 前三个小画面靠右从下往上铺
                _mixUser.x = videoWidth - offsetX - subWidth;
                _mixUser.y = videoHeight - offsetY - index * subHeight - subHeight;
                _mixUser.width = subWidth;
                _mixUser.height = subHeight;
            } else if (index < 6) {
                // 后三个小画面靠左从下往上铺
                _mixUser.x = offsetX;
                _mixUser.y = videoHeight - offsetY - (index - 3) * subHeight - subHeight;
                _mixUser.width = subWidth;
                _mixUser.height = subHeight;
            } else {
                // 最多只叠加六个小画面
            }
            TXLog.i(TAG, "updateCloudMixtureParams userId " + _mixUser.userId);
            config.mixUsers.add(_mixUser);
            ++index;
        }

        mTRTCCloud.setMixTranscodingConfig(config);
    }

    /**
     * 混流相关参数
     */
    private static class TRTCVideoStream {
        public String userId;
        public int streamType;
    }

    @Override
    public void onExitRoom(int reason) {

    }

    @Override
    public void onError(int errCode, String errMsg, Bundle extraInfo) {
        showMessage("进房错误: " + errMsg + "[" + errCode + "]");
        System.out.println("进房错误: " + errMsg + "[" + errCode + "]");
        // 执行退房
        exitRoom();
        finish();
    }

    @Override
    public void onRemoteUserEnterRoom(String userId) {

    }

    @Override
    public void onRemoteUserLeaveRoom(String userId, int reason) {
        mTRTCCloud.stopRemoteView(userId);
        mTRTCCloud.stopRemoteSubStreamView(userId);
        remoteUserVideoUnavailable(userId, TRTC_VIDEO_STREAM_TYPE_BIG);
    }

    @Override
    public void onUserVideoAvailable(String userId, boolean available) {
        if (available) {
            addView(userId);
        } else {
            remoteUserVideoUnavailable(userId, TRTC_VIDEO_STREAM_TYPE_BIG);
        }
    }

    /**
     * 远程用户关闭了视频流，停止界面的渲染，并移除一条视频流
     */
    public void remoteUserVideoUnavailable(String userId, int streamType) {
        if (streamType == TRTCCloudDef.TRTC_VIDEO_STREAM_TYPE_SUB || !mIsCustomCaptureAndRender) {
            stopSDKRender(userId, streamType);
        }
        removeVideoStream(userId, streamType);
    }

    private void removeVideoStream(String userId, int streamType) {
        Iterator<PlayerViewParams> iterator = list.iterator();
        PlayerViewParams removeView = null;
        PlayerViewParams myselfView = null;
        while (iterator.hasNext()) {
            PlayerViewParams next = iterator.next();
            if (userId.equals(next.userId)) {
                iterator.remove();
                removeView = next;
            }
            if (next.isMySelf) {
                myselfView = next;
            }
        }
        if (myselfView == null || removeView == null) {
            return;
        }
        if (!isGrid) {//最大的那个是别人而且被移除了。
            if (removeView.txCloudVideoView.getLayoutParams().width == -1) {
                flVideo.removeAllViews();
                llVideo.removeView(myselfView.txCloudVideoView);
                flVideo.addView(myselfView.txCloudVideoView);
                initLocalLayoutParams((MarginLayoutParams) myselfView.txCloudVideoView.getLayoutParams());
            } else {
                llVideo.removeView(removeView.txCloudVideoView);
            }
        } else {
            trtcVideoAdapter.deleteAdapter(removeView);
        }
        if (list.size() == 1) {
            viewBg.setVisibility(View.INVISIBLE);
        }
    }

    private void stopSDKRender(String userId, int streamType) {
        // 停止渲染
        if (streamType == TRTC_VIDEO_STREAM_TYPE_BIG) {
            mTRTCCloud.stopRemoteView(userId);
        } else if (streamType == TRTCCloudDef.TRTC_VIDEO_STREAM_TYPE_SUB) {
            mTRTCCloud.stopRemoteSubStreamView(userId);
        }
    }

    private void addView(String userId) {
        TXCloudVideoView txCloudVideoView;
        if (isGrid) {
            txCloudVideoView = getLayoutInflater().inflate(R.layout.item_local_vedio, rlGrid, false)
                    .findViewById(R.id.trtc_cdn_play_view);
        } else {
            txCloudVideoView = new TXCloudVideoView(this);
        }
        txCloudVideoView.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
        TRTCVideoStream stream = new TRTCVideoStream();
        stream.userId = userId;
        stream.streamType = TRTC_VIDEO_STREAM_TYPE_BIG;
        // 增加成员
        RemoteUserConfig remoteUserConfig = RemoteUserConfigHelper.getInstance().getRemoteUser(userId);
        if (remoteUserConfig == null) {
            remoteUserConfig = new RemoteUserConfig(userId, TRTC_VIDEO_STREAM_TYPE_BIG);
            RemoteUserConfigHelper.getInstance().addRemoteUser(remoteUserConfig);
        }

        // 如果用户勾选了打开远端视频
        if (remoteUserConfig.isEnableVideo()) {
            // 当有辅路上行(屏幕分享）或者没打开自定义渲染的时候需要调用
            if (stream.streamType == TRTCCloudDef.TRTC_VIDEO_STREAM_TYPE_SUB || !mIsCustomCaptureAndRender) {
                mTRTCCloud.setRemoteViewFillMode(userId, TRTCCloudDef.TRTC_VIDEO_RENDER_MODE_FILL);
                mTRTCCloud.startRemoteView(userId, txCloudVideoView);
            }
            txCloudVideoView.setOnClickListener(this);
            PlayerViewParams playerViewParams = new PlayerViewParams();
            playerViewParams.userId = userId;
            playerViewParams.txCloudVideoView = txCloudVideoView;
            list.add(playerViewParams);
            txCloudVideoView.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
            txCloudVideoView.setTag(playerViewParams);
            if (isGrid) {
                viewBg.setVisibility(View.INVISIBLE);
                UIUtils.removeView(playerViewParams.txCloudVideoView);
                trtcVideoAdapter.insertView(playerViewParams);
                rlGrid.getRecycledViewPool().clear();
                MarginLayoutParams layoutParams = (MarginLayoutParams) playerViewParams.txCloudVideoView.getLayoutParams();
                initGraidLayoutParams(layoutParams);
                txCloudVideoView.setLayoutParams(layoutParams);
            } else {

                viewBg.setVisibility(View.VISIBLE);
                llVideo.setVisibility(View.VISIBLE);
                llVideo.addView(txCloudVideoView);
                MarginLayoutParams layoutParams = (MarginLayoutParams) txCloudVideoView.getLayoutParams();
                initBottomGraidLayoutParams(layoutParams);
                txCloudVideoView.setLayoutParams(layoutParams);
            }


        }

        // 记录当前流的类型，以供设置混流参数时使用：
        if (!isContainVideoStream(userId, TRTC_VIDEO_STREAM_TYPE_BIG)) {
            mTRTCVideoStreams.add(stream);
            TXLog.i(TAG, "remoteUserVideoAvailable " + stream.userId +
                    ", stream " + TRTC_VIDEO_STREAM_TYPE_BIG +
                    ", size " + mTRTCVideoStreams.size());
        }
    }

    private boolean isContainVideoStream(String userId, int streamType) {
        for (TRTCVideoStream stream : mTRTCVideoStreams) {
            if (stream != null && stream.userId != null && stream.userId.equals(userId) && stream.streamType == streamType) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onUserSubStreamAvailable(String userId, boolean available) {

    }

    @Override
    public void onUserAudioAvailable(String userId, boolean available) {

    }

    @Override
    public void onFirstVideoFrame(String userId, int streamType, int width, int height) {

    }

    @Override
    public void onUserVoiceVolume(ArrayList<TRTCCloudDef.TRTCVolumeInfo> userVolumes, int totalVolume) {

    }

    @Override
    public void onStatistics(TRTCStatistics statics) {

    }

    @Override
    public void onConnectOtherRoom(String userID, int err, String errMsg) {

    }

    @Override
    public void onDisConnectOtherRoom(int err, String errMsg) {

    }

    @Override
    public void onNetworkQuality(TRTCCloudDef.TRTCQuality localQuality, ArrayList<TRTCCloudDef.TRTCQuality> remoteQuality) {

    }

    @Override
    public void onAudioEffectFinished(int effectId, int code) {

    }

    @Override
    public void onRecvCustomCmdMsg(String userId, int cmdID, int seq, byte[] message) {

    }

    @Override
    public void onRecvSEIMsg(String userId, byte[] data) {

    }

    /**
     * 背景噪音抑制功能，可探测出背景固定频率的杂音并消除背景噪音
     *
     * @param enable
     */
    public void enableANS(boolean enable) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("api", "enableAudioANS");
            JSONObject params = new JSONObject();
            params.put("enable", enable ? 1 : 0);
            jsonObject.put("params", params);
            mTRTCCloud.callExperimentalAPI(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        mTRTCCloud.setSystemVolumeType(TRTCCloudDef.TRTCSystemVolumeTypeMedia);
        exitRoom();
        if (mTRTCCloud != null) {
            mTRTCCloud.setListener(null);
            mTRTCCloud.stopLocalAudio();
        }
        list.clear();
        TRTCCloud.destroySharedInstance();
        llVideo.removeAllViews();
        rlGrid.removeAllViews();
        super.onDestroy();
        mTRTCCloud = null;
    }
}
