package com.shamildev.retro.ui.watchlist.fragment.modul;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.register.fragment.presenter.RegisterPresenter;
import com.shamildev.retro.ui.register.fragment.presenter.RegisterPresenterImpl;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchlistPresenter;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchlistPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        WatchlistModelModule.class
})
public abstract class WatchlistPresenterModule {

    @Binds
    @PerFragment
    abstract WatchlistPresenter presenter(WatchlistPresenterImpl registerPresenterImpl);


}