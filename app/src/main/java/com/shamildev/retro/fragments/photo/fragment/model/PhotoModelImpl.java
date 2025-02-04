package com.shamildev.retro.fragments.photo.fragment.model;

import android.util.Log;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseHandler;
import com.shamildev.retro.domain.models.AppUser;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar.
 */
@PerFragment
public class PhotoModelImpl extends PhotoModel {


    private static final String TAG = "PhotoModelImpl";


    @Inject
    protected DataConfig dataConfig;
    @Inject
    protected AppConfig appConfig;


    private UseCaseHandler useCaseHandler;



    @Inject
    AppUser appUser;

    @Inject
    public PhotoModelImpl(
            UseCaseHandler useCaseHandler) {

        this.useCaseHandler = useCaseHandler;


    }

    @Override
    public void initData() {
        Log.d(TAG, "initData: photo");


    }




}
