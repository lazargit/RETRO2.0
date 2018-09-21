package com.shamildev.retro.ui.splash.fragment.presenter;


import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.core.DomainObject;
import com.shamildev.retro.domain.core.MediaItem;
import com.shamildev.retro.domain.helper.ProcessData;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.splash.fragment.model.SplashModel;
import com.shamildev.retro.ui.splash.fragment.view.SplashView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


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


    @Inject Application application;
    @Inject RetroImage retroImage;
    @Inject Navigator navigator;

    @Inject
    SplashPresenterImpl(
            AppConfig appConfig,
            SplashView view,
            SplashModel model,
            DataConfig dataConfig) {
        super(view, model);
        //       this.networkManager = networkManager;
        //      this.networkManager.add(toString(), this::refreshData);
        this.dataConfig = dataConfig;
        this.appConfig = appConfig;


    }


    @Override
    public void onStart(@Nullable Bundle savedInstanceState) {
        if(this.appConfig.isFirstStart()){
            this.model.initTables();
        }else{
            this.model.initConfiguration();
        }
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
    public void finishPreload(HashMap<String, ResultWrapper> map) {
        toast("FINISH PRELOAD");
        appConfig.setPreloadDataMap(map);
        preloadSliderTeaserImages(map);

    }


    private void preloadSliderTeaserImages(HashMap<String, ResultWrapper> map){
        List<MediaItem> homeGalleryList = ProcessData.createHomeGalleryList(map, AppConfig.NOWPLAYINGKEY, 4);
        appConfig.setHomeGalleryList(homeGalleryList);
        retroImage.load(homeGalleryList)
                .Backdrop()
                .w780()
                .preload(new RetroImageRequestListener() {
                    @Override
                    public boolean onLoadFailed() {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady() {
                        navigator.navigateToHome(application);
                        return false;
                    }
                });


    }

    @Override
    public void setBgTeaser(ResultWrapper wrapper) {
        MediaItem mediaItem = (MediaItem) wrapper.results().get(0);
        retroImage
                .load(mediaItem)
                .Poster()
                .w780()
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
    public void configRetroImage(Configuration configuration) {
        retroImage.setConfigurations(configuration);
    }

    @Override
    public void toast(Object obj) {
        view.makeToast((String) obj);
    }


}