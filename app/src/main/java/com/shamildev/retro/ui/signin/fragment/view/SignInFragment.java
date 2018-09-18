package com.shamildev.retro.ui.signin.fragment.view;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
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


    OnMessageListener onMessageListener;
    public interface OnMessageListener{
        void onLoadDialog(String msg);
        void onRemoveDialog();
    }

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
    public void onAttach(Context context) {
        super.onAttach(context);
        AppCompatActivity activity = (AppCompatActivity) context;
        try {
           onMessageListener = (OnMessageListener) context;
        }catch (ClassCastException e){
                throw new ClassCastException(activity.toString()+" must override onMessageListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @OnClick(R.id.button_signin)
    public void onClickSignIn(Button button) {
        String email = mTextInputEditText_email.getText().toString();
        String password = mTextInputEditText_password.getText().toString();

        presenter.firebaseLogin(email,password);

    }
    @OnClick(R.id.button_facebook_signin)
    public void onClickFacebookSignIn(Button button) {
        ((SignInActivity)getActivity()).loginFacebook();
    }
    @OnClick(R.id.button_twitter_signin)
    public void onClickTwitterSignIn(Button button) {
        ((SignInActivity)getActivity()).loginTwitter();
    }

    public void test(){
        presenter.firebaseLogin();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume", "appUser"+appUser.getFbtoken());
        if(appUser.getFbtoken()!=null){
            presenter.fbLogin(appUser.getFbtoken());
        }


    }



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

    @Override
    public void loadDialog() {
       // onMessageListener.onLoadDialog("Loading. Please wait...");

    }

    @Override
    public void removeDialog() {
        onMessageListener.onRemoveDialog();
    }
}