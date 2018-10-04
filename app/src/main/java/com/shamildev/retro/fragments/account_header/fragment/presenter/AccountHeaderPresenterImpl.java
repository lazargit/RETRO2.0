package com.shamildev.retro.fragments.account_header.fragment.presenter;


import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.shamildev.retro.fragments.account_header.fragment.model.AccountHeaderModel;
import com.shamildev.retro.fragments.account_header.fragment.view.AccountHeaderView;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.net.error.BaseError;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.presenter.BasePresenter;

import javax.inject.Inject;


/**
 * Created by Shamil Lazar
 */

/**
 * An implementation of {@link AccountHeaderPresenter}.
 */
@PerFragment
public final class AccountHeaderPresenterImpl extends BasePresenter<AccountHeaderView, AccountHeaderModel> implements AccountHeaderPresenter, NetworkManager.NetworkListener {

    private static final String TAG = "AccountHeaderPresenterI";
    private NetworkManager networkManager;
    private DataConfig dataConfig;
    private AppConfig appConfig;


    @Inject Application application;
    @Inject AppUser appUser;
    @Inject Navigator navigator;


    private FirebaseAuth.AuthStateListener mAuthListener;


    @Inject
    AccountHeaderPresenterImpl(
            AppConfig appConfig,
            AccountHeaderView view,
            AccountHeaderModel model,
            NetworkManager networkManager,
            DataConfig dataConfig) {
        super(view, model);
        this.networkManager = networkManager;
        this.networkManager.add(toString(), this::refreshData);
;
        this.dataConfig = dataConfig;
        this.appConfig = appConfig;



    }


    @Override
    public void onStart(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
        super.onResume();
        view.tv_name().setText(appUser.getName());
        view.tv_details().setText(appUser.getEmail());




    }



    @Override
    public void onError(Throwable t) {
        if (t.getCause() instanceof TMDBError) {
            TMDBError error = (TMDBError) t.getCause();
            Log.d("onError", "<<<<< " + error.getResponseCode() + " : " + error.getMessage() + " : " + error.getStatusCode() + " : " + error.getSuccess());

        }
        if (t instanceof BaseError) {
            BaseError error = (BaseError) t;
            Log.d("onError", "<<<<< " + error.getMessage());
        }

    }





    @Override
    public void onNetworkAvailable() {

        Log.d("test", "onNetworkAvailable");
    }

    private void refreshData() {
        Log.d("test", "refreshData");
    }




}