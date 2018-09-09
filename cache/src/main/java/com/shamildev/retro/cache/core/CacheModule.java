package com.shamildev.retro.cache.core;


import com.shamildev.retro.cache.realm.RealmModule;
import com.shamildev.retro.cache.realm.mapper.RealmMapperModule;
import com.shamildev.retro.cache.repository.RealmCacheRepository;
import com.shamildev.retro.domain.repository.CacheRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;



@Module( includes = {
        RealmModule.class,RealmMapperModule.class
})
public abstract class CacheModule {

    @Binds
    @Reusable
    abstract CacheRepository cacheRepository(RealmCacheRepository repository);




}

