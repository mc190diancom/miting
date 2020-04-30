package com.miu30.common.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.miu30.common.MiuBaseApp;
import com.miu30.common.view.WebViewController;
import com.miu360.library.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebUtil {
    protected static final String TAG = "WebUtil";

    public static void wrapWebView(final WebView webView, WebViewController ctrl) {
        setUpWebView(webView, ctrl, null, null);
        setBtnOnState(webView, ctrl);
    }

    public static void wrapWebView(final WebView webView, WebViewController ctrl, List<HandleCallback> handlers, ProgressBar progressBar) {
        setUpWebView(webView, ctrl, handlers, progressBar);
        setBtnOnState(webView, ctrl);
    }

    private static void setBtnOnState(WebView webView, WebViewController ctrl) {
        ctrl.getBackBtn().setEnabled(webView.canGoBack());
        ctrl.getForwardBtn().setEnabled(webView.canGoForward());


        /*ctrl.getStopBtn().setVisibility(finish ? View.GONE : View.VISIBLE);
        ctrl.getRefreshBtn().setVisibility(finish ? View.VISIBLE : View.GONE);

        if (progress != 100) {
            if (ctrl.getProgressBar() != null) {
                ctrl.getProgressBar().setVisibility(View.VISIBLE);
                ctrl.getProgressBar().setMax(100);
                ctrl.getProgressBar().setProgress(progress);
            }
        } else {
            if (ctrl.getProgressBar() != null) {
                ctrl.getProgressBar().setVisibility(View.GONE);
            }
        }*/
    }

    @SuppressLint("NewApi")
    private static void setUpWebView(final WebView webView, final WebViewController ctrl,
                                     final List<HandleCallback> handlers, final ProgressBar progressBar) {

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        String dir = MiuBaseApp.self.getDir("database", Context.MODE_PRIVATE).getPath();
        //启用地理定位
        settings.setGeolocationEnabled(true);
        //设置定位的数据库路径
        settings.setGeolocationDatabasePath(dir);
        //最重要的方法，一定要设置，这就是出不来的主要原因
        settings.setDomStorageEnabled(true);
        /*settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(true);
        settings.setPluginState(PluginState.ON);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        long t = System.currentTimeMillis();
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {
                return false;
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                if(!TextUtils.isEmpty(title)){
                    ctrl.getTitle().setText(title);
                }
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (progressBar != null) {
                    progressBar.setProgress(newProgress);
                    if (newProgress == 100) {
                        progressBar.setVisibility(View.GONE);
                    }
                }
                setBtnOnState(webView, ctrl);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, true);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                if(!TextUtils.isEmpty(url) && url.contains("homes")){//暂时根据homes判断是否是首页，是首页隐藏返回键
                    ctrl.getBackBtn().setVisibility(View.GONE);
                }else{
                    ctrl.getBackBtn().setVisibility(View.VISIBLE);
                }
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    if (handlers != null) {
                        for (HandleCallback handleCallback : handlers) {
                            if (handleCallback.accept(url)) {
                                return handleCallback.handle(url);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                view.loadData("", "text/html", "utf-8");
                if(progressBar != null){
                    progressBar.setVisibility(View.GONE);
                }
                Toast.makeText(view.getContext(), "加载失败", Toast.LENGTH_LONG).show();
            }
        });

        webView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                        long contentLength) {
                Log.i("tag", "url=" + url);
                Log.i("tag", "userAgent=" + userAgent);
                Log.i("tag", "contentDisposition=" + contentDisposition);
                Log.i("tag", "mimetype=" + mimetype);
                Log.i("tag", "contentLength=" + contentLength);
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                webView.getContext().startActivity(intent);
            }
        });

    }

    /**
     * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> getParams(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit = null;

        String strUrlParam = truncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        // 每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            // 解析出键值
            if (arrSplitEqual.length > 1) {
                // 正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            } else {
                if (arrSplitEqual[0] != "") {
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String truncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }
        return strAllParam;
    }

    public interface HandleCallback {
        public boolean handle(String url);

        public boolean accept(String url);
    }

}
