package com.shamildev.retro.ui.settings;

import android.support.v7.app.AppCompatActivity;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.fragments.account_header.fragment.modul.AccountHeaderFragmentModule;
import com.shamildev.retro.fragments.account_header.fragment.view.AccountHeaderFragment;
import com.shamildev.retro.ui.common.BaseActivityModule;
import com.shamildev.retro.ui.settings.fragment.modul.SettingsFragmentModule;
import com.shamildev.retro.ui.settings.fragment.view.SettingsFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Shamil Lazar.


 * Provides Splashscreen activity dependencies.
 */
@Module(includes = {BaseActivityModule.class})
public abstract class SettingsActivityModule {


    @PerFragment
    @ContributesAndroidInjector(modules = SettingsFragmentModule.class)
    abstract SettingsFragment fragmentInjector();



    @Binds
    @PerActivity
    abstract AppCompatActivity activity(SettingsActivity activity);

}
