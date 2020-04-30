package com.miu30.common.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.miu360.library.R;

public class WebViewController extends LinearLayout {

    private View backBtn;
    private View forwardBtn;
    private TextView title;
    private View outBtn;
    private WebView webView;

    public WebViewController(Context context) {
        super(context);
        init();
    }

    public WebViewController(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setFocusable(false);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.web_controller, this, true);
        backBtn = findViewById(R.id.web_back);
        forwardBtn = findViewById(R.id.web_forward);
        title = findViewById(R.id.tv_web_title);
    }

    public View getBackBtn() {
        return backBtn;
    }

    public void setBackBtn(View backBtn) {
        this.backBtn = backBtn;
    }

    public View getForwardBtn() {
        return forwardBtn;
    }

    public void setForwardBtn(View forwardBtn) {
        this.forwardBtn = forwardBtn;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }


    public View getOutBtn() {
        return outBtn;
    }

    public void setOutBtn(View outBtn) {
        this.outBtn = outBtn;
    }

    public WebView getWebView() {
        return webView;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
        setUpBtn();
    }

    private void setUpBtn() {
        if (backBtn != null) {
            backBtn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (webView.canGoBack()) {
                        webView.goBack();
                    }
                }
            });
        }
        if (forwardBtn != null) {
            forwardBtn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (webView.canGoForward()) {
                        webView.goForward();
                    }
                }
            });
        }
        /*if (stopBtn != null) {
            stopBtn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    webView.stopLoading();
                }
            });
        }*/

    }

}
