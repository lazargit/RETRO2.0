package com.shamildev.retro.ui.search;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.shamildev.retro.R;
import com.shamildev.retro.ui.account.AccountActivity;
import com.shamildev.retro.ui.common.BaseActivity;
import com.shamildev.retro.ui.common.BaseActivitySupport;
import com.shamildev.retro.ui.home.HomeActivity;
import com.shamildev.retro.ui.search.fragment.view.SearchFragment;
import com.shamildev.retro.ui.splash.fragment.view.SplashFragment;
import com.shamildev.retro.util.BottomNavigationViewHelper;

import javax.inject.Inject;

/**
 * Created by Schamil on 30.10.2017.
 */

public class SearchActivity extends BaseActivitySupport {

    private static final String TAG = "SearchActivity";
    private static final int ACTIVITY_NUM = 3;
    @Inject
    SearchActivity mContext;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new SearchFragment());
        }
        Log.d(TAG, "onCreate: starting");
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
