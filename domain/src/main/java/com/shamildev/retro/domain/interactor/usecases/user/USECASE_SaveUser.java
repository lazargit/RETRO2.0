package com.shamildev.retro.domain.interactor.usecases.user;

import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.core.DomainObject;
import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.GuestSession;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.domain.repository.BaseRepository;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Use case for getting movies from watchlist.
 */
public final class USECASE_SaveUser implements UseCaseFlowable<ParamsBasic, AppUser> {


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
    USECASE_SaveUser(CacheRepository cache, RemoteRepository repository, BaseRepository baseRepository
                    ) {

        this.cache = cache;
        this.repository = repository;
        this.baseRepository = baseRepository;
    }


    @Override
    public Flowable<AppUser> execute(ParamsBasic params) {
        User user = ((Params) params).user;

        return  saveToCache(user)
                .flatMap(u->  Flowable.just(appUser)
                             .map(appUser -> {
                                  appUser.setUser(u);
                                 return appUser;
                             })
                ) ;




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






    public static final class Params implements ParamsBasic {

        private User user;
        private byte[] pic;

        private Params() {
        }

        public Params(byte[] facebookToken) {
            this.pic = facebookToken;
        }
        public Params(User user) {
            this.user = user;
        }

        public static Params justVoid() {
            return new Params();
        }

        public static  Params pic(byte[] pic) {
            return new Params(pic);
        }


        public static Params user(User user) {
            return new Params(user);
        }
    }
}
