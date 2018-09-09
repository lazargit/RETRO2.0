package com.shamildev.retro.firebase.core;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.repository.BaseRepository;
import com.shamildev.retro.firebase.repository.FirebaseRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shamil Lazar on 05.09.2018.
 */
@Module
public class FirebaseAuthModule {



    @Provides
    static FirebaseAuth provideFirebaseAuth() {
        FirebaseAuth instance = FirebaseAuth.getInstance();

        return instance;
    }




}
