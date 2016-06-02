package com.huanggusheng.graduationproject.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.SaveCallback;
import com.huanggusheng.graduationproject.R;
import com.huanggusheng.graduationproject.util.PostService;
import com.huanggusheng.graduationproject.util.PostUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Huang on 2016/4/10.
 */
public class PublishPostActivity extends BaseActivity {

    public static final int IMAGE_PICK_REQUEST = 0;

    AVFile picture;

    @Bind(R.id.pub_title)
    MaterialEditText mPubTitle;

    @Bind(R.id.pub_content)
    MaterialEditText mPubContent;

    @Bind(R.id.btn_commit_pub)
    Button mButtonCommit;

    @Bind(R.id.btn_imageAction)
    Button imageAction;

    @Bind(R.id.pub_image)
    ImageView mPubImg;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    Context context ;
    //是否插入了图片
    boolean haveImage = false ;

    Bitmap bitmap ;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 1);

        setContentView(R.layout.activity_pub_post);

        ButterKnife.bind(this);
        context = this;
        initToolBar();
        setButtonAndImage();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == IMAGE_PICK_REQUEST) {
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    haveImage = true;
                    setButtonAndImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void setButtonAndImage() {
        mPubImg.setImageBitmap(bitmap);
        if (haveImage) {
            imageAction.setText("取消图片");
            mPubImg.setVisibility(View.VISIBLE);
        } else {
            imageAction.setText("添加图片");
            mPubImg.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 选择照片
     * @param activity
     * @param requestCode
     */
    public static void pickImage(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 添加图片按钮响应
     */
    @OnClick(R.id.btn_imageAction)
    void imageAction() {
        if (haveImage == false) {
            pickImage(this, IMAGE_PICK_REQUEST);
        } else {
            bitmap = null;
            haveImage = false;
            setButtonAndImage();
        }
    }

    /**
     * 发送按钮响应
     */
    @OnClick(R.id.btn_commit_pub)
    void send() {
        String title = mPubTitle.getText().toString();
        final String content = mPubContent.getText().toString();
        if (TextUtils.isEmpty(title) == false || bitmap != null) {
            final ProgressDialog dialog = PostUtil.showSpinnerDialog(this);
            PostService.sendPost(title,content, bitmap, new SaveCallback() {
                @Override
                public void done(AVException e) {
                    dialog.dismiss();
                    if (PostUtil.filterException(context, e)) {
                        setResult(RESULT_OK);
                        Toast.makeText(context, "已发送", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            });
        }
    }



    private void initToolBar() {
        toolbar.setTitle("发帖");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_32dpdp));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("返回")
                .setMessage("要取消发帖吗୧(๑•̀⌄•́๑)૭✧")
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                       finish();
                    }
                })
                .show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
