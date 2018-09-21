package com.shamildev.retro.ui.home.fragment.modul;



import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.home.fragment.view.HomeFragment;
import com.shamildev.retro.ui.home.fragment.view.HomeView;


import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.

 * Provides Splashfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        HomePresenterModule.class
})
public abstract class HomeFragmentModule {


    @Binds
    @Named(BaseFragmentModule.FRAGMENT_V4)
    @PerFragment
    abstract Fragment fragment(HomeFragment fragment);

    @Binds
    @PerFragment
    abstract HomeView homeView(HomeFragment fragment);
}