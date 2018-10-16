package com.shamildev.retro.fragments.photo.fragment.model;

import com.shamildev.retro.fragments.photo.fragment.presenter.PhotoPresenter;
import com.shamildev.retro.ui.common.model.BaseModel;


/**
 * Created by Shamil Lazar.
 */

public abstract class PhotoModel extends BaseModel<PhotoPresenter> {

     public abstract void initData();


}
