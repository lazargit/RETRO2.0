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
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bumptech.glide.load.engine.GlideException;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.retroimage.bitmap.BitmapConverter;
import com.shamildev.retro.retroimage.core.ByteArray;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retroimage.views.RetroProfileImageView;
import com.shamildev.retro.ui.account.AccountActivity;
import com.shamildev.retro.ui.common.view.BaseViewFragment;

import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.listener.DialogListener;
import com.shamildev.retro.ui.photogallery.PhotoGalleryActivity;
import com.shamildev.retro.ui.register.RegisterActivity;
import com.shamildev.retro.ui.register.fragment.presenter.RegisterPresenter;
import com.shamildev.retro.ui.signin.fragment.view.SignInFragment;
import com.shamildev.retro.views.CircleButton;

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

    @Inject
    RegisterActivity mContext;
    @Inject
    AppUser appUser;

    @BindView(R.id.editText_username)
    EditText mEditTextUsername;
    @BindView(R.id.editText_email)
    EditText mEditTextEmail;
    @BindView(R.id.editText_password)
    EditText mEditTextPassword;
    @BindView(R.id.btn_edit_pic)
    CircleButton circleButtonEditPic;
    private DialogListener onDialogListener;
    private byte[] contact;


//    @BindView(R.id.btn_edit_pic)
//    Button btnEditPic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
        loadProfilePic();
        circleButtonEditPic.onPress(this::onClick_EditPic);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach: ");
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


    private void loadProfilePic() {
        profileImageView.src(appUser.getProfilePic(), retroImage, new RetroImageRequestListener() {
            @Override
            public GlideException onLoadFailed(GlideException e) {

                return null;
            }

            @Override
            public Drawable onResourceReady(Drawable resource) {

                return null;
            }
        });
    }

    public void onClick_EditPic() {



//        Intent intent = new Intent(application, PhotoGalleryActivity.class)
//                ;
//        Intent intent = PhotoGalleryActivity.getCallingIntent(application);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////
////
//        Bundle b = ActivityOptionsCompat
//                .makeSceneTransitionAnimation(getActivity(), profileImageView, "retroProfileImage")
//                .toBundle();
////
//        application.startActivity(intent, b);
//
//        Log.d(TAG, "onClick: click profileImage");


        Intent intent = new Intent(mContext, PhotoGalleryActivity.class);
        intent.putExtra(PhotoGalleryActivity.EXTRA_CONTACT, contact);

// Pass data object in the bundle and populate details activity.
        //intent.putExtra(PhotoGalleryActivity.EXTRA_CONTACT, contact);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(mContext,profileImageView, "profile");
        startActivityForResult(intent,PhotoGalleryActivity.RESULT_CODE, options.toBundle());

     // navigator.navigateToPhotoGallery(mContext,mContext);

    }

    @OnClick(R.id. button_register)
    public void onClick_ButtonRegister(Button button) {
        Log.e(TAG, "onClick_ButtonRegister: ");
        //onDialogListener.onLoadDialog("hhhhhhhhh!");

        presenter.checkInput(mEditTextUsername.getText().toString(),mEditTextEmail.getText().toString(),mEditTextPassword.getText().toString());

    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: "+requestCode+" "+resultCode+" "+data);
        if (requestCode == Navigator.SUB_ACTIVITY_CREATE_USER && resultCode == PhotoGalleryActivity.RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras != null) {
              //  Log.e(TAG, "onActivityResult:  extras: "+ extras.getString("keyName"));
              //  Log.e(TAG, "onActivityResult:  extras: "+ extras.getParcelable("bitmap"));
               // Bitmap b = extras.getParcelable("bitmap");
               // Log.e(TAG, "onActivityResult:  extras: height: "+b.getHeight()+" width: "+ b.getWidth());




              //  presenter.uploadImage(extras.getByteArray("bitmap"));


               // getFragmentById(R.id.fragmentContainer).onActivityResult(requestCode,resultCode,data);
            }
        }

        if (resultCode == PhotoGalleryActivity.RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras != null) {

               //  Log.e(TAG, "onActivityResult:  extras: "+ extras.getByteArray("bytes"));

               //  Bitmap b = extras.getParcelable("bitmap");
              //  Log.e(TAG, "onActivityResult:  extras: "+ b.getWidth());
                // Log.e(TAG, "onActivityResult:  extras: height: "+b.getHeight()+" width: "+ b.getWidth());

               // presenter.uploadImage(BitmapConverter.bitmapToByteArray(b));
//                profileImageView.src(appUser.getProfilePic(), retroImage, new RetroImageRequestListener() {
//                    @Override
//                    public GlideException onLoadFailed(GlideException e) {
//                       // mContext.supportStartPostponedEnterTransition();
//                        return null;
//                    }
//
//                    @Override
//                    public Drawable onResourceReady(Drawable resource) {
//                        //mContext.supportStartPostponedEnterTransition();
//                        Log.e(TAG, "onResourceReady: ");
//                        return null;
//                    }
//                });

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
