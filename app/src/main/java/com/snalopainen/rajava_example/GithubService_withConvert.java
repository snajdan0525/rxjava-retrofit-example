package com.snalopainen.rajava_example;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by snajdan on 2017/1/3.
 */

public interface GithubService_withConvert {
    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsByAddConvertGetCall(@Path("owner") String owner, @Path("repo") String repo);
}
