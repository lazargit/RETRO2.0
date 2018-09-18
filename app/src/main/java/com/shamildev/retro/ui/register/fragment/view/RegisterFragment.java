package com.shamildev.retro.ui.register.fragment.view;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shamildev.retro.R;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.view.BaseViewFragment;

import com.shamildev.retro.ui.register.fragment.presenter.RegisterPresenter;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar on 18.09.2018.
 */
public class RegisterFragment extends BaseViewFragment<RegisterPresenter> implements RegisterView {


    @Inject
    Navigator navigator;

    @Inject
    Application application;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void makeToast(String message) {

    }
}
