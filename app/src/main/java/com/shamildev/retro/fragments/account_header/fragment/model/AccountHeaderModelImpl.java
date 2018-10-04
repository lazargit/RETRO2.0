package com.shamildev.retro.fragments.account_header.fragment.model;

import android.util.Log;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseHandler;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_LogoutUser;
import com.shamildev.retro.domain.models.AppUser;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar.
 */
@PerFragment
public class AccountHeaderModelImpl extends AccountHeaderModel {


    private static final String TAG = "AccountHeaderModelImpl";


    @Inject
    protected DataConfig dataConfig;
    @Inject
    protected AppConfig appConfig;


    private UseCaseHandler useCaseHandler;
    private final USECASE_LogoutUser usecase_logoutUser;


    @Inject
    AppUser appUser;

    @Inject
    public AccountHeaderModelImpl(
            UseCaseHandler useCaseHandler,
            USECASE_LogoutUser usecase_logoutUser
    ) {

        this.useCaseHandler = useCaseHandler;
        this.usecase_logoutUser = usecase_logoutUser;

    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: ");


    }




}
