
package com.shamildev.retro.cache.local;

import android.app.Application;

import com.shamildev.retro.cache.local.json.JsonManager;
import com.shamildev.retro.cache.local.load.StreamFileFromLocal;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.core.DomainObjectStorable;
import com.shamildev.retro.domain.repository.LocalRepository;
import com.shamildev.retro.domain.util.Constants;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Flowable;


/**
 * An implementation of {@link LocalCacheRepository}.
 */

@Reusable
public final class LocalCacheRepository implements LocalRepository {


    private static final String INITJSONFILE = "initdata.json";
    private static final String CONFIGURATION = "configuration";
    private static final String GENRES_MOVIE = "genres_movie";
    private static final String GENRES_TV = "genres_tv";


    @Inject
    Application application;


    StreamFileFromLocal streamJsonFromLocal;
    private final DataConfig dataConfig;
    private Map<String,DomainObjectStorable> map;
    private JsonManager jsonManager;

    @Inject
    LocalCacheRepository(StreamFileFromLocal streamJsonFromLocal,
                         DataConfig dataConfig,
                         JsonManager jsonManager) {

        this.streamJsonFromLocal = streamJsonFromLocal;
        this.dataConfig = dataConfig;
        this.jsonManager = jsonManager;
        this.map = new HashMap<>();



    }


    public Flowable<String> initString() {
        return streamJsonFromLocal
                .streamJsonFile(INITJSONFILE, dataConfig.language())
                .flatMap(jsonManager::addJson_string);
    }



    @Override
    public Flowable<DomainObjectStorable> streamJsonCongiguration() {
              return jsonManager.json_string()
                        .switchIfEmpty(initString())
                        .flatMap(s -> jsonManager.mapJson(CONFIGURATION, null));
    }


    @Override
    public Flowable<DomainObjectStorable> streamJsonGenres(Constants.MEDIA_TYPE type, String language) {


        String typ="";
        if(type.name() == Constants.MEDIA_TYPE.MOVIE.name()) {
           typ = GENRES_MOVIE;
        }
        if(type.name() == Constants.MEDIA_TYPE.TV.name()) {
            typ = GENRES_TV;
        }
        String finalTyp = typ;
        return   jsonManager.json_string()
                .switchIfEmpty(initString())
                .flatMap(s -> jsonManager.mapJson(finalTyp,language))

                ;
    }

}