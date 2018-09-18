package com.shamildev.retro.domain.interactor.usecases.tmdb;


import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;


/**
 * Use case for getting a businesses with a given id.
 */
public final class USECASE_GetPopularPerson implements UseCaseFlowable<ParamsBasic,ResultWrapper> {

    private final RemoteRepository repository;

    @Inject
    USECASE_GetPopularPerson(RemoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flowable<ResultWrapper> execute(ParamsBasic params) {

        int page = ((Params) params).page;
        return this.repository.fetchPopularPerson(page);

    }




    public static final class Params implements ParamsBasic {

        private Params() {

        }


        private int page = 1;



        private Params(int page) {
            this.page = page;
        }



        public static Params withPage(int page) {
            return new Params(page);
        }



    }
}

