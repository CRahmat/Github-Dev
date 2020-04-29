package com.github.githubdev.searchusername;

import com.google.gson.annotations.SerializedName;

public class SearchModel {
    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatarURL;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
