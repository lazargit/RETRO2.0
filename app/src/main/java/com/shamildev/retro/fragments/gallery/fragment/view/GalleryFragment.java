package com.shamildev.retro.fragments.gallery.fragment.view;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.GlideException;
import com.shamildev.retro.R;
import com.shamildev.retro.fragments.gallery.fragment.presenter.GalleryPresenter;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retroimage.views.RetroImageView;
import com.shamildev.retro.ui.account.AccountActivity;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Shamil Lazar.
 * <p>
 * A fragment implementation of {@link GalleryView}.
 */
public final class GalleryFragment extends BaseViewFragmentV4<GalleryPresenter> implements GalleryView {

    private static final String TAG = "GalleryFragment";

    @Inject
    Navigator navigator;
    @Inject
    Application application;
    @Inject
    RetroImage retroImage;

    @BindView(R.id.galleryImageView)
    RetroImageView galleryImageView;
    @BindView(R.id.grid_view)
    GridView gridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }


    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "onAttach: start");
        super.onAttach(context);



    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: onResume");


    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: starting");


    }

    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }


}