package com.shamildev.retro.ui.register.fragment.modul;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.register.fragment.presenter.RegisterPresenter;
import com.shamildev.retro.ui.register.fragment.presenter.RegisterPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        RegisterModelModule.class
})
public abstract class RegisterPresenterModule {

    @Binds
    @PerFragment
    abstract RegisterPresenter presenter(RegisterPresenterImpl registerPresenterImpl);


}