package com.shamildev.retro.fragments.photo.fragment.view;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shamildev.retro.R;
import com.shamildev.retro.fragments.gallery.fragment.view.GalleryFragment;
import com.shamildev.retro.fragments.photo.fragment.presenter.PhotoPresenter;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.retroimage.bitmap.BitmapConverter;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.photogallery.PhotoGalleryActivity;
import com.shamildev.retro.ui.register.RegisterActivity;
import com.shamildev.retro.util.Permissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.support.v4.app.ShareCompat.EXTRA_CALLING_ACTIVITY;

/**
 * Created by Shamil Lazar.
 * <p>
 * A fragment implementation of {@link PhotoView}.
 */
public final class PhotoFragment extends BaseViewFragmentV4<PhotoPresenter> implements PhotoView {

    private static final String TAG = "PhotoFragment";

    @Inject
    Navigator navigator;
    @Inject
    Application application;

    @Inject
    RetroImage retroImage;

    private static final int  GALLERY_FRAGMENT_NUM = 2;
    private static final int  PHOTO_FRAMENT_NUM = 1;
    private static final int CAMERA_REQUEST_CODE = 12;


    @OnClick(R.id.btn_launch_camera)
    public void onClick_ButtonLaunchCamera(Button button) {
      //  Log.d(TAG, "onClick_ButtonLaunchCamera: click "+((PhotoGalleryActivity)getActivity()).getCurrentTabNum());
       // if(((PhotoGalleryActivity)getActivity()).getCurrentTabNum() == PHOTO_FRAMENT_NUM){

            /*
             if camera permission ok
             */
            if(((PhotoGalleryActivity)getActivity()).checkPermission(Permissions.CAMERA_PERMISSIONS[0])){
                Log.d(TAG, "onClick_ButtonLaunchCamera: camera starting");
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_REQUEST_CODE);

            }else{
                Intent intent = new Intent(getActivity(),PhotoGalleryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
       // }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.e(TAG, "onCreateView: starting "+getActivity());
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }


    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "onAttach: start");
        super.onAttach(context);



    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: onResume");


    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: starting");


    }

    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");
        if(requestCode == CAMERA_REQUEST_CODE){
            Log.e(TAG, "onActivityResult: photo"+getActivity()+" --- ");

        Log.d(TAG, "finish: ");

             Bitmap bitmap  = (Bitmap) data.getExtras().get("data");
             byte[] bytes = BitmapConverter.bitmapToByteArray(bitmap);

        Intent intent = new Intent();

        // TODO replace with real value
        intent.putExtra("keyName", "hallo test"); // hard-code value for testing
        intent.putExtra("bitmap",bitmap);
        getActivity().setResult(PhotoGalleryActivity.RESULT_CODE, intent);



             getActivity().finish();



//            ComponentName result = getActivity().getCallingActivity();
//            if (result == null) {
//                getActivity().getIntent().getParcelableExtra(EXTRA_CALLING_ACTIVITY);
//                Log.e(TAG, "onActivityResult: photo"+ getActivity().getIntent().getClass());
//            }

//            Bitmap bitmap  = (Bitmap) data.getExtras().get("data");
//            getActivity().getIntent().putExtra("TEST","hallo");
//            Intent intent = new Intent(getActivity(), RegisterActivity.class);
//            intent.putExtra("image","hallo from photo");
//            startActivity(intent);
//
//            getActivity().finish();

//            try {
//                Intent intent = new Intent(getActivity(),)
//
//            }


        }
    }
}