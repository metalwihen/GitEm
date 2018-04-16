package com.metalwihen.sample.gitem.ui.search_page;

import android.util.Log;

import com.metalwihen.sample.gitem.core.GithubCredentials;
import com.metalwihen.sample.gitem.net.user.User;
import com.metalwihen.sample.gitem.ui.adapter.BaseListItem;
import com.metalwihen.sample.gitem.ui.adapter.user_item.UserListItem;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 16/04/18.
 */

public class SearchPagePresenter implements SearchPageContract.Presenter {

    private SearchPageContract.View mView;
    private SearchPageContract.Data mData;

    private String mLastSearchedQuery;

    public SearchPagePresenter(SearchPageContract.View view, SearchPageContract.Data data) {
        mView = view;
        mData = data;
    }

    @Override
    public void onOpenPage() {
        mLastSearchedQuery = null;
    }

    @Override
    public void onClosePage() {
        mLastSearchedQuery = null;
        // TODO: Clean up
    }

    @Override
    public void onTypeText(String searchQuery) {
        mLastSearchedQuery = searchQuery;

        if (searchQuery == null || searchQuery.length() == 0) {
            mView.showBlankList();
        } else {
            loadUsers(searchQuery);
        }
    }

    @Override
    public void onClickRetryButtonDuringError() {
        loadUsers(mLastSearchedQuery);
    }

    private void loadUsers(String query) {
        mView.showLoadingList();
        mData.searchUsersByNameSortedByFollowersInDescendingOrder(query)
                .map(new Function<User, BaseListItem>() {
                    @Override
                    public UserListItem apply(User user) throws Exception {
                        return new UserListItem(user.id, user.name, user.login, user.avatar_url, user.followers, user.html_url);
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<BaseListItem>>() {
                    @Override
                    public void accept(List<BaseListItem> userListItems) throws Exception {
                        if (userListItems == null || userListItems.size() == 0) {
                            mView.showEmptyList();
                        } else {
                            mView.showUserList(userListItems);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        mView.showErrorList(String.valueOf(throwable.getMessage()));
                    }
                });
    }
}
