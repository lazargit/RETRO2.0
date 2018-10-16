package com.shamildev.retro.fragments.photo.fragment.modul;


import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.fragments.photo.fragment.view.PhotoFragment;
import com.shamildev.retro.fragments.photo.fragment.view.PhotoView;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.
 * <p>
 * Provides Galleryfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        PhotoPresenterModule.class
})
public abstract class PhotoFragmentModule {




    @Binds
    @Named(BaseFragmentModule.FRAGMENT_V4)
    @PerFragment
    abstract Fragment fragment(PhotoFragment fragment);

    @Binds
    @PerFragment
    abstract PhotoView view(PhotoFragment fragment);
}