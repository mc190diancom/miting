package com.miu30.common.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class NetChangeReceiver {
    Context context;

    public NetChangeReceiver(Context context){
        this.context = context;
    }

    public void listner() throws Exception{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            manager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                @Override
                public void onLost(Network network) {
                    super.onLost(network);
                    Log.i("aaa", "注册，网络不可用");
                }

                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    Log.i("aaa", "注册，网络可用");
                }
            });
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            manager.requestNetwork(new NetworkRequest.Builder().build(),
                    new ConnectivityManager.NetworkCallback() {
                        @Override
                        public void onAvailable(Network network) {
                            super.onAvailable(network);
                            Log.i("aaa", "监听，网络可用");
                        }
                        @Override
                        public void onLost(Network network) {
                            super.onLost(network);
                            Log.i("aaa", "监听，网络断开");
                        }
                    });
        }

    }

}
