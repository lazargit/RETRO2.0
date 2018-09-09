package com.shamildev.retro.data.entity.mapper;

import com.shamildev.retro.data.entity.EntityMapper;
import com.shamildev.retro.data.entity.tmdb.PosterEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.data.entity.tmdb.response.ImagesResponse;
import com.shamildev.retro.domain.models.ImageModel;
import com.shamildev.retro.domain.models.Images;
import com.shamildev.retro.domain.models.Movie;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Maps {@link ImagesResponse} to {@link Images} .
 */
@Reusable
public final class ImagesResponseEntityMapper implements EntityMapper<ImagesResponse, Images> {


    private final PosterEntityMapper posterEntityMapper;
    private final BackdropEntityMapper backdropEntityMapper;

    @Inject
    ImagesResponseEntityMapper(PosterEntityMapper posterEntityMapper, BackdropEntityMapper backdropEntity) {
         this.posterEntityMapper = posterEntityMapper;
         this.backdropEntityMapper = backdropEntity;


    }

    @Override
    public Images map(ImagesResponse imagesResponse) {

        List<ImageModel> imageModels = Observable.just(imagesResponse.getPosters())
                .map(posterEntities -> Flowable.fromIterable(posterEntities)
                        .map(posterEntityMapper::map)
                        .toList()
                        .blockingGet())
                .blockingSingle();

        List<ImageModel> imageModelBackdrops = Observable.just(imagesResponse.getBackdrops())
                .map(posterEntities -> Flowable.fromIterable(posterEntities)
                        .map(backdropEntityMapper::map)
                        .toList()
                        .blockingGet())
                .blockingSingle();


        return   Images.builder()
                .id(imagesResponse.getId())
                .posters(imageModels)
                .backdrops(imageModelBackdrops)
                .build();
    }

    @Override
    public ImagesResponse map(Images images) {
        return null;
    }
}
