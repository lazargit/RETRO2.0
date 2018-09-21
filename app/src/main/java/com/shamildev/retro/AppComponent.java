package com.shamildev.retro;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;


/**
 * Created by Shamil Lazar.
 */


/**
 * Injects application dependencies.
 */
@Singleton
@Component(modules = AppModule.class)
interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {

    }
}