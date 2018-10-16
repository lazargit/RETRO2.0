package com.shamildev.retro.fragments.gallery.fragment.model;

import com.shamildev.retro.fragments.gallery.fragment.presenter.GalleryPresenter;
import com.shamildev.retro.ui.common.model.BaseModel;


/**
 * Created by Shamil Lazar.
 */

public abstract class GalleryModel extends BaseModel<GalleryPresenter> {

     public abstract void initData();


}
