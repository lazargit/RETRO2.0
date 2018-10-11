package com.shamildev.retro.ui.account;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayoutSpringBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;


import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.Toolbar;

import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.bumptech.glide.load.engine.GlideException;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.fragments.account_header.fragment.view.AccountHeaderFragment;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retroimage.views.RetroProfileImageView;
import com.shamildev.retro.ui.account.fragment.view.AccountFragment;
import com.shamildev.retro.ui.common.BaseActivitySupport;
import com.shamildev.retro.util.BottomNavigationViewHelper;
import com.shamildev.retro.util.RetroAnimation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Schamil Lazar.
 */

public class AccountActivity extends BaseActivitySupport {


    private static final String TAG = "AccountActivity";
    private static final int ACTIVITY_NUM = 2;
    private int mMaxScrollSize;
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 30;
    private boolean mIsAvatarShown = true;
    @Inject
    RetroImage retroImage;
    @Inject
    AccountActivity mContext;
    @Inject
    AppUser appUser;

    @BindView(R.id.profile_image)
    RetroProfileImageView mProfileImage;

    @BindView(R.id.app_bar)
    AppBarLayout appbarLayout;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbar_layout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    private State state;
    private OnStateChangeListener onStateChangeListener;
    private Unbinder butterKnifeUnbinder;
    private CircularProgressDrawable mProgress;
    private boolean fragments_set = false;

    public interface OnStateChangeListener {
        void onStateChange(State toolbarChange);
    }

    public enum State {
        COLLAPSED,
        EXPANDED,
        IDLE
    }
    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, AccountActivity.class);
        return intent;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: start");
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart: start");
        super.onStart();

    }


    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        Log.e(TAG, "onActivityReenter: start");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setupWindowAnimations();
        setContentView(R.layout.activity_account);

        butterKnifeUnbinder = ButterKnife.bind(this);



       // setSupportActionBar(mToolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.inflateMenu(R.menu.profile_settings);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override public boolean onMenuItemClick(MenuItem item) {
              //  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_REPO_URL));
              //  startActivity(browserIntent);
                navigator.navigateToSettings(mContext,mContext);
                return true;
            }
        });



        Log.e(TAG, "onCreate: starting "+savedInstanceState);


        mToolbar_layout.setTitle(appUser.getName());
       // mToolbar_layout.setLayoutParams(new AppBarLayout.LayoutParams(COLLAPS));

        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();
        setupBottomNavigation();

        if (savedInstanceState == null) {


            addFragment(R.id.fragmentContainerHeader, new AccountHeaderFragment());
            addFragment(R.id.fragmentContainer, new AccountFragment());





        }


        mProfileImage.setScaleX(0);
        mProfileImage.setScaleY(0);
      // final RealtimeBlurView realtimeBlurView = (RealtimeBlurView) findViewById(R.id.blur_view);
      //  realtimeBlurView.setBlurRadius(15);
        AppBarLayoutSpringBehavior behavior = (AppBarLayoutSpringBehavior) ((CoordinatorLayout.LayoutParams) appbarLayout.getLayoutParams()).getBehavior();
        behavior.setSpringOffsetCallback(new AppBarLayoutSpringBehavior.SpringOffsetCallback() {
            @Override
            public void springCallback(int offset) {
                int div = 220+offset;
                int radius = 15 * (div - offset > 0 ? div - offset : 0) / div;
                int dr = (15-radius);
              // realtimeBlurView.setBlurRadius(radius);

                      //  Log.e("HEIGHT", "> "+div+" radius: "+radius+" dr"+dr);
                if(offset>120){
                  //  createProgressView();
                }


            }
        });

//              <ImageView
//        android:id="@+id/materialup.profile_backdrop"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:scaleType="centerCrop"
//        android:src="@drawable/london_flat"
//        app:layout_collapseMode="parallax"
//                />
    }




    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

        changeState(appBarLayout, i);

        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;
            RetroAnimation
                    .PopAway(mProfileImage)
                    .start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;
            RetroAnimation
                    .PopUp(mProfileImage)
                    .start();

        }


    }

    private void changeState(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (onStateChangeListener != null && state != State.EXPANDED) {
                onStateChangeListener.onStateChange(State.EXPANDED);
            }
            state = State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (onStateChangeListener != null && state != State.COLLAPSED) {
                onStateChangeListener.onStateChange(State.COLLAPSED);
            }
            state = State.COLLAPSED;
        } else {
            if (onStateChangeListener != null && state != State.IDLE) {
                onStateChangeListener.onStateChange(State.IDLE);
            }
            state = State.IDLE;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void setOnStateChangeListener(OnStateChangeListener listener) {
        this.onStateChangeListener = listener;
    }



    /*
     * Bottomnavigationview setup
     */
    private void setupBottomNavigation() {

        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigationViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext,this,bottomNavigationViewEx,navigator);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }

    public void setPic(byte[] profilePic) {
        mProfileImage
                .src(profilePic, retroImage, new RetroImageRequestListener() {
            @Override
            public GlideException onLoadFailed(GlideException e) {
                return e;
            }

            @Override
            public Drawable onResourceReady(Drawable resource) {
               RetroAnimation
                       .PopUp(mProfileImage)
                       .start();


                return resource;
            }
        });

    }



    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT < 21) return;
        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide_left_out);

        slide.excludeTarget(android.R.id.statusBarBackground, true);
        slide.excludeTarget(android.R.id.navigationBarBackground, true);
        slide.excludeTarget(R.id.toolbar, true);
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        window.setExitTransition(slide);
    }



}

