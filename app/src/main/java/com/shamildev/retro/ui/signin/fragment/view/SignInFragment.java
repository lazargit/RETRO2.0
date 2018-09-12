package com.shamildev.retro.ui.signin.fragment.view;

import android.app.Application;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.signin.SignInActivity;
import com.shamildev.retro.ui.signin.fragment.presenter.SignInPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Shamil Lazar.
 * <p>
 * A fragment implementation of {@link SignInView}.
 */
public final class SignInFragment extends BaseViewFragmentV4<SignInPresenter> implements SignInView {


    @Inject
    Navigator navigator;

    @Inject
    Application application;

    @Inject
    AppUser appUser;

    @BindView(R.id.textinput_email)
    TextInputEditText mTextInputEditText_email;

    @BindView(R.id.textinput_password)
    TextInputEditText mTextInputEditText_password;


    @BindView(R.id.button_signin)
    Button mButton_signin;

    @BindView(R.id.button_facebook_signin)
    Button mButton_facebook_signin;

    @BindView(R.id.button_twitter_signin)
    Button mButton_twitter_signin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @OnClick(R.id.button_signin)
    public void onClickSignIn(Button button) {

    }
    @OnClick(R.id.button_facebook_signin)
    public void onClickFacebookSignIn(Button button) {
        ((SignInActivity)getActivity()).loginFacebook();
    }

    public void test(String token){
     //   presenter.fbLogin(token);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume", "appUser"+appUser.getFbtoken());
        if(appUser.getFbtoken()!=null){
            presenter.fbLogin(appUser.getFbtoken());
        }

//        callbackManager = CallbackManager.Factory.create();
//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        Log.e("FACEBOOK#", "onSuccess " + loginResult.getAccessToken().getToken());
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Log.e("FACEBOOK#", "onCancel ");
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        Log.e("FACEBOOK#", "onError"+exception.getMessage());
//                    }
//                });

    }
    //
//    @Override
//    public ImageSliderView getImageSliderView() {
//        return img_slider;
//    }
//    @Override
//    public ImageSliderView getImageSliderView2() {
//        return img_slider2;
//    }
//
//    @Override
//    public RetroImageView getCustomImageView() {
//        return customImageView;
//    }


    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }



    @Override
    public Button getButton_signin() {
        return mButton_signin;
    }

    @Override
    public Button getButton_facebook_signin() {
        return mButton_facebook_signin;
    }

    @Override
    public Button getButton_twitter_signin() {
        return mButton_twitter_signin;
    }

    @Override
    public void loginFb() {

    }
}