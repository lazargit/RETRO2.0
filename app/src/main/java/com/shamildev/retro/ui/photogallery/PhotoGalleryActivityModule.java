package com.shamildev.retro.ui.photogallery;



import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.fragments.gallery.fragment.modul.GalleryFragmentModule;
import com.shamildev.retro.fragments.gallery.fragment.view.GalleryFragment;
import com.shamildev.retro.fragments.photo.fragment.modul.PhotoFragmentModule;
import com.shamildev.retro.fragments.photo.fragment.view.PhotoFragment;
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
public abstract class PhotoGalleryActivityModule {

    /**
     * Provides the injector for the {@link GalleryFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = GalleryFragmentModule.class)
    abstract GalleryFragment galleryFragmentInjector();

    /**
     * Provides the injector for the {@link PhotoFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = PhotoFragmentModule.class)
    abstract PhotoFragment photoFragmentInjector();

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
    abstract AppCompatActivity activity(PhotoGalleryActivity activity);
}
