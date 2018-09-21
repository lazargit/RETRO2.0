package com.shamildev.retro.ui.home.fragment.view;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.retroimage.views.RetroImageView;
import com.shamildev.retro.ui.common.view.BaseViewFragment;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenter;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;
import com.shamildev.retro.ui.splash.fragment.view.SplashView;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Shamil Lazar.

 * A fragment implementation of {@link SplashView}.
 */
public final class HomeFragment extends BaseViewFragmentV4<HomePresenter> implements HomeView {


    @Inject
    Navigator navigator;

    @Inject
    Application application;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }




    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }
}