package com.shamildev.retro.ui.home;
import android.os.Bundle;

import com.shamildev.retro.R;
import com.shamildev.retro.ui.common.BaseActivity;
import com.shamildev.retro.ui.splash.fragment.view.SplashFragment;

/**
 * Created by Schamil on 30.10.2017.
 */

public class HomeActivity extends BaseActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new SplashFragment());
        }
    }



}
