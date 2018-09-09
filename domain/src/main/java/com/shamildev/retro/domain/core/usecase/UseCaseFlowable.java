package com.shamildev.retro.domain.core.usecase;

import io.reactivex.Flowable;


public interface UseCaseFlowable<K, V> {

    Flowable<V> execute(K params);
}
