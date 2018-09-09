
package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.CrewEntity;
import com.shamildev.retro.domain.models.Crew;
import com.shamildev.retro.domain.models.Person;

import javax.inject.Inject;

import dagger.Reusable;


/**
 * Maps {@link CrewEntity} to {@link Crew} .
 */
@Reusable
public final class CrewEntityMapper implements EntityMapper<CrewEntity, Crew> {


    @Inject
    CrewEntityMapper() {

    }


    @Override
    public Crew map(CrewEntity entity) throws MappingError {
        try {
            Person person = Person.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .gender(entity.getGender())
                    .profilePath(entity.getProfilePath())
                    .build();
            return Crew.builder()
                    .person(person)
                    .job(entity.getJob())
                    .creditId(entity.getCreditId())
                    .department(entity.getDepartment())
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());

        }


    }

    @Override
    public CrewEntity map(Crew model) {
        return null;
    }
}
