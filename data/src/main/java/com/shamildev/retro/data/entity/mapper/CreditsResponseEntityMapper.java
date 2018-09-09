package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.response.CreditsResponse;
import com.shamildev.retro.domain.models.Cast;
import com.shamildev.retro.domain.models.Credits;
import com.shamildev.retro.domain.models.Crew;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Flowable;
import io.reactivex.Observable;


/**
 * Maps {@link CreditsResponse} to {@link Credits} .
 */
@Reusable
public final class CreditsResponseEntityMapper implements EntityMapper<CreditsResponse, Credits> {


    private final CastEntityMapper castEntityMapper;
    private final CrewEntityMapper crewEntityMapper;

    @Inject
    CreditsResponseEntityMapper(CastEntityMapper castEntityMapper, CrewEntityMapper crewEntityMapper) {
        this.castEntityMapper = castEntityMapper;
        this.crewEntityMapper = crewEntityMapper;
    }

    @Override
    public Credits map(CreditsResponse creditsResponse) throws MappingError {


        try {

            List<Cast> castList = Observable.just(creditsResponse.getCast())
                    .map(posterEntities -> Flowable.fromIterable(posterEntities)
                            .map(castEntityMapper::map)
                            .toList()
                            .blockingGet())
                    .blockingSingle();
            List<Crew> crewList = Observable.just(creditsResponse.getCrew())
                    .map(crewEntities -> Flowable.fromIterable(crewEntities)
                            .map(crewEntityMapper::map)
                            .toList()
                            .blockingGet())
                    .blockingSingle();
           return Credits.create(creditsResponse.getId(), castList, crewList);
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());

        }

    }

    @Override
    public CreditsResponse map(Credits images) {
        return null;
    }
}
