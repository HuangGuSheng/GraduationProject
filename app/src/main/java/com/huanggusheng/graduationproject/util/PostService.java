package com.huanggusheng.graduationproject.util;

import android.graphics.Bitmap;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.huanggusheng.graduationproject.model.Post;

import java.io.ByteArrayOutputStream;

/**
 * 发帖
 * Created by Huang on 2016/6/1.
 */
public class PostService {
    public static final int MUTUAL_FOLLOW = 0;//disable follow
    public static final int FOLLOWER = 1;  //can follow
    public static final int FOLLOWING = 2;  //disable follow
    public static final int NONE_FOLLOW = 3; //can follow

    public static void sendPost(final String title, final String content, Bitmap bitmap, final SaveCallback saveCallback) {
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
                        sendPost(title,content, url, saveCallback);
                    }
                }
            });
        } else {
            sendPost(title, content,"", saveCallback);
        }
    }

    public static void sendPost(final String title, final String content, final String url, final SaveCallback saveCallback) {
//        final AVObject statusDetail = new AVObject(BaseApplication.STATUS_DETAIL);
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
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


}
