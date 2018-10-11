package com.shamildev.retro.ui.signin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.ui.common.BaseActivitySupport;
import com.shamildev.retro.ui.signin.fragment.view.SignInFragment;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Schamil Lazar.
 */

public class SignInActivity extends BaseActivitySupport implements SignInFragment.OnMessageListener {


    private int RC_SIGN_IN = 123;
    private CallbackManager mCallbackManager;
    @Inject AppUser appUser;
    @Inject AppConfig appConfig;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    TwitterAuthClient mTwitterAuthClient;
    ProgressDialog dialog;
    private Unbinder butterKnifeUnbinder;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());





        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isFacebookTokenValid = accessToken != null && !accessToken.isExpired();
        Log.d("Success", "accessToken not isExpired " + isFacebookTokenValid);

            facebookLoginCall();



        setContentView(R.layout.activity_signin);
        butterKnifeUnbinder = ButterKnife.bind(this);
        setupToolBar();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new SignInFragment());
        }

        // Choose authentication providers


        // Create and launch sign-in intent
//        startActivityForResult(
//                AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .setIsSmartLockEnabled(!BuildConfig.DEBUG /* credentials */, true /* hints */)
//
//                        .build(),
//                RC_SIGN_IN);


    }

    private void setupToolBar() {
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.activity_signin);
            toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        }


    }


    private void twitterLoginCall() {
        mTwitterAuthClient = new TwitterAuthClient();
        mTwitterAuthClient.authorize(this, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> twitterSessionResult) {
                Log.d("Success", "TWITTER " + twitterSessionResult.data.getAuthToken().token + " " + twitterSessionResult.data.getAuthToken().secret);
                appUser.setTwtoken(twitterSessionResult.data.getAuthToken().token, twitterSessionResult.data.getAuthToken().secret);
                signInFragment();

            }

            @Override
            public void failure(TwitterException e) {
                Log.d("Success", "TWITTER ERROR " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private void signInFragment() {
        SignInFragment signInFragment = (SignInFragment) getFragmentById(R.id.fragmentContainer);
        if (signInFragment != null) {

        }
    }

    private void facebookLoginCall() {
        //onLoadDialog("lade..");
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        AccessToken accessToken = loginResult.getAccessToken();
                        Log.d("Success", "Login" + accessToken.getToken() + " " + loginResult.getRecentlyDeniedPermissions().toString());

                        appConfig.setFacebookToken(accessToken.getToken());

                        signInFragment();
                    }

                    @Override
                    public void onCancel() {
                        Log.d("Cancel", "Login");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("Error", "Login" + exception);
                    }
                });
    }


    public void loginFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
    }

    public void loginTwitter() {
        twitterLoginCall();
    }

    @Override
    public void onBackPressed() {
        Log.e("BACKPRESSED ", "getBackStackEntryCount " + getSupportFragmentManager().getBackStackEntryCount());
      //  moveTaskToBack(true);
//        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
//            // triggerFragmentBackPress(getSupportFragmentManager().getBackStackEntryCount());
//        } else {
//
//        }
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (mCallbackManager.onActivityResult(requestCode, resultCode, data)) {
            Log.e("onActivityResult", "Login" + data);
            return;
        } else {
            mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }


    @Override
    public void onLoadDialog(String msg) {
        dialog = ProgressDialog.show(this, "",
                msg, true);
    }

    @Override
    public void onRemoveDialog() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public View getCoordinator() {
       return findViewById(R.id.coordinator);
    }
}
