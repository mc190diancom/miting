package com.miu30.common.connect.handler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.miu30.common.MiuBaseApp;
import com.miu30.common.base.BaseData;
import com.miu30.common.config.Config;
import com.miu30.common.connect.entity.IMesage;
import com.miu30.common.data.YuJingPreference;
import com.miu30.common.ui.entity.AlarmInfo;
import com.miu30.common.util.UIUtils;

import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.Timer;
import java.util.TimerTask;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import timber.log.Timber;

/**
 * 作者：wanglei on 2019/5/22.
 * 邮箱：forwlwork@gmail.com
 */
@ChannelHandler.Sharable
public class MessageHandler extends ChannelInboundHandlerAdapter {

    private static final String FLAG = "com.feidi.cameraInfo";
    MessageCallBack messageCallBack;
    YuJingPreference yuJingPreference;

    public MessageHandler(MessageCallBack messageCallBack){
        this.messageCallBack = messageCallBack;
        yuJingPreference = new YuJingPreference(MiuBaseApp.self);
    }

    @SuppressLint("TimberArgCount")
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object o) throws Exception {
        super.channelRead(ctx, o);
        JSONObject jsonObject = new JSONObject((String) o);
        int type = jsonObject.optInt("msgType", -1);
        Timber.tag("netty").i(jsonObject.toString());
        System.out.println("netty:"+jsonObject.toString());
        switch (type) {
            case IMesage.LOGOUT:
                Timber.tag("netty").i("登录注销");
                ctx.close();
                break;
            case IMesage.BIND_CAMERA:
                if (jsonObject.optInt("status", -1) == 0) {
                    UIUtils.toast(MiuBaseApp.self, "绑定摄像头成功", Toast.LENGTH_LONG);
                    Intent intent = new Intent();
                    intent.setAction(FLAG);
                    intent.putExtra("msgType", 4);
                    MiuBaseApp.self.sendBroadcast(intent);
                } else {
                    UIUtils.toast(MiuBaseApp.self, "绑定摄像头失败", Toast.LENGTH_LONG);
                }
                /*test();
                test2();
                test3();
                test4();
                test5();
                test6();*/
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        test2();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 20 * 1000);
                break;
            case IMesage.ALARM:
                AlarmInfo alarmInfo = BaseData.gson.fromJson(jsonObject.toString(),new TypeToken<AlarmInfo>() {
                }.getType());
                if(yuJingPreference.getBoolean("isCameraChecked",false) && yuJingPreference.getBoolean("isTop",false) && messageCallBack != null){
                    messageCallBack.getMessageAndStartActivity(alarmInfo);
                }else{
                    Intent intent = new Intent();
                    intent.setAction(FLAG);
                    intent.putExtra("msgType", 1);
                    intent.putExtra("data", alarmInfo);
                    MiuBaseApp.self.sendBroadcast(intent);
                }
                break;
            case IMesage.CANCEL_BIND_CAMERA:
                if (jsonObject.optInt("status", -1) == 0) {
                    //UIUtils.toast(MiuBaseApp.self, "取消绑定摄像头成功", Toast.LENGTH_LONG);
                    Intent intent = new Intent();
                    intent.setAction(FLAG);
                    intent.putExtra("msgType", 5);
                    MiuBaseApp.self.sendBroadcast(intent);
                }
                break;
            case IMesage.SYSTEM_LOGOUT:
                EventBus.getDefault().post(true, Config.SYSTEMLOGOUT);//通知书tcp服务传递数据过来
                break;
            default:
                break;
        }
    }

    public interface MessageCallBack{
        void getMessageAndStartActivity(AlarmInfo alarmInfo);
    }

    private void test() {
        String path = "{\n" +
                "    \"eventID\":\"0005712faa86d18048be88c6265296d6b031\",\n" +
                "    \"alarmType\":\"存在疑点\",\n" +
                "    \"msgType\":3,\n" +
                "    \"vehicleIndustryType\":\"巡游出租车\",\n" +
                "    \"latitude\":40.05179977416992,\n" +
                "    \"occurTime\":\"2019-09-02T10:56:35\",\n" +
                "    \"cameraIDList\":[\n" +
                "        \"11000000001325291355\"\n" +
                "    ],\n" +
                "    \"pictureIDList\":[\n" +
                "        \"ftp://snap_ftp:snapftp12345678@10.212.160.152:12021/PicPath/2019-09-02/00043e9f83469d4844c39a3ced38a3eb5e22.jpg\",\n" +
                "        \"ftp://snap_ftp:snapftp12345678@10.212.160.152:12021/PicPath/2019-09-02/0004dbf610a8a97b4f7084bb9e3a4aaee474.jpg\"\n" +
                "    ],\n" +
                "    \"deviceID\":\"11000000001325291355\",\n" +
                "    \"deviceName\":\"T3B1出口\",\n" +
                "    \"vehiclePlatNo\":\"蓝京BZK552\",\n" +
                "    \"longitude\":116.6136016845703\n" +
                "}";
        AlarmInfo alarmInfo2 = BaseData.gson.fromJson(path,new TypeToken<AlarmInfo>() {
        }.getType());
        Intent intent2 = new Intent();
        intent2.setAction(FLAG);
        intent2.putExtra("data", alarmInfo2);
        MiuBaseApp.self.sendBroadcast(intent2);
    }

    private void test2() {
        String path = "{\"eventID\":\"00059a87cf212187400dad96efd28c822b5a\",\"alarmType\":\"存在疑点\",\"msgType\":3,\"vehicleIndustryType\":\"巡游出租车\",\"latitude\":40.05179977416992,\"occurTime\":\"2019-09-02T14:19:57\",\"cameraIDList\":[\"11000000001325291355\"],\"pictureIDList\":[\"ftp:\\/\\/snap_ftp:snapftp12345678@10.212.160.152:12021\\/PicPath\\/2019-09-02\\/0004444053f9526941b4bf905884b50459ae.jpg\",\"ftp:\\/\\/snap_ftp:snapftp12345678@10.212.160.152:12021\\/PicPath\\/2019-09-02\\/0004e3a9296360f7490dbbd7aa1106503f53.jpg\"],\"deviceID\":\"11000000001325291355\",\"deviceName\":\"T3B1出口\",\"vehiclePlatNo\":\"蓝京BU5392\",\"longitude\":116.6136016845703}\n";
        AlarmInfo alarmInfo2 = BaseData.gson.fromJson(path,new TypeToken<AlarmInfo>() {
        }.getType());
        if(yuJingPreference.getBoolean("isCameraChecked",false) && !yuJingPreference.getBoolean("isTop",false) && messageCallBack != null){
            messageCallBack.getMessageAndStartActivity(alarmInfo2);
        }else{
            Intent intent = new Intent();
            intent.setAction(FLAG);
            intent.putExtra("msgType", 1);
            intent.putExtra("data", alarmInfo2);
            MiuBaseApp.self.sendBroadcast(intent);
        }
    }

    private void test3() {
        String path = "{\n" +
                "    \"eventID\":\"000503ec984aaf99465ebe0cbe3e77e7b78e\",\n" +
                "    \"alarmType\":\"套牌车辆\",\n" +
                "    \"msgType\":3,\n" +
                "    \"vehicleIndustryType\":\"小货车\",\n" +
                "    \"latitude\":40.05179977416992,\n" +
                "    \"occurTime\":\"2019-09-02T14:22:15\",\n" +
                "    \"cameraIDList\":[\n" +
                "        \"11000000001325291355\"\n" +
                "    ],\n" +
                "    \"pictureIDList\":[\n" +
                "        \"ftp://snap_ftp:snapftp12345678@10.212.160.152:12021/PicPath/2019-09-02/000443d7f5b7707c432f8b8db9afd8d4f8c5.jpg\",\n" +
                "        \"ftp://snap_ftp:snapftp12345678@10.212.160.152:12021/PicPath/2019-09-02/0004b228eabc065d452a9027e870b0994505.jpg\"\n" +
                "    ],\n" +
                "    \"deviceID\":\"11000000001325291355\",\n" +
                "    \"deviceName\":\"T3B1出口\",\n" +
                "    \"vehiclePlatNo\":\"蓝京BU7326\",\n" +
                "    \"longitude\":116.6136016845703\n" +
                "}";
        AlarmInfo alarmInfo2 = BaseData.gson.fromJson(path,new TypeToken<AlarmInfo>() {
        }.getType());
        Intent intent2 = new Intent();
        intent2.setAction(FLAG);
        intent2.putExtra("data", alarmInfo2);
        MiuBaseApp.self.sendBroadcast(intent2);
    }

    private void test4() {
        String path = "{\n" +
                "    \"eventID\":\"0005691c07cf656e468894d7c74cbfb31b44\",\n" +
                "    \"alarmType\":\"存在疑点\",\n" +
                "    \"msgType\":3,\n" +
                "    \"vehicleIndustryType\":\"巡游出租车\",\n" +
                "    \"latitude\":40.05179977416992,\n" +
                "    \"occurTime\":\"2019-09-02T14:26:08\",\n" +
                "    \"cameraIDList\":[\n" +
                "        \"11000000001325291355\"\n" +
                "    ],\n" +
                "    \"pictureIDList\":[\n" +
                "        \"ftp://snap_ftp:snapftp12345678@10.212.160.152:12021/PicPath/2019-09-02/00046de915811565498ab7dc30c1f7c35d3a.jpg\",\n" +
                "        \"ftp://snap_ftp:snapftp12345678@10.212.160.152:12021/PicPath/2019-09-02/0004d5c55b8a7d7b4953b714b41596fcf198.jpg\"\n" +
                "    ],\n" +
                "    \"deviceID\":\"11000000001325291355\",\n" +
                "    \"deviceName\":\"T3B1出口\",\n" +
                "    \"vehiclePlatNo\":\"蓝京BP3724\",\n" +
                "    \"longitude\":116.6136016845703\n" +
                "}";
        AlarmInfo alarmInfo2 = BaseData.gson.fromJson(path,new TypeToken<AlarmInfo>() {
        }.getType());
        Intent intent2 = new Intent();
        intent2.setAction(FLAG);
        intent2.putExtra("data", alarmInfo2);
        MiuBaseApp.self.sendBroadcast(intent2);
    }

    private void test5() {
        String path = "{\n" +
                "    \"eventID\":\"000507991a7caf4f4a73bd9f08960c645b52\",\n" +
                "    \"alarmType\":\"存在疑点\",\n" +
                "    \"msgType\":3,\n" +
                "    \"vehicleIndustryType\":\"巡游出租车\",\n" +
                "    \"latitude\":40.05179977416992,\n" +
                "    \"occurTime\":\"2019-09-02T14:37:19\",\n" +
                "    \"cameraIDList\":[\n" +
                "        \"11000000001325291355\"\n" +
                "    ],\n" +
                "    \"deviceID\":\"11000000001325291355\",\n" +
                "    \"deviceName\":\"T3B1出口\",\n" +
                "    \"vehiclePlatNo\":\"蓝京BS7062\",\n" +
                "    \"longitude\":116.6136016845703\n" +
                "}";
        AlarmInfo alarmInfo2 = BaseData.gson.fromJson(path,new TypeToken<AlarmInfo>() {
        }.getType());
        Intent intent2 = new Intent();
        intent2.setAction(FLAG);
        intent2.putExtra("data", alarmInfo2);
        MiuBaseApp.self.sendBroadcast(intent2);
    }

    private void test6() {
        String path = "{\n" +
                "    \"eventID\":\"000587c7350c09e44adca5a2c89e8d21ab95\",\n" +
                "    \"alarmType\":\"套牌车辆\",\n" +
                "    \"msgType\":3,\n" +
                "    \"vehicleIndustryType\":\"巡游出租车\",\n" +
                "    \"latitude\":40.05179977416992,\n" +
                "    \"occurTime\":\"2019-09-02T14:37:38\",\n" +
                "    \"cameraIDList\":[\n" +
                "        \"11000000001325291355\"\n" +
                "    ],\n" +
                "    \"pictureIDList\":[\n" +
                "        \"ftp://snap_ftp:snapftp12345678@10.212.160.152:12021/PicPath/2019-09-02/000413083078dc544c7282197e6894dd2fdd.jpg\",\n" +
                "        \"ftp://snap_ftp:snapftp12345678@10.212.160.152:12021/PicPath/2019-09-02/0004f56ebedac32940ffbf9f0e2e727b3658.jpg\"\n" +
                "    ],\n" +
                "    \"deviceID\":\"11000000001325291355\",\n" +
                "    \"deviceName\":\"T3B1出口\",\n" +
                "    \"vehiclePlatNo\":\"蓝京BQ7482\",\n" +
                "    \"longitude\":116.6136016845703\n" +
                "}";
        AlarmInfo alarmInfo2 = BaseData.gson.fromJson(path,new TypeToken<AlarmInfo>() {
        }.getType());
        if(yuJingPreference.getBoolean("isCameraChecked",false) && yuJingPreference.getBoolean("isTop",false)){
            messageCallBack.getMessageAndStartActivity(alarmInfo2);
        }else{
            Intent intent = new Intent();
            intent.setAction(FLAG);
            intent.putExtra("msgType", 1);
            intent.putExtra("data", alarmInfo2);
            MiuBaseApp.self.sendBroadcast(intent);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }

}
