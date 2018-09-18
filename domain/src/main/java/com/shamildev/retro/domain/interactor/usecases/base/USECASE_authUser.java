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

        final AppUser.SignInType type = ((Params) params).type;

        if(type == AppUser.SignInType.facebook){
            return this.baseRepository.signInWithFacebook();
        }
        if(type == AppUser.SignInType.twitter){
            return this.baseRepository.signInWithTwitter();
        }
        if(type == AppUser.SignInType.email){
            return this.baseRepository.signInWithEmailAndPassword(((Params) params).email,((Params) params).password);
        }

        return Flowable.empty();
    }







    public static final class Params implements ParamsBasic {

        private  String password;
        private  String email;
        private  AppUser.SignInType type;


        public Params(AppUser.SignInType type) {
            this.type = type;
        }

        public Params(AppUser.SignInType type, String email, String password) {
            this.type = type;
            this.email = email;
            this.password = password;
        }

        public static Params with(AppUser.SignInType type) {
            return new Params(type);
        }

        public static Params withEmailAndPassword(AppUser.SignInType type,String email, String password) {
            return new Params(type,email,password);
        }


    }
}
