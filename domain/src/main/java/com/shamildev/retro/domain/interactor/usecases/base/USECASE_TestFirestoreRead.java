package com.shamildev.retro.domain.interactor.usecases.base;

import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.usecase.UseCase;
import com.shamildev.retro.domain.core.usecase.UseCaseCompletable;
import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.repository.BaseRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;


/**
 * Use case for getting a businesses with a given id.
 */
public final class USECASE_TestFirestoreRead implements UseCase<ParamsBasic,AppUser> {

    private final BaseRepository baseRepository;


    @Inject
    AppConfig appConfig;

    @Inject
    USECASE_TestFirestoreRead(BaseRepository repository
             ) {
        this.baseRepository = repository;


    }




    @Override
    public Observable<AppUser> execute(ParamsBasic params) {



        return baseRepository.testListenerData();
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
