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
import com.shamildev.retro.data.entity.tmdb.GenreEntity;

import com.shamildev.retro.domain.models.Cast;
import com.shamildev.retro.domain.models.Credits;
import com.shamildev.retro.domain.models.Crew;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Maps {@link GenreEntity} to {@link Genre} .
 */
@Reusable
public final class GenreEntityMapper implements EntityMapper<GenreEntity, Genre> {




    @Inject
    GenreEntityMapper() { }

    @Override
    public Genre map(GenreEntity entity) throws MappingError {

        try {
            return Genre.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();
        } catch (NullPointerException e) {
            throw MappingError.getError(e.getMessage(), this.getClass());

        }

    }

    @Override
    public GenreEntity map(Genre entity) {

        return null;

    }
}
