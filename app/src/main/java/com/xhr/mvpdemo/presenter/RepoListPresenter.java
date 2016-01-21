package com.xhr.mvpdemo.presenter;

import com.google.common.collect.ImmutableList;
import com.xhr.mvpdemo.model.IUser;
import com.xhr.mvpdemo.model.UserModel;
import com.xhr.mvpdemo.repository.model.Repository;
import com.xhr.mvpdemo.view.IRepoListView;

import rx.Subscriber;


/**
 * Created by Miroslaw Stanek on 23.04.15.
 */
public class RepoListPresenter implements IRepoListPresenter {
    private IRepoListView repoListView;
    private IUser user;

    public RepoListPresenter(IRepoListView repoListView) {
        this.repoListView = repoListView;
        this.user = new UserModel();
    }

    @Override
    public void loadRepositories(String userName) {
        repoListView.showLoading(true);
        user.getRepositories(userName).subscribe(
                new Subscriber<ImmutableList<Repository>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        repoListView.showLoading(false);
                    }

                    @Override
                    public void onNext(ImmutableList<Repository> repositories) {
                        repoListView.showLoading(false);
                        repoListView.setRepositories(repositories);
                    }
                });
    }

}
