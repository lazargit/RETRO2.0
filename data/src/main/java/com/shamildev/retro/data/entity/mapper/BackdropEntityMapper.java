package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.BackdropEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.domain.models.ImageModel;
import com.shamildev.retro.domain.models.Movie;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link BackdropEntity} to {@link ImageModel} .
 */

@Reusable
public final class BackdropEntityMapper implements EntityMapper<BackdropEntity, ImageModel> {


    @Inject
    BackdropEntityMapper() {}

    @Override
    public ImageModel map(BackdropEntity entity) throws MappingError {

        try {
            return ImageModel.builder()
                    .aspectRatio(entity.getAspectRatio())
                    .filePath(entity.getFilePath())
                    .voteAverage(entity.getVoteAverage())
                    .voteCount(entity.getVoteCount())
                    .iso6391(entity.getIso6391())
                    .height(entity.getHeight())
                    .width(entity.getWidth())
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());
        }


    }

    @Override
    public BackdropEntity map(ImageModel imageModel) {
        return null;
    }
}
