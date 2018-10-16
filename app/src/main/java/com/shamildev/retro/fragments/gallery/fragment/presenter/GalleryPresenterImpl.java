package com.shamildev.retro.fragments.gallery.fragment.presenter;


import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.net.error.BaseError;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.fragments.gallery.fragment.model.GalleryModel;
import com.shamildev.retro.fragments.gallery.fragment.view.GalleryView;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.presenter.BasePresenter;

import javax.inject.Inject;


/**
 * Created by Shamil Lazar
 */

/**
 * An implementation of {@link GalleryPresenter}.
 */
@PerFragment
public final class GalleryPresenterImpl extends BasePresenter<GalleryView, GalleryModel> implements GalleryPresenter {

    private static final String TAG = "PhotoPresenterImpl";
    private NetworkManager networkManager;
    private DataConfig dataConfig;
    private AppConfig appConfig;

    @Inject
    Application application;
    @Inject
    AppUser appUser;
    @Inject
    Navigator navigator;





    @Inject
    GalleryPresenterImpl(
            AppConfig appConfig,
            GalleryView view,
            GalleryModel model,
            DataConfig dataConfig) {
        super(view, model);


        this.dataConfig = dataConfig;
        this.appConfig = appConfig;


    }


    @Override
    public void onStart(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
        super.onResume();


    }


    @Override
    public void onError(Throwable t) {


    }


}