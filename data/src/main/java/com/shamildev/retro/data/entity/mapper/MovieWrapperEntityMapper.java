package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.domain.models.MovieWrapper;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link ResponseEntity} to {@link MovieWrapper} .
 */
@Reusable
public final class MovieWrapperEntityMapper implements EntityMapper<ResponseEntity, MovieWrapper> {


    private final EntityListMapper entityListMapper;
    private final MovieEntityMapper movieEntityMapper;

    @Inject
    MovieWrapperEntityMapper(EntityListMapper entityListMapper,
                             MovieEntityMapper movieEntityMapper) {

        this.entityListMapper = entityListMapper;
        this.movieEntityMapper = movieEntityMapper;
    }

    @Override
    public MovieWrapper map(ResponseEntity entity) throws MappingError {

        try {
            return MovieWrapper.builder()
                    .page(entity.getPage())
                    .totalPages(entity.getTotalPages())
                    .totalResults(entity.getTotalResults())
                    .results(entityListMapper.mapToV(movieEntityMapper, entity.getResults()))
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());
        }


    }

    @Override
    public ResponseEntity map(MovieWrapper entity) {
        return null;


    }
}
