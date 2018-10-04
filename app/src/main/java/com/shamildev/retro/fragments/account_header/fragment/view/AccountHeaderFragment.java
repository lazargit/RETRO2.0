package com.shamildev.retro.fragments.account_header.fragment.view;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shamildev.retro.R;
import com.shamildev.retro.fragments.account_header.fragment.presenter.AccountHeaderPresenter;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Shamil Lazar.
 * <p>
 * A fragment implementation of {@link AccountHeaderView}.
 */
public final class AccountHeaderFragment extends BaseViewFragmentV4<AccountHeaderPresenter> implements AccountHeaderView {


    @Inject
    Navigator navigator;
    @Inject
    Application application;

    @BindView(R.id.tv_name)
    TextView mTv_name;

    @BindView(R.id.tv_details)
    TextView mTv_details;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_account_header, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }

    @Override
    public TextView tv_name() {
        return mTv_name;
    }

    @Override
    public TextView tv_details() {
        return mTv_details;
    }
}