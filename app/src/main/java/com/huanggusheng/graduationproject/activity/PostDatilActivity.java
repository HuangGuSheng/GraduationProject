package com.huanggusheng.graduationproject.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.huanggusheng.graduationproject.BaseApplication;
import com.huanggusheng.graduationproject.R;
import com.huanggusheng.graduationproject.model.Post;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Huang on 2016/6/3.
 */
public class PostDatilActivity extends BaseActivity {
    @Bind(R.id.detail_avatar)
    ImageView avatar;

    @Bind(R.id.detail_user)
    TextView user;

    @Bind(R.id.detail_title)
    TextView title;

    @Bind(R.id.detail_content)
    TextView content;

    @Bind(R.id.detail_picture)
    SimpleDraweeView draweeView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    Post post ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        post = (Post) intent.getSerializableExtra("post");

        setContentView(R.layout.activity_post_detail);

        ButterKnife.bind(this);
        initView();
        initToolBar();
    }

    private void initView() {
        title.setText(post.getTitle());
        content.setText(post.getContent());
        if (post.getIfhavePic() == true) {
            draweeView.setImageURI(Uri.parse(post.getImageUrl()));
        } else {
            draweeView.findViewById(R.id.detail_picture).setVisibility(View.GONE);
        }
    }
    private void initToolBar() {
        toolbar.setTitle("帖子");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_32dpdp));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
