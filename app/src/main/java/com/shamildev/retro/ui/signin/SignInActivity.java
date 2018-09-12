package com.shamildev.retro.ui.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

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
import com.shamildev.retro.MainActivity;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.ui.common.BaseActivitySupport;
import com.shamildev.retro.ui.signin.fragment.view.SignInFragment;
import com.shamildev.retro.ui.splash.fragment.view.SplashFragment;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Schamil Lazar.
 */

public class SignInActivity extends BaseActivitySupport {


    private int RC_SIGN_IN = 123;
    private CallbackManager mCallbackManager;

//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);

    //I rather using a list, this way handling providers is separeted from the login methods
//    List<AuthUI.IdpConfig> providers = Arrays.asList(
//            new AuthUI.IdpConfig.EmailBuilder().build(),
//            new AuthUI.IdpConfig.TwitterBuilder().build(),
//            new AuthUI.IdpConfig.FacebookBuilder().build());

    @Inject
    AppUser appUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        AccessToken accessToken = loginResult.getAccessToken();
                        Log.d("Success", "Login"+accessToken.getToken()+" "+loginResult.getRecentlyDeniedPermissions().toString());
                        SignInFragment signInFragment = (SignInFragment) getFragmentByTag("SignInFragment");
                        //signInFragment.test(accessToken.getToken());
                        appUser.setFBToken(accessToken.getToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d("Cancel", "Login");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("Error", "Login"+exception);
                    }
                });


        setContentView(R.layout.activity_signin);

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


    public void loginFacebook(){
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(mCallbackManager.onActivityResult(requestCode, resultCode, data)) {
            Log.e("onActivityResult", "Login"+data);


            return;
        }

//        if (requestCode == RC_SIGN_IN) {
//            IdpResponse response = IdpResponse.fromResultIntent(data);
//
//            if (resultCode == RESULT_OK) {
//                // Successfully signed in
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                Log.e("SIGNIN FACEBOOK OK ", ">> "+response.getEmail()+" user "+user.getPhotoUrl().getPath()+" name "+user.getDisplayName());
//                // ...
//            } else {
//                Log.e("SIGNIN FACEBOOK ", ">> "+response.getError().getErrorCode());
//                // Sign in failed. If response is null the user canceled the
//                // sign-in flow using the back button. Otherwise check
//                // response.getError().getErrorCode() and handle the error.
//                // ...
//            }
//        }



    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }


    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        return intent;
    }

}
