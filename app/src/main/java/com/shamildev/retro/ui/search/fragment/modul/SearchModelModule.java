package com.shamildev.retro.ui.search.fragment.modul;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.search.fragment.model.SearchModel;
import com.shamildev.retro.ui.search.fragment.model.SearchModelImpl;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

@Module
public abstract class SearchModelModule {

    @Binds
    @PerFragment
    abstract SearchModel model(SearchModelImpl model);


}