package com.shamildev.retro.domain.interactor.usecases.user;

import com.shamildev.retro.domain.core.DomainObject;
import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Use case for getting movies from watchlist.
 */
public final class USECASE_GetMyWatchList implements UseCaseFlowable<ParamsBasic, List<DomainObject>> {


    private final CacheRepository cache;

    @Inject
    USECASE_GetMyWatchList(CacheRepository cache) {

        this.cache = cache;
    }


    @Override
    public Flowable<List<DomainObject>> execute(ParamsBasic params) {

        return cache.fetchWatchList();

    }


    public static final class Params implements ParamsBasic {

        private Params() {
        }

        public static Params justVoid() {
            return new Params();
        }

    }
}
