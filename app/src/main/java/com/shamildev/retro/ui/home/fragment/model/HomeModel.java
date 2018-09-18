package com.shamildev.retro.ui.home.fragment.model;

import com.shamildev.retro.ui.common.model.BaseModel;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenter;


/**
 * Created by Shamil Lazar.
 */

public abstract class HomeModel extends BaseModel<HomePresenter> {

    public abstract void initConfiguration();

}
