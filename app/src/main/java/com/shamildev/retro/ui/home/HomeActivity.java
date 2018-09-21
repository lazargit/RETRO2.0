package com.shamildev.retro.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.MediaItem;
import com.shamildev.retro.domain.helper.ProcessData;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.ui.common.BaseActivity;
import com.shamildev.retro.ui.common.BaseActivitySupport;
import com.shamildev.retro.ui.home.fragment.view.HomeFragment;
import com.shamildev.retro.views.retroslider.views.ImageSliderView;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Schamil Lazar.
 */

public class HomeActivity extends BaseActivitySupport {


    private Unbinder butterKnifeUnbinder;
    @Inject RetroImage retroImage;
    @Inject AppConfig appConfig;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.img_slider) ImageSliderView img_slider;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        butterKnifeUnbinder = ButterKnife.bind(this);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new HomeFragment());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initImghHeader();
    }

    private void initImghHeader() {
        img_slider.startSlide(appConfig.getHomeGalleryList(),retroImage);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }
}
