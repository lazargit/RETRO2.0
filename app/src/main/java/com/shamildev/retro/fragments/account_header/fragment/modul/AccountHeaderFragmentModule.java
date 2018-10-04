package com.shamildev.retro.fragments.account_header.fragment.modul;


import android.support.v4.app.Fragment;

import com.shamildev.retro.fragments.account_header.fragment.view.AccountHeaderFragment;
import com.shamildev.retro.fragments.account_header.fragment.view.AccountHeaderView;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.

 * Provides Splashfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        AccountHeaderPresenterModule.class
})
public abstract class AccountHeaderFragmentModule {

//    @PerChildFragment
//    @ContributesAndroidInjector(modules = HomePageFragmentModule.class)
//    abstract HomePageFragment homePageFragmentInjector();


    @Binds
    @Named(BaseFragmentModule.FRAGMENT_V4)
    @PerFragment
    abstract Fragment fragment(AccountHeaderFragment fragment);

    @Binds
    @PerFragment
    abstract AccountHeaderView view(AccountHeaderFragment fragment);
}