package com.shamildev.retro.ui.settings.fragment.view;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shamildev.retro.R;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.settings.fragment.presenter.SettingsPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Shamil Lazar.
 * <p>
 * A fragment implementation of {@link SettingsView}.
 */
public final class SettingsFragment extends BaseViewFragmentV4<SettingsPresenter> implements SettingsView {


    @Inject
    Navigator navigator;
    @Inject
    Application application;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_settings, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }


    @OnClick(R.id.button_logout)
    public void onClick_ButtonLogOut(Button button) {
        presenter.logout();

    }





}