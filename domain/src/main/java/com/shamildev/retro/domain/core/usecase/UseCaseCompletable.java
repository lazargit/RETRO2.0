package com.shamildev.retro.domain.core.usecase;

import io.reactivex.Completable;


public interface UseCaseCompletable<K> {

    Completable execute(K params);
}
