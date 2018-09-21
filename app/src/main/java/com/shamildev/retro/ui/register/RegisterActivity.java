package com.shamildev.retro.ui.register;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.shamildev.retro.R;
import com.shamildev.retro.ui.common.BaseActivity;
import com.shamildev.retro.ui.register.fragment.view.RegisterFragment;
import com.shamildev.retro.ui.signin.SignInActivity;
import com.shamildev.retro.ui.splash.fragment.view.SplashFragment;

/**
 * Created by Schamil on 30.10.2017.
 */

public class RegisterActivity extends BaseActivity {


    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new RegisterFragment());
        }
    }



}
