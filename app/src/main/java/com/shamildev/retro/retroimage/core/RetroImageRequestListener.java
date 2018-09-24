package com.shamildev.retro.retroimage.core;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.engine.GlideException;

/**
 * Created by Shamil Lazar on 27.07.2018.
 */

public interface RetroImageRequestListener {

    boolean onLoadFailed(GlideException e);
    boolean onResourceReady(Drawable resource);

}
