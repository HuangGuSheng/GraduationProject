package com.huanggusheng.graduationproject.model;

/**
 * Created by Huang on 2016/4/6.
 */

import android.os.Parcelable;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

import java.io.Serializable;
import java.util.List;

@AVClassName("Post")
public class Post extends AVObject implements Serializable {
    public static final Parcelable.Creator CREATOR = AVObjectCreator.instance;

    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String AUTHOR = "author";
    public static final String IMAGEURL = "URL";
    public static final String IFHAVEPIC = "isPicItem";
    public static final String POSTTYPE = "postType";


    public Post() {

    }

    /**
     * 发帖人
     * @return
     */
    public User getAuthor() {
        return getAVObject(AUTHOR);
    }

    public void setAuthor(User author) {
        put(AUTHOR, author);
    }

    /**
     * 帖子标题
     * @return
     */
    public String getTitle() {
        return getString(TITLE);
    }

    public void setTitle(String title) {
        put(TITLE, title);
    }

    /**
     * 帖子内容
     * @return
     */
    public String getContent() {
        return getString(CONTENT);
    }

    public void setContent(String content) {
        put(CONTENT, content);
    }

    /**
     * 图片url
     * @param url
     */
    public void setImageUrl(String url) {
        if(url != null) {
            put(IMAGEURL, url);
        }
    }

    public String getImageUrl() {
        String url = getString(IMAGEURL);
        return url;
    }

    /**
     * 是否含有图片
     * @param ifHavePic
     */
    public void setIfHavePic(boolean ifHavePic) {
        put(IFHAVEPIC, ifHavePic);
    }

    public boolean getIfhavePic() {
        boolean ifHavePic = getBoolean(IFHAVEPIC);
        return ifHavePic;
    }

    /**
     * 帖子类型
     * @param posttype
     */
    public void setPosttype(int posttype) {
        put(POSTTYPE, posttype);
    }

    public int getPostType() {
        int postType = getInt(POSTTYPE);
        return postType;
    }

}
