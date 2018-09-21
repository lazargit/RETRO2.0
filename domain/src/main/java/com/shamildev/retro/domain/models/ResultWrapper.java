package com.shamildev.retro.domain.models;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.core.DomainObject;

import java.io.Serializable;
import java.util.List;

import io.reactivex.annotations.Nullable;


/**
 * Created by Shamil Lazar
 */


@AutoValue
public abstract class ResultWrapper implements DomainObject,Serializable {

    public abstract int page();

    public abstract int totalPages();

    public abstract int totalResults();

    @Nullable
    public abstract List<DomainObject> results();

    public static Builder builder() {
        return new AutoValue_ResultWrapper.Builder();
    }




    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder page(int page);

        public abstract Builder totalPages(int totalPages);

        public abstract Builder results(List<DomainObject> results);

        public abstract Builder totalResults(int totalResults);


        public abstract ResultWrapper build();
    }
}
