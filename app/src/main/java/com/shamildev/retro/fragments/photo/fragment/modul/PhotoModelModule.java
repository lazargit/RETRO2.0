package com.shamildev.retro.fragments.photo.fragment.modul;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.fragments.photo.fragment.model.PhotoModel;
import com.shamildev.retro.fragments.photo.fragment.model.PhotoModelImpl;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

@Module
public abstract class PhotoModelModule {

    @Binds
    @PerFragment
    abstract PhotoModel model(PhotoModelImpl model);


}