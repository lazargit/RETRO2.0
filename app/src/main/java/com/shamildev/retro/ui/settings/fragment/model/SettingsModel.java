package com.shamildev.retro.ui.settings.fragment.model;

import com.shamildev.retro.ui.common.model.BaseModel;
import com.shamildev.retro.ui.settings.fragment.presenter.SettingsPresenter;


/**
 * Created by Shamil Lazar.
 */

public abstract class SettingsModel extends BaseModel<SettingsPresenter> {

     public abstract void saveData();
     public abstract void logOut();

}
