

package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.SeasonEntity;
import com.shamildev.retro.domain.models.Season;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link SeasonEntity} to {@link Season} .
 */
@Reusable
public final class SeasonEntityMapper implements EntityMapper<SeasonEntity, Season> {


    @Inject
    SeasonEntityMapper(){}

    @Override
    public Season map(SeasonEntity entity) throws MappingError {
        try {
            return Season.builder()
                    .airDate(entity.getAirDate())
                    .episodeCount(entity.getEpisodeCount())
                    .id(entity.getId())
                    .posterPath(entity.getPosterPath())
                    .seasonNumber(entity.getSeasonNumber())
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());
        }
    }

    @Override
    public SeasonEntity map(Season model) {
        return null;
    }
}
