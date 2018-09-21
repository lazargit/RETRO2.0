package com.shamildev.retro.domain.models;


import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.core.DomainObject;

import java.io.Serializable;
import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by Shamil Lazar.
 */

@AutoValue
public abstract class Images implements DomainObject,Serializable {


    public abstract int id();
    @Nullable
    public abstract List<ImageModel> posters();
    @Nullable
    public abstract List<ImageModel> backdrops();

    public static Images create(int id, List<ImageModel> posters, List<ImageModel> backdrops) {
        return builder()
                .id(id)
                .posters(posters)
                .backdrops(backdrops)
                .build();
    }


    public static Builder builder() {
        return new AutoValue_Images.Builder();
    }




    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(int id);

        public abstract Builder posters(List<ImageModel> posters);

        public abstract Builder backdrops(List<ImageModel> backdrops);

        public abstract Images build();
    }
}
