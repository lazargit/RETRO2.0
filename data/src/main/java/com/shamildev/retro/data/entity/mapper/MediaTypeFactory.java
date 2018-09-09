package com.shamildev.retro.data.entity.mapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.domain.core.DomainObject;
import com.shamildev.retro.domain.util.Constants;


public final class MediaTypeFactory {


    @SuppressWarnings("unchecked")
    public static <T extends DomainObject> T create(Result item, MovieEntityMapper movieEntityMapper, TVShowEntityMapper tvShowEntityMapper, PersonResultEntityMapper personResultEntityMapper) throws MappingError {


        T mediaType = null;
        if (item.getMediaType().equals(Constants.MEDIA_TYPE.MOVIE.toString())) {
            mediaType = (T) movieEntityMapper.map(item);
        }
        if (item.getMediaType().equals(Constants.MEDIA_TYPE.TV.toString())) {
            mediaType = (T) tvShowEntityMapper.map(item);
        }
        if (item.getMediaType().equals(Constants.MEDIA_TYPE.PERSON.toString())) {
            mediaType = (T) personResultEntityMapper.map(item);

        }
        return mediaType;

    }


}
