package com.shamildev.retro.cache.core;


import com.shamildev.retro.cache.local.LocalCacheRepository;

import com.shamildev.retro.cache.local.load.LocalLoaderModule;
import com.shamildev.retro.cache.realm.RealmModule;
import com.shamildev.retro.cache.realm.mapper.RealmMapperModule;
import com.shamildev.retro.cache.repository.RealmCacheRepository;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.LocalRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;



@Module( includes = {
        RealmModule.class,
        RealmMapperModule.class,
        LocalLoaderModule.class

})
public abstract class CacheModule {

    @Binds
    @Reusable
    abstract CacheRepository cacheRepository(RealmCacheRepository repository);

    @Binds
    @Reusable
    abstract LocalRepository localRepository(LocalCacheRepository repository);




}

