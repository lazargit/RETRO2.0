package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.CastEntity;
import com.shamildev.retro.domain.models.Cast;
import com.shamildev.retro.domain.models.Person;

import javax.inject.Inject;

import dagger.Reusable;


/**
 * Maps {@link CastEntity} to {@link Cast} .
 */
@Reusable
public final class CastEntityMapper implements EntityMapper<CastEntity, Cast> {


    @Inject
    CastEntityMapper() {

    }

    @Override
    public Cast map(CastEntity entity) throws MappingError {

        try {
            Person person = Person.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .gender(entity.getGender())
                    .profilePath(entity.getProfilePath())
                    .build();
            return Cast.builder()
                    .person(person)
                    .castId(entity.getCastId())
                    .creditId(entity.getCreditId())
                    .character(entity.getCharacter())
                    .order(entity.getOrder())
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());
        }
    }

    @Override
    public CastEntity map(Cast model) {
        return null;
    }
}
