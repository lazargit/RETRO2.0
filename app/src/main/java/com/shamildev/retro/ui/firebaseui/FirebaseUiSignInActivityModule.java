package com.shamildev.retro.ui.firebaseui;

import android.support.v7.app.AppCompatActivity;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.BaseActivityModule;
import com.shamildev.retro.ui.signin.SignInActivity;
import com.shamildev.retro.ui.signin.fragment.modul.SignInFragmentModule;
import com.shamildev.retro.ui.signin.fragment.view.SignInFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Shamil Lazar.


 * Provides Splashscreen activity dependencies.
 */
@Module(includes = {BaseActivityModule.class})
public abstract class FirebaseUiSignInActivityModule {





    @Binds
    @PerActivity
    abstract AppCompatActivity activity(FirebaseUiSignInActivity activity);



}
