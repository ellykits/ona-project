package com.coder.nerdsoft.projectx.data.remote;

import com.coder.nerdsoft.projectx.data.model.OAuthToken;
import com.coder.nerdsoft.projectx.data.model.Project;
import com.coder.nerdsoft.projectx.data.util.DataConstants;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OnaApiService {

    @GET("/api/v1/projects")
    Single<List<Project>> getProjects(@Header("Authorization") String token);

    @GET("/api/v1/projects/{pk}")
    Single<Project> getProject(@Header("Authorization") String token, @Path("pk") long pk);

    @POST
    Completable createProject(@Header("Authorization") String token, @Body Project project);

    @POST("o/token/?grant_type=password")
    @FormUrlEncoded

    Single<OAuthToken> login(@Header(DataConstants.AUTHORIZATION) String authHeader,
                             @Field("username") String username, @Field("password") String password);

    @POST("o/token/?grant_type=refresh_token")
    @FormUrlEncoded
    Single<OAuthToken> refreshToken(@Header(DataConstants.AUTHORIZATION) String authHeader,
                                    @Field("refresh_token") String refreshToken);
}
