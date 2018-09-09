/*
 * Copyright 2017 Vandolf Estrellado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.PosterEntity;
import com.shamildev.retro.domain.models.ImageModel;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link PosterEntity} to {@link ImageModel} .
 */
@Reusable
public final class PosterEntityMapper implements EntityMapper<PosterEntity, ImageModel> {




    @Inject
    PosterEntityMapper() {

    }

    @Override
    public ImageModel map(PosterEntity entity) throws MappingError {

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
    public PosterEntity map(ImageModel imageModel) {
        return null;
    }
}
