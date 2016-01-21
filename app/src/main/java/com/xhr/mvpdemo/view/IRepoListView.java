package com.xhr.mvpdemo.view;

import android.view.View;

import com.google.common.collect.ImmutableList;
import com.xhr.mvpdemo.repository.model.Repository;

/**
 * Created by xhrong on 2016/1/21.
 */
public interface IRepoListView {
    public void showLoading(boolean loading);

    public void setRepositories(ImmutableList<Repository> repositories);
}
