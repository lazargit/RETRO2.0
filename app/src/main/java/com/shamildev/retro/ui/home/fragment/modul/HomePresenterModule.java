package com.shamildev.retro.ui.home.fragment.modul;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenter;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenterImpl;
import com.shamildev.retro.ui.splash.fragment.modul.SplashModelModule;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        HomeModelModule.class
})
public abstract class HomePresenterModule {

    @Binds
    @PerFragment
    abstract HomePresenter presenter(HomePresenterImpl homePresenterImpl);


}