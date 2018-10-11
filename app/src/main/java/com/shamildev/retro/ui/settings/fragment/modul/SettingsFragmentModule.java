package com.shamildev.retro.ui.settings.fragment.modul;


import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.settings.fragment.view.SettingsFragment;
import com.shamildev.retro.ui.settings.fragment.view.SettingsView;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.

 * Provides Splashfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        SettingsPresenterModule.class
})
public abstract class SettingsFragmentModule {

//    @PerChildFragment
//    @ContributesAndroidInjector(modules = HomePageFragmentModule.class)
//    abstract HomePageFragment homePageFragmentInjector();


    @Binds
    @Named(BaseFragmentModule.FRAGMENT_V4)
    @PerFragment
    abstract Fragment fragment(SettingsFragment fragment);

    @Binds
    @PerFragment
    abstract SettingsView view(SettingsFragment fragment);
}