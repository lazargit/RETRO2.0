package com.shamildev.retro.fragments.account_header.fragment.view;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.load.engine.GlideException;
import com.shamildev.retro.R;
import com.shamildev.retro.fragments.account_header.fragment.presenter.AccountHeaderPresenter;
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
 * A fragment implementation of {@link AccountHeaderView}.
 */
public final class AccountHeaderFragment extends BaseViewFragmentV4<AccountHeaderPresenter> implements AccountHeaderView {

    private static final String TAG = "AccountHeaderFragment";

    @Inject
    Navigator navigator;
    @Inject
    Application application;

    @Inject
    RetroImage retroImage;

    @Inject
    AccountActivity accountActivity;


    @BindView(R.id.tv_name)
    TextView mTv_name;

    @BindView(R.id.tv_details)
    TextView mTv_details;

    @BindView(R.id.rimg_account_header)
    RetroImageView retroProfileImageView;

    Drawable drawable;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_account_header, container, false);
    }


    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "onAttach: start");
        super.onAttach(context);
        accountActivity.setOnStateChangeListener(new AccountActivity.OnStateChangeListener() {
            @Override
            public void onStateChange(AccountActivity.State toolbarChange) {
                Log.d(TAG, "onStateChange: state:"+toolbarChange);
                if(toolbarChange == AccountActivity.State.COLLAPSED && (retroProfileImageView.getVisibility()== View.VISIBLE)){
                    retroProfileImageView.setVisibility(View.INVISIBLE);
                }else if (retroProfileImageView.getVisibility()== View.INVISIBLE){
                    retroProfileImageView.setVisibility(View.VISIBLE);
                }

            }
        });




    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: onResume");

        if(drawable==null){
            retroImage.load(ResourcesCompat.getDrawable(getResources(), R.drawable.background2, null))
                    .into(retroProfileImageView, new RetroImageRequestListener() {
                        @Override
                        public GlideException onLoadFailed(GlideException e) {

                            return e;
                        }

                        @Override
                        public Drawable onResourceReady(Drawable resource) {
                            drawable = resource;
                            return resource;
                        }
                    });
        }else{
            retroProfileImageView.getImageView().setImageDrawable(drawable);
        }

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

    @Override
    public TextView tv_name() {
        return mTv_name;
    }

    @Override
    public TextView tv_details() {
        return mTv_details;
    }
}