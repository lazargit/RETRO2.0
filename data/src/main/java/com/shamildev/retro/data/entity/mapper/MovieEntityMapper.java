package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.domain.models.Movie;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link Result} to {@link Movie} .
 */
@Reusable
public final class MovieEntityMapper implements EntityMapper<Result, Movie> {


    @Inject
    MovieEntityMapper() {
    }

    @Override
    public Movie map(Result entity) throws MappingError {

        try {
            return Movie.create(entity.getId(),
                    entity.getTitle(),
                    entity.getPosterPath(),
                    entity.getAdult(),
                    entity.getOverview(),
                    entity.getReleaseDate(),
                    entity.getGenreIds(),
                    entity.getOriginalTitle(),
                    entity.getOriginalLanguage(),
                    entity.getBackdropPath(),
                    entity.getPopularity(),
                    entity.getVoteCount(),
                    entity.getVideo(),
                    entity.getVoteAverage());
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());

        }

    }

    @Override
    public Result map(Movie entity) {

        return null;

    }
}
