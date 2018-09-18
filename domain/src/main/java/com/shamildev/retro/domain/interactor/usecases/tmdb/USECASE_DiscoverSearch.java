
package com.shamildev.retro.domain.interactor.usecases.tmdb;

import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;


public final class USECASE_DiscoverSearch implements UseCaseFlowable<ParamsBasic, ResultWrapper> {

    private final RemoteRepository repository;
    private final CacheRepository cache;

    @Inject
    USECASE_DiscoverSearch(RemoteRepository repository, CacheRepository cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public Flowable<ResultWrapper> execute(ParamsBasic params) {
        int page = ((Params) params).page;
        Map<String, Object> map = ((Params) params).map;
        return repository.fetchDiscover(
                map,
                page)
                //  .flatMap(resultWrapper -> ProcessData.prepareResultWrapper(resultWrapper,cache.fetchWatchList().blockingLast()))
//                .flatMapCompletable(movieWrapper -> Flowable.fromIterable(movieWrapper.results())
//                       .flatMapCompletable(movie -> cache.saveItemWatchList((Movie) movie))).toFlowable()
                ;


    }


    public static final class Params implements ParamsBasic {

        private int page = 1;
        private Map<String, Object> map;

        public Params(Map<String, Object> map, int page) {
            this.map = map;
            this.page = page;

        }


        public static USECASE_DiscoverSearch.Params with(Map<String, Object> map, int page) {
            return new USECASE_DiscoverSearch.Params(map, page);
        }


    }

}
