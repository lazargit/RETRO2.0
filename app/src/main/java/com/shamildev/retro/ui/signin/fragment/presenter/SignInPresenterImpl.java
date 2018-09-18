package com.shamildev.retro.ui.signin.fragment.presenter;


import android.app.Application;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shamildev.retro.R;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.net.error.BaseError;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.signin.fragment.model.SignInModel;
import com.shamildev.retro.ui.signin.fragment.view.SignInView;

import javax.inject.Inject;


/**
 * Created by Shamil Lazar
 */

/**
 * An implementation of {@link SignInPresenter}.
 */
@PerFragment
public final class SignInPresenterImpl extends BasePresenter<SignInView, SignInModel> implements SignInPresenter, NetworkManager.NetworkListener {

    private final String TAG = getClass().getName();
    private final FirebaseAuth mAuth;

    private NetworkManager networkManager;
    private DataConfig dataConfig;
    private AppConfig appConfig;
    private CallbackManager mCallBackManager;

    @Inject Application application;
    @Inject Navigator navigator;
    @Inject AppUser appUser;


    private FirebaseAuth.AuthStateListener mAuthListener;


    @Inject
    SignInPresenterImpl(
            AppConfig appConfig,
            SignInView view,
            SignInModel model,
            NetworkManager networkManager,
            DataConfig dataConfig) {
        super(view, model);
        this.networkManager = networkManager;
        this.networkManager.add(toString(), this::refreshData);
//            this.bootstrap = bootstrap;
//            this.bootstrap.setUp(this);
        this.dataConfig = dataConfig;
        this.appConfig = appConfig;
        this.mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onStart(@Nullable Bundle savedInstanceState) {
        this.model.initData();

    }

    @Override
    public void onResume() {
        super.onResume();

        if (mAuth.getCurrentUser() != null) {
            toast("u r logged in!");
         //   signInState();
        } else {
         //   logOutState();

        }


    }

    @Override
    public void login() {
        view.loadDialog();
        model.signInUser();
    }

    @Override
    public void fbLogin(String token) {
        Log.d("facebook#", "facebook:login ");
        view.loadDialog();
        model.signInUser();
    }

    @Override
    public void firebaseLogin() {
        view.loadDialog();
        model.signInUser();
    }

    @Override
    public void firebaseLogin(String email, String password) {
        model.signInUserWithEmailAndPassword(email,password);
    }




    @Override
    public void logout() {
        model.logOut();
    }

    @Override
    public void logoutSuccesfull() {
        toast("Logout Succesfull !!" + FirebaseAuth.getInstance().getCurrentUser());

        view.removeDialog();
    }

    @Override
    public void signSuccesfull() {
        toast("Sign Succesfull !!");
        if(mAuth.getCurrentUser()!=null){
            navigator.navigateToAccount(application);
        }
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
    public void onNetworkAvailable() {

        Log.d("test", "onNetworkAvailable");
    }

    private void refreshData() {
        Log.d("test", "refreshData");
    }




}