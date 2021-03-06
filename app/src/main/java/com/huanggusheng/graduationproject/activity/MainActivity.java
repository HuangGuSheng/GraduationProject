package com.huanggusheng.graduationproject.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.serializer.CurrencyCodec;
import com.avos.avoscloud.AVUser;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.huanggusheng.graduationproject.R;
import com.huanggusheng.graduationproject.fragment.RecyclerViewFragment;
import com.huanggusheng.graduationproject.model.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int CAMPUS_HELP = 1;
    public static final int SUBJECT_TRANS = 2;
    public static final int LOST_FOUND = 3;
    public static final int TALK = 4;

    private long exitTime = 0; ////记录第一次点击的时间
    private ActionBarDrawerToggle mDrawerToggle;

    private AVUser currentUser;

    private Toolbar toolbar;

    @Bind(R.id.menu1) FloatingActionMenu fmenu;
    @Bind(R.id.fab1) FloatingActionButton fab1;
    @Bind(R.id.fab2) FloatingActionButton fab2;
    @Bind(R.id.fab3) FloatingActionButton fab3;

    @Bind(R.id.materialViewPager)
    MaterialViewPager mViewPager;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawer;


    /**
     * FloatActionButton 响应
     * 选择发帖种类
     */
    @OnClick({R.id.fab1,R.id.fab2,R.id.fab3}) void fabOnClick(FloatingActionButton button){
        int touchId = button.getId();
        if (touchId == fab1.getId()) {
            createPost(CAMPUS_HELP);
        } else if (touchId == fab2.getId()) {
            createPost(SUBJECT_TRANS);
        } else if (touchId == fab3.getId()) {
            createPost(LOST_FOUND);
        } else {
            createPost(TALK);
        }
        }



    /**
     * 启动MainACtivity
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //获取缓存账户
        currentUser = User.getCurrentUser();
        initView();
        initDrawer();
        setTitle("");


        toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }

        //mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        //mDrawer.setDrawerListener(mDrawerToggle);

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    //case 0:
                    //    return RecyclerViewFragment.newInstance();
                    //case 1:
                    //    return RecyclerViewFragment.newInstance();
                    //case 2:
                    //    return WebViewFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
               return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "校园求助";
                    case 1:
                        return "学术交流";
                    case 2:
                        return "寻物启事";
                    case 3:
                        return "各种";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }
                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        View logo = findViewById(R.id.logo_white);
        if (logo != null)
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });

    }


    /**
     * 初始化基础view
     */
    private void initView() {
        fmenu.setClosedOnTouchOutside(true);
    }

    /**
     * FloatingActionButton设置
     */
    private void initFloatingActionButton() {

    }
    /**
     * 初始化抽屉
     */
    private void initDrawer(){
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        mDrawer.addDrawerListener(mDrawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View v = navigationView.getHeaderView(0);

        ImageView img_nav_avatar = (ImageView) v.findViewById(R.id.nav_avatar);
        TextView tv_nav_username = (TextView) v.findViewById(R.id.nav_username);
        //获取用户名和头像
        tv_nav_username.setText(currentUser.getUsername());

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawer.addDrawerListener(mDrawerToggle);

    }


    /**
     * 创建帖子
     * @param fabId
     */
    private void createPost(int fabId){
        Intent pubNewPost = new Intent(MainActivity.this, PublishPostActivity.class);
        pubNewPost.putExtra("post_type", fabId);
        startActivity(pubNewPost);
    }

    /**
     * 连续两次点击返回就退出程序
     */
    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar.make(fmenu, "再按一次退出程序", Snackbar.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                Log.e("Tag", currentUser.getUsername());
            }
            else {
                finish();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return mDrawerToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
//        return super.onOptionsItemSelected(item);
    }


    /**
     * 侧滑菜单响应
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // 我的主页
        } else if (id == R.id.nav_collect) {
            // 收藏
        } else if (id == R.id.nav_message) {
            // 查看消息
        }else if (id == R.id.nav_setting) {
            // 设置
        } else if (id == R.id.nav_about) {
            // 关于
            startActivity(new Intent(this, AboutActivity.class));
        } else if (id == R.id.nav_out) {
            //登出
            new AlertDialog.Builder(MainActivity.this).setTitle("登出？")
                    .setMessage("要退出当前账户吗୧(๑•̀⌄•́๑)૭✧")
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialog, int which) {
                            currentUser.logOut();
                            LoginActivity.actionStart(MainActivity.this);
                            finish();
                        }
                    })
                    .show();

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

}
