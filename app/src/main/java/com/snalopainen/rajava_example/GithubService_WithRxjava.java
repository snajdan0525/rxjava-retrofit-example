package com.snalopainen.rajava_example;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by snajdan on 2017/1/3.
 */

public interface GithubService_WithRxjava {
    @GET("repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> contributorsByRxJava(@Path("owner") String owner, @Path("repo") String repo);
}
