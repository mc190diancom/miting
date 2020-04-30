package com.miu30.common.util;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.jess.arms.integration.lifecycle.ActivityLifecycleable;
import com.jess.arms.integration.lifecycle.FragmentLifecycleable;
import com.jess.arms.integration.lifecycle.Lifecycleable;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.jess.arms.utils.RxLifecycleUtils;
import com.miu30.common.MiuBaseApp;
import com.miu30.common.app.MyRetryWithDelay;
import com.miu30.common.async.Result;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * ================================================
 * 放置便于使用 RxJava 的一些工具方法
 * <p>
 * Created by MVPArmsTemplate
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class RxUtils {


    private RxUtils() {
    }


    public static <T> ObservableTransformer<T, T> applySchedulers(final IView view) {
        final LifecycleTransformer<T> objectLifecycleTransformer = bindToTargetLifeCycle(view);

        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                               /* if (view != null) {
                                    view.showLoading();//显示进度条
                                }*/
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(new Action() {
                            @Override
                            public void run() {
                                /*if (view != null) {
                                    view.hideLoading();//隐藏进度条
                                }*/
                            }
                        }).compose(objectLifecycleTransformer);
            }
        };
    }

    public static <T> ObservableTransformer<T, T> applySchedulersNoBind(final IView view) {
        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                                if (view != null) {
                                    view.showLoading();//显示进度条
                                }

                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(new Action() {
                            @Override
                            public void run() {
                                if (view != null) {
                                    view.hideLoading();//隐藏进度条
                                }

                            }
                        });

            }
        };

    }

    private static final String TAG = "RxUtils";

    public static <T> ObservableTransformer<T, T> applySchedulers(final IView view, final boolean isShowLoading) {
        final LifecycleTransformer<T> objectLifecycleTransformer = bindToTargetLifeCycle(view);
        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                                if (view != null && isShowLoading) {
                                    view.showLoading();//显示进度条
                                }

                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(new Action() {
                            @Override
                            public void run() {
                                if (view != null) {
                                    view.hideLoading();//隐藏进度条
                                }

                            }
                        }).compose(objectLifecycleTransformer);

            }
        };

    }

    private static <T> LifecycleTransformer<T> bindToTargetLifeCycle(IView view) {
        Preconditions.checkNotNull(view, "view == null");
        if (view instanceof Lifecycleable) {
            if (view instanceof ActivityLifecycleable) {
                return RxLifecycleUtils.<T>bindUntilEvent(view, ActivityEvent.DESTROY);
            } else if (view instanceof FragmentLifecycleable) {
                return RxLifecycleUtils.<T>bindUntilEvent(view, FragmentEvent.DETACH);
            }
        }
        throw new IllegalArgumentException("Lifecycleable not match");
    }

    /**
     * 此方法已废弃
     *
     * @param view
     * @param <T>
     * @return
     * @deprecated Use {@link RxLifecycleUtils#bindToLifecycle(IView)} instead
     */
    @Deprecated
    public static <T> LifecycleTransformer<T> bindToLifecycle(IView view) {
        return RxLifecycleUtils.bindToLifecycle(view);
    }

    /**
     * 网络重试，在特定的情况下重试
     *
     * @param maxRetries       重试次数
     * @param retryDelaySecond 每次重试的间隔
     * @param <T>
     * @return
     */
    public static <T extends Result> ObservableTransformer<T, T> tryWhen(final int maxRetries, final int retryDelaySecond) {
        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(Observable<T> observable) {
                return (Observable<T>) observable.flatMap((new Function<Result, ObservableSource<Result>>() {
                    @Override
                    public ObservableSource<Result> apply(Result result) throws Exception {
                        if (result.getThrowable() != null) {
                            Log.d(TAG, "重试 失败 发送 apply 0");
                            return Observable.error(result.getThrowable());
                        }
                        return Observable.just(result);
                    }
                })).retryWhen(new MyRetryWithDelay(maxRetries, retryDelaySecond));

            }
        };
    }

    public static <T> ObservableTransformer<? super Result<String>, Result<T>> convert() {
        return new ObservableTransformer<Result<String>, Result<T>>() {
            @Override
            public ObservableSource<Result<T>> apply(Observable<Result<String>> upstream) {
                return upstream.map(new Function<Result<String>, Result<T>>() {
                    @Override
                    public Result<T> apply(Result<String> stringResult) throws Exception {
                        Result<T> result = new Result<>();
                        if (!TextUtils.isEmpty(stringResult.getData())) {
                            result.setError(-1);
                            result.setMsg("暂无数据");
                            return result;
                        }
                        return null;
                    }
                });


            }
        };
    }

    public static <T> ObservableTransformer<? super Result<String>, Result<T>> convertStringToEntity(final String key, final TypeToken<T> typeToken) {
        return new ObservableTransformer< Result<String>, Result<T>>() {
            @Override
            public ObservableSource<Result<T>> apply(final Observable< Result<String>> upstream) {
                return upstream.map(new Function<Result<String>, Result<T>>() {
                    @Override
                    public Result<T> apply(Result<String> resultData) throws Exception {
                        String s = resultData.getData();
                        Result<T> result = new Result<>();
                        T datas = null;
                        Type type = typeToken.getType();
                        Object json = new JSONTokener(s).nextValue();
                        if (json instanceof JSONObject) {
                            JSONObject jObj = (JSONObject) json;
                            result.setError(jObj.optInt("code", -1));
                            result.setMsg(jObj.optString("message"));
                            String dataString = jObj.optString(key, "");
                            if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(dataString)) {
                                if (type == new TypeToken<String>() {
                                }.getType()) {
                                    datas = (T) dataString;
                                } else {
                                    datas = ArmsUtils.obtainAppComponentFromContext(MiuBaseApp.self).gson().fromJson(dataString, type);
                                }
                                result.setData(datas);
                            } else {
                                if (type == new TypeToken<String>() {
                                }.getType()) {
                                    datas = (T) s;
                                } else {
                                    datas = ArmsUtils.obtainAppComponentFromContext(MiuBaseApp.self).gson().fromJson(s, type);
                                }
                                result.setData(datas);
                            }
                        } else if (json instanceof JSONArray) {
                            datas = ArmsUtils.obtainAppComponentFromContext(MiuBaseApp.self).gson().fromJson(s, type);
                            result.setData(datas);
                        }
                        return result;
                    }
                });

            }
        };
    }
}
