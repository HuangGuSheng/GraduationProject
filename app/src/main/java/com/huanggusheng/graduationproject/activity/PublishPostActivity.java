package com.huanggusheng.graduationproject.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.huanggusheng.graduationproject.R;
import com.huanggusheng.graduationproject.fragment.PublishPostFragment;

/**
 * Created by Huang on 2016/4/10.
 */
public class PublishPostActivity extends BaseActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 1);
        setContentView(R.layout.activity_new_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("发帖");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_32dpdp));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //---------------getActivity()
                new AlertDialog.Builder(PublishPostActivity.this).setTitle("返回")
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
                finish();
            }
        });

        getFragmentManager().beginTransaction().replace(R.id.framelayout, new PublishPostFragment()).commit();
    }
}
