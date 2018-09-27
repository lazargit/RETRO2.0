package com.shamildev.retro.firebase.core;

import com.shamildev.retro.domain.repository.BaseRepository;
import com.shamildev.retro.firebase.repository.FirebaseRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;


@Module(includes = {FirebaseAuthModule.class})
//public class FirebaseModule {
//
//
////
////    @Provides
////    public FirebaseAuth auth() {
////        return FirebaseAuth.getInstance();
////        // firebaseMapper = new FirebaseMapper(appUser);
////
////    }
////    @Provides
////    public FirebaseUser firebaseUser(FirebaseAuth firebaseAuth) {
////        return firebaseAuth.getCurrentUser();
////        // firebaseMapper = new FirebaseMapper(appUser);
////
////    }
//
//
//
//
//    @Provides
//    public BaseRepository firebaseRepository(AppUser appUser,FirebaseAuth firebaseAuth){
//        Log.e("DAG",appUser.toString());
//
//        return new FirebaseRepository(appUser,firebaseAuth);
//    }
//
//
//
//
//}
public abstract class FirebaseModule {

    @Binds
    abstract BaseRepository firebaseRepository(FirebaseRepository repository);






}


