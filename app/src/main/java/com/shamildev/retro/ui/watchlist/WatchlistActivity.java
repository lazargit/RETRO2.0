package com.shamildev.retro.ui.watchlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shamildev.retro.R;
import com.shamildev.retro.ui.common.BaseActivity;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchlistFragment;

/**
 * Created by Schamil Lazar.
 */

public class WatchlistActivity extends BaseActivity {


    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, WatchlistActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new WatchlistFragment());
        }
    }


}
