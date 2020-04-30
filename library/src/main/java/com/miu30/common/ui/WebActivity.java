package com.miu30.common.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miu30.common.base.BaseActivity;
import com.miu30.common.util.WebUtil;
import com.miu30.common.view.WebViewController;
import com.miu360.library.R;
import com.miu360.library.R2;

import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {

    private static final String TAG = "WebActivity";
    public static String type;
    @BindView(R2.id.left_btn)
    ImageButton leftBtn;
    @BindView(R2.id.rl_left)
    RelativeLayout rlLeft;
    @BindView(R2.id.left_delete_btn)
    ImageButton leftDeleteBtn;
    @BindView(R2.id.title)
    TextView tvTitle;
    @BindView(R2.id.right_btn_1)
    ImageButton rightBtn1;
    @BindView(R2.id.right_btn_2)
    ImageButton rightBtn2;
    @BindView(R2.id.right_text_btn)
    Button rightTextBtn;
    @BindView(R2.id.bottom_line)
    ImageView bottomLine;
    @BindView(R2.id.header_layout)
    RelativeLayout headerLayout;
    @BindView(R2.id.prog)
    ProgressBar prog;
    @BindView(R2.id.more_controller)
    WebViewController moreController;
    @BindView(R2.id.more_webview)
    WebView moreWebview;
    private WebView webView;
    private String title;
    private String url;
    private ProgressBar progressBar;

    public static Intent getTargetIntent(Context ctx, String title, String url, boolean canGoHistory,boolean isCha) {//isCha显示X还是显示返回键
        Intent intent = new Intent(ctx, WebActivity.class);
        intent.putExtra("head", true);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("canGoHistory", canGoHistory);
        return intent;
    }

    public static Intent getTargetIntent(Context ctx, boolean head, String title, String url, boolean canGoHistory) {
        Intent intent = new Intent(ctx, WebActivity.class);
        intent.putExtra("head", head);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("canGoHistory", canGoHistory);
        return intent;
    }

    public static Intent getTargetIntent(Context ctx, String title, String url) {
        Intent intent = new Intent(ctx, WebActivity.class);
        intent.putExtra("head", true);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("canGoHistory", true);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        ButterKnife.bind(this);
        type = null;
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        if (!url.isEmpty()) {
            if (url.indexOf("type") != -1) {
                int left = url.indexOf("type") + 5;
                type = url.substring(left, url.length());
            }
            initView();
            initData();
        }
        initTitle();
    }

    private void initTitle() {
        tvTitle.setText(title);
        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        webView.loadUrl(url);
    }

    private void initView() {
        webView = findViewById(R.id.more_webview);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        progressBar = findViewById(R.id.prog);
        WebViewController ctrl =  findViewById(R.id.more_controller);
        ctrl.setVisibility(getIntent().getBooleanExtra("controller", false) ? View.VISIBLE : View.GONE);
        List<WebUtil.HandleCallback> handlers = new ArrayList<>();
        /*handlers.add(new AppWebViewHandler());
        handlers.add(new WebUtil.HandleCallback() {

            @Override
            public boolean handle(String url) {
                finish();
                return true;
            }

            @Override
            public boolean accept(String url) {
                return url.startsWith("http://ytk_finish");
            }
        });
        handlers.add(new WebUtil.HandleCallback() {

            @Override
            public boolean handle(String url) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }

            @Override
            public boolean accept(String url) {
                return url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("tel:");
            }
        });*/
        WebUtil.wrapWebView(webView, ctrl, handlers, progressBar);
        ctrl.setWebView(webView);
    }

    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    public void onBackPressed() {
        if (getIntent().getBooleanExtra("canGoHistory", true)) {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                super.onBackPressed();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((ViewGroup) webView.getParent()).removeView(webView);
        webView.destroy();
    }


    public class AppWebViewHandler implements WebUtil.HandleCallback {
        private final String PRE_FIX = "fd://";

        @Override
        public boolean handle(String rawUrl) {
            try {
                rawUrl = URLDecoder.decode(rawUrl, "UTF-8");
                String method = rawUrl.substring(rawUrl.indexOf("//") + 2, rawUrl.indexOf("?"));
                JSONObject params = new JSONObject(rawUrl.substring(rawUrl.indexOf("?") + 1));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        public boolean accept(String url) {
            return url.startsWith(PRE_FIX);
        }

    }

}
