package com.shamildev.retro.ui.account;

import android.support.v7.app.AppCompatActivity;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.fragments.account_header.fragment.modul.AccountHeaderFragmentModule;
import com.shamildev.retro.fragments.account_header.fragment.view.AccountHeaderFragment;
import com.shamildev.retro.ui.account.fragment.modul.AccountFragmentModule;
import com.shamildev.retro.ui.account.fragment.view.AccountFragment;
import com.shamildev.retro.ui.common.BaseActivityModule;
import com.shamildev.retro.ui.home.fragment.modul.HomeFragmentModule;
import com.shamildev.retro.ui.home.fragment.view.HomeFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Shamil Lazar.


 * Provides Splashscreen activity dependencies.
 */
@Module(includes = {BaseActivityModule.class})
public abstract class AccountActivityModule {


    @PerFragment
    @ContributesAndroidInjector(modules = AccountFragmentModule.class)
    abstract AccountFragment fragmentInjector();


    @PerFragment
    @ContributesAndroidInjector(modules = AccountHeaderFragmentModule.class)
    abstract AccountHeaderFragment accountheaderFragmentInjector();



    @Binds
    @PerActivity
    abstract AppCompatActivity activity(AccountActivity activity);

}
