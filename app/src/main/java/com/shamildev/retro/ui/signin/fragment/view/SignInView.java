package com.shamildev.retro.ui.signin.fragment.view;

import android.widget.Button;
import android.widget.TextView;

import com.shamildev.retro.ui.common.view.MVPView;


/**
 * Created by Shamil Lazar.
 */
public interface SignInView extends MVPView {


    Button getButton_signin();
    Button getButton_facebook_signin();
    Button getButton_twitter_signin();
    void loginFb();

}
