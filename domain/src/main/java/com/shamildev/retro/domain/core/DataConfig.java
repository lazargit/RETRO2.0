
package com.shamildev.retro.domain.core;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;

import java.util.List;

import io.reactivex.annotations.Nullable;


/**
 * Contains configuration values used throughout the data module.
 */
@AutoValue
public abstract class DataConfig {

    /**
     * @return a new {@link Builder}
     */
    public static Builder builder() {

        return new AutoValue_DataConfig.Builder();
    }

    public abstract String baseUrl();

    public abstract String youtubeKey();

    public abstract String authGrantType();

    public abstract String authClientId();

    public abstract String authClientSecret();

    public abstract String cacheRootDir();

    public abstract String cacheDir();

    public abstract int cacheMaxSizeMb();

    public abstract int offlineCacheTimeDays();

    public abstract int networkCacheTimeSeconds();

    public abstract boolean debug();

    public abstract String language();

    public abstract String country();


    public abstract int maxCacheTime();

    @Nullable
    public abstract String facebookApiToken();

    @Nullable
    public abstract Long lastUpdate();

    @Nullable
    public abstract Configuration configurations();
    @Nullable
    public abstract List<Genre> genres();

    public DataConfig setBaseUrl(String arg) {
        Builder builder = getBuilder();
        builder.baseUrl(arg);
        return builder.build();
    }
    public  DataConfig setLanguage(String arg) {
        Builder builder = getBuilder();
        builder.language(arg);
        return builder.build();
    }

    public DataConfig addConfiguration(Configuration configuration) {
        Builder builder = getBuilder();
        builder.configurations(configuration);
        return builder.build();
    }
    public  DataConfig addGenres(List<Genre> genres) {
        Builder builder = getBuilder();
        builder.genres(genres);
        return builder.build();
    }



    private Builder getBuilder() {
        return builder()
                    .baseUrl(this.baseUrl())
                    .authGrantType(this.authGrantType())
                    .authClientId(this.authClientId())
                    .authClientSecret(this.authClientSecret())
                    .cacheRootDir(this.cacheRootDir())
                    .cacheDir(this.cacheDir())
                    .cacheMaxSizeMb(this.cacheMaxSizeMb())
                    .offlineCacheTimeDays(this.offlineCacheTimeDays())
                    .networkCacheTimeSeconds(this.networkCacheTimeSeconds())
                    .debug(this.debug())
                    .language(this.language())
                    .country(this.country())
                    .maxCacheTime(this.maxCacheTime())
                    .lastUpdate(this.lastUpdate())
                    .configurations(this.configurations())
                    .genres(this.genres());
    }

    public static DataConfig create(String baseUrl, String youtubeKey, String authGrantType, String authClientId, String authClientSecret, String cacheRootDir, String cacheDir, int cacheMaxSizeMb, int offlineCacheTimeDays, int networkCacheTimeSeconds, boolean debug, String language, String country, int maxCacheTime, String facebookApiToken, Long lastUpdate, Configuration configurations, List<Genre> genres) {
        return builder()
                .baseUrl(baseUrl)
                .youtubeKey(youtubeKey)
                .authGrantType(authGrantType)
                .authClientId(authClientId)
                .authClientSecret(authClientSecret)
                .cacheRootDir(cacheRootDir)
                .cacheDir(cacheDir)
                .cacheMaxSizeMb(cacheMaxSizeMb)
                .offlineCacheTimeDays(offlineCacheTimeDays)
                .networkCacheTimeSeconds(networkCacheTimeSeconds)
                .debug(debug)
                .language(language)
                .country(country)
                .maxCacheTime(maxCacheTime)
                .facebookApiToken(facebookApiToken)
                .lastUpdate(lastUpdate)
                .configurations(configurations)
                .genres(genres)
                .build();
    }


    public DataConfig() {
    }

    /**
     * Builder used to create instances of {@link DataConfig}
     */

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder baseUrl(String baseUrl);

        public abstract Builder authGrantType(String authGrantType);

        public abstract Builder authClientId(String authClientId);

        public abstract Builder authClientSecret(String authClientSecret);

        public abstract Builder cacheRootDir(String cacheRootDir);

        public abstract Builder cacheDir(String cacheDir);

        public abstract Builder cacheMaxSizeMb(int cacheMaxSizeMb);

        public abstract Builder offlineCacheTimeDays(int offlineCacheTimeDays);

        public abstract Builder networkCacheTimeSeconds(int networkCacheTimeSeconds);

        public abstract Builder debug(boolean debug);

        public abstract Builder language(String language);

        public abstract Builder country(String country);

        public abstract Builder configurations(Configuration configurations);

        public abstract Builder genres(List<Genre> genres);

        public abstract Builder lastUpdate(Long lastUpdate);

        public abstract Builder maxCacheTime(int maxCacheTime);

        public abstract Builder youtubeKey(String youtubeKey);

        public abstract Builder facebookApiToken(String facebookApiToken);

        public abstract DataConfig build();



    }









}