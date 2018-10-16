package com.shamildev.retro.ui.register.fragment.presenter;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.load.engine.GlideException;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.retroimage.bitmap.BitmapConverter;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.home.fragment.model.HomeModel;
import com.shamildev.retro.ui.home.fragment.view.HomeView;
import com.shamildev.retro.ui.register.fragment.model.RegisterModel;
import com.shamildev.retro.ui.register.fragment.view.RegisterView;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;

import javax.inject.Inject;


/**
 * Created by Shamil Lazar
 */

/**
 * An implementation of {@link SplashPresenter}.
 */
@PerFragment
public final class RegisterPresenterImpl extends BasePresenter<RegisterView, RegisterModel> implements RegisterPresenter {



    private DataConfig dataConfig;
    private AppConfig appConfig;


    @Inject
    RetroImage retroImage;

    @Inject
    RegisterPresenterImpl(
            AppConfig appConfig,
            RegisterView view,
            RegisterModel model,
            DataConfig dataConfig) {
        super(view, model);
        this.dataConfig = dataConfig;
        this.appConfig = appConfig;


    }


    @Override
    public void onStart(@Nullable Bundle savedInstanceState) {

    }


    @Override
    public void onError(Throwable t) {

        if (t.getCause() instanceof TMDBError) {
            TMDBError error = (TMDBError) t.getCause();
            Log.d("onError", "<<<<< " + error.getResponseCode() + " : " + error.getMessage() + " : " + error.getStatusCode() + " : " + error.getSuccess());

        } else {

        }
        Log.d("onError", t.getMessage());
    }

    @Override
    public void checkInput(String username, String email, String password) {
        if(username.equals("") || email.equals("") || password.equals("")){
            view.showSnackBar("complete your details!");
        }

    }

    @Override
    public void uploadImage(byte[] bytes) {
        model.createUser(bytes);
    }

    @Override
    public void pasteImage(byte[] bytes) {
        view.profileImage()
                .src(bytes, retroImage, new RetroImageRequestListener() {
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

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void toast(Object obj) {
        view.makeToast((String) obj);
    }


}