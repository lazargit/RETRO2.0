package com.shamildev.retro.domain.interactor.usecases.tmdb;

import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.repository.RemoteRepository;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;


/**
 * Use case for getting a businesses with a given id.
 */
public final class USECASE_GetGenre implements UseCaseFlowable<ParamsBasic,List<Genre>> {

    private final RemoteRepository repository;


    @Inject
    AppConfig appConfig;

    @Inject
    USECASE_GetGenre(RemoteRepository repository
             ) {
        this.repository = repository;


    }




    @Override
    public Flowable<List<Genre>> execute(ParamsBasic params) {

        final int cacheTime = ((Params) params).cacheTime;
        final String language = ((Params) params).language;

        return  Flowable.empty();



    }







    public static final class Params implements ParamsBasic {
        private int cacheTime = 0;
        private String language;
        public Params(int cacheTime) {
            this.cacheTime = cacheTime;
        }
        public Params(int cacheTime,String language ) {
            this.cacheTime = cacheTime;
            this.language = language;
        }


        public static Params with(int cacheTime) {
            return new Params(cacheTime);
        }
        public static Params with(String language, int cacheTime) {
            return new Params(cacheTime,language);
        }



    }
}
