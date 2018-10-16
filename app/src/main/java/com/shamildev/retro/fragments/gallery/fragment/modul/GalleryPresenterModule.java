package com.shamildev.retro.fragments.gallery.fragment.modul;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.fragments.gallery.fragment.presenter.GalleryPresenter;
import com.shamildev.retro.fragments.gallery.fragment.presenter.GalleryPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        GalleryModelModule.class
})
public abstract class GalleryPresenterModule {

    @Binds
    @PerFragment
    abstract GalleryPresenter presenter(GalleryPresenterImpl presenter);


}