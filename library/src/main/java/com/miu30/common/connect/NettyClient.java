package com.miu30.common.connect;

import android.annotation.SuppressLint;

import com.miu30.common.connect.handler.HeartBeatHandler;
import com.miu30.common.connect.handler.LoginAuthHandler;
import com.miu30.common.connect.handler.MessageHandler;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import timber.log.Timber;

/**
 * 作者：wanglei on 2019/5/22.
 * 邮箱：forwlwork@gmail.com
 */
public class NettyClient {
    private EventLoopGroup group = new NioEventLoopGroup();
    private LoginAuthHandler loginAuthHandler = new LoginAuthHandler();
    private boolean needReconnection = true;

    /**
     * 当不需要进行重连时资源会全部释放，再次进行连接请 new NettyClient().connect(host,port);
     */
    public void stop(boolean reconnection) {
        //是否需要进行重连
        needReconnection = reconnection;
        //关闭通道
        if (loginAuthHandler.getCtx() != null) {
            loginAuthHandler.getCtx().close();
        }
        ChannelManager.getInstance().close();
        //不需要进行重连时关闭线程池
        if (!needReconnection) {
            group.shutdownGracefully();
        }
        //通知连接已断开
        //EventBus.getDefault().post(false, NettyConstants.TAG_TCP_CONNECT_STATE);
    }

    public void close(){
         //关闭通道
        ChannelManager.getInstance().close();
    }

    public void connect(String host, int port, final MessageHandler.MessageCallBack messageCallBack) {
        try {
            Timber.tag("netty").i("准备连接到 -->> host:%s ; port:%s", host, port);
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    //超时时间
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            //读写空闲设置
                            channel.pipeline().addLast(new IdleStateHandler(20, 20, 20, TimeUnit.SECONDS));
                            //编码器
                            channel.pipeline().addLast("Encoder", new Encoder());
                            //json解码器
                            channel.pipeline().addLast(new JsonObjectDecoder());
                            //字符串解码器
                            channel.pipeline().addLast(new StringDecoder());
                            //登录认证处理器
                            channel.pipeline().addLast("LoginAuthHandler", loginAuthHandler);
                            //心跳处理器
                            channel.pipeline().addLast("HeartBeatHandler", new HeartBeatHandler());
                            //消息处理器
                            channel.pipeline().addLast(new MessageHandler(messageCallBack));
                        }
                    });

            ChannelFuture future = bootstrap.connect(host, port)
                    .addListener(new ChannelFutureListener() {
                        @SuppressLint("BinaryOperationInTimber")
                        @Override
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            if (channelFuture.isSuccess()) {
                                Timber.tag("netty").d("-------------->>连接服务器成功");
                                Timber.tag("netty").d("-------------->>服务器地址：" + channelFuture.channel().remoteAddress().toString());
                                Timber.tag("netty").d("-------------->>本机地址：" + channelFuture.channel().localAddress().toString());
                            } else {
                                channelFuture.channel().close();
                            }
                        }
                    })
                    .sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //断线重连
            if (needReconnection) {
                try {
                    TimeUnit.SECONDS.sleep(15);
                    connect(host, port,messageCallBack);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
