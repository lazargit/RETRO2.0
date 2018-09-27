package com.shamildev.retro.ui.splash.fragment.presenter;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.presenter.Presenter;

import java.util.HashMap;

/**
 * A {@link Presenter} that does some work and shows the results.
 */
public interface SplashPresenter extends Presenter {


    void toast(Object obj);
    void onError(Throwable t);

    void finishPreload(HashMap<String, ResultWrapper> map);

    void setBgTeaser(ResultWrapper wrapper);

    void configRetroImage(Configuration configuration);

    void setTestPerson(ResultWrapper wrapper);

    void signout();

    void setByteArrayPis(byte[] pic);

    void setByteArray(byte[] bytes);
}
