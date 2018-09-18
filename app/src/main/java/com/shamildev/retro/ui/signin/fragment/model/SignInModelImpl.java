package com.shamildev.retro.ui.signin.fragment.model;

import android.util.Log;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseHandler;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_LogoutUser;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_authUser;
import com.shamildev.retro.domain.models.AppUser;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar.
 */
@PerFragment
public class SignInModelImpl extends SignInModel {


    private final String TAG = getClass().getName().toString();



    @Inject
    protected DataConfig dataConfig;
    @Inject
    protected AppConfig appConfig;


    private UseCaseHandler useCaseHandler;
    private final USECASE_LogoutUser usecase_logoutUser;
    private final USECASE_authUser usecase_authUser;

    @Inject
    AppUser appUser;

    @Inject
    public SignInModelImpl(
            UseCaseHandler useCaseHandler,
            USECASE_LogoutUser usecase_logoutUser,
            USECASE_authUser usecase_authUser
    ) {

        this.useCaseHandler = useCaseHandler;
        this.usecase_logoutUser = usecase_logoutUser;
        this.usecase_authUser = usecase_authUser;

    }

    @Override
    public void initData() {
        Log.e(TAG, "" + appUser.getEmail());
        presenter.toast(appUser.getEmail());
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



    @Override
    public void signInUser() {
        useCaseHandler.execute( this.usecase_authUser,USECASE_authUser.Params.with(appUser.getSignintype()), new DisposableSubscriber<AppUser>() {
            @Override
            public void onNext(AppUser appUser) {
                Log.e(TAG,"USER sign in  "+appUser.toString());
              //  presenter.toast("User after  :"+appUser.isLoggedIn());
                presenter.signSuccesfull();


            }

            @Override
            public void onError(Throwable t) {
                presenter.onError(t);

            }

            @Override
            public void onComplete() {
                presenter.signSuccesfull();
            }
        });

    }

    @Override
    public void signInUserWithEmailAndPassword(String email, String password) {
        useCaseHandler.execute( this.usecase_authUser,USECASE_authUser.Params.withEmailAndPassword(AppUser.SignInType.email,email,password), new DisposableSubscriber<AppUser>() {
            @Override
            public void onNext(AppUser appUser) {
                Log.e(TAG,"USER sign in with email "+appUser.toString());
                presenter.toast("User after  :"+appUser.isLoggedIn());


            }

            @Override
            public void onError(Throwable t) {
                presenter.onError(t);

            }

            @Override
            public void onComplete() {
                presenter.signSuccesfull();
            }
        });
    }


    @Override
    public void createUserWithEmailAndPassword(String email, String password) {
        useCaseHandler.execute( this.usecase_authUser,USECASE_authUser.Params.withEmailAndPassword(AppUser.SignInType.email,email,password), new DisposableSubscriber<AppUser>() {
            @Override
            public void onNext(AppUser appUser) {
                Log.e(TAG,"USER sign in with email "+appUser.toString());
                presenter.toast("User after  :"+appUser.isLoggedIn());


            }

            @Override
            public void onError(Throwable t) {
                presenter.onError(t);

            }

            @Override
            public void onComplete() {
                presenter.signSuccesfull();
            }
        });
    }
}
