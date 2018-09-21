package com.shamildev.retro.ui.search;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shamildev.retro.R;
import com.shamildev.retro.ui.account.AccountActivity;
import com.shamildev.retro.ui.common.BaseActivity;
import com.shamildev.retro.ui.search.fragment.view.SearchFragment;
import com.shamildev.retro.ui.splash.fragment.view.SplashFragment;

/**
 * Created by Schamil on 30.10.2017.
 */

public class SearchActivity extends BaseActivity {


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
    }



}
