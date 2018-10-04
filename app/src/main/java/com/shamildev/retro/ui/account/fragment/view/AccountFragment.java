package com.shamildev.retro.ui.account.fragment.view;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shamildev.retro.R;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.account.fragment.presenter.AccountPresenter;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Shamil Lazar.
 * <p>
 * A fragment implementation of {@link AccountView}.
 */
public final class AccountFragment extends BaseViewFragmentV4<AccountPresenter> implements AccountView {


    @Inject
    Navigator navigator;
    @Inject
    Application application;

    @BindView(R.id.textview_name)
    TextView mTextView_name;

    @BindView(R.id.textview_name_details)
    TextView mTextView_name_details;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_account, container, false);
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
    public TextView getTextView_UserEmail() {
        return mTextView_name;
    }


}