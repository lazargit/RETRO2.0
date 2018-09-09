package com.shamildev.retro.cache.repository;
import android.util.Log;
import com.shamildev.retro.cache.realm.mapper.RealmMapperHolder;
import com.shamildev.retro.cache.realm.models.ConfigurationRealm;
import com.shamildev.retro.cache.realm.models.GenreRealm;
import com.shamildev.retro.cache.realm.models.TMDbConfigurationRealm;
import com.shamildev.retro.cache.realm.models.UserRealm;
import com.shamildev.retro.cache.realm.models.WatchListRealm;
import com.shamildev.retro.domain.core.DomainObject;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.util.Constants;
import com.shamildev.retro.domain.util.DateUtil;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import dagger.Reusable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


/**
 * An implementation of {@link RealmCacheRepository}.
 */
@Reusable
public final class RealmCacheRepository implements CacheRepository {





    private final Provider<Realm> realmProvider;
    private final RealmMapperHolder realmMapperHolder;


    @Inject
    RealmCacheRepository(Provider<Realm> realmProvider, RealmMapperHolder realmMapperHolder) {
        Log.d("RealmCacheRepository", "start");

        this.realmProvider = realmProvider;
        this.realmMapperHolder = realmMapperHolder;
    }


    @Override
    public Flowable<User> fetchUser() {
        return Flowable.create(e -> {
            Realm realm = realmProvider.get();
            RealmQuery<UserRealm> query = realm.where(UserRealm.class);
            RealmResults<UserRealm> result = query.findAll();
            if (result.size() == 0) {
                e.onComplete();
            }else {
                UserRealm realmObj  = result.get(0);
                User map = realmMapperHolder.userRealmMapper().map(realmObj);
                e.onNext(map);
                e.onComplete();
            }
            realm.close();
        }, BackpressureStrategy.LATEST);
    }

    @Override
    public Completable saveUser(DomainObject item) {

        User user = (User) item;
        final UserRealm realmObj = realmMapperHolder.userRealmMapper().map(user);
        realmObj.setLast_update(DateUtil.NOW());
        return Completable.create(e -> {
            try (Realm realm = realmProvider.get()) {
                realm.executeTransaction(realm1 -> {

                    realm1.copyToRealmOrUpdate(realmObj);
                    e.onComplete();
                });
            } //autoclose

        });


    }


    @Override
    public void saveObj(DomainObject object) {

        Log.d("saveObj",">>>> "+object.toString());
        Realm realm = this.realmProvider.get();



            realm.beginTransaction();
            ConfigurationRealm realmObj = new ConfigurationRealm();
            realmObj.setUser("Schamil");
            realm.copyToRealmOrUpdate(realmObj);
            realm.commitTransaction();
            realm.close();






    }


    @Override
    public Completable saveItemWatchList(DomainObject item) {
        WatchListRealm realmObj = null;
        if(item instanceof Movie){

            Movie movie = (Movie) item;
            realmObj = realmMapperHolder.movieRealmMapper().map(movie);
            realmObj.setLast_update(DateUtil.NOW());
        }
        if(item instanceof TVShow){

            TVShow show = (TVShow) item;
            realmObj = realmMapperHolder.tvshowRealmMapper().map(show);
            realmObj.setLast_update(DateUtil.NOW());
        }


        final WatchListRealm finalRealmObj1 = realmObj;
        return Completable.create(e -> {

            try (Realm realm = realmProvider.get()) {

                realm.executeTransaction(realm1 -> {
                    Log.d("TAG", ">>>" + realm1.toString());

                    realm1.copyToRealmOrUpdate(finalRealmObj1);
                    e.onComplete();
                });
            } //autoclose

        });
    }

    @Override
    public Flowable<List<DomainObject>> fetchWatchList() {

        return Flowable.create(e -> {
            try (Realm realm = realmProvider.get()) {
                    realm.executeTransaction(realm1 -> {
                    RealmResults<WatchListRealm> result = realm1.where(WatchListRealm.class)
                            .findAll();
                    if (result.size() == 0) {
                        List<DomainObject> list1 = Observable.fromIterable(result)
                                .cast(DomainObject.class)
                                .toList()
                                .blockingGet();

                        e.onNext(list1);
                        e.onComplete();
                    }else {
                        List<DomainObject> list = Observable.fromIterable(result)
                                .map(movieRealm -> {
                                    if (movieRealm.getMedia_type().equals(Constants.MEDIA_TYPE.TV.toString())) {
                                        return realmMapperHolder.tvshowRealmMapper().map(movieRealm);
                                    }
                                        return realmMapperHolder.movieRealmMapper().map(movieRealm);
                                })
                                .cast(DomainObject.class)
                                .toList()
                                .blockingGet();
                        e.onNext(list);
                        e.onComplete();
                    }

                });
            } //autoclose

        }, BackpressureStrategy.LATEST);
    }




    @Override
    public Completable saveGenre(final Genre genreModel) {
        final GenreRealm realmObj = realmMapperHolder.genreRealmMapper().map(genreModel);
                         realmObj.setLast_update(DateUtil.NOW());
        return Completable.create(e -> {
            try (Realm realm = realmProvider.get()) {
                realm.executeTransaction(realm1 -> {
                    realm1.insertOrUpdate(realmObj);
                    e.onComplete();
                });
            }
        });
    }



    @Override
    public Flowable<List<Genre>> fetchGenre(final Constants.MEDIA_TYPE mediaType, final String language) {

        return Flowable.create(e -> {
            try (Realm realm = realmProvider.get()) {
                realm.executeTransaction(realm1 -> {
                    RealmResults<GenreRealm> result = realm1.where(GenreRealm.class)
                            .equalTo(GenreRealm.FIELD_LANGUAGE,language)
                            .equalTo(GenreRealm.FIELD_MEDIATYPE,mediaType.toString())
                            .findAll();
                    if (result.size() == 0) {
                        e.onComplete();
                    }else {
                        List<Genre> genreResult = Observable
                                .fromIterable(result)
                                .map(realmMapperHolder.genreRealmMapper()::map)
                                .toList()
                                .blockingGet();
                        e.onNext(genreResult);
                        e.onComplete();
                    }

                });
            } //autoclose
        }, BackpressureStrategy.LATEST);
    }





    @Override
    public Flowable<Configuration> fetchConfiguration() {

        return Flowable.create(e -> {
            Realm realm = realmProvider.get();
            RealmQuery<TMDbConfigurationRealm> query = realm.where(TMDbConfigurationRealm.class);
            RealmResults<TMDbConfigurationRealm> result = query.findAll();
            if (result.size() == 0) {
                e.onComplete();
            }else {
                TMDbConfigurationRealm realmObj  = result.get(0);
                Configuration map = realmMapperHolder.configurationRealmMapper().map(realmObj);
                e.onNext(map);
                e.onComplete();

            }
            realm.close();
        }, BackpressureStrategy.LATEST);

    }

    @Override
    public Completable saveTMDbConfiguration(Configuration configuration) {
        TMDbConfigurationRealm realmObj = realmMapperHolder.configurationRealmMapper().map(configuration);
        realmObj.setLast_update(DateUtil.NOW());
        return Completable.create(e -> {
            Realm realm = realmProvider.get();
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(realmObj);
            realm.commitTransaction();
            e.onComplete();
            realm.close();

        });
    }

    @Override
    public Completable insertTMDbConfiguration(Configuration configuration,Long date) {
        TMDbConfigurationRealm realmObj = realmMapperHolder.configurationRealmMapper().map(configuration);
        realmObj.setLast_update(date);
        return Completable.create(e -> {
            Realm realm = realmProvider.get();
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(realmObj);
            realm.commitTransaction();
            e.onComplete();
            realm.close();

        });
    }

}
