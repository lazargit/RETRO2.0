package com.shamildev.retro.fragments.gallery.fragment.modul;


import android.support.v4.app.Fragment;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.fragments.gallery.fragment.view.GalleryFragment;
import com.shamildev.retro.fragments.gallery.fragment.view.GalleryView;
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
        GalleryPresenterModule.class
})
public abstract class GalleryFragmentModule {




    @Binds
    @Named(BaseFragmentModule.FRAGMENT_V4)
    @PerFragment
    abstract Fragment fragment(GalleryFragment fragment);

    @Binds
    @PerFragment
    abstract GalleryView view(GalleryFragment fragment);
}