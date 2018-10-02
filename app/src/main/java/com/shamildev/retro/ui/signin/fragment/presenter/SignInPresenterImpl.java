package com.shamildev.retro.ui.signin.fragment.presenter;


import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.CallbackManager;
import com.google.firebase.auth.FirebaseAuth;
import com.shamildev.retro.data.net.NetworkManager;
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
    public void prepareEmailLogin(String email, String password) {
        view.loadDialog();
        model.signInUserWithEmailAndPassword(email,password);
    }

    @Override
    public void prepareFacebookLogin() {
        view.loadDialog();
        appUser.setSignintype(AppUser.SignInType.facebook);
        if(appConfig.getFacebookToken()!=null){
            model.signInUser();
        }else{
            view.loginFacebook();
        }

    }

    @Override
    public void prepareTwitterLogin() {
        view.loadDialog();
        appUser.setSignintype(AppUser.SignInType.twitter);
        if(appConfig.getTwitterToken().key!=null && appConfig.getTwitterToken().value!=null){
            model.signInUser();
        }else{
            view.loginTwitter();
        }
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
        view.removeDialog();
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
        if (t instanceof com.shamildev.retro.domain.error.BaseError) {
            Log.d("onError", "<<<<< " + t.getMessage());

            view.removeDialog();
            view.showSnackBar("Hallo");



        }

        Log.e("onError", t + "---" + t);
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

    @Override
    public void snackbar(Object obj) {
        view.showSnackBar(obj);
    }
}