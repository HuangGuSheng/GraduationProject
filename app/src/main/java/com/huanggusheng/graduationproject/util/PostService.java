package com.huanggusheng.graduationproject.util;

import android.graphics.Bitmap;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.huanggusheng.graduationproject.model.Post;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * 发帖
 * Created by Huang on 2016/6/1.
 */
public class PostService {
    public static final int MUTUAL_FOLLOW = 0;//disable follow
    public static final int FOLLOWER = 1;  //can follow
    public static final int FOLLOWING = 2;  //disable follow
    public static final int NONE_FOLLOW = 3; //can follow

    public static void sendPost(final String title, final String content, final boolean ifHavePic,
                                final int postType, Bitmap bitmap, final SaveCallback saveCallback) {
        if (bitmap != null) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
            byte[] bs = out.toByteArray();
            AVUser user = AVUser.getCurrentUser();
            String name = user.getUsername() + "_" + System.currentTimeMillis();
            final AVFile file = new AVFile(name, bs);
            file.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e != null) {
                        saveCallback.done(e);
                    } else {
                        String url = file.getUrl();
                        sendPost(title, content, true, postType, url, saveCallback);
                    }
                }
            });
        } else {
            sendPost(title, content, false, postType, "", saveCallback);
        }
    }

    public static void sendPost(final String title, final String content,final boolean ifHavePic,
                                final int postType, final String url, final SaveCallback saveCallback) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setIfHavePic(ifHavePic);
        post.setPosttype(postType);
        post.setImageUrl(url);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    saveCallback.done(e);
                } else {
                    Log.e("false",e.toString());
                }
            }
        });
    }

    private static List<Post> data = null;
    public static List<Post> queryPost(int type) {

        //查询
        AVQuery<Post> post = new AVQuery<Post>("Post");
        post.limit(10);
        post.orderByDescending("createdAt");
        post.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> list, AVException e) {
                data = list;
            }
        });
        return data;
    }
}
