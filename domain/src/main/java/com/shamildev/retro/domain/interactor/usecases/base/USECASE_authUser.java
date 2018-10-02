package com.shamildev.retro.domain.interactor.usecases.base;

import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.domain.repository.BaseRepository;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;


/**
 * Use case for getting a businesses with a given id.
 */
public final class USECASE_authUser implements UseCaseFlowable<ParamsBasic, AppUser> {

    private final BaseRepository baseRepository;
    private final CacheRepository cache;

    @Inject
    AppConfig appConfig;

    @Inject
    USECASE_authUser(BaseRepository repository, CacheRepository cacheRepository) {
        this.baseRepository = repository;
        this.cache = cacheRepository;
    }


    @Override
    public Flowable<AppUser> execute(ParamsBasic params) {

        final AppUser.SignInType type = ((Params) params).type;

        if (type == AppUser.SignInType.facebook) {
            return signIn(this.baseRepository.signInWithFacebook(), type);
        }
        if (type == AppUser.SignInType.twitter) {
            return signIn(this.baseRepository.signInWithTwitter(), type);
        }
        if (type == AppUser.SignInType.email) {
            return signIn(this.baseRepository.signInWithEmailAndPassword(((Params) params).email, ((Params) params).password), type);

        }

        return Flowable.empty();
    }

    private Flowable<AppUser> signIn(Flowable<AppUser> appUserFlowable, AppUser.SignInType type) {
        return appUserFlowable
                .flatMap(appUser -> Flowable.just(type)
                        .map(appUser.getUser()::setLastLogin)
                        .flatMap(this::saveToCache)
                        .flatMap(user -> {
                            appUser.setUser(user);
                            return Flowable.just(appUser);
                        }));
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

        private String password;
        private String email;
        private AppUser.SignInType type;


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

        public static Params withEmailAndPassword(AppUser.SignInType type, String email, String password) {
            return new Params(type, email, password);
        }


    }
}
