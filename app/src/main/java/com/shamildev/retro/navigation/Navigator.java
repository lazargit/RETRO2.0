package com.shamildev.retro.navigation;

import android.content.Context;
import android.content.Intent;

import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.account.AccountActivity;
import com.shamildev.retro.ui.firebaseui.FirebaseUiSignInActivity;
import com.shamildev.retro.ui.signin.SignInActivity;


import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */

public final class Navigator {


    /**
     * Provides methods to navigate to the different activities in the application.
     */

    @Inject
    Navigator() {
    }

    public void navigateToSignIn(Context context) {

        if (context != null) {
            Intent intent = SignInActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void navigateToFirebaseUiSignIn(Context context) {

        if (context != null) {
            Intent intent = FirebaseUiSignInActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void navigateToAccount(Context context) {

        if (context != null) {
            Intent intent = AccountActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }


}
