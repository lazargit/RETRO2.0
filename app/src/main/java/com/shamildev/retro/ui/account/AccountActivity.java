package com.shamildev.retro.ui.account;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Base64;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.firebase.ui.auth.IdpResponse;
import com.shamildev.retro.R;
import com.shamildev.retro.ui.account.fragment.view.AccountFragment;
import com.shamildev.retro.ui.common.BaseActivitySupport;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Schamil Lazar.
 */

public class AccountActivity extends BaseActivitySupport {


    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, AccountActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        //printhashkey();

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new AccountFragment());
        }
    }

    public void printhashkey(){

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.shamildev.retro",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

    }




    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}
