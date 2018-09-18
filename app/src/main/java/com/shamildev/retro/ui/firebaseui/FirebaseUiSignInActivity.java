package com.shamildev.retro.ui.firebaseui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shamildev.retro.BuildConfig;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.BaseActivitySupport;
import com.shamildev.retro.ui.signin.fragment.view.SignInFragment;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Schamil Lazar.
 */

public class FirebaseUiSignInActivity extends BaseActivitySupport {


    private int RC_SIGN_IN = 123;
    private CallbackManager mCallbackManager;

//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);

    //I rather using a list, this way handling providers is separeted from the login methods
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.TwitterBuilder().build(),
            new AuthUI.IdpConfig.FacebookBuilder().build());

    @Inject
    AppUser appUser;

    @Inject
    Navigator navigator;

    @Inject
    Application application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_firebaseuisignin);
        finish();

        // Choose authentication providers


        // Create and launch sign-in intent

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            navigator.navigateToAccount(application);

        }else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setIsSmartLockEnabled(!BuildConfig.DEBUG /* credentials */, true /* hints */)
                            .setTheme(R.style.LoginTheme)
                            .build(),
                    RC_SIGN_IN);
        }


    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        public static final int RESULT_CANCELED = 0;
//        public static final int RESULT_FIRST_USER = 1;
//        public static final int RESULT_OK = -1;

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            Log.e("SIGNIN  ", ">> "+resultCode);
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.e("SIGNIN FACEBOOK OK >>>", ">> " + response.getEmail() + " user " + response.getProviderType());
                // ...

            } if (resultCode == RESULT_CANCELED) {
                Log.e("SIGNIN CANCELED ", "#");

            } else {
                Log.e("SIGNIN FACEBOOK>>> ", ">> "+resultCode);
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }



    }

    @Override
    public void onBackPressed() {

        Log.e("BACKPRESSED ", "getBackStackEntryCount "+getSupportFragmentManager().getBackStackEntryCount());
        moveTaskToBack(false);
        if(getSupportFragmentManager().getBackStackEntryCount() > 1) {
            //   triggerFragmentBackPress(getSupportFragmentManager().getBackStackEntryCount());
        } else {
            finish();
        }
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }


    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, FirebaseUiSignInActivity.class);
        return intent;
    }

}
