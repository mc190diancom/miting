package com.feidi.template.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.feidi.template.R;
import com.feidi.template.di.component.DaggerLiveComponent;
import com.feidi.template.mvp.contract.LiveContract;
import com.feidi.template.mvp.model.entity.LiveChatRoomBean;
import com.feidi.template.mvp.presenter.LivePresenter;
import com.feidi.template.mvp.ui.activity.LiveRoomsActivity;
import com.jess.arms.di.component.AppComponent;
import com.miu30.common.base.BaseMvpFragment;
import com.miu30.common.util.UIUtils;

import butterknife.BindView;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/22/2020 10:26
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class LiveFragment extends BaseMvpFragment<LivePresenter> implements LiveContract.View, View.OnClickListener {

    @BindView(R.id.et_live)
    EditText etLive;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.layout_live_dialog)
    CardView layoutLiveDialog;
    @BindView(R.id.fl_live)
    FrameLayout flLive;


    public static LiveFragment newInstance() {
        LiveFragment fragment = new LiveFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerLiveComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_live, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                if (TextUtils.isEmpty(etLive.getText().toString())) {
                    UIUtils.toast(self, "房间号不能为空", Toast.LENGTH_SHORT);
                    return;
                }
                // startActivity(new Intent(self, TRTCVedioRoomActivity.class));
                mPresenter.invate(etLive.getText().toString());
            default:
                break;
        }
    }


    @Override
    public void Success(LiveChatRoomBean data) {
        Intent intent = new Intent(self, LiveRoomsActivity.class);
        intent.putExtra("livechat", data);
        ActivityUtils.startActivity(intent);
    }
}
