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

import com.shamildev.retro.R;



/**
 * Created by Shamil Lazar .
 */

public class RetroImageView extends RelativeLayout {

    private ImageView imageView;
    private ProgressBar progressBar;
    private boolean mShowFX=false;
    private int scaleType=0;
    private  boolean mShowProgressBar=true;
    private boolean mCircle=false;
    private Drawable mPlaceHolder;


    public RetroImageView(Context context) {
        super(context);
        init(null);
    }

    public RetroImageView(Context context, Boolean mShowFX) {
        super(context);
        this.mShowFX = mShowFX;
        init(null);
    }
    public RetroImageView(Context context, AttributeSet attrs) {
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
                    R.styleable.RetroImageView,  0, 0);
            try {
                mCircle = a.getBoolean(R.styleable.RetroImageView_circle, false);
                mShowFX = a.getBoolean(R.styleable.RetroImageView_showFX, false);
                mPlaceHolder = a.getDrawable(R.styleable.RetroImageView_placeholder);
                mShowProgressBar = a.getBoolean(R.styleable.RetroImageView_showProgressBar, true);
                scaleType = a.getInteger(R.styleable.RetroImageView_scaleType,0);
            } finally {
                a.recycle();
            }
        }

            if(mShowFX){
                inflate(getContext(), R.layout.view_retro_imagefx, this);
            }else{
                inflate(getContext(), R.layout.view_retro_image, this);

            }


        this.imageView =  findViewById(R.id.image_custom);
        this.progressBar =  findViewById(R.id.progressbar_image);
        this.imageView.setScaleType(this.scaleType(scaleType));
        //if(mCircle)  this.imageView.setImageDrawable(getResources().getDrawable(R.drawable.circle_shape));
        if(mPlaceHolder!=null)  this.imageView.setBackground(mPlaceHolder);

        if(!mShowProgressBar) this.progressBar.setVisibility(GONE);


    }


    private ImageView.ScaleType scaleType(int scaleType) {

        switch (scaleType){
            case 0: return ImageView.ScaleType.CENTER;
            case 1: return ImageView.ScaleType.CENTER_CROP;
            case 2: return ImageView.ScaleType.CENTER_INSIDE;
            case 3: return ImageView.ScaleType.FIT_CENTER;
            case 4: return ImageView.ScaleType.FIT_END;
            case 5: return ImageView.ScaleType.FIT_START;
            case 6: return ImageView.ScaleType.FIT_XY;
            case 7: return ImageView.ScaleType.MATRIX;
            default: return ImageView.ScaleType.CENTER_CROP;

        }

    }

    public ImageView getImageView() {
        return imageView;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
    public void setmShowFX(boolean mShowFX) {
        this.mShowFX = mShowFX;
    }
    public boolean isShowProgressBar() {
        return mShowProgressBar;
    }

    public Boolean getImageCircle() {
        return mCircle;
    }
}

