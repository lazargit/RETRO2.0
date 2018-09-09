package com.shamildev.retro.data.entity.mapper;
import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.NetworkEntity;
import com.shamildev.retro.domain.models.TVShowNetwork;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link NetworkEntity} to {@link TVShowNetwork} .
 */
@Reusable
public final class NetworkEntityMapper implements EntityMapper<NetworkEntity, TVShowNetwork> {


    @Inject
    NetworkEntityMapper() {

    }


    @Override
    public TVShowNetwork map(NetworkEntity entity) throws MappingError {

        try {
            return TVShowNetwork.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());
        }
    }

    @Override
    public NetworkEntity map(TVShowNetwork model) {
        return null;
    }
}
