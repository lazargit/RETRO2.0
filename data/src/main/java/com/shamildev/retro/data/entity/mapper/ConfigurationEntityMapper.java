package com.shamildev.retro.data.entity.mapper;


import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.ConfigurationResponseEntity;
import com.shamildev.retro.domain.models.Configuration;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link ConfigurationResponseEntity} to {@link Configuration} .
 */
@Reusable
public final class ConfigurationEntityMapper implements EntityMapper<ConfigurationResponseEntity, Configuration> {


    @Inject
    ConfigurationEntityMapper() { }

    @Override
    public Configuration map(ConfigurationResponseEntity entity) throws MappingError {

        try {
           return Configuration.builder()
                    .changeKeys(entity.getChangeKeys())
                    .baseUrl(entity.getImages().getBaseUrl())
                    .secureBaseUrl(entity.getImages().getSecureBaseUrl())
                    .logoSizes(entity.getImages().getLogoSizes())
                    .posterSizes(entity.getImages().getPosterSizes())
                    .backdropSizes(entity.getImages().getBackdropSizes())
                    .profileSizes(entity.getImages().getProfileSizes())
                    .stillSizes(entity.getImages().getStillSizes())
                    .lastUpdate(0L)
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());

        }

    }

    @Override
    public ConfigurationResponseEntity map(Configuration entity) {

        return null;

    }


}
