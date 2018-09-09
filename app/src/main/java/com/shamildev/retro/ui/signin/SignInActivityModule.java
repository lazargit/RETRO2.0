package com.shamildev.retro.ui.signin;

import android.support.v7.app.AppCompatActivity;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.ui.common.BaseActivityModule;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.


 * Provides Splashscreen activity dependencies.
 */
@Module(includes = {BaseActivityModule.class})
public abstract class SignInActivityModule {





    @Binds
    @PerActivity
    abstract AppCompatActivity activity(SignInActivity activity);

}
