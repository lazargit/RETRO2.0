package com.shamildev.retro.ui.signin.fragment.modul;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.signin.fragment.presenter.SignInPresenter;
import com.shamildev.retro.ui.signin.fragment.presenter.SignInPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        SignInModelModule.class
})
public abstract class SignInPresenterModule {

    @Binds
    @PerFragment
    abstract SignInPresenter presenter(SignInPresenterImpl presenter);


}