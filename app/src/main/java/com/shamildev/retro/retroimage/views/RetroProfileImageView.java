package com.shamildev.retro.retroimage.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.engine.GlideException;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.core.MediaItem;

import com.shamildev.retro.retroimage.bitmap.BitmapConverter;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shamil Lazar on 08.05.2018.
 */

public class RetroProfileImageView extends RelativeLayout {

    private ImageView imageView;
    private ProgressBar progressBar;



    private boolean mShowFX;

    private int size = 1;

    private ImageView image_circle;
    private RetroImageView retroimage_view;
    private CircleImageView profileimage_view;


    public RetroProfileImageView(Context context) {
        super(context);
        init(null);
    }

    public RetroProfileImageView(Context context, Boolean mShowFX) {
        super(context);
        this.mShowFX = mShowFX;
        init(null);
    }
    public RetroProfileImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void init(AttributeSet attrs) {


        if(attrs!= null){
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.RetroProfileImageView,  0, 0);

            try {
                size = a.getInteger(R.styleable.RetroProfileImageView_size,1);

            } finally {
                a.recycle();
            }


            inflate(getContext(), R.layout.view_retro_profile, this);
            this.retroimage_view =  findViewById(R.id.retroimage_view);

        }


        //this.retroimage_view.getImageView().setImageDrawable(getResources().getDrawable(R.drawable.placeholderuserphoto));

    }

    public RetroImageView getImageView() {
        return retroimage_view;
    }

    public void src(MediaItem mediaItem, RetroImage retroImg){

        if (mediaItem!=null) {
                    retroImg
                    .load(mediaItem)
                    .Profile()
                    .w185()
                    .into(this.retroimage_view,new RetroImageRequestListener() {
                        @Override
                        public boolean onLoadFailed(GlideException e) {
                            Log.e("TAG","IMAGE PROFILE LOAD FAILED.");
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource) {
                            Log.e("TAG","IMAGE PROFILE LOAD...!");

                            return true;
                        }
                    });

        } else {
            throw new IllegalStateException("no mediaItem found !");
        }
    }
    public void src(String url , RetroImage retroImg){

        if (!url.equals("")) {
            retroImg
                    .load(url)

                    .into(this.retroimage_view,new RetroImageRequestListener() {
                        @Override
                        public boolean onLoadFailed(GlideException e) {
                            Log.e("TAG","IMAGE FACEBOOK PROFILE LOAD FAILED.");
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource) {
                            Log.e("TAG","IMAGE FACEBOOK PROFILE LOAD...! "+BitmapConverter.DrawableToByteArray(resource));


                            return true;
                        }
                    });

        } else {
            throw new IllegalStateException("no mediaItem found !");
        }
    }

    public void src(byte[] bytes , RetroImage retroImg){

        if (!bytes.equals("")) {
            retroImg
                    .load(bytes)

                    .into(this.retroimage_view,new RetroImageRequestListener() {
                        @Override
                        public boolean onLoadFailed(GlideException e) {
                            Log.e("TAG","IMAGE BYTES PROFILE LOAD FAILED."+e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource) {
                            Log.e("TAG","IMAGE BYTES PROFILE LOAD...! ");


                            return true;
                        }
                    });

        } else {
            throw new IllegalStateException("no mediaItem found !");
        }
    }


}

