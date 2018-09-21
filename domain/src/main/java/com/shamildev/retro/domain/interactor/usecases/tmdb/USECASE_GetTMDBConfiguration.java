package com.shamildev.retro.domain.interactor.usecases.tmdb;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseFlowable;
import com.shamildev.retro.domain.interactor.params.ParamsBasic;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.DateUtil;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Use case for getting a businesses with a given id.
 */
public final class USECASE_GetTMDBConfiguration implements UseCaseFlowable<ParamsBasic, Configuration> {


    private final CacheRepository cache;
    private final DataConfig dataConfig;
    private final RemoteRepository remoteRepository;

    @Inject
    AppConfig appConfig;


    @Inject
    USECASE_GetTMDBConfiguration(RemoteRepository remoteRepository, CacheRepository cache, DataConfig dataConfig) {

        this.cache = cache;
        this.dataConfig = dataConfig;
        this.remoteRepository = remoteRepository;


    }




    @Override
    public Flowable<Configuration> execute(ParamsBasic params) {
        int cacheTime = ((Params) params).cacheTime;

        System.out.println("### "+dataConfig.baseUrl());

       // return  remoteRepository.fetchConfiguration();
        return fetchConfigurationFromCache()

                .switchIfEmpty(fetchConfigurationFromNet())
                .map(configuration -> {

                    if(DateUtil.isCacheTimeExpired(configuration, cacheTime).blockingSingle()){
                        return fetchConfigurationFromNet().blockingSingle();
                    }
                    return configuration;
                })


                .map(configuration -> fetchConfigurationFromCache().blockingSingle())
                .map((Configuration configuration) -> {
                    appConfig.setConfigurations(configuration);
                    return configuration;
                });



    }


    public Flowable<Configuration> saveToCache(Configuration model ) {

        System.out.println("saveToCache"+model.baseUrl());
        return   Flowable.defer(() -> {

            try {

                return Flowable.just(model)
                                .flatMap(configModel -> Flowable.just(configModel)
                                                                .flatMapCompletable(cache::saveTMDbConfiguration)
                                                                .toFlowable()
                                                                .startWith(configModel)

                                ).cast(Configuration.class);

            } catch (Exception e) {

                return Flowable.error(e);

            }
        });


    }
    public Flowable<Configuration> fetchConfigurationFromNet() {
        System.out.println("fetchConfigurationFromNet");


        return  this.remoteRepository.fetchConfiguration()
                .flatMap(this::saveToCache);


    }
    public Flowable<Configuration> fetchConfigurationFromCache() {
        System.out.println("fetchConfigurationFromCache");

        return  cache.fetchConfiguration()
                .subscribeOn(Schedulers.computation())
                ;

    }




    public static final class Params implements ParamsBasic {

        private Params() { }

        private int cacheTime = 0;

        public Params(int cacheTime) {
            this.cacheTime = cacheTime;
        }





        public static USECASE_GetTMDBConfiguration.Params withCacheTime(int cacheTime) {
            return new Params(cacheTime);
        }


}
}
