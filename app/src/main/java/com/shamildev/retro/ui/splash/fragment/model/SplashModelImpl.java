package com.shamildev.retro.ui.splash.fragment.model;

import android.util.Log;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseHandler;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_authUser;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetGenre;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetTMDBConfiguration;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar.
 *             //https://media.giphy.com/media/87kcVw4PjxGr6/giphy.gif
 //            ArrayList<String> gifUrl = new ArrayList<>();
 //            gifUrl.add("https://i.gifer.com/2Gr0.gif");
 //            gifUrl.add("https://media.giphy.com/media/87kcVw4PjxGr6/giphy.gif");
 //            gifUrl.add("https://media.giphy.com/media/lR8eXSeYUF5vO/giphy.gif");
 */
@PerFragment
public class SplashModelImpl extends SplashModel{


    private final USECASE_authUser usecase_authUser;
    @Inject
    protected AppConfig appConfig;



    private  UseCaseHandler useCaseHandler;
    private final USECASE_GetTMDBConfiguration usecase_getTMDBConfiguration;
    private final USECASE_GetGenre usecase_getGenre;
    private  SplashPresenter pres;
    private HashMap<String,ResultWrapper> map = new HashMap<>();
    private final ArrayList<String> mListTopic = new ArrayList<String>();


    @Inject
    AppUser appUser;

    @Inject
    public SplashModelImpl(
                           UseCaseHandler useCaseHandler,
                           USECASE_GetGenre getGenre,
                           USECASE_GetTMDBConfiguration getTMDBConfiguration,
                           USECASE_authUser usecase_authUser
                       ) {
        this.useCaseHandler = useCaseHandler;
        this.usecase_getGenre = getGenre;
        this.usecase_getTMDBConfiguration = getTMDBConfiguration;
        this.usecase_authUser = usecase_authUser;

//        this.mListTopic.add(AppConfig.NOWPLAYINGKEY);
//        this.mListTopic.add(AppConfig.NOWPLAYINGTVKEY);
//        this.mListTopic.add(AppConfig.UPCOMMINGKEY);
//        this.mListTopic.add(AppConfig.TOPRATEDKEY);
//        this.mListTopic.add(AppConfig.POPULARPERSONKEY);
//        this.mListTopic.add(AppConfig.HOMEHEADERKEY);
    }









    @Override
    public void initConfiguration(){


//        useCaseHandler.execute(usecase_authUser, USECASE_authUser.Params.with(0), new DisposableSubscriber<AppUser>() {
//            @Override
//            public void onNext(AppUser appUser) {
//                Log.e("usecase_authUser","onNext>>: "+appUser.toString());
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                Log.e("usecase_authUser","error "+t);
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e("usecase_authUser","complete");
//            }
//        });

//        useCaseHandler.execute(usecase_getTMDBConfiguration, USECASE_GetTMDBConfiguration.Params.withCacheTime(1),
//        new DisposableSubscriber<Configuration>() {
//            @Override
//            public void onNext(Configuration configuration) {
//               // presenter.configRetroImage(configuration);
//                Log.e("---->",">>"+configuration.baseUrl());
//
//            }
//
//            @Override
//            public void onError(Throwable t) {
//               // presenter.onError(t);
//                Log.e("ERRROR ",">>"+t);
//
//            }
//
//            @Override
//            public void onComplete() {
//
//
//
//            }
//        });
    }


}
