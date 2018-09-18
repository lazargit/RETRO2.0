package com.shamildev.retro.ui.register.fragment.modul;

import android.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.register.fragment.view.RegisterFragment;
import com.shamildev.retro.ui.register.fragment.view.RegisterView;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.
 * <p>
 * Provides Registerfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        RegisterPresenterModule.class
})
public abstract class RegisterFragmentModule {


    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    @PerFragment
    abstract Fragment fragment(RegisterFragment fragment);

    @Binds
    @PerFragment
    abstract RegisterView registerView(RegisterFragment fragment);
}