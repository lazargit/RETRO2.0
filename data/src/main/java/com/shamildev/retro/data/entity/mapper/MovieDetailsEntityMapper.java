
package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.GenreEntity;
import com.shamildev.retro.data.entity.tmdb.response.MovieResponse;
import com.shamildev.retro.domain.models.Movie;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link MovieResponse} to {@link Movie} .
 */
@Reusable
public final class MovieDetailsEntityMapper implements EntityMapper<MovieResponse, Movie> {


    private final EntityListMapper entityListMapper;
    private final GenreEntityMapper genreEntityMapper;
    private final ImagesResponseEntityMapper imagesResponseEntityMapper;


    @Inject
    MovieDetailsEntityMapper(GenreEntityMapper genreEntityMapper,
                             EntityListMapper entityListMapper,
                             ImagesResponseEntityMapper imagesResponseEntityMapper) {

        this.genreEntityMapper = genreEntityMapper;
        this.entityListMapper = entityListMapper;
        this.imagesResponseEntityMapper = imagesResponseEntityMapper;
    }

    @Override
    public Movie map(MovieResponse entity) throws MappingError {
        List<GenreEntity> genres = entity.getGenres();


        try {
            return Movie.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .overview(entity.getOverview())
                    .originalTitle(entity.getTitle())
                    .originalLanguage(entity.getOriginalLanguage())
                    .posterPath(entity.getPosterPath())
                    .backdropPath(entity.getBackdropPath())
                    .adult(entity.getAdult())
                    .video(entity.getVideo())
                    .releaseDate(entity.getReleaseDate())
                    .popularity(entity.getPopularity())
                    .voteAverage(entity.getVoteAverage())
                    .budget(entity.getBudget())
                    .revenue(entity.getRevenue())
                    .status(entity.getStatus())
                    .tagline(entity.getTagline())
                    .homepage(entity.getHomepage())
                    .imdbId(entity.getImdbId())
                    .voteCount(entity.getVoteCount())
                    .genres(entityListMapper.mapToV(genreEntityMapper, entity.getGenres()))
                    //  .productionCountries(entity.getProductionCountries())
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());
        }


    }

    @Override
    public MovieResponse map(Movie entity) {

        return null;

    }
}
