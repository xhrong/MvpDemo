package com.xhr.mvpdemo.repository.net;

import com.xhr.mvpdemo.repository.net.response.RepositoryResponse;
import com.xhr.mvpdemo.repository.net.response.UserResponse;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public interface GithubApiService {

    @GET("/users/{username}")
    Observable<UserResponse> getUser(
            @Path("username") String username
    );

    @GET("/users/{username}/repos")
    Observable<List<RepositoryResponse>> getUsersRepositories(
            @Path("username") String username
    );
}
