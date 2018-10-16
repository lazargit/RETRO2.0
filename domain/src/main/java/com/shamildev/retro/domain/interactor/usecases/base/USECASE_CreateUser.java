package com.shamildev.retro.domain.interactor.usecases.base;

import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.domain.repository.BaseRepository;
import com.shamildev.retro.domain.repository.CacheRepository;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;


/**
 * Use case for getting a businesses with a given id.
 */
public final class USECASE_CreateUser implements UseCaseFlowable<ParamsBasic, Object> {

    private final BaseRepository baseRepository;
    private final CacheRepository cache;

    @Inject
    AppConfig appConfig;

    @Inject
    USECASE_CreateUser(BaseRepository repository, CacheRepository cacheRepository) {
        this.baseRepository = repository;
        this.cache = cacheRepository;
    }



    @Override
    public Flowable<Object> execute(ParamsBasic params) {
        byte[] imageBytes = ((Params) params).imageBytes;
        final long ONE_MEGABYTE = 1024 * 1024 *5;
        return this.baseRepository
                .uploadImage(imageBytes)

//                .flatMap(new Function<Object, Publisher<String>>() {
//                    @Override
//                    public Publisher<String> apply(Object o) throws Exception {
//                        System.out.println("DOMAIN "+o);
//                        if(o instanceof String){
//                            return Flowable.just((String)o);
//                        }
//                        return Flowable.empty();
//                    }
//                })
                .takeLast(1)
                .filter(s-> s instanceof String)
                .cast(String.class)

                .flatMap(s -> baseRepository.getBytes(s,ONE_MEGABYTE).toFlowable())


                ;



    }




    public static final class Params implements ParamsBasic {

        private byte[] imageBytes;



        public Params(byte[] bytes) {
            this.imageBytes = bytes;
        }


        public static Params withImageBytes(byte[] bytes) {
            return new Params(bytes);
        }


    }
}
