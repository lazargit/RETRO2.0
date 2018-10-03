package com.shamildev.retro.ui.search;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.BaseActivityModule;
import com.shamildev.retro.ui.search.fragment.modul.SearchFragmentModule;
import com.shamildev.retro.ui.search.fragment.view.SearchFragment;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchlistFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Shamil Lazar.
 * <p>
 * <p>
 * Provides Splashscreen activity dependencies.
 */
@Module(includes = {BaseActivityModule.class})
public abstract class SearchActivityModule {

    /**
     * Provides the injector for the {@link com.shamildev.retro.ui.search.fragment.view.SearchFragment}, which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = SearchFragmentModule.class)
    abstract SearchFragment searchFragmentInjector();

    /**
     * As per the contract specified in {@link BaseActivityModule}; "This must be included in all
     * activity modules, which must provide a concrete implementation of {@link Activity}."
     * <p>
     * This provides the activity required to inject the
     * {@link BaseActivityModule#ACTIVITY_FRAGMENT_MANAGER} into the
     * {@link com.shamildev.retro.ui.common.BaseActivity}.
     *
     * @param activity the WatchlistActivity
     * @return the activity
     */
    @Binds
    @PerActivity
    abstract AppCompatActivity activity(SearchActivity activity);
}
