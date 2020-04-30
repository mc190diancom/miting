package com.feidi.template.mvp.ui.fragment;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.feidi.template.R;
import com.feidi.template.di.component.DaggerLiveRoomFragmentComponent;
import com.feidi.template.mvp.contract.LiveRoomFragmentContract;
import com.feidi.template.mvp.contract.LiveRoomsContract;
import com.feidi.template.mvp.model.entity.LiveChatRoomBean;
import com.feidi.template.mvp.presenter.LiveRoomFragmentPresenter;
import com.feidi.template.mvp.ui.view.HideTextView;
import com.feidi.template.mvp.ui.view.PicView;
import com.feidi.template.mvp.util.SoftKeyBoardListener;
import com.feidi.tim.helper.ChatLayoutHelper;
import com.jess.arms.di.component.AppComponent;
import com.miu30.common.base.BaseMvpFragment;
import com.miu30.common.config.Config;
import com.miu30.common.util.UIUtils;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.demo.trtc.sdkadapter.cdn.CdnPlayManager;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfoUtil;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.trtc.TRTCCloudDef;

import org.simple.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/23/2020 18:46
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class LiveRoomFragment extends BaseMvpFragment<LiveRoomFragmentPresenter> implements LiveRoomFragmentContract.View, ITXLivePlayListener {

    @BindView(R.id.tvTitle)
    HideTextView tvTitle;
    @BindView(R.id.trtc_cdn_play_view)
    TXCloudVideoView trtcCdnPlayView;
    @BindView(R.id.tvSend)
    TextView tvSend;
    @BindView(R.id.llCheckAll)
    LinearLayout llCheckAll;
    @BindView(R.id.tvCheckAll)
    TextView tvCheckAll;
    @BindView(R.id.rlVideoView)
    FrameLayout rlVideoView;
    @BindView(R.id.pvRotationLand)
    PicView pvRotationLand;
    @BindView(R.id.flPortrait)
    LinearLayout flPortrait;
    @BindView(R.id.etInput)
    EditText etInput;

    @BindView(R.id.chat_layout)
    ChatLayout mChatLayout;
    @BindView(R.id.flInputLand)
    RelativeLayout flInputLand;
    @BindView(R.id.llTing)
    RadioGroup llTing;
    @BindView(R.id.viewGray)
    View viewGray;
    @BindView(R.id.llCompany)
    LinearLayout llCompany;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvCompanyLand)
    TextView tvCompanyLand;
    @BindView(R.id.ivRotation)
    ImageView ivRotation;
    @BindView(R.id.rlTitleLand)
    RelativeLayout rlTitleLand;
    @BindView(R.id.ll_chat_send)
    LinearLayout llChatSend;
    @BindView(R.id.ivCheckAll)
    ImageView ivCheckAll;
    private CdnPlayManager mCdnPlayManager;
    private TRTCCloudDef.TRTCParams mTRTCParams;
    private TXLivePlayer mTXLivePlayer;
    private TXLivePlayConfig mTXLivePlayConfig;
    private int mCurrentRenderRotation;
    private ChatInfo mChatInfo;
    private ChatLayoutHelper helper;

    private LiveChatRoomBean mLiveChatRoomBean;

    public static LiveRoomFragment newInstance() {
        LiveRoomFragment fragment = new LiveRoomFragment();
        return fragment;
    }

    //private String title = "莫德生物医疗科技有限公司：主营医疗器械、口罩、防护莫德生物医疗科技有限公司：主营医疗器械、口罩、防护";
    private boolean showAll = true;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLiveRoomFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_live_room, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mLiveChatRoomBean = self.getIntent().getParcelableExtra("livechat");
        if (initShowData()) {
            self.finish();
            return;
        }
        initLivePlayer();
        initChat();
        initTing();
        initTitle();
        initSoftListner();
    }

    /**
     * 当
     */
    private void initSoftListner() {
        int bottom = UIUtils.dip2px(self, 10);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mChatLayout.getInputLayout().getLayoutParams();
        SoftKeyBoardListener.setListener(self, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                if (mCurrentRenderRotation != 360) {
                    lp.setMargins(0, 0, 0, height);
                    lp.bottomMargin = height;
                    mChatLayout.getInputLayout().setLayoutParams(lp);
                }
            }

            @Override
            public void keyBoardHide(int height) {
                if (mCurrentRenderRotation != 360) {
                    lp.setMargins(0, 0, 0, 0);
                    mChatLayout.getInputLayout().setLayoutParams(lp);
                }
            }
        });

    }

    private boolean initShowData() {
        if (mLiveChatRoomBean == null || mLiveChatRoomBean.getLive() == null || mLiveChatRoomBean.getLive().isEmpty()) {
            UIUtils.toast(self, "缺少数据", Toast.LENGTH_SHORT);
            return true;
        }
        return false;
    }

    RadioButton mRadioButton;

    private void initTing() {
        if (mLiveChatRoomBean.getLive().size() == 1) {//舞台只有一个时，隐藏二级标题
            llTing.setVisibility(View.GONE);
            return;
        }
        for (int i = 0; i < mLiveChatRoomBean.getLive().size(); i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setButtonDrawable(null);
            if (0 == i) {
                radioButton.setText(getResources().getString(R.string.live_small_title1));
            } else if (1 == i) {
                radioButton.setText(getResources().getString(R.string.live_small_title2));
            } else {
                radioButton.setText(getResources().getString(R.string.live_small_title3));
            }
            radioButton.setHeight(UIUtils.dip2px(getContext(), 44));
            radioButton.setTextSize(14);
            if (i == 0) {
                radioButton.setTextColor(getResources().getColor(R.color.txt_color_blue));
                mRadioButton = radioButton;
            } else {
                radioButton.setTextColor(getResources().getColor(R.color.txt_color_gray));
            }
            llTing.addView(radioButton);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) radioButton.getLayoutParams();
            layoutParams.rightMargin = UIUtils.dip2px(getContext(), 34);
        }
        llTing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                radioButton.setTextColor(getResources().getColor(R.color.txt_color_blue));
                mRadioButton.setTextColor(getResources().getColor(R.color.txt_color_gray));
                mRadioButton = radioButton;
                if (mLiveChatRoomBean.getLive().size() > checkedId - 1) {
                    setChat(checkedId - 1);
                    setTitle(checkedId - 1);
                    setPlay(checkedId - 1);
                }
            }
        });
    }

    Handler mHandler = new Handler();

    private void initLivePlayer() {
        mTXLivePlayer = new TXLivePlayer(getContext());
        mTXLivePlayConfig = new TXLivePlayConfig();
        mTXLivePlayConfig.setAutoAdjustCacheTime(true);
        mTXLivePlayConfig.setMaxAutoAdjustCacheTime(2.0f);
        mTXLivePlayConfig.setMinAutoAdjustCacheTime(2.0f);
        mTXLivePlayer.setConfig(mTXLivePlayConfig);
        mTXLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT);
        mTXLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
        mTXLivePlayer.setPlayerView(trtcCdnPlayView);
        mTXLivePlayer.setPlayListener(this);
        trtcCdnPlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentRenderRotation == 360) {
                    if (rlTitleLand.getVisibility() == View.VISIBLE) {
                        rlTitleLand.setVisibility(View.GONE);
                    } else {
                        rlTitleLand.setVisibility(View.VISIBLE);
                        setRotationLandVisible();
                    }
                } else {
                    setRotationLandVisible();
                }
            }
        });
        setPlay(0);
    }

    private void setRotationLandVisible() {
        pvRotationLand.setVisibility(View.VISIBLE);
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pvRotationLand.setVisibility(View.GONE);
            }
        }, 3 * 1000);
    }

    //开始播放
    private void setPlay(int index) {
        String url = mLiveChatRoomBean.getLive().get(index).getExhibitionLiveUrl();
        //url = "http://5815.liveplay.myqcloud.com/live/5815_89aad37e06ff11e892905cb9018cf0d4.flv";
        mTXLivePlayer.startPlay(url, getPlayType(url));
    }

    private void initChat() {
        mChatInfo = new ChatInfo();
        mChatInfo.setType(TIMConversationType.Group);
        setChat(0);
    }

    private void setChat(int index) {
        mChatInfo.setChatName(mLiveChatRoomBean.getLive().get(index).getTitle());
        mChatInfo.setId(mLiveChatRoomBean.getLive().get(index).getRoomNumber());//
        mChatLayout.initDefault();
        /*
         * 需要聊天的基本信息
         */
        mChatLayout.setChatInfo(mChatInfo);
        helper = new ChatLayoutHelper(getActivity());
        helper.customizeChatLayout(mChatLayout);
    }


    private void initTitle() {
        llCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showAll) {
                    tvTitle.setSingleLine(false);
                    tvTitle.setEllipsize(null);
                    showAll = false;
                    tvCheckAll.setText("收拢");
                    ivCheckAll.animate().rotation(180).setDuration(100).start();
                } else {
                    ivCheckAll.animate().rotation(0).setDuration(100).start();
                    showAll = true;
                    tvTitle.setSingleLine(true);
                    tvTitle.setEllipsize(TextUtils.TruncateAt.END);
                    tvCheckAll.setText("展开");
                }
            }
        });
        tvTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (tvTitle != null && tvTitle.isOverFlowed()) {
                    llCheckAll.setVisibility(View.VISIBLE);
                    showAll = true;
                } else {
                    llCheckAll.setVisibility(View.GONE);
                    showAll = false;
                }

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setTitle(0);
            }
        }, 200);
    }

    private void setTitle(int index) {
        tvTitle.setText(mLiveChatRoomBean.getLive().get(index).getTitle());
    }

    @Override
    public void onPlayEvent(int event, Bundle param) {
        if (event == TXLiveConstants.PLAY_ERR_NET_DISCONNECT) {
            String msg = "[LivePlayer] 拉流失败[" + param.getString(TXLiveConstants.EVT_DESCRIPTION) + "]";
            TXCLog.e(TAG, msg);
        } else if (event == TXLiveConstants.PLAY_EVT_CHANGE_RESOLUTION) {

        }
    }

    @Override
    public void onNetStatus(Bundle bundle) {
        Log.d(TAG, "onNetStatus: " + bundle);
    }

    @OnClick({R.id.ivBack, R.id.pvRotationLand, R.id.tvSend})
    public void onViewClicked(View view) {
        if (view == pvRotationLand) {
            //设置Activity横屏显示
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        } else if (view == ivBack) {
            //设置Activity竖屏显示
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        } else if (view == tvSend) {
            hideNav();
            String trim = etInput.getText().toString().trim();
            if (!TextUtils.isEmpty(trim)) {
                etInput.setText("");
                mChatLayout.getChatManager().sendMessage(MessageInfoUtil.buildTextMessage(trim), false, new IUIKitCallBack() {
                    @Override
                    public void onSuccess(Object data) {

                    }

                    @Override
                    public void onError(String module, int errCode, String errMsg) {

                    }
                });
            }
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LiveRoomsContract.View view = (LiveRoomsContract.View) getActivity();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideNav();
            if (view != null) {
                view.showTitle(View.GONE);
            }
            flInputLand.setVisibility(View.VISIBLE);
            llTing.setVisibility(View.GONE);
            mChatLayout.setVisibility(View.GONE);
            flPortrait.setVisibility(View.GONE);
            viewGray.setVisibility(View.GONE);
            llCompany.setVisibility(View.GONE);
            mCurrentRenderRotation = 360;
            ViewGroup.LayoutParams layoutParams = trtcCdnPlayView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            trtcCdnPlayView.setLayoutParams(layoutParams);

        } else {
            rlTitleLand.setVisibility(View.GONE);
            flInputLand.setVisibility(View.GONE);
            if (view != null) {
                view.showTitle(View.VISIBLE);
            }
            llTing.setVisibility(View.VISIBLE);
            mChatLayout.setVisibility(View.VISIBLE);
            flPortrait.setVisibility(View.VISIBLE);
            viewGray.setVisibility(View.VISIBLE);
            llCompany.setVisibility(View.VISIBLE);
            mCurrentRenderRotation = TXLiveConstants.RENDER_ROTATION_PORTRAIT;
            ViewGroup.LayoutParams layoutParams = trtcCdnPlayView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = UIUtils.dip2px(getContext(), 300);
            trtcCdnPlayView.setLayoutParams(layoutParams);

        }
        if (mTXLivePlayer == null) {
            return;
        }
        mTXLivePlayer.setRenderRotation(mCurrentRenderRotation);
    }

    protected int getPlayType(String playUrl) {
        int playType;
        if (playUrl.startsWith("rtmp://")) {
            playType = TXLivePlayer.PLAY_TYPE_LIVE_RTMP;
        } else if (playUrl.endsWith(".m3u8")) {
            playType = TXLivePlayer.PLAY_TYPE_VOD_HLS;
        } else {
            playType = TXLivePlayer.PLAY_TYPE_LIVE_FLV;
        }
        return playType;
    }

    private void hideNav() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    public void onPause() {
        super.onPause();
        mTXLivePlayer.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mTXLivePlayer.resume();
    }

    @Override
    public void onDestroy() {
        if (mTXLivePlayer != null && mTXLivePlayer.isPlaying()) {
            mTXLivePlayer.stopPlay(true);
            mTXLivePlayer.stopRecord();
        }
        if (helper != null) {
            helper.onDestroy();
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

}
