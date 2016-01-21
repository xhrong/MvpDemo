package com.xhr.mvpdemo.model;

import com.google.common.collect.ImmutableList;
import com.xhr.mvpdemo.presenter.OnLoginListener;
import com.xhr.mvpdemo.repository.model.Repository;

import rx.Observable;

/**
 * Created by xhrong on 2016/1/12.
 */
public interface IUser {
    public void login(String userName,OnLoginListener listener);
    public Observable<ImmutableList<Repository>> getRepositories(String userName);
}
