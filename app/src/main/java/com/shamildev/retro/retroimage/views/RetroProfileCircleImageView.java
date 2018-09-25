package com.shamildev.retro.retroimage.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.engine.GlideException;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.core.MediaItem;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shamil Lazar on 08.05.2018.
 */

public class RetroProfileCircleImageView extends RelativeLayout {

    private ImageView imageView;
    private ProgressBar progressBar;



    private boolean mShowFX;

    private int size = 1;

    private ImageView image_circle;
    private RetroImageView retroimage_view;
    private CircleImageView profileimage_view;


    public RetroProfileCircleImageView(Context context) {
        super(context);
        init(null);
    }

    public RetroProfileCircleImageView(Context context, Boolean mShowFX) {
        super(context);
        this.mShowFX = mShowFX;
        init(null);
    }
    public RetroProfileCircleImageView(Context context, AttributeSet attrs) {
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


            inflate(getContext(), R.layout.view_retro_circleimageview, this);
           // this.image_circle =  findViewById(R.id.image_circle);
           // this.retroimage_view =  findViewById(R.id.retroimage_view);
            this.profileimage_view =  findViewById(R.id.profile_image);
           // this.retroimage_view.setVisibility(INVISIBLE);
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
                    .preload(new RetroImageRequestListener() {
                        @Override
                        public boolean onLoadFailed(GlideException e) {
                            Log.e("TAG","IMAGE PROFILE LOAD FAILED.");
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource) {
                            Log.e("TAG","IMAGE PROFILE LOAD...!");
                           profileimage_view.setImageDrawable(resource);
                          //  retroimage_view.setVisibility(View.VISIBLE);
                            return false;
                        }
                    });

        } else {
            throw new IllegalStateException("no mediaItem found !");
        }



    }


}

