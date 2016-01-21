package com.xhr.mvpdemo.repository;

import com.xhr.mvpdemo.repository.datasource.IUserDataStore;
import com.xhr.mvpdemo.repository.datasource.MemoryUserDataStore;
import com.xhr.mvpdemo.repository.datasource.NetworkUserDataStore;
import com.xhr.mvpdemo.repository.datasource.UserDataStoreFactory;

/**
 * Created by xhrong on 2016/1/12.
 */
public class UserRepository implements IUserRepository {

    private final UserDataStoreFactory userDataStoreFactory;
    private IUserDataStore userDataStore;

    public UserRepository() {
        this.userDataStoreFactory = new UserDataStoreFactory();
        //TODO：这个应该通过配置文件设置，或者通过检查某些状态自动选择
        this.userDataStore = this.userDataStoreFactory.create(NetworkUserDataStore.class);
    }


    @Override
    public IUserDataStore getUseDateStore() {
        return userDataStore;
    }
}
