package com.shamildev.retro.ui.signin.fragment.modul;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.signin.fragment.model.SignInModel;
import com.shamildev.retro.ui.signin.fragment.model.SignInModelImpl;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

@Module
public abstract class SignInModelModule {

    @Binds
    @PerFragment
    abstract SignInModel model(SignInModelImpl model);


}