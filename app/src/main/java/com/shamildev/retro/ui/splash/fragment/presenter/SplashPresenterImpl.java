package com.shamildev.retro.ui.splash.fragment.presenter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.splash.fragment.model.SplashModel;
import com.shamildev.retro.ui.splash.fragment.view.SplashView;

import javax.inject.Inject;


/**
 * Created by Shamil Lazar
 */

/**
 * An implementation of {@link SplashPresenter}.
 */
@PerFragment
public final class SplashPresenterImpl extends BasePresenter<SplashView, SplashModel> implements SplashPresenter {



    private DataConfig dataConfig;
    private AppConfig appConfig;


    @Inject
    RetroImage retroImage;

    @Inject
    SplashPresenterImpl(
            AppConfig appConfig,
            SplashView view,
            SplashModel model,
            DataConfig dataConfig) {
        super(view, model);
        //       this.networkManager = networkManager;
        //      this.networkManager.add(toString(), this::refreshData);
//            this.bootstrap = bootstrap;
//            this.bootstrap.setUp(this);
        this.dataConfig = dataConfig;
        this.appConfig = appConfig;


    }


    @Override
    public void onStart(@Nullable Bundle savedInstanceState) {
        Log.e("BasePresenter", "onStart!!!! " + this.appConfig.isFirstStart());
        model.initConfiguration();

        retroImage
                .load("https://media.giphy.com/media/l0Iyn2ZHQCM3tSqR2/giphy.gif")
                .into(view.getSplashBg(),new RetroImageRequestListener() {
                    @Override
                    public boolean onLoadFailed() {
                        Log.e("TAG","IMAGES LOAD FAILED.");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady() {
                        Log.e("TAG","ALL IMAGES PRELOADED...!");

                        return false;
                    }
                });


    }


    @Override
    public void onError(Throwable t) {
        if (t.getCause() instanceof TMDBError) {
            TMDBError error = (TMDBError) t.getCause();
            Log.d("onError", "<<<<< " + error.getResponseCode() + " : " + error.getMessage() + " : " + error.getStatusCode() + " : " + error.getSuccess());

        } else {

        }
        Log.d("onError", t.getMessage());
    }


    @Override
    public void toast(Object obj) {
        view.makeToast((String) obj);
    }


}