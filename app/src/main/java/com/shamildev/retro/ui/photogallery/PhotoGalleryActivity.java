package com.shamildev.retro.ui.photogallery;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Window;

import com.bumptech.glide.load.engine.GlideException;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.fragments.gallery.fragment.view.GalleryFragment;
import com.shamildev.retro.fragments.photo.fragment.view.PhotoFragment;
import com.shamildev.retro.retroimage.bitmap.BitmapConverter;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retroimage.views.RetroProfileImageView;
import com.shamildev.retro.ui.common.BaseActivitySupport;
import com.shamildev.retro.util.Permissions;
import com.shamildev.retro.util.SectionPagerAdapter;
import com.shamildev.retro.views.CircleButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Schamil on 30.10.2017.
 */

public class PhotoGalleryActivity extends BaseActivitySupport {

    private static final String TAG = "PhotoGalleryActivity";
    public static final String EXTRA_CONTACT = "imagebytes";
    public static int RESULT_CODE= 15;
    Integer SELECT_FILE = 0;



    @BindView(R.id.button_circle_camera)
    CircleButton circleButtonCamera;

    @BindView(R.id.button_circle_gallery)
    CircleButton circleButtonGallery;

    @BindView(R.id.retroProfileImage)
    RetroProfileImageView profileImageView;
    @Inject
    RetroImage retroImage;
    byte[] bytes;

    @Inject
    AppUser appUser;

    @Inject
    Application application;

    @Inject PhotoGalleryActivity activity;

    private static final int  VERIFY_PERMISSION_REQUEST = 1;
    private Unbinder butterKnifeUnbinder;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, PhotoGalleryActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimations();
        setContentView(R.layout.activity_photogallery);
        postponeEnterTransition();

        Log.d(TAG, "onCreate: started");

        butterKnifeUnbinder = ButterKnife.bind(this);
        if(checkPermissions(Permissions.PERMISSIONS)){

            if (savedInstanceState == null) {
              // addFragment(R.id.fragmentContainer, new GalleryFragment());
             //  setupViewPager();
            }
        }else{
            verifyPermissions(Permissions.PERMISSIONS);
        }

        circleButtonCamera.onPress(this::clickCamera);
        circleButtonGallery.onPress(this::clickGallery);
        loadProfilePic();



//        circleButton.onMyClickListener(new CircleButton.OnClickListener() {
//            @Override
//            public void onClick(View var1) {
//                Log.d(TAG, "onClick: 2");
//            }
//        });

     //   try {
          //  Class callerClass = Class.forName(str);
           // Log.e(TAG, "onCreate: get class Class "+callerClass.getCanonicalName());

          //  registerActivity = AppCompatActivity.class.cast(callerClass);
           // Log.e(TAG, "onCreate: get class Class "+callerClass.getSimpleName());
          //  AppCompatActivity.class.cast(callerClass).getIntent().putExtra("image","hallo!");;
            //registerActivity.getIntent().putExtra("image","hallo!");

//            String className         = "com.shamildev.retro.ui.register.RegisterActivity";
//            Class someClass          = Class.forName( className );
//            Constructor constructor  = (Constructor) someClass.getConstructor();
//            AppCompatActivity someInstance      = (AppCompatActivity) constructor.newInstance();


           // Log.e(TAG, "onCreate: ---------- "+registerActivity.getIntent());



//            Activity obj = (Activity) callerClass.newInstance();
//            Log.e(TAG, "onCreate: get class Class Activity"+obj);
//            obj.getIntent().putExtra("image","hallo");
       // } catch (ClassNotFoundException e) {
          //  e.printStackTrace();
        //}
    }

    private void loadProfilePic() {
        profileImageView.src(appUser.getProfilePic(), retroImage, new RetroImageRequestListener() {
            @Override
            public GlideException onLoadFailed(GlideException e) {
                startPostponedEnterTransition();
                return null;
            }

            @Override
            public Drawable onResourceReady(Drawable resource) {
                startPostponedEnterTransition();
                return null;
            }
        });
    }


    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT < 21) return;
        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.activity_slide_from_right);

      slide.excludeTarget(android.R.id.statusBarBackground, true);
        slide.excludeTarget(android.R.id.navigationBarBackground, true);
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        window.setEnterTransition(slide);




    }


    private void clickCamera() {

    }

    private void clickGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent,"Select file"),SELECT_FILE);
        // startActivityForResult(new Intent(Intent.ACTION_PICK).setType("image/*"), SELECT_FILE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode: "+requestCode+" resultCode: "+resultCode);
        if(resultCode == RESULT_OK){
            if(requestCode == SELECT_FILE){
                Uri selectedImage = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    bytes = BitmapConverter.bitmapToByteArray(bitmap);
                    profileImageView.src(bytes, retroImage, new RetroImageRequestListener() {
                        @Override
                        public GlideException onLoadFailed(GlideException e) {
                            return e;
                        }

                        @Override
                        public Drawable onResourceReady(Drawable resource) {

                            //data.putExtra("keyName", "hallo test"); // hard-code value for testing
                            //data.putExtra("bitmap",bitmap);
                           // data.putExtra("bitmap", bytes); // hard-code value for testing

                            Intent intent = new Intent();

                            // TODO replace with real value
                             intent.putExtra("keyName", "hallo test"); // hard-code value for testing
                         //    intent.putExtra("bitmap", resource); // hard-code value for testing
                            // setResult(RESULT_CODE, intent);
                            //intent.putExtra("image", bytes);
                            appUser.setProfilePic(bytes);
                            setResult(RESULT_CODE, intent);


                            return resource;
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }




            }



        }



    }

        @Override
    public void finish() {
        Log.d(TAG, "finish: ");
       // Intent intent = new Intent();

        // TODO replace with real value
       // intent.putExtra("keyName", "hallo test"); // hard-code value for testing
       //  intent.putExtra("bitmap", bytes); // hard-code value for testing
       // setResult(RESULT_CODE, intent);

        super.finish();
            //  supportFinishAfterTransition();
    }




    private void verifyPermissions(String[] permissions){
        Log.d(TAG, "verifyPermissions: verify permission");
        ActivityCompat.requestPermissions(PhotoGalleryActivity.this,permissions,VERIFY_PERMISSION_REQUEST);


    }

    public Boolean checkPermissions(String[] permissions){
        Log.d(TAG, "checkPermissions: check permissions array");
        for(int i=0 ; i < permissions.length; i++){
            String check = permissions[i];
            if(!checkPermission(check)){
                return false;
            }
        }
        return true;
    }

    public boolean checkPermission(String permission) {
        Log.d(TAG, "checkPermission: check permission");
       int perReq = ActivityCompat.checkSelfPermission(PhotoGalleryActivity.this,permission);
       if(perReq != PackageManager.PERMISSION_GRANTED){
           Log.e(TAG, "checkPermission: permissoin was not granded for: "+permission);
           return false;
       }else{
           Log.e(TAG, "checkPermission: permissoin was granded for: "+permission);
           return true;
       }
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }

    private Boolean clickFunction(){
        Log.d(TAG, "clickFunction: was clicked");
        navigator.navigateToHome(application, activity);
        return true;
    }




}
