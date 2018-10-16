package com.shamildev.retro;

import android.app.Application;

import com.shamildev.retro.core.ConfigModule;
import com.shamildev.retro.data.DataModule;
import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.domain.core.DomainModule;
import com.shamildev.retro.domain.executor.ExecutionThread;
import com.shamildev.retro.domain.executor.PostExecutionThread;
import com.shamildev.retro.executor.ExecutorModule;
import com.shamildev.retro.executor.IOExecutionThread;
import com.shamildev.retro.executor.MainPostExecutionThread;
import com.shamildev.retro.networkmanager.NetworkMangerModule;
import com.shamildev.retro.retroimage.core.RetroImageModule;
import com.shamildev.retro.ui.account.AccountActivity;
import com.shamildev.retro.ui.account.AccountActivityModule;
import com.shamildev.retro.ui.firebaseui.FirebaseUiSignInActivity;
import com.shamildev.retro.ui.firebaseui.FirebaseUiSignInActivityModule;
import com.shamildev.retro.ui.home.HomeActivity;
import com.shamildev.retro.ui.home.HomeActivityModule;
import com.shamildev.retro.ui.photogallery.PhotoGalleryActivity;
import com.shamildev.retro.ui.photogallery.PhotoGalleryActivityModule;
import com.shamildev.retro.ui.register.RegisterActivity;
import com.shamildev.retro.ui.register.RegisterActivityModule;
import com.shamildev.retro.ui.search.SearchActivity;
import com.shamildev.retro.ui.search.SearchActivityModule;
import com.shamildev.retro.ui.settings.SettingsActivity;
import com.shamildev.retro.ui.settings.SettingsActivityModule;
import com.shamildev.retro.ui.signin.SignInActivity;
import com.shamildev.retro.ui.signin.SignInActivityModule;
import com.shamildev.retro.ui.splash.SplashActivity;
import com.shamildev.retro.ui.splash.SplashActivityModule;
import com.shamildev.retro.ui.watchlist.WatchlistActivity;
import com.shamildev.retro.ui.watchlist.WatchlistActivityModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


/**
 * Created by Shamil Lazar on 13.12.2017.
 * includes = {
 * AndroidSupportInjectionModule.class,
 * ConfigModule.class,
 * GlideModule.class,
 * ProcessImageModule.class,
 * VideoPlayerModule.class,
 * NetworkMangerModule.class,
 * DataModule.class,
 * ExecutorModule.class,
 * DomainModule.class}
 * <p>
 * )
 */

@Module(
        includes = {
                AndroidSupportInjectionModule.class,
                ConfigModule.class,
                NetworkMangerModule.class,
                DataModule.class,
                DomainModule.class,
                ExecutorModule.class,
                RetroImageModule.class
        }

)
public abstract class AppModule {

    @Binds
    @Singleton
    abstract Application application(App app);


//
//    @Binds
//    @ApplicationScope
//    abstract NetworkManagerImpl networkManager(Application app);


    @Binds
    @Singleton
    abstract ExecutionThread providesThreadScheduler(IOExecutionThread schedulers);


    @Binds
    @Singleton
    abstract PostExecutionThread providesPostExecutionScheduler(MainPostExecutionThread mainPostExecutionThread);


    /**
     * Provides the injector for the {@link SplashActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity splashActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity homeActivityInjector();


    @PerActivity
    @ContributesAndroidInjector(modules = SignInActivityModule.class)
    abstract SignInActivity signInActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = RegisterActivityModule.class)
    abstract RegisterActivity registerActivityInjector();


    @PerActivity
    @ContributesAndroidInjector(modules = AccountActivityModule.class)
    abstract AccountActivity accountActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = SettingsActivityModule.class)
    abstract SettingsActivity settingsActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = WatchlistActivityModule.class)
    abstract WatchlistActivity watchlistActivityInjector();


    @PerActivity
    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    abstract SearchActivity searchActivityInjector();

    @PerActivity
    @ContributesAndroidInjector(modules = PhotoGalleryActivityModule.class)
    abstract PhotoGalleryActivity photogalleryActivityInjector();


    @PerActivity
    @ContributesAndroidInjector(modules = FirebaseUiSignInActivityModule.class)
    abstract FirebaseUiSignInActivity firebaseUiSignInActivityInjector();


}
