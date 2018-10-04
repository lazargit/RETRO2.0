package com.shamildev.retro.fragments.account_header.fragment.model;

import com.shamildev.retro.fragments.account_header.fragment.presenter.AccountHeaderPresenter;
import com.shamildev.retro.ui.common.model.BaseModel;


/**
 * Created by Shamil Lazar.
 */

public abstract class AccountHeaderModel extends BaseModel<AccountHeaderPresenter> {

     public abstract void initData();

}
