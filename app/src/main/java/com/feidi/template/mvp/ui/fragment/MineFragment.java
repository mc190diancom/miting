package com.feidi.template.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.blankj.utilcode.util.SPUtils;
import com.feidi.template.R;
import com.feidi.template.di.component.DaggerMineComponent;
import com.feidi.template.mvp.contract.MineContract;
import com.feidi.template.mvp.presenter.MinePresenter;
import com.feidi.template.mvp.ui.activity.ClipActivity;
import com.feidi.template.mvp.ui.activity.LoginActivity;
import com.feidi.template.mvp.util.CircleTransform;
import com.jess.arms.base.App;
import com.jess.arms.di.component.AppComponent;
import com.miu30.common.MiuBaseApp;
import com.miu30.common.base.BaseMvpFragment;
import com.miu30.common.config.Config;
import com.miu30.common.util.CommonDialog;
import com.miu30.common.util.FileUtil;
import com.miu30.common.util.UIUtils;
import com.miu30.common.util.Windows;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.zkteco.android.biometric.core.utils.ToolUtils.getApplicationContext;


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
public class MineFragment extends BaseMvpFragment<MinePresenter> implements MineContract.View, View.OnClickListener {
    @BindView(R.id.iv_mine_head)
    ImageView ivMineHead;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.tv_mine_myinfo)
    TextView tvMineMyinfo;
    @BindView(R.id.tv_mine_login_out)
    TextView tvMineLoginOut;
    @BindView(R.id.view_switcher)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.left_btn)
    ImageButton leftBtn;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.iv_xiangji)
    ImageView ivXiangji;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    private LayoutInflater layoutInflater;
    private String[] items = {"男", "女"};

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMineComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initHead();
        tvMineMyinfo.setOnClickListener(this);
        tvMineLoginOut.setOnClickListener(this);
        leftBtn.setOnClickListener(this);
        ivXiangji.setOnClickListener(this);
        //tvSex.setOnClickListener(this);
        tvCommit.setOnClickListener(this);
        initPaiZhao();
    }

    private void initPaiZhao() {
        layoutInflater = (LayoutInflater) self.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        photoSavePath = Environment.getExternalStorageDirectory() + "/LiveChat/";
        File file = new File(photoSavePath);
        if(!file.exists()){
            file.mkdirs();
        }
        photoSaveName = System.currentTimeMillis() + ".jpg";
    }

    private void initHead() {
        Picasso.get().load("https://pics5.baidu.com/feed/54fbb2fb43166d22adca21aa84ee9af19152d2a7.jpeg?token=f17d497ced6dcf974ac3c645193f3cff")
                .transform(new CircleTransform()).into(ivMineHead);
        String userName = SPUtils.getInstance(Config.SP_USERINFO).getString(Config.USER_NAME);
        tvMineName.setText(userName);
        tvUserName.setText(userName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_mine_myinfo:
                viewSwitcher.showNext();
                break;
            case R.id.tv_mine_login_out:
                Windows.confirm(self, "是否确认退出？", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SPUtils.getInstance(Config.SP_USERINFO).clear();
                        self.finish();
                        startActivity(new Intent(self, LoginActivity.class));
                    }
                });
                break;
            case R.id.left_btn:
                viewSwitcher.showPrevious();
                break;
            case R.id.iv_xiangji:
               showPopupWindow(ivXiangji);
                break;
            case R.id.tv_sex:
                Windows.singleChoice(self, "选择性别", items, new CommonDialog.OnDialogItemClickListener() {
                    @Override
                    public void dialogItemClickListener(int position) {
                        tvSex.setText(items[position]);
                    }
                });
                break;
            case R.id.tv_commit:
                UIUtils.toast(self,"暂时无法修改用户信息",Toast.LENGTH_SHORT);
                break;
            default:
                break;
        }
    }

    ///////////////////////////////////////////////
    public static final int PHOTOZOOM = 0; // 相册/拍照
    public static final int PHOTOTAKE = 1; // 相册/拍照
    private PopupWindow popWindow;
    private TextView photograph, albums;
    private LinearLayout cancel;
    private String photoSavePath;// 保存路径
    private String photoSaveName;// 图pian名
    private String path;// 图片全路径
    public static final int IMAGE_COMPLETE = 2; // 结果
    private void showPopupWindow(View parent) {
        if (popWindow == null) {
            View view = layoutInflater.inflate(R.layout.pop_select_photo, null);
            popWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            initPop(view);
        }
        popWindow.setAnimationStyle(android.R.style.Animation_InputMethod);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    }

    public void initPop(View view) {
        photograph = (TextView) view.findViewById(R.id.photograph);// 拍照
        albums = (TextView) view.findViewById(R.id.albums);// 相册
        cancel = (LinearLayout) view.findViewById(R.id.cancel);// 取消
        photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                popWindow.dismiss();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (intent.resolveActivity(MiuBaseApp.self.getPackageManager()) != null) {
                    Uri uri = FileUtil.getFileUri(getApplicationContext(), new File(photoSavePath
                            , photoSaveName));
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, PHOTOTAKE);
                }
            }
        });
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                popWindow.dismiss();
                Intent openAlbumIntent = new Intent(Intent.ACTION_PICK);
                openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(openAlbumIntent, PHOTOZOOM);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                popWindow.dismiss();

            }
        });
    }
    /**
     * 图片选择及拍照结果
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        Uri uri = null;
        switch (requestCode) {
            case PHOTOZOOM:// 相册
                if (data == null) {
                    return;
                }
                uri = data.getData();
                String[] proj = { MediaStore.Images.Media.DATA };
                Cursor cursor = self.managedQuery(uri, proj, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                path = cursor.getString(column_index);// 图片在的路径
                Intent intent3 = new Intent(self, ClipActivity.class);
                intent3.putExtra("path", path);
                startActivityForResult(intent3, IMAGE_COMPLETE);
                break;
            case PHOTOTAKE:// 拍照
                path = photoSavePath + photoSaveName;
                uri = Uri.fromFile(new File(path));
                Intent intent2 = new Intent(self, ClipActivity.class);
                intent2.putExtra("path", path);
                startActivityForResult(intent2, IMAGE_COMPLETE);
                break;
            case IMAGE_COMPLETE:
                final String temppath = data.getStringExtra("path");
                ivXiangji.setImageBitmap(getLoacalBitmap(temppath));
                ivXiangji.setScaleType(ImageView.ScaleType.FIT_XY);
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);

            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
