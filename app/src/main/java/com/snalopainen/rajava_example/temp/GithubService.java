package com.snalopainen.rajava_example.temp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by snajdan on 2017/1/3.
 */

/**
 * 在retrofit中通过一个Java接口作为http请求的api接口。
 */
public interface GithubService {

    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorBySimpleGetCall(@Path("owner") String owner, @Path("repo") String repo);
}
