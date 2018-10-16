package com.shamildev.retro.ui.common.presenter;

/**
 * Created by Shamil Lazar.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.shamildev.retro.ui.common.model.BaseModel;
import com.shamildev.retro.ui.common.view.MVPView;


/**
 * Abstract {@link Presenter} for all presenters to extend.
 *
 * @param <T> the type of the {@link MVPView}.
 */
public abstract class BasePresenter<T extends MVPView, M extends BaseModel> implements Presenter {

    protected final T view;
    protected final M model;


    protected BasePresenter(T view,M model) {
        this.view = view;
        this.model = model;
        this.model.setPresenter(this);
    }

    @Override
    public void onStart(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {


    }

    @Override
    public void onPause() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onEnd() {

    }
}