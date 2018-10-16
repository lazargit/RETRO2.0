package com.shamildev.retro.fragments.photo.fragment.modul;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.fragments.photo.fragment.presenter.PhotoPresenter;
import com.shamildev.retro.fragments.photo.fragment.presenter.PhotoPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        PhotoModelModule.class
})
public abstract class PhotoPresenterModule {

    @Binds
    @PerFragment
    abstract PhotoPresenter presenter(PhotoPresenterImpl presenter);


}