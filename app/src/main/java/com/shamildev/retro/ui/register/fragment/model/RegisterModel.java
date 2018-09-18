package com.shamildev.retro.ui.register.fragment.model;

import com.shamildev.retro.ui.common.model.BaseModel;
import com.shamildev.retro.ui.register.fragment.presenter.RegisterPresenter;


/**
 * Created by Shamil Lazar.
 */

public abstract class RegisterModel extends BaseModel<RegisterPresenter> {

    public abstract void createUser();

}
