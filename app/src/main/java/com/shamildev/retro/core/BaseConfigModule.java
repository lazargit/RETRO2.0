package com.shamildev.retro.core;


import com.shamildev.retro.BuildConfig;
import com.shamildev.retro.domain.core.DataConfig;

/**
 * Provides an abstract config module for other config modules to extend.
 */
public abstract class BaseConfigModule {

    /**
     * The base data config builder to be optionally modified by subclasses.
     * <p>
     * Note that we could retrieve the config values here from a resource/config file or from a
     * remote config endpoint. The values are statically defined here for simplicity and brevity.
     */
    protected static final DataConfig.Builder BASE_CONFIG_BUILDER = DataConfig.builder()
            .baseUrl("https://api.themoviedb.org/")
            .youtubeKey(BuildConfig.YOUTUBE_API_TOKEN)
            .authGrantType("client_credentials")
            .authClientId("pTnZUGJqLM_Q0pQt-jsCXw") // Replace this with your own client id
            .authClientSecret("zRs7F1e2B3Dc4OBNsp7yF0Jfh5eksGRXroaLE59JCxxwZUDO" + "ppzjn6bQaVPB3bop") // Replace this with your own client secret
            .cacheRootDir("business-search")
            .cacheDir("data-cache")
            .cacheMaxSizeMb(10)
            .offlineCacheTimeDays(3)
            .networkCacheTimeSeconds(60);
}
