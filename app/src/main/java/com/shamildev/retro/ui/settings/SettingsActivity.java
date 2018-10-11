package com.shamildev.retro.ui.settings;

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
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
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
import com.shamildev.retro.ui.settings.fragment.view.SettingsFragment;
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

public class SettingsActivity extends BaseActivitySupport {


    private static final String TAG = "SettingsActivity";
    private static final int ACTIVITY_NUM = 2;
    private int mMaxScrollSize;
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 30;
    private boolean mIsAvatarShown = true;
    @Inject
    RetroImage retroImage;
    @Inject
    SettingsActivity mContext;
    @Inject
    AppUser appUser;


    @BindView(R.id.toolbar)
    Toolbar toolbar;



    private Unbinder butterKnifeUnbinder;
    private CircularProgressDrawable mProgress;


    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupWindowAnimations();
        setContentView(R.layout.activity_settings);
        butterKnifeUnbinder = ButterKnife.bind(this);
       // setupWindowAnimations();


        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new SettingsFragment());
        }

       // setSupportActionBar(mToolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupToolBar(R.string.settings);

//        mToolbar.inflateMenu(R.menu.profile_settings);
//        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override public boolean onMenuItemClick(MenuItem item) {
//              //  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_REPO_URL));
//              //  startActivity(browserIntent);
//                return true;
//            }
//        });



    }

    private void setupToolBar(int title) {
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(title);
            toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;


        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT < 21) return;
        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.activity_slide_from_right);

        slide.excludeTarget(android.R.id.statusBarBackground, true);
        slide.excludeTarget(android.R.id.navigationBarBackground, true);
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        window.setEnterTransition(slide);




    }

    public void setSceneTransitionAnimation() {
        if (Build.VERSION.SDK_INT < 21) return;
        Transition slide = new Slide(Gravity.RIGHT);
        slide.excludeTarget(android.R.id.statusBarBackground, true);
        slide.excludeTarget(android.R.id.navigationBarBackground, true);
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        window.setExitTransition(slide);
        window.setEnterTransition(slide);
        window.setTransitionBackgroundFadeDuration(getResources()
                .getInteger(android.R.integer.config_longAnimTime));
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }


}
