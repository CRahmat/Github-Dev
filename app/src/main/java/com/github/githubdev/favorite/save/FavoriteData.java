package com.github.githubdev.favorite.save;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "repository_database")
public class FavoriteData {
    @NonNull

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "login")
    private String login;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "private")
    private boolean isPrivate;
    @ColumnInfo(name = "html_url")
    private String link;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "full_name")
    private String fullName = "";
    @ColumnInfo(name = "created_at")
    private String createdAt = "";
    @ColumnInfo( name = "updated_at")
    private String updatedAt = "";
    @ColumnInfo(name = "pushed_at")
    private String pushedAt = "";
    @ColumnInfo(name = "stargazers_count")
    private String starGazersCount = "";
    @ColumnInfo(name = "forks_count")
    private String forksCount = "";
    @ColumnInfo(name = "watchers_count")
    private String watchersCount = "";
    @ColumnInfo(name = "language")
    private String language = "";

    @NonNull

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

}
