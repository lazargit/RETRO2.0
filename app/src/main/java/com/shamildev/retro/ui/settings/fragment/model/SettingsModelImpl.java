package com.shamildev.retro.ui.settings.fragment.model;

import android.util.Log;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseHandler;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_LogoutUser;
import com.shamildev.retro.domain.models.AppUser;


import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by Shamil Lazar.
 */
@PerFragment
public class SettingsModelImpl extends SettingsModel {


    private final String TAG = getClass().getName().toString();


    @Inject
    protected DataConfig dataConfig;
    @Inject
    protected AppConfig appConfig;


    private UseCaseHandler useCaseHandler;
    private final USECASE_LogoutUser usecase_logoutUser;


    @Inject
    AppUser appUser;


    @Inject
    public SettingsModelImpl(
            UseCaseHandler useCaseHandler,
            USECASE_LogoutUser usecase_logoutUser
    ) {

        this.useCaseHandler = useCaseHandler;
        this.usecase_logoutUser = usecase_logoutUser;

    }

    @Override
    public void saveData() {

    }



    @Override
    public void logOut() {
        useCaseHandler.execute(usecase_logoutUser, USECASE_LogoutUser.Params.with(0),
                new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        presenter.logoutSuccesfull();
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.onError(e);
                    }
                });

    }







}
