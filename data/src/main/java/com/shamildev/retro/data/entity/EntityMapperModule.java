package com.shamildev.retro.data.entity;


import com.shamildev.retro.data.entity.mapper.BackdropEntityMapper;
import com.shamildev.retro.data.entity.mapper.CastEntityMapper;
import com.shamildev.retro.data.entity.mapper.ConfigurationEntityMapper;
import com.shamildev.retro.data.entity.mapper.CreditsResponseEntityMapper;
import com.shamildev.retro.data.entity.mapper.CrewEntityMapper;
import com.shamildev.retro.data.entity.mapper.GenreEntityMapper;
import com.shamildev.retro.data.entity.mapper.ImagesResponseEntityMapper;
import com.shamildev.retro.data.entity.mapper.MovieDetailsEntityMapper;
import com.shamildev.retro.data.entity.mapper.MovieEntityMapper;
import com.shamildev.retro.data.entity.mapper.MovieWrapperEntityMapper;
import com.shamildev.retro.data.entity.mapper.NetworkEntityMapper;
import com.shamildev.retro.data.entity.mapper.PersonEntityMapper;
import com.shamildev.retro.data.entity.mapper.PersonResultEntityMapper;
import com.shamildev.retro.data.entity.mapper.PosterEntityMapper;
import com.shamildev.retro.data.entity.mapper.ResultEntityMapper;
import com.shamildev.retro.data.entity.mapper.SeasonEntityMapper;
import com.shamildev.retro.data.entity.mapper.TVShowDetailsEntityMapper;
import com.shamildev.retro.data.entity.mapper.TVShowEntityMapper;

import com.shamildev.retro.data.entity.tmdb.BackdropEntity;
import com.shamildev.retro.data.entity.tmdb.CastEntity;
import com.shamildev.retro.data.entity.tmdb.ConfigurationResponseEntity;
import com.shamildev.retro.data.entity.tmdb.CrewEntity;
import com.shamildev.retro.data.entity.tmdb.GenreEntity;
import com.shamildev.retro.data.entity.tmdb.NetworkEntity;
import com.shamildev.retro.data.entity.tmdb.PersonEntity;
import com.shamildev.retro.data.entity.tmdb.PosterEntity;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.data.entity.tmdb.SeasonEntity;
import com.shamildev.retro.data.entity.tmdb.response.CreditsResponse;
import com.shamildev.retro.data.entity.tmdb.response.ImagesResponse;
import com.shamildev.retro.data.entity.tmdb.response.MovieResponse;
import com.shamildev.retro.data.entity.tmdb.response.TVShowResponse;

import com.shamildev.retro.domain.models.Cast;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Credits;
import com.shamildev.retro.domain.models.Crew;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.ImageModel;
import com.shamildev.retro.domain.models.Images;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.Person;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.models.Season;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.domain.models.TVShowNetwork;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

/**
 * Provides entity mapper dependencies.
 */
@Module
public abstract class EntityMapperModule {

    @Binds
    @Reusable
    abstract EntityMapper<Result, Movie> movieEntityMapper(MovieEntityMapper movieEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<MovieResponse, Movie> movieDetailsEntityMapper(MovieDetailsEntityMapper movieDetailsEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<ResponseEntity, MovieWrapper> movieEntityMapperMapper(MovieWrapperEntityMapper movieWrapperEntityMapper);


    @Binds
    @Reusable
    abstract EntityMapper<ResponseEntity, ResultWrapper> resultEntityMapperMapper(ResultEntityMapper resultEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<ConfigurationResponseEntity, Configuration> configurationEntityMapper(ConfigurationEntityMapper configurationEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<GenreEntity, Genre> genreEntityMapper(GenreEntityMapper genreEntityMapper);


    @Binds
    @Reusable
    abstract EntityMapper<ImagesResponse, Images> imagesResponseEntityMapper(ImagesResponseEntityMapper imagesResponseEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<PosterEntity, ImageModel> posterEntityMapper(PosterEntityMapper posterEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<BackdropEntity, ImageModel> backdropEntityMapper(BackdropEntityMapper backdropEntityMapper);


    @Binds
    @Reusable
    abstract EntityMapper<CreditsResponse, Credits> creditsResponseEntityMapper(CreditsResponseEntityMapper creditsResponseEntityMapper);


    @Binds
    @Reusable
    abstract EntityMapper<CastEntity, Cast> castEntityMapper(CastEntityMapper castEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<CrewEntity, Crew> crewEntityMapper(CrewEntityMapper crewEntityMapper);


    @Binds
    @Reusable
    abstract EntityMapper<Result, TVShow> tvshowResultEntityMapper(TVShowEntityMapper tvShowEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<TVShowResponse, TVShow> tvshowEntityMapper(TVShowDetailsEntityMapper tvShowDetailsEntityMapper);


    @Binds
    @Reusable
    abstract EntityMapper<NetworkEntity, TVShowNetwork> tvshowNetworkEntityMapper(NetworkEntityMapper networkEntityMapper);


    @Binds
    @Reusable
    abstract EntityMapper<SeasonEntity, Season> seasonEntityMapper(SeasonEntityMapper seasonEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<PersonEntity, Person> personEntityMapper(PersonEntityMapper personEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<Result, Person> personResultEntityMapper(PersonResultEntityMapper personResultEntityMapper);


}
