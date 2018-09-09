package com.shamildev.retro.firebase.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shamildev.retro.domain.error.BaseError;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.repository.BaseRepository;


import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;


/**
 * An implementation of {@link FirebaseRepository}.
 */
@Reusable
public final class FirebaseRepository implements BaseRepository {



    private final FirebaseUser mFUser;

    private  FirebaseAuth mAuth;
    //private  FirebaseAuth mAuthinstance;
    private AppUser appUser;

//    @Inject
//    FirebaseAuth mAuth;

    @Inject
    public FirebaseRepository(AppUser appUser) {
        this.appUser = appUser;
        //this.mAuthinstance=instance;

        this.mAuth = FirebaseAuth.getInstance();
        this.mFUser = mAuth.getCurrentUser();


        Log.e("TAG", "FirebaseAuth "+this.mAuth);
        Log.e("TAG", "FirebaseUser "+this.mFUser);
       // firebaseMapper = new FirebaseMapper(appUser);

    }




    @Override
    public Flowable<AppUser> initUser() {
        Log.d("FirebaseRepository", "user###>>>> :");
        mAuth = FirebaseAuth.getInstance();
        Log.d("FirebaseRepository", "user###>>>> :"+mAuth.getCurrentUser());
        if(mAuth.getCurrentUser()!=null){
            Log.d("FirebaseRepository", "user:success"+mAuth.getCurrentUser().getEmail());
        }else {
            mAuth.signInWithEmailAndPassword("shamil@aaa.de", "123456")
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                                Log.d("TAG", "sign in:success" + user.getEmail());
                                // updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "sig in:failure", task.getException());

                            }


                        }
                    });


        }
        return Flowable.empty();
    }

    @Override
    public Flowable<AppUser> checkUser() {
        return null;
    }

    @Override
    public Flowable<AppUser> signInWithEmailAndPassword() {

        Log.d("TAG", "FirebaseUser "+mFUser);
        return Flowable.create(e -> {
            try {
                if (mFUser == null) {
                    mAuth.signInWithEmailAndPassword("shamil@aaa.de", "123456")
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("TAG", "signInWithEmailAndPassword:onComplete");
                                    if (task.isSuccessful()) {
                                        FirebaseUser currentUser = mAuth.getCurrentUser();

                                        if (currentUser != null) {
                                            String photoPath = null;
                                            if(currentUser.getPhotoUrl() != null){
                                                photoPath = currentUser.getPhotoUrl().getPath();
                                            }
                                            appUser.setFirebaseUser(currentUser.getUid(), currentUser.getEmail(), currentUser.getDisplayName(),currentUser.getProviderId(), photoPath);
                                            e.onNext(appUser);
                                            e.onComplete();
                                        } else {
                                            String photoPath = null;
                                            if(currentUser.getPhotoUrl().getPath() != null){
                                                photoPath = currentUser.getPhotoUrl().getPath();
                                            }
                                            appUser.setFirebaseUser(currentUser.getUid(), currentUser.getEmail(), currentUser.getDisplayName(),currentUser.getProviderId(),photoPath);
                                            e.onNext(appUser);
                                            e.onComplete();
                                        }
                                    } else {
                                            e.onError(new BaseError(401,"Firebase sign in with Email and Password failed!"));

                                    }

                                }
                            })
                    ;
                }else{
                   // firebaseMapper.map(appUser,user);
                    String photoPath = null;
                    if(mFUser.getPhotoUrl() != null){
                        photoPath = mFUser.getPhotoUrl().getPath();
                    }

                    Log.e("TAG",appUser.toString());
                     appUser.setFirebaseUser(mFUser.getUid(), mFUser.getEmail(), mFUser.getDisplayName(),mFUser.getProviderId(),photoPath);
                    e.onNext(appUser);
                    e.onComplete();
                }


            } catch (Exception t) {
                e.onError(t);
            }


        }, BackpressureStrategy.BUFFER);



    }



    @Override
    public Flowable<AppUser> signIn(String token) {
        return null;
    }

    @Override
    public Flowable<String> signInWithFacebook() {
        return null;
    }

    @Override
    public Completable signOut() {
        return Completable.create(e -> {
            if (this.mAuth.getCurrentUser() != null) {
                Log.d("TAG", "FirebaseUser befor logout "+mAuth.getCurrentUser());


                mAuth.signOut();
                appUser.removetFirebaseUser();
                Log.d("TAG", "FirebaseUser after logout "+mAuth.getCurrentUser());
                e.onComplete();
            }
            // e.onError(new Exception("User not logged in!"));
        });


    }
}
