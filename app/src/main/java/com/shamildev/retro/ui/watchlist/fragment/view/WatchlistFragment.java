package com.shamildev.retro.ui.watchlist.fragment.view;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shamildev.retro.R;
import com.shamildev.retro.navigation.Navigator;

import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchlistPresenter;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar.
 * <p>
 * A fragment implementation of {@link WatchlistView}.
 */
public final class WatchlistFragment extends BaseViewFragmentV4<WatchlistPresenter> implements WatchlistView {


    @Inject
    Navigator navigator;

    @Inject
    Application application;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watchlist, container, false);
    }


    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }
}