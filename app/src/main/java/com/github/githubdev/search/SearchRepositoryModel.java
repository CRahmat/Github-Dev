package com.github.githubdev.search;

import com.google.gson.annotations.SerializedName;

public class SearchRepositoryModel {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("private")
    private boolean isPrivate;
    @SerializedName("html_url")
    private String link;
    @SerializedName("description")
    private String description;
    @SerializedName("full_name")
    private String fullName = "";
    @SerializedName("created_at")
    private String createdAt = "";
    @SerializedName("updated_at")
    private String updatedAt = "";
    @SerializedName("pushed_at")
    private String pushedAt = "";
    @SerializedName("stargazers_count")
    private String starGazersCount = "";
    @SerializedName("forks_count")
    private String forksCount = "";
    @SerializedName("watchers_count")
    private String watchersCount = "";
    @SerializedName("language")
    private String language = "";
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    public String getStarGazersCount() {
        return starGazersCount;
    }

    public void setStarGazersCount(String starGazersCount) {
        this.starGazersCount = starGazersCount;
    }

    public String getForksCount() {
        return forksCount;
    }

    public void setForksCount(String forksCount) {
        this.forksCount = forksCount;
    }

    public String getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(String watchersCount) {
        this.watchersCount = watchersCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
