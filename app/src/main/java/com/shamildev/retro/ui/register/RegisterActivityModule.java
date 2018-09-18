package com.shamildev.retro.ui.register;

import android.app.Activity;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.BaseActivityModule;
import com.shamildev.retro.ui.register.fragment.modul.RegisterFragmentModule;
import com.shamildev.retro.ui.register.fragment.view.RegisterFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Shamil Lazar.
 * <p>
 * <p>
 * Provides Splashscreen activity dependencies.
 */
@Module(includes = {BaseActivityModule.class})
public abstract class RegisterActivityModule {

    /**
     * Provides the injector for the {@link RegisterFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = RegisterFragmentModule.class)
    abstract RegisterFragment registerFragmentInjector();

    /**
     * As per the contract specified in {@link BaseActivityModule}; "This must be included in all
     * activity modules, which must provide a concrete implementation of {@link Activity}."
     * <p>
     * This provides the activity required to inject the
     * {@link BaseActivityModule#ACTIVITY_FRAGMENT_MANAGER} into the
     * {@link com.shamildev.retro.ui.common.BaseActivity}.
     *
     * @param activity the SplashActivity
     * @return the activity
     */
    @Binds
    @PerActivity
    abstract Activity activity(RegisterActivity activity);
}
