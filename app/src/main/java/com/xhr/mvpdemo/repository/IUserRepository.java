package com.xhr.mvpdemo.repository;

import com.xhr.mvpdemo.repository.datasource.IUserDataStore;

/**
 * Created by xhrong on 2016/1/12.
 */
public interface IUserRepository {

    IUserDataStore getUseDateStore();
}
