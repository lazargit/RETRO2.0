package com.shamildev.retro.retroimage.core;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.shamildev.retro.domain.util.Pair;
import com.shamildev.retro.glide.RetroGlide;
import com.shamildev.retro.util.DeviceUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RetroImageModule {


    @Provides
    @Singleton
    static RetroImage RetroImageModule(Application application) {
        Pair<Integer, Integer> screenSizes = new Pair<>(DeviceUtils.getScreenWidth(application), DeviceUtils.getScreenHeight(application));
        return new RetroImage(RetroGlide.with(application.getBaseContext()),screenSizes);
    }



}
