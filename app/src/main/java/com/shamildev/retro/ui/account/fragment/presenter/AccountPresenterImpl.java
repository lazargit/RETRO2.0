package com.shamildev.retro.ui.account.fragment.presenter;


import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shamildev.retro.R;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.net.error.BaseError;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.account.fragment.model.AccountModel;
import com.shamildev.retro.ui.account.fragment.view.AccountView;
import com.shamildev.retro.ui.common.presenter.BasePresenter;

import javax.inject.Inject;


/**
 * Created by Shamil Lazar
 */

/**
 * An implementation of {@link AccountPresenter}.
 */
@PerFragment
public final class AccountPresenterImpl extends BasePresenter<AccountView, AccountModel> implements AccountPresenter, NetworkManager.NetworkListener {

    private final String TAG = getClass().getName();
    private final FirebaseAuth mAuth;
    private NetworkManager networkManager;
    private DataConfig dataConfig;
    private AppConfig appConfig;


    @Inject Application application;
    @Inject AppUser appUser;
    @Inject Navigator navigator;


    private FirebaseAuth.AuthStateListener mAuthListener;


    @Inject
    AccountPresenterImpl(
            AppConfig appConfig,
            AccountView view,
            AccountModel model,
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
                if(mAuth.getCurrentUser()==null){
                    navigator.navigateToSignIn(application);
                }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mAuth.getCurrentUser()!=null){
            toast("u r logged in!");
            signInState();
        }else{
            logOutState();

        }

        model.initData();

    }

    @Override
    public void login() {
        model.signInUser();
    }

    @Override
    public void fbLogin(String token) {

//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d("facebook#", "facebook:onSuccess:## " + loginResult.getAccessToken().getToken());
//                //
//               // presenter.fbLogin(loginResult.getAccessToken().getToken());
//                model.signInUser(loginResult.getAccessToken().getToken());
//
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d("facebook#", "facebook:onCancel");
//                // ...
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d("facebook#", "facebook:onError"+error.getMessage(), error);
//                // ...
//            }
//        });


        // Callback registration

//


//        view.getFbLoginButton()
//                .registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        Log.e("FACEBOOK#", "check user " + loginResult.getAccessToken().getToken());
//                        model.signInUser(loginResult.getAccessToken().getToken());
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Log.e("FACEBOOK#", "onCancel ");
//                       // Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(FacebookException error) {
//                        Log.e("FACEBOOK#", "onError"+error.getMessage());
//                        // toast(R.string.error_login);
//                       // Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
//                    }
//                });
        // model.signInUser();
    }

    @Override
    public void logout() {
        model.logOut();
    }

    @Override
    public void logoutSuccesfull() {
        toast("Logout Succesfull !!"+FirebaseAuth.getInstance().getCurrentUser());
        signInState();

    }

    @Override
    public void signSuccesfull() {
        toast("Sign Succesfull !!");
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

    private void logOutState(){
        String info =appUser.getName();
        view.getTextView_UserEmail().setText(R.string.notloggedin);
        //view.getButton_logout().setVisibility(View.GONE);
        //view.getButton_signin().setVisibility(View.VISIBLE);


    }
    private void signInState(){
       // view.getTextView_UserEmail().setText( mAuth.getCurrentUser().getEmail());
        view.getTextView_UserEmail().setText("eingelogt");
       // view.getButton_signin().setVisibility(View.GONE);
       // view.getButton_logout().setVisibility(View.VISIBLE);

    }


}