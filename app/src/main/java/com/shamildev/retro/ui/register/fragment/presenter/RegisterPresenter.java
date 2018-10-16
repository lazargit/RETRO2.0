package com.shamildev.retro.ui.register.fragment.presenter;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


import android.text.Editable;

import com.shamildev.retro.ui.common.presenter.Presenter;

/**
 * A {@link Presenter} that does some work and shows the results.
 */
public interface RegisterPresenter extends Presenter {
    void pasteImage(byte[] bytes);

    void toast(Object obj);
    void onError(Throwable t);

    void checkInput(String username, String email, String password);

    void uploadImage(byte[] bytes);
}
