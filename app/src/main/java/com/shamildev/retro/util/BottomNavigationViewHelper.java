package com.shamildev.retro.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.shamildev.retro.R;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.home.HomeActivity;

/**
 * Created by Shamil Lazar on 03.10.2018.
 */
public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationView){
        Log.d(TAG, "setupBottomNavigationView: setting BottomNavigationView");
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.setTextVisibility(false);

    }

    public static void enableNavigation(final Context context,final Activity callActivity,BottomNavigationViewEx view, Navigator navigator) {
        view.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.ic_home:
                    navigator.navigateToHome(context,callActivity);
                    break;
                case R.id.ic_watchlist:
                    navigator.navigateToWatchlist(context,callActivity);
                    break;
                case R.id.ic_account:
                    navigator.navigateToAccount(context,callActivity);
                    break;
                case R.id.ic_search:
                    navigator.navigateToSearch(context,callActivity);
                    break;
            }
            return false;
        });





    }
}
