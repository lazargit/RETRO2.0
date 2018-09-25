package com.shamildev.retro.retroimage.handler;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import com.shamildev.retro.domain.core.MediaItem;
import com.shamildev.retro.glide.RetroGlide;
import com.shamildev.retro.retroimage.bitmap.BitmapConverter;
import com.shamildev.retro.retroimage.bitmap.ConvolutionMatrix;
import com.shamildev.retro.retroimage.core.RetroImageRequest;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retroimage.views.RetroImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shamil Lazar on 28.07.2018.
 */

public class RetroImageLoadingHandler {


    private final RetroImageRequestListener imageRequestListener;
    private Boolean preload = false;
    private int loadCount = 0;
    private RetroImageView imageView;
    private RequestManager requestManager;

    private RetroImageRequest imageRequest;
    private RetroImageView customImageView;
    private Map<Object, Object> map;
    private Bitmap bitmap;
    private CircleImageView imageCircleView;


    public RetroImageLoadingHandler(RetroImageRequest imageRequest, RequestManager requestManager, RetroImageRequestListener imageRequestListener) {
        this.imageRequestListener = imageRequestListener;
        this.imageRequest = imageRequest;
        this.requestManager = requestManager;
        this.map = new HashMap<>();

    }

    public void startLoad(View imageView) {

        if (!imageRequest.getItems().isEmpty()) {
            for (Object obj : imageRequest.getItems()) {
               // if (!prepareRequest(obj).isEmpty()) {
                    this.map.put(obj, prepareRequest(obj));
               // }

            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            try {
                map.forEach((o, s) -> {

                    if (imageView instanceof RetroImageView) {

                        this.imageView = (RetroImageView) imageView;
                        if (this.imageView.getImageCircle()) {



                            loadFile(o)


                                    .apply(RequestOptions.circleCropTransform())

                                    //.preload();

                                    .into(this.imageView.getImageView());
                        } else {
                            loadFile(o)
                                    .into(this.imageView.getImageView());
                        }

                        //  this.imageView.getImageView().setImageBitmap(applySmoothEffect(BitmapFactory.decodeResource(getResources(), R.drawable.beautiful),5));
                    }else if (imageView instanceof CircleImageView) {
                        this.imageCircleView = (CircleImageView) imageView;
                        loadFile(o)
                                .apply(RequestOptions.circleCropTransform())
                                .preload();
                    } else {
                        loadFile(o).preload();
                    }

                });
            } catch (Exception t) {

            }

        }


    }


    private void imageLoadSuccessful(Drawable resource, Object obj) {
        if (this.imageView != null) {
            if (this.imageView.isShowProgressBar()) {
                this.imageView.getProgressBar().setVisibility(View.GONE);
            }
        }
        removeFromMap(obj);
        if (map.isEmpty()) {
            imageRequestListener.onResourceReady(resource);
        }
    }

    private void imageLoadFailed(GlideException e, Object obj) {
        if (this.imageView != null) {
            if (this.imageView.isShowProgressBar()) {
                this.imageView.getProgressBar().setVisibility(View.GONE);
            }

        }
        removeFromMap(obj);
        imageRequestListener.onLoadFailed(e);
    }

    private void removeFromMap(Object obj) {
        map.remove(obj);
    }


    private Object prepareRequest(Object obj) {
        if (obj instanceof MediaItem) {
            MediaItem item = (MediaItem) obj;
            String tmdbConfigurationSizes = this.imageRequest.getTMDBConfigurationSizes(this.imageRequest.getImageType(), this.imageRequest.getImageSizeSetting());
            String path = "";


            switch (this.imageRequest.getImageType()) {
                case POSTER:
                    if (item.itemPosterPath() != null)
                        path = this.imageRequest.getConfigurations().baseUrl() + tmdbConfigurationSizes + item.itemPosterPath();
                    break;
                case BACKDROP:
                    if (item.itemBackdropPath() != null)
                        path = this.imageRequest.getConfigurations().baseUrl() + tmdbConfigurationSizes + item.itemBackdropPath();
                    break;
                case PROFILE:
                    if (item.itemPosterPath() != null)
                        path = this.imageRequest.getConfigurations().baseUrl() + tmdbConfigurationSizes + item.itemPosterPath();
                    break;
                case LOGO:

                    break;
                case STILL:

                    break;
                case GIF:
                    break;
                default:
                    break;

            }


            return path;

        } else if (obj instanceof String) {
            String urlPath = (String) obj;
            Pattern p = Pattern.compile("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)");//. represents single character
            Matcher match = p.matcher(obj.toString());
            if (match.matches()) {
                return urlPath;
            } else {
                throw new IllegalStateException("illegal url path for image loading");
            }

        }else if(obj instanceof Drawable){

        }else if(obj instanceof Integer){
            return obj;
         }
        return null;
    }


    @SuppressLint("CheckResult")
    public RequestBuilder<Drawable> loadFile(Object obj) {

        Log.e("TAG", "LOAD FILE.....>># " + prepareRequest(obj) + " " + obj.hashCode());






        return
                imageRequest.requestManager


                        .load(prepareRequest(obj))





                        .transition(DrawableTransitionOptions.withCrossFade(1500))

                        .listener(new RequestListener<Drawable>() {

                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                Log.e("TAG", "onLoadFailed.." + obj.hashCode());
                                imageLoadFailed(e, obj);

                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                Log.e("TAG", "glide load file>>>>.*." + isFirstResource + "##" + resource.getMinimumWidth());

                                imageLoadSuccessful(resource, obj);
                                return false;
                            }


                        });

    }

    public Bitmap applySmoothEffect(Bitmap src, double value) {
        //create convolution matrix instance
        ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
        convMatrix.setAll(1);
        convMatrix.Matrix[1][1] = value;
        // set weight of factor and offset
        convMatrix.Factor = value + 8;
        convMatrix.Offset = 1;
        return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
    }
}
