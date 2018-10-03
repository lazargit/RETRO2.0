package com.shamildev.retro.ui.splash.fragment.presenter;


import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.core.MediaItem;
import com.shamildev.retro.domain.helper.ProcessData;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.home.HomeActivity;
import com.shamildev.retro.ui.splash.fragment.model.SplashModel;
import com.shamildev.retro.ui.splash.fragment.view.SplashView;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by Shamil Lazar
 */

/**
 * An implementation of {@link SplashPresenter}.
 */
@PerFragment
public final class SplashPresenterImpl extends BasePresenter<SplashView, SplashModel> implements SplashPresenter {



    private DataConfig dataConfig;
    private AppConfig appConfig;


    @Inject Application application;
    @Inject RetroImage retroImage;
    @Inject Navigator navigator;
    RequestManager requestManager;

    @Inject
    SplashPresenterImpl(
            AppConfig appConfig,
            SplashView view,
            SplashModel model,
            DataConfig dataConfig) {
        super(view, model);
        //       this.networkManager = networkManager;
        //      this.networkManager.add(toString(), this::refreshData);
        this.dataConfig = dataConfig;
        this.appConfig = appConfig;


    }


    @Override
    public void onStart(@Nullable Bundle savedInstanceState) {
        if(this.appConfig.isFirstStart()){
            this.model.initTables();
        }else{
            this.model.initConfiguration();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        model.testFireStoreRead();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("ONPAUSE ","ONPAUSE ");
        model.getUseCaseHandler().clear();
    }

    @Override
    public void onEnd() {
        super.onEnd();
        Log.e("ONEND ","ONEDND ");

    }

    @Override
    public void onError(Throwable t) {


        if (t.getCause() instanceof TMDBError) {
            TMDBError error = (TMDBError) t.getCause();
            Log.d("onError", "<<<<< " + error.getResponseCode() + " : " + error.getMessage() + " : " + error.getStatusCode() + " : " + error.getSuccess());

        } else {

        }

    }

    @Override
    public void goHome() {
        navigator.navigateToHome(application,null);
    }

    @Override
    public void finishPreload(HashMap<String, ResultWrapper> map) {
        toast("FINISH PRELOAD");



        String path = "https://pbs.twimg.com/profile_images/853321507979882496/UMAwVI3j_normal.jpg";
        String pathfb = "https://graph.facebook.com//10215197067952270/picture?type=large";
        view.getProfileView()
                .src(path,retroImage);



        final byte[][] byteimage = new byte[1][1];
        retroImage
                .load(pathfb)

                .preload(new RetroImageRequestListener() {
                    @Override
                    public boolean onLoadFailed(GlideException e) {
                        Log.e("TAG","IMAGE PROFILE LOAD FAILED.");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource) {

//                        byteimage[0] = BitmapConverter.DrawableToByteArray(resource);
//                        Log.e("TAG","IMAGE PROFILE LOAD...!"+byteimage[0].toString());
//
//                        ;
//                        String str = BitmapConverter.drawableToString(resource);
//                        String imgBase64 = "iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAb1BMVEX///+ZzACTyQCRyQDi8MTk8cu+33f2+u3N5piczRSu2EnA4Hr+//rE4oTr9tXy+eOo1Dr1+unS6KOi0Sey2Fvt9tvd7ru322jb7bb6/PPE4oa73XHK5JLr9dfW6q32+uuo1DzQ6KC02mCv11LH4oz7JUSRAAAGUUlEQVR4nO2d63qiMBBASYK2eBcEReul6vs/4wJuEcJEg+Yy3Z3zc7fhyyEjl2TIBAFB/D/ELFmaOtZZrE0dyhy5YDwxdKytYGJj6Fjm+BCM8S8jh9oXh0JoGEx50a+5iSNFjDFT4WAUUx0zd6pMszcTp4YOYwUzJx9tjJaY6BzeGC0xEGCYY7Rk+/YAoI7Rknc7iDtGS6ogu77cfIY8RkuqOE07/5zPB+Hh+7qdlmyvn4dskH50W6OP0RKpk5PZYbtKuBBcpvin6DKNBw1R/DFakt7jdLkZJ6UaU1OasnW8r/6+itFPn53X42+chlMmHrm1PAVfb/KA/YYYLUmKjkbs4dCBlmU7/DFaEvZ0azL23XkN0vUbgowvMt8CT5ivxBt+JSLC7Jjv3vWrHBcz3yIq4r5XF6XjOvftArE8GfJj5W0S4TTNwUSA3hEr30Iya7OCxTAyVLfGPDEXoTUC0UV1bl6vUkTzjJpaGMCb4tS32g1rglgU5/YEC8XX5wuMkQP9Kt6GuPar0+MmIvYtWL0rSZ2NDsfiASDuJXhNi5OVLbqOYuBZsPsmweulv4v2MCY/Uxmf3dsqP3pSuwE8yTQeRka6hpO6yVf3rHh97V8Cp3zy8L8hRNg4JBD1Pi+op253W905axlGzSYH4Kfo720qBoaw9agFdBdg2GwyB8Y9CjyRA/3nrfOtNWXTnuSeAIbephiHkGHr4r7RMtw2m3xAv13h540YiifpdE+1ovTUbAIOO9+5FKuBLyOt34yOXzFCy6dHFcZSdXoADmFxuhuPWVfNW/7l3mSmuMH4GEToV1id7vrupj2xcb/jKd80BbBSZZlc2X0xra4Lxx5Ti3x0W5D7VjZ5Y1HyVWJ1BHIxGu4W/SZuRLIbXx7ORjo37D5d2YW7nrVJTc+tPcX19KLuZdIcYvK8VyaJXAsyx9PgipuhVdwm1T64ktrDqaHei59ZgCQWi3gQbD0PWsfHz9Dts2nm42fo9FUfmBFzgMs74tqHoNNLzcKLoctHUy+CLi+m0HyYC0N374hpN5fSBcLd7SIP/YA2k4ggCIL4L5mEAy+Yf/JOs/gzzro5gkc8zzT7TdnF11amZjt+ExFsLEmCq5gOnkuHUhfDc93Fa2/J+ahhwcW5tQiLw3CftLo47Pd+vJEceGv1GoWhnFzUL+UWWPlrZl9hMLwCeSD6intwhf6+RonAMAO7oG0IrpzxMyZD+A90s6cGsICoU+j8GypSkbimoSJHhB/wGCpWFXgICnW4KJrXMeDfULECzb/1DBVJk7z+dM6/oWL5Ujc9jAzJ0BZkSIZkSIb2IUMyJEMytA8ZkiEZkqF9yJAMyZAM7UOGZEiGZGgfMiRDMiRD+5AhGZIhGdqHDMmQDMnQPmRIhmRIhvYhQzIkQzK0DxmSIRmSoX3IUN9wpWhe79ns31D1ZZfmLkSKfS0xfZ2n2IpLt5CCYrfi+6ba/g3hj2S1v7CEtstnzT21/RsqvpLVLmEKbxp4/94dgSH4JW+PTfmAIOCNTSkQGEJVC1ifWh+htC1z+3N+DIbdTRGSflt+5+tGXRsuVSVCYRgsL80uvlBGeH6N/m6rkXxKpQlwGAZBWlZzLXsoTvFru0Z+DDabbNatvIDFsOBYdnFvfFNMRIaWIEMyJEMyJEMyJEMyJMPfYKiuhGTXcPy8a6bwsq1+j2mY9/Ei6LTSjKfKAQ5rkWvVNTSPO0E/P0SXP8Mg2HpQdFvIcuLe0HX1PHm20j7OC8ruHI9ij+1XTbFyquil6vHQXaBy5rReV03GHA2jWDmuDFgz+WLCuiQXI82VXTuE4xMXP6gHAUZ5dhqHXH37KAUsM7mhKgPFpz9/0SZIFYp8Vh8SGWpDGFWBOo62ZA4ZypAhPshQhgzxQYYyZIgPMpQhQ3yQoQwZ4oMMZcgQH2QoQ4b4IEMZMsQHGcqQIT7IUIYM8UGGMr/PUFXAVPWVvCoT12USYj++4FVroazwCgu6TULshSrqlA3gL/9dZjv3BcyTEuoStnACoOjzxbJjcmhETg8aQAmAQnPrBz/MO4PCk4cpI3IN+EJQt7i2J45J21GsnjTYSN/F4x7Bivie7cZF8rxQdr4TjQZrDBlQT8l2UZWxlUz1EtLy+FLlhbERigwvPSbHZc8dDpZLp2nc/xJ/AMM4nGaREA01AAAAAElFTkSuQmCC";
//
//
//                        byte[] imageByteArray = Base64.decode(str, Base64.DEFAULT);
//
//                        if(imageByteArray!=null){
//                            Log.e("TAG","BYTEARRAY  LOAD...!"+imageByteArray);
//                           // BitmapFactory.decodeByteArray(Base64.encode(byteimage[0], Base64.NO_WRAP), 0, imageByteArray.length)
//                            retroImage
//
//                                    .load(imageByteArray)
//                                    .preload(new RetroImageRequestListener() {
//                                        @Override
//                                        public boolean onLoadFailed(GlideException e) {
//                                            Log.e("BYTETEST", "onLoadFailed.." + e);
//                                            return false;
//                                        }
//
//                                        @Override
//                                        public boolean onResourceReady(Drawable resource) {
//                                            Log.e("BYTETEST", "onResourceReady.." + resource);
//                                            view.getPersonImage().getImageView().setImageDrawable(resource);
//                                            return false;
//                                        }
//                                    });
//
//
//                            ;
//                        }


                        return false;
                    }
                });


       // appConfig.setPreloadDataMap(map);
       // preloadSliderTeaserImages(map);

    }


    private void preloadSliderTeaserImages(HashMap<String, ResultWrapper> map){
        List<MediaItem> homeGalleryList = ProcessData.createHomeGalleryList(map, AppConfig.NOWPLAYINGKEY, 4);
        appConfig.setHomeGalleryList(homeGalleryList);
        retroImage.load(homeGalleryList)
                .Backdrop()
                .w780()
                .preload(new RetroImageRequestListener() {
                    @Override
                    public boolean onLoadFailed(GlideException e) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource) {
                         navigator.navigateToHome(application,null);
                        return false;
                    }
                });


    }

    @Override
    public void setBgTeaser(ResultWrapper wrapper) {
        MediaItem mediaItem = (MediaItem) wrapper.results().get(0);
        retroImage
                .load(mediaItem)
                .Poster()
                .w780()
                .into(view.getSplashBg(),new RetroImageRequestListener() {
                    @Override
                    public boolean onLoadFailed(GlideException e) {
                        Log.e("TAG","IMAGES LOAD FAILED.");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource) {
                        Log.e("TAG","ALL IMAGES PRELOADED...!");

                        return false;
                    }
                });
    }

    @Override
    public void setTestPerson(ResultWrapper wrapper) {
        MediaItem mediaItem = (MediaItem) wrapper.results().get(13);





//        view.getProfileView()
//                .src(mediaItem,retroImage);


    }

    @Override
    public void setByteArray(byte[] bytes) {
        retroImage
                .load(bytes)
                .into(view.getPersonImage(),new RetroImageRequestListener() {
                    @Override
                    public boolean onLoadFailed(GlideException e) {
                        Log.e("BYTETEST>>", "onLoadFailed.." + e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource) {
                        Log.e("BYTETEST>>", "onResourceReady.." + resource);
                        return false;
                    }
                });
    }

    @Override
    public void setByteArrayPis(byte[] pic) {

        requestManager
                .load(pic)
                .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.e("BYTETEST", "onLoadFailed.." + e);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.e("BYTETEST", "onResourceReady.." + resource);
                return false;
            }
        }).preload();


//                .load(pic)
//
//
//
//
//
//                .transition(DrawableTransitionOptions.withCrossFade(1000))
//
//                .listener(new RequestListener<Drawable>() {
//
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        Log.e("TAG", "onLoadFailed.." + obj.hashCode());
//                        imageLoadFailed(e, obj);
//
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        Log.e("TAG", "glide load file>>>>.*." + isFirstResource + "##" + resource.getMinimumWidth());
//
//                        imageLoadSuccessful(resource, obj);
//                        return false;
//                    }
//
//
//                });

//        view.getProfileView()
//                .src(pic,retroImage);
    }

    @Override
    public void signout() {
        model.signout();
    }

    @Override
    public void configRetroImage(Configuration configuration) {
        retroImage.setConfigurations(configuration);
    }

    @Override
    public void toast(Object obj) {
        view.makeToast((String) obj);
    }


}