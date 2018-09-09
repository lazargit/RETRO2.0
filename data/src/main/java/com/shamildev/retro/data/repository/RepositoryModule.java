package com.shamildev.retro.data.repository;


import com.shamildev.retro.data.entity.EntityModule;
import com.shamildev.retro.data.net.NetModule;
import com.shamildev.retro.domain.repository.RemoteRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

/**
 * Provides repository dependencies.
 */
@Module(includes = {
        EntityModule.class,
        NetModule.class


})
public abstract class RepositoryModule {

    @Binds
    @Reusable
    abstract RemoteRepository remoteRepository(TMDBRepository repository);

}
