package com.feidi.template.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.feidi.template.R;
import com.feidi.template.di.component.DaggerLoginComponent;
import com.feidi.template.mvp.contract.LoginContract;
import com.feidi.template.mvp.model.entity.UserBean;
import com.feidi.template.mvp.presenter.LoginPresenter;
import com.jess.arms.di.component.AppComponent;
import com.miu30.common.base.BaseMvpActivity;
import com.miu30.common.config.Config;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/27/2020 10:11
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.tvLogin)
    TextView tvLogin;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        SPUtils userInfo = SPUtils.getInstance(Config.SP_USERINFO);
        String account = userInfo.getString("account");
        if (!TextUtils.isEmpty(account)) {
            etAccount.setText(account);
            etAccount.setSelection(account.length());
        }
    }

    @OnClick(R.id.tvLogin)
    public void onViewClicked() {
        String account = etAccount.getText().toString();
        String pwd = etPwd.getText().toString();

        if (TextUtils.isEmpty(account)) {
            showMessage("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showMessage("请输入密码");
            return;
        }
        mPresenter.toLogin(account, pwd);
    }

    @Override
    public void LoginSuccess(UserBean data) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userinfo", data);
        startActivity(intent);
        finish();
    }
}
