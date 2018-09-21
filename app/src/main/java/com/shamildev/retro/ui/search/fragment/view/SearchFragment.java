package com.shamildev.retro.ui.search.fragment.view;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shamildev.retro.R;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.view.BaseViewFragment;
import com.shamildev.retro.ui.search.fragment.presenter.SearchPresenter;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar.
 * <p>
 * A fragment implementation of {@link SearchView}.
 */
public final class SearchFragment extends BaseViewFragment<SearchPresenter> implements SearchView {


    @Inject
    Navigator navigator;

    @Inject
    Application application;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_seach, container, false);
    }


    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }
}