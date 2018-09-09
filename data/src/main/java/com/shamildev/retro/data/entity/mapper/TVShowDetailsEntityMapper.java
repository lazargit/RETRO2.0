package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.data.entity.tmdb.response.TVShowResponse;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.domain.models.TVShowNetwork;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link TVShowResponse} to {@link TVShow} .
 */
@Reusable
public final class TVShowDetailsEntityMapper implements EntityMapper<TVShowResponse, TVShow> {


    private final EntityListMapper entityListMapper;
    private final GenreEntityMapper genreEntityMapper;
    private final ImagesResponseEntityMapper imagesResponseEntityMapper;
    private final SeasonEntityMapper seasonEntityMapper;
    private final NetworkEntityMapper networkEntityMapper;
    private final PersonEntityMapper personEntityMapper;
    private final ProductionCompanyEntityMapper productionCompanyEntityMapper;



    @Inject
    TVShowDetailsEntityMapper(GenreEntityMapper genreEntityMapper,
                              SeasonEntityMapper seasonEntityMapper,
                              EntityListMapper entityListMapper,
                              ImagesResponseEntityMapper imagesResponseEntityMapper,
                              NetworkEntityMapper networkEntityMapper,
                              PersonEntityMapper personEntityMapper,
                              ProductionCompanyEntityMapper productionCompanyEntityMapper) {

    this.genreEntityMapper = genreEntityMapper;
    this.entityListMapper = entityListMapper;
    this.imagesResponseEntityMapper = imagesResponseEntityMapper;
    this.seasonEntityMapper = seasonEntityMapper;
    this.networkEntityMapper = networkEntityMapper;
    this.personEntityMapper = personEntityMapper;
    this.productionCompanyEntityMapper = productionCompanyEntityMapper;
    }

    @Override
    public TVShow map(TVShowResponse entity) throws MappingError {

        try {
            return  TVShow.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .originalName(entity.getOriginalName())
                    .overview(entity.getOverview())
                    .backdropPath(entity.getBackdropPath())
                    .posterPath(entity.getPosterPath())
                    .languages(entity.getLanguages())
                    .originalLanguage(entity.getOriginalLanguage())
                    .originCountry(entity.getOriginCountry())
                    .inProduction(entity.getInProduction())
                    .firstAirDate(entity.getFirstAirDate())
                    .lastAirDate(entity.getLastAirDate())
                    .homepage(entity.getHomepage())
                    .status(entity.getStatus())
                    .numberOfEpisodes(entity.getNumberOfEpisodes())
                    .numberOfSeasons(entity.getNumberOfSeasons())
                    .popularity(entity.getPopularity())
                    .voteCount(entity.getVoteCount())
                    .voteAverage(entity.getVoteAverage())
                    .type(entity.getType())
                    .episodeRunTime(entity.getEpisodeRunTime())
                    .productionCompanies(entityListMapper.mapToV(productionCompanyEntityMapper, entity.getProductionCompanies()))
                    .genres(entityListMapper.mapToV(genreEntityMapper, entity.getGenres()))
                    .seasons(entityListMapper.mapToV(seasonEntityMapper, entity.getSeasons()))
                    .networks(entityListMapper.mapToV(networkEntityMapper,entity.getNetworks()))
                    .createdBy(entityListMapper.mapToV(personEntityMapper, entity.getCreatedBy()))
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());
        }
    }

    @Override
    public TVShowResponse map(TVShow entity) {

        return null;

    }
}
