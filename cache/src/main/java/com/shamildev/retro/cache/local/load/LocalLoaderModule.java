package com.shamildev.retro.cache.local.load;

import android.app.Application;

import com.shamildev.retro.cache.local.json.JsonManager;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalLoaderModule {

    @Provides
    static StreamFromLocal processRetroImage(Application application) {
        return new StreamFileFromLocal(application);
    }

    @Provides
    static JsonManager jsonManager() {
        return new JsonManager();
    }

}
