package com.shamildev.retro.ui.common;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.transition.TransitionInflater;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.util.Log;

import android.view.View;

import com.shamildev.retro.R;
import com.shamildev.retro.core.TransitionHelper;
import com.shamildev.retro.navigation.Navigator;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */
public abstract class BaseActivitySupport extends AppCompatActivity implements HasSupportFragmentInjector, AppBarLayout.OnOffsetChangedListener {

    @Inject
    protected Navigator navigator;

    @Inject
    @Named(BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER2)
    protected FragmentManager fragmentManager;

    @Inject AppCompatActivity activity;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }



    protected  void addFragment(@IdRes int containerViewId, Fragment fragment) {
        Log.d("resplace",">>"+fragment.getClass().getSimpleName());
        fragmentManager
                .beginTransaction()

                .add(containerViewId,fragment)
                .addToBackStack(fragment.getClass().getSimpleName())

                .commit();
    }


    protected  void replaceFragment(@IdRes int containerViewId, Fragment fragment, String tag) {

        fragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,android.R.anim.slide_out_right,android.R.anim.slide_in_left)
               // .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(containerViewId,fragment,tag)
                .addToBackStack(tag)
                .commit();


    }




    protected  void addFragment(@IdRes int containerViewId, Fragment fragment, @AnimRes int enterAni, @AnimRes int exitAni) {
        Log.d("replace",">>"+fragment.getClass().getSimpleName());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(enterAni,exitAni,enterAni,exitAni);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(containerViewId,fragment,fragment.getClass().getSimpleName()).commit();


    }

    protected  void removeFragment( String tag, @AnimRes int enterAni, @AnimRes int exitAni) {
        Log.d("remove",">>"+tag);
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(enterAni,exitAni,enterAni,exitAni);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.remove(fragmentByTag).commit();


    }
    protected Fragment getFragmentByTag(String tag) {
        Log.d("getFragmentByTag",">>"+tag);
        return fragmentManager.findFragmentByTag(tag);



    }
    protected Fragment getFragmentById(@IdRes int containerViewId) {
        Log.d("getFragmentById",">>"+containerViewId);
        return fragmentManager.findFragmentById(containerViewId);



    }


    @SuppressWarnings("unchecked") void transitionTo(Intent i) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }




}
