package com.shamildev.retro.ui.settings.fragment.presenter;


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
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.settings.SettingsActivity;
import com.shamildev.retro.ui.settings.fragment.model.SettingsModel;
import com.shamildev.retro.ui.settings.fragment.view.SettingsView;

import javax.inject.Inject;


/**
 * Created by Shamil Lazar
 */

/**
 * An implementation of {@link SettingsPresenter}.
 */
@PerFragment
public final class SettingsPresenterImpl extends BasePresenter<SettingsView, SettingsModel> implements SettingsPresenter, NetworkManager.NetworkListener {

    private final String TAG = getClass().getName();

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
    SettingsActivity mContext;



    @Inject
    SettingsPresenterImpl(
            AppConfig appConfig,
            SettingsView view,
            SettingsModel model,
            NetworkManager networkManager,
            DataConfig dataConfig) {
        super(view, model);
        this.networkManager = networkManager;
        this.networkManager.add(toString(), this::refreshData);
//            this.bootstrap = bootstrap;
//            this.bootstrap.setUp(this);
        this.dataConfig = dataConfig;
        this.appConfig = appConfig;


    }


    @Override
    public void onStart(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "> " + appUser.toString());


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
        Log.d("onError", t.getMessage() + "---" + t);
    }


    @Override
    public void toast(Object obj) {
        view.makeToast((String) obj);
    }

    @Override
    public void save() {

    }

    @Override
    public void logout() {
        model.logOut();

    }

    @Override
    public void logoutSuccesfull() {
        mContext.onBackPressed();
    }

    @Override
    public void onNetworkAvailable() {

        Log.d("test", "onNetworkAvailable");
    }

    private void refreshData() {
        Log.d("test", "refreshData");
    }


}