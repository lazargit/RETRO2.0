package com.shamildev.retro.ui.watchlist.fragment.modul;

import android.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.register.fragment.view.RegisterFragment;
import com.shamildev.retro.ui.register.fragment.view.RegisterView;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchlistFragment;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchlistView;

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
        WatchlistPresenterModule.class
})
public abstract class WatchlistFragmentModule {


    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    @PerFragment
    abstract Fragment fragment(WatchlistFragment fragment);

    @Binds
    @PerFragment
    abstract WatchlistView registerView(WatchlistFragment fragment);
}