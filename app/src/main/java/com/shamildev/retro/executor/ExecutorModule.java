package com.shamildev.retro.executor;


import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Provides executor dependencies.
 */
@Module
public  class ExecutorModule {
    @Provides
    CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }

}
