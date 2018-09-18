package com.shamildev.retro.domain.interactor.usecases.user;


import com.shamildev.retro.domain.core.DomainObject;
import com.shamildev.retro.domain.core.usecase.UseCaseCompletable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Use case for save movies and shows in to the watchlist.
 */
public final class USECASE_SaveToWatchList implements UseCaseCompletable<ParamsBasic> {


    private final CacheRepository cache;

    @Inject
    USECASE_SaveToWatchList(CacheRepository cache) {

        this.cache = cache;
    }


    @Override
    public Completable execute(ParamsBasic params) {
        DomainObject item = ((Params) params).item;

        return cache.saveItemWatchList(item);
    }


    public static final class Params implements ParamsBasic {

        private DomainObject item;

        private Params() {
        }

        private Params(DomainObject item) {
            this.item = item;
        }

        public static Params justVoid() {
            return new Params();

        }

        public static Params withItem(DomainObject item) {
            return new Params(item);
        }

    }


}
