package com.shamildev.retro.domain.interactor.usecases.base;

import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.domain.repository.BaseRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;


/**
 * Use case for getting a businesses with a given id.
 */
public final class USECASE_authUser implements UseCaseFlowable<ParamsBasic,AppUser> {

    private final BaseRepository baseRepository;


    @Inject
    AppConfig appConfig;

    @Inject
    USECASE_authUser(BaseRepository repository
             ) {
        this.baseRepository = repository;


    }




    @Override
    public Flowable<AppUser> execute(ParamsBasic params) {

        final int cacheTime = ((Params) params).cacheTime;
        final String language = ((Params) params).language;
        final String token = ((Params) params).token;
        if(token!=null){
            return this.baseRepository.signInWithFacebook(token);
        }
        return  this.baseRepository.signInWithEmailAndPassword();




    }







    public static final class Params implements ParamsBasic {


        private  String token;
        private int cacheTime = 0;
        private String language;

        public Params(int cacheTime) {
            this.cacheTime = cacheTime;
        }
        public Params(String token) {
            this.token = token;
        }
        public Params(int cacheTime,String language ) {
            this.cacheTime = cacheTime;
            this.language = language;
        }


        public static Params with(int cacheTime) {
            return new Params(cacheTime);
        }
        public static Params withEmailAndPassword(String language, int cacheTime) {
            return new Params(cacheTime,language);
        }
        public static Params withFacebook(String token) {
            return new Params(token);
        }


    }
}
