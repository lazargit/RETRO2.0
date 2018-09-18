package com.shamildev.retro.domain.interactor.usecases.tmdb;


import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;


public final class USECASE_GetTopRatedMovies implements UseCaseFlowable<ParamsBasic, ResultWrapper> {

    private final RemoteRepository repository;
    private final CacheRepository cache;

    @Inject
    USECASE_GetTopRatedMovies(RemoteRepository repository, CacheRepository cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public Flowable<ResultWrapper> execute(ParamsBasic params) {
        int page = ((Params) params).page;

        return this.repository.fetchTopRatedMovies(page)
                // .flatMap(resultWrapper -> ProcessData.prepareResultWrapper(resultWrapper,cache.fetchWatchList().blockingLast()))
                ;


//        return  this.repository.fetchTopRatedMovies(page)
//                .flatMap(movieWrapper -> repository.fetchTopRatedMovies(2))
//                .flatMapCompletable(movieWrapper -> Flowable.fromIterable(movieWrapper.results())
//                        .flatMapCompletable(movie -> cache.save((Movie) movie))).toFlowable();

    }


    public static final class Params implements ParamsBasic {

        private int page = 1;

        public Params(int page) {
            this.page = page;
        }

        public static USECASE_GetTopRatedMovies.Params withPage(int page) {
            return new USECASE_GetTopRatedMovies.Params(page);
        }

    }

}
