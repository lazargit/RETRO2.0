package com.shamildev.retro.data.entity.mapper;
import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.PersonEntity;
import com.shamildev.retro.domain.models.Person;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link PersonEntity} to {@link Person} .
 */
@Reusable
public final class PersonEntityMapper implements EntityMapper<PersonEntity, Person> {


    @Inject
    PersonEntityMapper() {

    }


    @Override
    public Person map(PersonEntity entity) throws MappingError {
        try {
            return Person.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .gender(entity.getGender())
                    .profilePath(entity.getProfilePath())
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());

        }


    }

    @Override
    public PersonEntity map(Person model) {
        return null;
    }
}
