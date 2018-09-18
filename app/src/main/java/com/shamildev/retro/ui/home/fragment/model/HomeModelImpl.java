package com.shamildev.retro.ui.home.fragment.model;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseHandler;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_authUser;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetGenre;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetTMDBConfiguration;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.splash.fragment.model.SplashModel;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar.

 */
@PerFragment
public class HomeModelImpl extends HomeModel{


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
    public HomeModelImpl(
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

    }


}
