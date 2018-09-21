package com.shamildev.retro.networkmanager;

import android.app.Application;


import com.shamildev.retro.data.net.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shamil Lazar on 21.09.2018.
 */
@Module
public class NetworkMangerModule {

    @Provides
    @Singleton
    NetworkManager providesNetworkManager(Application application) {
        return new NetworkManagerImpl(application);
    }


}
