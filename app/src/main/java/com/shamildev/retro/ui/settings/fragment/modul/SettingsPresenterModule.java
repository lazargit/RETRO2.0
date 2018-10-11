package com.shamildev.retro.ui.settings.fragment.modul;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.settings.fragment.presenter.SettingsPresenter;
import com.shamildev.retro.ui.settings.fragment.presenter.SettingsPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        SettingsModelModule.class
})
public abstract class SettingsPresenterModule {

    @Binds
    @PerFragment
    abstract SettingsPresenter presenter(SettingsPresenterImpl presenter);


}