package com.shamildev.retro.ui.search.fragment.modul;



import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.search.fragment.view.SearchFragment;
import com.shamildev.retro.ui.search.fragment.view.SearchView;

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
        SearchPresenterModule.class
})
public abstract class SearchFragmentModule {


    @Binds
    @Named(BaseFragmentModule.FRAGMENT_V4)
    @PerFragment
    abstract Fragment fragment(SearchFragment fragment);

    @Binds
    @PerFragment
    abstract SearchView registerView(SearchFragment fragment);
}