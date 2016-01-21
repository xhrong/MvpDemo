package com.xhr.mvpdemo.repository.datasource;

import com.google.common.collect.ImmutableList;
import com.xhr.mvpdemo.repository.OnGetUserListener;
import com.xhr.mvpdemo.repository.model.Repository;
import com.xhr.mvpdemo.repository.model.User;
import com.xhr.mvpdemo.repository.net.GithubApiService;
import com.xhr.mvpdemo.repository.net.UserManager;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by xhrong on 2016/1/21.
 */
public class NetworkUserDataStore implements IUserDataStore {

    private static final String API_URL = "https://api.github.com";


    private GithubApiService githubApiService;
    private UserManager userManager;

    private MemoryUserDataStore memoryUserDataStore;

    public NetworkUserDataStore() {

        memoryUserDataStore=new MemoryUserDataStore();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        githubApiService = retrofit.create(GithubApiService.class);

        userManager = new UserManager(githubApiService);
    }

    @Override
    public void getUser(final String userName, final OnGetUserListener listener) {
        //先从内存中取，取到了就返回，否则使用网络
        memoryUserDataStore.getUser(userName,new OnGetUserListener() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
            }

            @Override
            public void onFail() {

                userManager.getUser(userName).subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        listener.onFail();
                    }

                    @Override
                    public void onNext(User user) {
                        listener.onSuccess();
                    }
                });
            }
        });

    }

    @Override
    public Observable<ImmutableList<Repository>> getRepositories(String userName) {
      return  userManager.getUsersRepositories(userName);
    }

}
