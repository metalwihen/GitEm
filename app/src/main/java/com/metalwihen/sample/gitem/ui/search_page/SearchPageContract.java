package com.metalwihen.sample.gitem.ui.search_page;

import com.metalwihen.sample.gitem.net.user.User;
import com.metalwihen.sample.gitem.ui.adapter.BaseListItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created on 16/04/18.
 */

public interface SearchPageContract {

    interface Data {

        Observable<User> searchUsersByNameSortedByFollowersInDescendingOrder(String query);

    }

    interface View {

        void showLoadingList();

        void showEmptyList();

        void showBlankList();

        void showErrorList(String message);

        void showUserList(List<BaseListItem> users);
    }

    interface Presenter {

        void onOpenPage();

        void onClosePage();

        void onTypeText(String searchQuery);

        void onClickRetryButtonDuringError();
    }
}
