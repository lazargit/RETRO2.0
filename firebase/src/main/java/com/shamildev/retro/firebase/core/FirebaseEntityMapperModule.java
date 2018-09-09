package com.shamildev.retro.firebase.core;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shamildev.retro.domain.models.AppUser;

import java.io.File;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Provides entity mapper dependencies.
 */
@Module
public class FirebaseEntityMapperModule {




    @Provides
    FirebaseMapper firebaseMapper(AppUser appUser){
        return new FirebaseMapper(appUser);
    }


}
