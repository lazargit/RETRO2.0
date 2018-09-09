package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.domain.core.DomainObject;
import com.shamildev.retro.domain.models.ResultWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link ResponseEntity} to {@link ResultWrapper}.
 */
@Reusable
public final class ResultEntityMapper implements EntityMapper<ResponseEntity, ResultWrapper> {


    private final EntityListMapper entityListMapper;
    private final MovieEntityMapper movieEntityMapper;
    private final TVShowEntityMapper tvShowEntityMapper;
    private final PersonResultEntityMapper personResultEntityMapper;

    @Inject
    ResultEntityMapper(EntityListMapper entityListMapper,
                       MovieEntityMapper movieEntityMapper,
                       TVShowEntityMapper tvShowEntityMapper,
                       PersonResultEntityMapper personResultEntityMapper) {

        this.entityListMapper = entityListMapper;
        this.movieEntityMapper = movieEntityMapper;
        this.tvShowEntityMapper = tvShowEntityMapper;
        this.personResultEntityMapper = personResultEntityMapper;
    }


    @Override
    public ResultWrapper map(ResponseEntity entity) throws MappingError {

        try {
            List<DomainObject> list = new ArrayList<>();
            for (Result item : entity.getResults()) {
                list.add(MediaTypeFactory
                        .create(item, movieEntityMapper, tvShowEntityMapper, personResultEntityMapper));
            }
           return ResultWrapper.builder()
                    .page(entity.getPage())
                    .totalPages(entity.getTotalPages())
                    .totalResults(entity.getTotalResults())
                    .results(list)
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());

        }

    }

    @Override
    public ResponseEntity map(ResultWrapper entity) {

        return null;


    }
}
