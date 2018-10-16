package com.shamildev.retro.ui.register.fragment.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bumptech.glide.load.engine.GlideException;
import com.shamildev.retro.R;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.retroimage.bitmap.BitmapConverter;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retroimage.views.RetroProfileImageView;
import com.shamildev.retro.ui.common.view.BaseViewFragment;

import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.listener.DialogListener;
import com.shamildev.retro.ui.photogallery.PhotoGalleryActivity;
import com.shamildev.retro.ui.register.RegisterActivity;
import com.shamildev.retro.ui.register.fragment.presenter.RegisterPresenter;
import com.shamildev.retro.ui.signin.fragment.view.SignInFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Shamil Lazar on 18.09.2018.
 */
public class RegisterFragment extends BaseViewFragmentV4<RegisterPresenter> implements RegisterView {

    private static final String TAG = "RegisterFragment";

    @Inject
    Navigator navigator;

    @Inject
    Application application;

    @Inject
    RetroImage retroImage;

    @BindView(R.id.retroProfileImage)
    RetroProfileImageView profileImageView;


    @BindView(R.id.editText_username)
    EditText mEditTextUsername;
    @BindView(R.id.editText_email)
    EditText mEditTextEmail;
    @BindView(R.id.editText_password)
    EditText mEditTextPassword;
    private DialogListener onDialogListener;

//    @BindView(R.id.btn_edit_pic)
//    Button btnEditPic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AppCompatActivity activity = (AppCompatActivity) context;
        try {
            onDialogListener = (DialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must override onDialogListener");
        }
    }




    @Override
    public void makeToast(String message) {

    }

    @OnClick(R.id.btn_edit_pic)
    public void onClick_EditPic(Button button) {

        Log.d(TAG, "onClick: click profileImage");
        navigator.navigateToPhotoGallery(application,getActivity());

    }

    @OnClick(R.id. button_register)
    public void onClick_ButtonRegister(Button button) {
        Log.d(TAG, "onClick_ButtonRegister: ");
        //onDialogListener.onLoadDialog("hhhhhhhhh!");

        presenter.checkInput(mEditTextUsername.getText().toString(),mEditTextEmail.getText().toString(),mEditTextPassword.getText().toString());

    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: "+requestCode+" "+resultCode+" "+data);
        if (requestCode == Navigator.SUB_ACTIVITY_CREATE_USER && resultCode == PhotoGalleryActivity.RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Log.e(TAG, "onActivityResult:  extras: "+ extras.getString("keyName"));
                Log.e(TAG, "onActivityResult:  extras: "+ extras.getParcelable("bitmap"));
                Bitmap b = extras.getParcelable("bitmap");
                Log.e(TAG, "onActivityResult:  extras: height: "+b.getHeight()+" width: "+ b.getWidth());

                presenter.uploadImage(BitmapConverter.bitmapToByteArray(b));


               // getFragmentById(R.id.fragmentContainer).onActivityResult(requestCode,resultCode,data);
            }
        }


    }

    @Override
    public RetroProfileImageView profileImage() {
        return profileImageView;
    }

    @Override
    public void showSnackBar(Object obj) {
        Snackbar snackbar = Snackbar
                .make( ((RegisterActivity)getActivity()).getCoordinator(), "www.journaldev.com", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
