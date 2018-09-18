package com.shamildev.retro.ui.register.fragment.model;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseHandler;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_authUser;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetGenre;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetTMDBConfiguration;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar.

 */
@PerFragment
public class RegisterModelImpl extends RegisterModel {



    @Inject  protected AppConfig appConfig;
    @Inject protected AppUser appUser;

    private final USECASE_authUser usecase_authUser;
    private final UseCaseHandler useCaseHandler;



    @Inject
    public RegisterModelImpl(
                           UseCaseHandler useCaseHandler,
                           USECASE_authUser usecase_authUser) {
        this.useCaseHandler = useCaseHandler;
        this.usecase_authUser = usecase_authUser;

    }

    @Override
    public void createUser() {

    }
}
