package com.metalwihen.sample.gitem.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metalwihen.sample.gitem.R;
import com.metalwihen.sample.gitem.core.GitEmApp;
import com.metalwihen.sample.gitem.ui.adapter.empty_item.EmptyListItem;
import com.metalwihen.sample.gitem.ui.adapter.empty_item.EmptyViewHolder;
import com.metalwihen.sample.gitem.ui.adapter.error_item.ErrorListItem;
import com.metalwihen.sample.gitem.ui.adapter.error_item.ErrorViewHolder;
import com.metalwihen.sample.gitem.ui.adapter.loading_item.LoadingListItem;
import com.metalwihen.sample.gitem.ui.adapter.loading_item.LoadingViewHolder;
import com.metalwihen.sample.gitem.ui.adapter.user_item.UserListItem;
import com.metalwihen.sample.gitem.ui.adapter.user_item.UserViewHolder;
import com.metalwihen.sample.gitem.utils.BindingUtils;
import com.metalwihen.sample.gitem.utils.OpenIntentUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 15/04/18.
 */

public class ListAdapter extends RecyclerView.Adapter {

    private List<BaseListItem> data;

    public ListAdapter(List<BaseListItem> data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid");
        }
        this.data = data;
        setHasStableIds(true);
    }

    public void setData(List<BaseListItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void showLoadingView() {
        List<BaseListItem> items = new ArrayList<>();
        items.add(new LoadingListItem());
        setData(items);
    }

    public void showEmptyView() {
        List<BaseListItem> items = new ArrayList<>();
        items.add(new EmptyListItem());
        setData(items);
    }

    public void showErrorView(String message, ErrorListItem.RetryButtonListener retryButtonListener) {
        List<BaseListItem> items = new ArrayList<>();
        items.add(new ErrorListItem(message, retryButtonListener));
        setData(items);
    }

    public void showBlankView() {
        setData(Collections.<BaseListItem>emptyList());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        switch (viewType) {
            case R.layout.list_item_user:
                return new UserViewHolder(viewItem);

            case R.layout.list_item_loading:
                return new LoadingViewHolder(viewItem);

            case R.layout.list_item_empty:
                return new EmptyViewHolder(viewItem);

            case R.layout.list_item_error:
                return new ErrorViewHolder(viewItem);

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case R.layout.list_item_user:
                UserViewHolder userViewHolder = (UserViewHolder) holder;
                final UserListItem userListItem = (UserListItem) data.get(position);
                BindingUtils.bindText(userViewHolder.getName(), userListItem.getFullName());
                BindingUtils.bindText(userViewHolder.getUsername(), userListItem.getUsername());
                BindingUtils.bindText(userViewHolder.getFollowers(), String.valueOf(userListItem.getNoOfFollowers()));
                BindingUtils.bindImage(userViewHolder.getAvatar(), userListItem.getAvatarUrl(), 0);

                if (!TextUtils.isEmpty(userListItem.getProfileUrl())) {
                    userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            OpenIntentUtils.openUrl(holder.itemView.getContext(), userListItem.getProfileUrl());
                        }
                    });
                }
                break;


            case R.layout.list_item_empty:
            case R.layout.list_item_loading:
                // nothing to bind
                break;

            case R.layout.list_item_error:
                ErrorViewHolder errorViewHolder = (ErrorViewHolder) holder;
                final ErrorListItem errorListItem = (ErrorListItem) data.get(position);
                BindingUtils.bindText(errorViewHolder.getErrorText(), errorListItem.getErrorText());
                if (errorListItem.getRetryButtonListener() == null) {
                    errorViewHolder.getRetryButton().setVisibility(View.GONE);
                } else {
                    errorViewHolder.getRetryButton().setVisibility(View.VISIBLE);
                    errorViewHolder.getRetryButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            errorListItem.getRetryButtonListener().onClickRetryButton();
                        }
                    });
                }
                break;

            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public int getItemViewType(int position) {
        if (data == null) {
            return 0;
        }

        BaseListItem item = data.get(position);
        if (item instanceof UserListItem) {
            return R.layout.list_item_user;
        } else if (item instanceof LoadingListItem) {
            return R.layout.list_item_loading;
        } else if (item instanceof EmptyListItem) {
            return R.layout.list_item_empty;
        } else if (item instanceof ErrorListItem) {
            return R.layout.list_item_error;

        } else {
            return 0;
        }
    }

}
