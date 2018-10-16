package com.shamildev.retro.navigation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.shamildev.retro.R;
import com.shamildev.retro.ui.account.AccountActivity;
import com.shamildev.retro.ui.firebaseui.FirebaseUiSignInActivity;
import com.shamildev.retro.ui.home.HomeActivity;
import com.shamildev.retro.ui.photogallery.PhotoGalleryActivity;
import com.shamildev.retro.ui.register.RegisterActivity;
import com.shamildev.retro.ui.search.SearchActivity;
import com.shamildev.retro.ui.settings.SettingsActivity;
import com.shamildev.retro.ui.signin.SignInActivity;
import com.shamildev.retro.ui.watchlist.WatchlistActivity;


import javax.inject.Inject;

/**
 * Created by Shamil Lazar.
 */

public final class Navigator {



    /**
     * Provides methods to navigate to the different activities in the application.
     */

    private static final String TAG = "Navigator";
    public static final int SUB_ACTIVITY_CREATE_USER = 16;

    @Inject
    Navigator() {
    }


    public void navigateToHome(Context context,Activity callActivity) {
        if (context != null) {
            Intent intent = HomeActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            if(callActivity!=null) {
                callActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }

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

    public void navigateToWatchlist(Context context,Activity callActivity) {

        if (context != null) {
            Intent intent = WatchlistActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            if(callActivity!=null) {
                callActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }

        }
    }

    public void navigateToSearch(Context context,Activity callActivity) {

        if (context != null) {
            Intent intent = SearchActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            if(callActivity!=null) {
                callActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }

        }
    }

    public void navigateToFirebaseUiSignIn(Context context) {

        if (context != null) {
            Intent intent = FirebaseUiSignInActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void navigateToAccount(Context context,Activity callActivity) {

        if (context != null) {
            Intent intent = AccountActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            if(callActivity!=null) {
                callActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        }
    }


    public void navigateToSettings(Context context,Activity callActivity) {
        if (context != null) {
            Intent intent = SettingsActivity.getCallingIntent(context);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(callActivity).toBundle());
        }
    }

    public void navigateToPhotoGallery(Context context,Activity callActivity) {
        if (context != null) {
            Intent intent = PhotoGalleryActivity.getCallingIntent(context);
          //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          //  PhotoGalleryActivity.startActivityForResult(intent, SUB_ACTIVITY_CREATE_USER);
            callActivity.startActivityForResult(intent,SUB_ACTIVITY_CREATE_USER);
            if(callActivity!=null) {
                callActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }

        }
    }

}
