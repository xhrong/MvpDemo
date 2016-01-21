package com.xhr.mvpdemo.repository.net;


import com.google.common.collect.ImmutableList;
import com.xhr.mvpdemo.repository.model.Repository;
import com.xhr.mvpdemo.repository.model.User;
import com.xhr.mvpdemo.repository.net.response.RepositoryResponse;
import com.xhr.mvpdemo.repository.net.response.UserResponse;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Miroslaw Stanek on 22.04.15.
 */
public class UserManager {

    private GithubApiService githubApiService;


    public UserManager(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

    public Observable<User> getUser(String username) {


        return githubApiService.getUser(username)
                .map(new Func1<UserResponse, User>() {
                    @Override
                    public User call(UserResponse userResponse) {
                        User user = new User();
                        user.login = userResponse.login;
                        user.id = userResponse.id;
                        user.url = userResponse.url;
                        user.email = userResponse.email;
                        return user;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<ImmutableList<Repository>> getUsersRepositories(String username) {
        return githubApiService.getUsersRepositories(username)
                .map(new Func1<List<RepositoryResponse>, ImmutableList<Repository>>() {
                    @Override
                    public ImmutableList<Repository> call(List<RepositoryResponse> repositoriesListResponse) {
                        final ImmutableList.Builder<Repository> listBuilder = ImmutableList.builder();
                        for (RepositoryResponse repositoryResponse : repositoriesListResponse) {
                            Repository repository = new Repository();
                            repository.id = repositoryResponse.id;
                            repository.name = repositoryResponse.name;
                            repository.url = repositoryResponse.url;
                            listBuilder.add(repository);
                        }
                        return listBuilder.build();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
