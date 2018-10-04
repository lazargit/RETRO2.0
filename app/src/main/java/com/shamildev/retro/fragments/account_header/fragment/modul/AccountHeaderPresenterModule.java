package com.shamildev.retro.fragments.account_header.fragment.modul;

import com.shamildev.retro.fragments.account_header.fragment.presenter.AccountHeaderPresenter;
import com.shamildev.retro.fragments.account_header.fragment.presenter.AccountHeaderPresenterImpl;
import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        AccountHeaderModelModule.class
})
public abstract class AccountHeaderPresenterModule {

    @Binds
    @PerFragment
    abstract AccountHeaderPresenter presenter(AccountHeaderPresenterImpl presenter);


}