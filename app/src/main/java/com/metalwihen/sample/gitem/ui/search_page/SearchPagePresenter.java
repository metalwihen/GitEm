package com.metalwihen.sample.gitem.ui.search_page;

import com.metalwihen.sample.gitem.net.user.User;
import com.metalwihen.sample.gitem.ui.adapter.BaseListItem;
import com.metalwihen.sample.gitem.ui.adapter.user_item.UserListItem;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 16/04/18.
 */

public class SearchPagePresenter implements SearchPageContract.Presenter {

    private SearchPageContract.View mView;
    private SearchPageContract.Data mData;
    private Disposable mDisposable;

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
        cancelRequests();
    }

    @Override
    public void onTypeText(String searchQuery) {
        mLastSearchedQuery = searchQuery;

        if (searchQuery == null || searchQuery.length() == 0 || searchQuery.trim().length() == 0) {
            cancelRequests();
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

        cancelRequests();
        mDisposable = mData.searchUsersByNameSortedByFollowersInDescendingOrder(query)
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

    private void cancelRequests() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
