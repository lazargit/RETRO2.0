package com.shamildev.retro.ui.photogallery;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.shamildev.retro.R;
import com.shamildev.retro.fragments.gallery.fragment.view.GalleryFragment;
import com.shamildev.retro.fragments.photo.fragment.view.PhotoFragment;
import com.shamildev.retro.ui.common.BaseActivity;
import com.shamildev.retro.ui.common.BaseActivitySupport;
import com.shamildev.retro.ui.register.RegisterActivity;
import com.shamildev.retro.ui.register.fragment.view.RegisterFragment;
import com.shamildev.retro.ui.settings.SettingsActivity;
import com.shamildev.retro.util.Permissions;
import com.shamildev.retro.util.SectionPagerAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Schamil on 30.10.2017.
 */

public class PhotoGalleryActivity extends BaseActivitySupport {

    private static final String TAG = "PhotoGalleryActivity";
    public static int RESULT_CODE= 5;

    @BindView(R.id.viewpager_photoGallery)
    ViewPager viewPager;
    @BindView(R.id.viewpager_tabsBottom)
    TabLayout  tabLayout;






    private static final int  VERIFY_PERMISSION_REQUEST = 1;
    private Unbinder butterKnifeUnbinder;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, PhotoGalleryActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photogallery);
        Log.d(TAG, "onCreate: started");

        butterKnifeUnbinder = ButterKnife.bind(this);
        if(checkPermissions(Permissions.PERMISSIONS)){

            if (savedInstanceState == null) {
              // addFragment(R.id.fragmentContainer, new GalleryFragment());
               setupViewPager();
            }
        }else{
            verifyPermissions(Permissions.PERMISSIONS);
        }



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


//    @Override
//    public void finish() {
//        Log.d(TAG, "finish: ");
//        Intent intent = new Intent();
//
//        // TODO replace with real value
//        intent.putExtra("keyName", "hallo test"); // hard-code value for testing
//
//        setResult(RESULT_CODE, intent);
//        super.finish();
//    }


    /**
     * Retrurn ViewPager current item
     * 0 = GalleryFragment
     * 1 = PhotoFragment
     * @return
     */
    public int getCurrentTabNum(){
        return viewPager.getCurrentItem();

    }
    private void setupViewPager(){

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        sectionPagerAdapter.addFragment(new GalleryFragment());
        sectionPagerAdapter.addFragment(new PhotoFragment());


        viewPager.setAdapter(sectionPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(getString(R.string.gallery));
        tabLayout.getTabAt(1).setText(getString(R.string.photo));


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


}
