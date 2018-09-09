package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.TVShow;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link Result} to {@link TVShow} .
 */
@Reusable
public final class TVShowEntityMapper implements EntityMapper<Result, TVShow> {




    @Inject
    TVShowEntityMapper() {

        //  this.entityListMapper = entityListMapper;
    }

    @Override
    public TVShow map(Result entity) throws MappingError {
        try {
            return TVShow.create(entity.getId(),
                    entity.getName(),
                    entity.getOriginalName(),
                    entity.getOverview(),
                    entity.getPosterPath(),
                    entity.getBackdropPath(),
                    entity.getOriginalLanguage(),
                    entity.getPopularity(),
                    entity.getVoteCount(),
                    entity.getVoteAverage(),
                    entity.getFirstAirDate(),
                    entity.getOriginCountry(),
                    entity.getGenreIds());
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());
        }
    }

    @Override
    public Result map(TVShow entity) {

        return null;

    }
}
