package com.shamildev.retro.domain.models;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.core.DomainObject;
import com.shamildev.retro.domain.core.MediaItem;

import java.io.Serializable;
import java.util.List;

import io.reactivex.annotations.Nullable;


/**
 * Created by Shamil Lazar.
 */

@AutoValue
public abstract class Person implements DomainObject,MediaItem,Serializable
{


    public abstract Long id();
    public abstract String name();

    public abstract Integer gender();
    @Nullable
    public abstract String profilePath();
    @Nullable
    public abstract Float popularity();


    @Nullable
    public abstract List<DomainObject> knownFor();



    public static Builder builder() {
        return new AutoValue_Person.Builder();
    }


    @Override
    public String toString() {
        return "Person{" +
                ", gender=" + gender() +
                ", id=" + id() +
                ", name='" + name() + '\'' +
                ", profilePath='" + profilePath() + '\'' +
                '}';
    }

    public static Person create(Long id, String name, Integer gender, String profilePath, Float popularity, List<DomainObject> knownFor) {

        return builder()
                .id(id)
                .name(name)
                .gender(gender)
                .profilePath(profilePath)
                .popularity(popularity)
                .knownFor(knownFor)
                .build();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder name(String name);

        public abstract Builder gender(Integer gender);

        public abstract Builder profilePath(String profilePath);

        public abstract Builder popularity(Float popularity);

        public abstract Builder knownFor(List<DomainObject> knownFor);

        public abstract Person build();
    }

    @Override
    public Long itemId() {
        return null;
    }

    @Override
    public String itemTitle() {
        return name();
    }

    @Override
    public Float itemPopularity() {
        return popularity();
    }

    @Override
    public String itemPosterPath() {
        return profilePath();
    }

    @Override
    public String itemBackdropPath() {
        return profilePath();
    }

    @Override
    public Boolean itemIsInWatchList() {
        return null;
    }

    @Override
    public MediaItem itemAddToWatchList() {
        return null;
    }

    @Override
    public MediaItem itemRemoveFromWatchList() {
        return null;
    }
}