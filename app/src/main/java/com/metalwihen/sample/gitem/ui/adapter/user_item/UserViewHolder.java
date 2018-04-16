package com.metalwihen.sample.gitem.ui.adapter.user_item;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.metalwihen.sample.gitem.R;

public class UserViewHolder extends ViewHolder {

    private TextView name;

    private ImageView avatar;

    private TextView followers;

    private TextView username;

    public UserViewHolder(View itemView) {
        super(itemView);

        username = (TextView) itemView.findViewById(R.id.username);
        name = (TextView) itemView.findViewById(R.id.full_name);
        followers = (TextView) itemView.findViewById(R.id.follower_count);
        avatar = (ImageView) itemView.findViewById(R.id.user_avatar);
    }

    public TextView getName() {
        return name;
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public TextView getFollowers() {
        return followers;
    }

    public TextView getUsername() {
        return username;
    }
}
