package com.shamildev.retro.ui.register.fragment.modul;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.register.fragment.model.RegisterModel;
import com.shamildev.retro.ui.register.fragment.model.RegisterModelImpl;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

@Module
public abstract class RegisterModelModule {

    @Binds
    @PerFragment
    abstract RegisterModel model(RegisterModelImpl model);


}