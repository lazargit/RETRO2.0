package com.shamildev.retro.executor;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Shamil Lazar
 */


@Reusable
public class CompositDisp {


    @Inject
    CompositDisp() {
    }


    public CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }

}
