package com.shamildev.retro.domain.core;

import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.domain.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Singleton;

/**
 * Created by Shamil Lazar on 20.02.2018.
 */

@Singleton
public class AppConfig {


    private  Boolean firststart;
    String imageUrl;


    private Configuration configurations;
    private User user;


    private List<Genre> genres;
    private List<DomainObject> watchList = new ArrayList<>();
    private HashMap<String, ResultWrapper> preloadDataMap;

    public static final String NOWPLAYINGKEY = "nowplaying";
    public static final String NOWPLAYINGTVKEY = "nowplayingtv";
    public static final String UPCOMMINGKEY = "upcomming";
    public static final String TOPRATEDKEY = "toprated";
    public static final String POPULARPERSONKEY = "popularperson";
    public static final String HOMEHEADERKEY = "homeheader";
    public static final String WATCHLISTKEY = "watchlist";



    private final Pair<Integer, Integer> screenSizes;
    private List<MediaItem> homeGalleryList;


    public AppConfig(Pair<Integer, Integer> screenSizes, Boolean firststart) {
        this.screenSizes = screenSizes;
        this.firststart = firststart;


    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Configuration getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Configuration configurations) {
        this.configurations = configurations;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setWatchList(List<DomainObject> items) {

        this.watchList = items;
    }

    public HashMap<String, ResultWrapper> getPreloadDataMap() {
        return preloadDataMap;
    }

    public void setPreloadDataMap(HashMap<String, ResultWrapper> preloadDataMap) {
        this.preloadDataMap = preloadDataMap;
    }

    public List<DomainObject> getWatchList() {
        return watchList;
    }

    public Pair<Integer, Integer> getScreenSize() {
        return screenSizes;
    }


    public Boolean isFirstStart() {
        return this.firststart;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setHomeGalleryList(List<MediaItem> homeGalleryList) {
        this.homeGalleryList = homeGalleryList;
    }

    public List<MediaItem> getHomeGalleryList() {
        return homeGalleryList;
    }
}
