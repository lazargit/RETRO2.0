package com.shamildev.retro.data;

import com.shamildev.retro.cache.core.CacheModule;
import com.shamildev.retro.cache.realm.RealmModule;
import com.shamildev.retro.cache.realm.mapper.RealmMapperModule;
import com.shamildev.retro.data.repository.RepositoryModule;
import com.shamildev.retro.firebase.core.FirebaseModule;

import dagger.Module;


/**
 * Provides data dependencies.
 */
@Module(includes =
        {RepositoryModule.class,
         CacheModule.class,
         FirebaseModule.class})
public class DataModule {


}
