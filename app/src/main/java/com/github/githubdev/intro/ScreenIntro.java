package com.github.githubdev.intro;

import android.view.animation.Animation;

import java.security.acl.AclNotFoundException;

public class ScreenIntro {

    String Title,Description;
    int ScreenImg;
    Animation imageAnimation, titleAnimation, descAnimation;

    public ScreenIntro(String title, String description, int screenImg, Animation imageAnimation, Animation titleAnimation, Animation descAnimation) {
        Title = title;
        Description = description;
        ScreenImg = screenImg;
        this.imageAnimation = imageAnimation;
        this.titleAnimation = titleAnimation;
        this.descAnimation = descAnimation;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }

    public Animation getImageAnimation() {
        return imageAnimation;
    }

    public void setImageAnimation(Animation imageAnimation) {
        this.imageAnimation = imageAnimation;
    }

    public Animation getTitleAnimation() {
        return titleAnimation;
    }

    public void setTitleAnimation(Animation titleAnimation) {
        this.titleAnimation = titleAnimation;
    }

    public Animation getDescAnimation() {
        return descAnimation;
    }

    public void setDescAnimation(Animation descAnimation) {
        this.descAnimation = descAnimation;
    }
}
