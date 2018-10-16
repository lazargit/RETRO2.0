package com.shamildev.retro.ui.register;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.View;

import com.shamildev.retro.R;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.BaseActivitySupport;
import com.shamildev.retro.ui.listener.DialogListener;
import com.shamildev.retro.ui.photogallery.PhotoGalleryActivity;
import com.shamildev.retro.ui.register.fragment.view.RegisterFragment;

/**
 * Created by Schamil on 30.10.2017.
 */

public class RegisterActivity extends BaseActivitySupport implements DialogListener {

    private static final String TAG = "RegisterActivity";
    private ProgressDialog dialog;

    public View getCoordinator() { return findViewById(R.id.coontainer);}


    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.e(TAG, "onCreate: started");

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new RegisterFragment());
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.e(TAG, "onPostResume: ");
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Log.e(TAG, "onContentChanged: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        getIncomingIntent();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: ");
        Intent intent = getIntent();
        Log.d(TAG, "getIncomingIntent: ");
        if (intent != null) {
            String image = intent.getStringExtra("image");
            Log.d(TAG, "getIncomingIntent: "+image);
        }


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState: ");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.e(TAG, "onRestoreInstanceState: PersistableBundle ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState: ");
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e(TAG, "onRequestPermissionsResult: " + requestCode);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: "+requestCode+" "+resultCode+" == "+Activity.RESULT_OK);

        if (requestCode == Navigator.SUB_ACTIVITY_CREATE_USER && resultCode == PhotoGalleryActivity.RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Log.e(TAG, "onActivityResult:  extras: "+ extras.getString("keyName"));
                Log.e(TAG, "onActivityResult:  extras: "+ extras.getParcelable("bitmap"));
                Bitmap b = extras.getParcelable("bitmap");
                Log.e(TAG, "onActivityResult:  extras: "+ b.getWidth());
                getFragmentById(R.id.fragmentContainer).onActivityResult(requestCode,resultCode,data);
            }
        }



    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.e(TAG, "onPointerCaptureChanged: ");
    }



    @Override
    public void onLoadDialog(String msg) {
        dialog = ProgressDialog.show(this, "",
                msg, true);
    }

    @Override
    public void onRemoveDialog() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
