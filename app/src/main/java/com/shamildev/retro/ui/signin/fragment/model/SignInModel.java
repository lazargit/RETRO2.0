package com.shamildev.retro.ui.signin.fragment.model;

import com.shamildev.retro.ui.common.model.BaseModel;
import com.shamildev.retro.ui.signin.fragment.presenter.SignInPresenter;


/**
 * Created by Shamil Lazar.
 */

public abstract class SignInModel extends BaseModel<SignInPresenter> {
    public abstract void initData();
    public abstract void logOut();
    public abstract void signInUser();
    public abstract void signInUserWithEmailAndPassword(String email,String password);
    public abstract void createUserWithEmailAndPassword(String email,String password);
}
