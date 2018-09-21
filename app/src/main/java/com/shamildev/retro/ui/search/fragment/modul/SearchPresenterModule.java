package com.shamildev.retro.ui.search.fragment.modul;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.search.fragment.presenter.SearchPresenter;
import com.shamildev.retro.ui.search.fragment.presenter.SearchPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        SearchModelModule.class
})
public abstract class SearchPresenterModule {

    @Binds
    @PerFragment
    abstract SearchPresenter presenter(SearchPresenterImpl registerPresenterImpl);


}