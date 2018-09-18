

package com.shamildev.retro.domain.interactor.usecases.tmdb;


import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;



public final class USECASE_GetMultiSearch implements UseCaseFlowable<ParamsBasic,ResultWrapper> {

    private final RemoteRepository repository;

    @Inject
    USECASE_GetMultiSearch(RemoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flowable<ResultWrapper> execute(ParamsBasic params) {
        String quary = ((Params) params).quary;
        int page = ((Params) params).page;
        return this.repository.fetchMultiSearch(quary,page);

    }




    public static final class Params implements ParamsBasic {

        private Params() {

        }

        private String quary = "";
        private int page = 1;



        private Params(String quary, int page) {
            this.quary = quary;
            this.page = page;
        }



        public static Params with(String quary,int page) {
            return new Params(quary,page);
        }



    }
}

