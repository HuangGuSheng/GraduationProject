package com.huanggusheng.graduationproject.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.huanggusheng.graduationproject.R;
import com.huanggusheng.graduationproject.model.Post;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.ByteArrayOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Huang on 2016/4/10.
 */
public class PublishPostFragment extends Fragment {


    AVFile picture;

    @Bind(R.id.pub_title)
    MaterialEditText mPubTitle;

    @Bind(R.id.pub_content)
    MaterialEditText mPubContent;

    @Bind(R.id.btn_commit_pub)
    Button mButtonCommit;

    @Bind(R.id.pub_image)
    ImageView mPubImg;

    @OnClick(R.id.btn_commit_pub)
    void publistPost() {
        Log.e("hello", "提交响应");
        //其余属性后续添加
        Post post = new Post();

        post.setTitle(mPubTitle.getText().toString());
        post.setContent(mPubContent.getText().toString());
        post.setPicture(picture);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if(e == null){
                    Log.e("saved","success!");
                }
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish_post, container, false);
//        ButterKnife.bind(view);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        picture = new AVFile("picture",getPictureBytes());
    }

    public byte[] getPictureBytes() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.first);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, output);
        byte[] bytes = output.toByteArray();
        return bytes;
    }
}
