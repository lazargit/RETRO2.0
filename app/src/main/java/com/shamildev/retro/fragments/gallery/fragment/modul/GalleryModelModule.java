package com.shamildev.retro.fragments.gallery.fragment.modul;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.fragments.gallery.fragment.model.GalleryModel;
import com.shamildev.retro.fragments.gallery.fragment.model.GalleryModelImpl;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

@Module
public abstract class GalleryModelModule {

    @Binds
    @PerFragment
    abstract GalleryModel model(GalleryModelImpl model);


}