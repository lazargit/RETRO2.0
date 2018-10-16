package com.shamildev.retro.fragments.photo.fragment.presenter;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */

import com.shamildev.retro.ui.common.presenter.Presenter;

/**
 * A {@link Presenter} that does some work and shows the results.
 */
public interface PhotoPresenter extends Presenter {



    void onError(Throwable t);


}
