package com.shamildev.retro.domain.interactor.usecases.user;

import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.core.DomainObject;
import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_authUser;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetGenre;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.GuestSession;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.domain.repository.BaseRepository;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.DateUtil;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Use case for getting movies from watchlist.
 */
public final class USECASE_GetUser implements UseCaseFlowable<ParamsBasic, AppUser> {


    private final CacheRepository cache;
    private final RemoteRepository repository;
    private final BaseRepository baseRepository;
    @Inject
    AppConfig appConfig;

    @Inject
    DataConfig dataConfig;
    @Inject
    AppUser appUser;

    @Inject
    USECASE_GetUser(CacheRepository cache,RemoteRepository repository,BaseRepository baseRepository
                    ) {

        this.cache = cache;
        this.repository = repository;
        this.baseRepository = baseRepository;
    }


    @Override
    public Flowable<AppUser> execute(ParamsBasic params) {




        return cache.fetchUser()
                .switchIfEmpty(initNewUser())
                .flatMap(user->  Flowable.just(appUser)
                             .map(appUser -> {
                                  appUser.setUser(user);
                                 return appUser;
                             })
                )
                .map(u->appUser)
                .flatMap(appUser -> baseRepository.checkUser())
                .switchIfEmpty(Flowable.just(appUser))
//                .flatMap(appUser ->{
//                         if(!facebookToken.equals("")){
//                             appUser.setFBToken(facebookToken);
//                             return baseRepository.signInWithFacebook();
//                         }
//                          return Flowable.just(appUser);
//                         }
//                )
                ;



    }

    public Flowable<User> saveToCache(User model) {

        return Flowable.defer(() -> {
            try {
                return Flowable.just(model)
                        .flatMap(userModel -> Flowable.just(userModel)
                                .flatMapCompletable(cache::saveUser)
                                .toFlowable()
                                .startWith(userModel)
                        ).cast(User.class);
            } catch (Exception e) {
                return Flowable.error(e);

            }
        });


    }

    private Flowable<User> initNewUser() {

        return Flowable.just(dataConfig.language())
                .map(User::create)
                .flatMap(u -> Flowable.just(u.setSession(fetchGuestSession().blockingSingle())))
                .flatMap(this::saveToCache);
    }

    private Flowable<GuestSession> fetchGuestSession() {
        return repository.fetchGuestSession();

    }




    public static final class Params implements ParamsBasic {

        private  String facebookToken ="";

        private Params() {
        }

        public Params(String facebookToken) {
            this.facebookToken = facebookToken;
        }

        public static Params justVoid() {
            return new Params();
        }

        public static  Params with(String facebookToken) {
            return new Params(facebookToken);
        }



    }
}
