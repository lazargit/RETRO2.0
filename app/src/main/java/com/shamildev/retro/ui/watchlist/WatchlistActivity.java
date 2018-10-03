package com.shamildev.retro.ui.watchlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.shamildev.retro.R;
import com.shamildev.retro.ui.common.BaseActivity;
import com.shamildev.retro.ui.common.BaseActivitySupport;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchlistFragment;
import com.shamildev.retro.util.BottomNavigationViewHelper;

import javax.inject.Inject;

/**
 * Created by Schamil Lazar.
 */

public class WatchlistActivity extends BaseActivitySupport {


    private static final String TAG = "WatchlistActivity";
    private static final int ACTIVITY_NUM = 1;
    @Inject
    WatchlistActivity mContext;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, WatchlistActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        Log.d(TAG, "onCreate: starting");
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new WatchlistFragment());
        }

        setupBottomNavigation();
    }

    /*
     * Bottomnavigationview setup
     */
    private void setupBottomNavigation() {

        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigationViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext,this,bottomNavigationViewEx,navigator);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }
}
