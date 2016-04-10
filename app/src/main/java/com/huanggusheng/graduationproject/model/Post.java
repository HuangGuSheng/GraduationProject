package com.huanggusheng.graduationproject.model;

/**
 * Created by Huang on 2016/4/6.
 */

import android.os.Parcelable;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

import java.util.List;

@AVClassName("Post")
public class Post extends AVObject{
    public static final Parcelable.Creator CREATOR = AVObjectCreator.instance;

    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String AUTHOR = "author";
    public static final String LIKES = "likes";
    public static final String REWARDS = "rewards"; // 打赏
    public static final String PICTURE = "picture";

    public Post() {

    }

    public User getAuthor() {
        return getAVObject(AUTHOR);
    }

    public void setAuthor(User author) {
        put(AUTHOR, author);
    }

    public String getTitle() {
        return getString(TITLE);
    }

    public void setTitle(String title) {
        put(CONTENT, title);
    }

    public String getContent() {
        return getString(CONTENT);
    }

    public void setContent(String content) {
        put(CONTENT, content);
    }

    public List<User> getLikes() {
        return getList(LIKES);
    }

    public void setLikes(List<User> likes) {
        put(LIKES, likes);
    }

    public AVRelation<User> getRewardStudents() {
        return getRelation(REWARDS);
    }

    public void setRewardStudents(AVRelation<User> rewardStudents) {
        put(REWARDS, rewardStudents);
    }

    public AVFile getPicture() {
        return getAVFile(PICTURE);
    }

    public void setPicture(AVFile picture) {
        put(PICTURE, picture);
    }
}
