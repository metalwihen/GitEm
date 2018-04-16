package com.metalwihen.sample.gitem.ui.adapter.user_item;

import com.metalwihen.sample.gitem.ui.adapter.BaseListItem;

/**
 * Created on 15/04/18.
 */

public class UserListItem extends BaseListItem {

    private String fullName;

    private String username;

    private String avatarUrl;

    private long noOfFollowers;

    public String profileUrl;

    public UserListItem(long id, String fullName, String username, String avatarUrl, long noOfFollowers, String profileUrl) {
        super(id);
        this.fullName = fullName;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.noOfFollowers = noOfFollowers;
        this.profileUrl = profileUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public long getNoOfFollowers() {
        return noOfFollowers;
    }

    public String getProfileUrl() {
        return profileUrl;
    }
}
