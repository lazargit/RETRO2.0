package com.shamildev.retro.ui.signin.fragment.modul;


import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.signin.fragment.view.SignInFragment;
import com.shamildev.retro.ui.signin.fragment.view.SignInView;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.
 * <p>
 * Provides Splashfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        SignInPresenterModule.class
})
public abstract class SignInFragmentModule {

//    @PerChildFragment
//    @ContributesAndroidInjector(modules = HomePageFragmentModule.class)
//    abstract HomePageFragment homePageFragmentInjector();


    @Binds
    @Named(BaseFragmentModule.FRAGMENT_V4)
    @PerFragment
    abstract Fragment fragment(SignInFragment fragment);

    @Binds
    @PerFragment
    abstract SignInView view(SignInFragment fragment);
}