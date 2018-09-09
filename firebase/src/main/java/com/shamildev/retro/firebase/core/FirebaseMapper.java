package com.shamildev.retro.firebase.core;


import com.google.firebase.auth.FirebaseUser;
import com.shamildev.retro.domain.models.AppUser;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar on 05.09.2018.
 */
public class FirebaseMapper {

    private final AppUser appuser;


    @Inject
    public FirebaseMapper(AppUser appUser) {
        this.appuser = appUser;

    }

    public AppUser map(AppUser appUser) {

        return this.appuser;
    }
}
