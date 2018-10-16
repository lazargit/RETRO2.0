package com.shamildev.retro.ui.register.fragment.view;

import com.shamildev.retro.retroimage.views.RetroProfileImageView;
import com.shamildev.retro.ui.common.view.MVPView;

/**
 * Created by Shamil Lazar on 18.09.2018.
 */
public interface RegisterView extends MVPView {

    void showSnackBar(Object obj);
    RetroProfileImageView profileImage();

}
