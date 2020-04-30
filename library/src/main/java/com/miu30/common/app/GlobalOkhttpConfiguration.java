package com.miu30.common.app;

import android.content.Context;

import com.jess.arms.di.module.ClientModule;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;

public class GlobalOkhttpConfiguration implements ClientModule.OkhttpConfiguration {
    private static final int DEFAULT_TIMEOUT = 20;

    public static void addDefault(OkHttpClient.Builder builder) {
        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try {
            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        builder.sslSocketFactory(sslContext.getSocketFactory()).hostnameVerifier(DO_NOT_VERIFY);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        RetrofitUrlManager.getInstance().with(builder);

    }

    @Override
    public void configOkhttp(Context context, OkHttpClient.Builder builder) {
        //time our
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        addDefault(builder);
        //builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.addInterceptor(new EncInterceptor());
        ProgressManager.getInstance().with(builder);
        //让 Retrofit 同时支持多个 BaseUrl 以及动态改变 BaseUrl. 详细使用请方法查看 https://github.com/JessYanCoding/RetrofitUrlManager
        RetrofitUrlManager.getInstance().with(builder);
    }
}
