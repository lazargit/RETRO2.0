package com.shamildev.retro.ui.search.fragment.presenter;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


import com.shamildev.retro.ui.common.presenter.Presenter;

/**
 * A {@link Presenter} that does some work and shows the results.
 */
public interface SearchPresenter extends Presenter {
    void toast(Object obj);

    void onError(Throwable t);
}
