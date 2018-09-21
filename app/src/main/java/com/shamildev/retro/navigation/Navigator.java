package com.shamildev.retro.navigation;

import android.content.Context;
import android.content.Intent;
import com.shamildev.retro.ui.account.AccountActivity;
import com.shamildev.retro.ui.firebaseui.FirebaseUiSignInActivity;
import com.shamildev.retro.ui.home.HomeActivity;
import com.shamildev.retro.ui.register.RegisterActivity;
import com.shamildev.retro.ui.search.SearchActivity;
import com.shamildev.retro.ui.signin.SignInActivity;
import com.shamildev.retro.ui.watchlist.WatchlistActivity;


import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar.
 */

public final class Navigator {


    /**
     * Provides methods to navigate to the different activities in the application.
     */

    @Inject
    Navigator() {
    }


    public void navigateToHome(Context context) {
        if (context != null) {
            Intent intent = HomeActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void navigateToSignIn(Context context) {

        if (context != null) {
            Intent intent = SignInActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
    public void navigateToRegister(Context context) {

        if (context != null) {
            Intent intent = RegisterActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void navigateToWatchlist(Context context) {

        if (context != null) {
            Intent intent = WatchlistActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void navigateToSearch(Context context) {

        if (context != null) {
            Intent intent = SearchActivity.getCallingIntent(context);
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
