package com.shamildev.retro.ui.settings.fragment.modul;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.settings.fragment.model.SettingsModel;
import com.shamildev.retro.ui.settings.fragment.model.SettingsModelImpl;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

 @Module
 public abstract class SettingsModelModule {

        @Binds
        @PerFragment
        abstract SettingsModel model(SettingsModelImpl model);


 }