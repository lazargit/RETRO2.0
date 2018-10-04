package com.shamildev.retro.fragments.account_header.fragment.modul;


import com.shamildev.retro.fragments.account_header.fragment.model.AccountHeaderModel;
import com.shamildev.retro.fragments.account_header.fragment.model.AccountHeaderModelImpl;
import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

 @Module
 public abstract class AccountHeaderModelModule {

        @Binds
        @PerFragment
        abstract AccountHeaderModel model(AccountHeaderModelImpl model);


 }