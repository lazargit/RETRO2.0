package com.shamildev.retro.domain.core;

/**
 * Created by Shamil Lazar on 21.04.2018.
 */

public interface MediaItem {

    Long itemId();
    String itemTitle();
    Float itemPopularity();
    String itemPosterPath();
    String itemBackdropPath();
    Boolean itemIsInWatchList();
    MediaItem itemAddToWatchList();
    MediaItem itemRemoveFromWatchList();



}
