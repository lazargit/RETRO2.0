package com.shamildev.retro.ui.watchlist.fragment.modul;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.watchlist.fragment.model.WatchlistModel;
import com.shamildev.retro.ui.watchlist.fragment.model.WatchlistModelImpl;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

@Module
public abstract class WatchlistModelModule {

    @Binds
    @PerFragment
    abstract WatchlistModel model(WatchlistModelImpl model);


}