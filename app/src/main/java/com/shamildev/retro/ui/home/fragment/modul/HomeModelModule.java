package com.shamildev.retro.ui.home.fragment.modul;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.home.fragment.model.HomeModel;
import com.shamildev.retro.ui.home.fragment.model.HomeModelImpl;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

@Module
public abstract class HomeModelModule {

    @Binds
    @PerFragment
    abstract HomeModel model(HomeModelImpl model);


}