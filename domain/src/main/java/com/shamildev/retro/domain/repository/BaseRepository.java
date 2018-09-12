package com.shamildev.retro.domain.repository;

import com.shamildev.retro.domain.models.AppUser;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Created by Shamil Lazar on 30.08.2018.
 */
public interface BaseRepository {
    Flowable<AppUser> initUser();
    Flowable<AppUser> checkUser();
    Flowable<AppUser> signInWithEmailAndPassword();
    Flowable<AppUser> signIn(String token);
    Flowable<AppUser> signInWithFacebook(String token);
    Completable signOut();


}
