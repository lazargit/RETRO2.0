package com.shamildev.retro.ui.register.fragment.model;

import android.util.Log;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseHandler;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_CreateUser;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_authUser;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetGenre;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetTMDBConfiguration;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar.

 */
@PerFragment
public class RegisterModelImpl extends RegisterModel {

    private static final String TAG = "RegisterModelImpl";
    private final USECASE_CreateUser usecase_createUser;
    @Inject  protected AppConfig appConfig;
    @Inject protected AppUser appUser;

    private final USECASE_authUser usecase_authUser;
    private final UseCaseHandler useCaseHandler;



    @Inject
    public RegisterModelImpl(
                           UseCaseHandler useCaseHandler,
                           USECASE_authUser usecase_authUser,
                           USECASE_CreateUser usecase_createUser) {
        this.useCaseHandler = useCaseHandler;
        this.usecase_authUser = usecase_authUser;
        this.usecase_createUser = usecase_createUser;

    }

    @Override
    public void createUser(byte[] imageBytes) {
       useCaseHandler.execute(usecase_createUser, USECASE_CreateUser.Params.withImageBytes(imageBytes), new DisposableSubscriber<Object>() {
           @Override
           public void onNext(Object o) {
               Log.d(TAG, "createUser onNext: "+o.toString());
               presenter.pasteImage((byte[])o);

           }

           @Override
           public void onError(Throwable t) {
               Log.d(TAG, "createUser onError: "+t);
           }

           @Override
           public void onComplete() {
               Log.d(TAG, "onComplete: createUser");
           }
       });
    }
}
