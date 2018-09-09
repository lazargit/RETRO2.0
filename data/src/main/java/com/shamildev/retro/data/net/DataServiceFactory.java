package com.shamildev.retro.data.net;


import com.shamildev.retro.data.net.error.ErrorInterceptor;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;


/**
 * Creates concrete data services.
 */

public final class DataServiceFactory {

    private static final String HTTP_CACHE_PATH = "http-cache";


    private final Boolean isDebug;
    private final DataConfig config;
    //private final Object testobj;

    private final NetworkCacheInterceptor networkCacheInterceptor;
    private final OfflineCacheInterceptor offlineCacheInterceptor;
    private final ErrorInterceptor errorInterceptor;
    private GsonConverterFactory gsonConverterFactory;
    private MoshiConverterFactory moshiConverterFactory;
    private RxJava2CallAdapterFactory rxJava2CallAdapterFactory;
    private final Lazy<Cache> cache;


    // Lazy to only create the cache when needed


    @Inject
    DataServiceFactory(@Named("cacheDir") File cacheDir,
                       @Named("cacheSize") long cacheSize,
                       @Named("isDebug") Boolean isDebug,
                       NetworkManager networkManager,
                       DataConfig config) {

        this.gsonConverterFactory = GsonConverterFactory.create();
        this.moshiConverterFactory = MoshiConverterFactory.create();
        this.rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
        this.errorInterceptor = new ErrorInterceptor();
        this.offlineCacheInterceptor = new OfflineCacheInterceptor(config, networkManager);
        this.networkCacheInterceptor = new NetworkCacheInterceptor(config);
        this.isDebug = isDebug;
        this.config = config;
        this.cache = () -> provideCache(cacheDir, cacheSize);


    }


    public Cache provideCache(File cacheDir, long cacheSize) {
        Cache cache = null;
        try {
            cache = new Cache(new File(cacheDir.getPath(), HTTP_CACHE_PATH), cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cache;
    }

    public <T> T create(Class<T> serviceClass) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (isDebug) {
            builder.addInterceptor(httpLoggingInterceptor());

        }
        builder.addInterceptor(this.errorInterceptor);
        builder.cache(cache.get())
                .addInterceptor(this.offlineCacheInterceptor)
                .addNetworkInterceptor(this.networkCacheInterceptor);
        return new Retrofit.Builder()
                .addConverterFactory(this.gsonConverterFactory)
                .addConverterFactory(this.moshiConverterFactory)
                .addCallAdapterFactory(this.rxJava2CallAdapterFactory)
                .client(builder.build())
                .baseUrl(config.baseUrl())
                .build()
                .create(serviceClass);
    }


    private HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Timber.i(message))
                .setLevel(HttpLoggingInterceptor.Level.BODY);


    }


}
