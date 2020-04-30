package com.feidi.template.di.api;

import com.feidi.template.mvp.model.entity.LiveChatRoomBean;
import com.miu30.common.async.Result;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {


    @POST("odm/api/user/_login")
    Observable<Result<String>> toLogin(@Body RequestBody body);

    @GET("odm/api/exhibition/treeByUser/{invitationCode}")
    Observable<Result<String>> invate(@Path("invitationCode") String code, @Header("x-gxy-auth-user-token") String str);
}

