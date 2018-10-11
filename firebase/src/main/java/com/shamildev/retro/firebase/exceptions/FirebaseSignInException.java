package com.shamildev.retro.firebase.exceptions;

import android.support.annotation.NonNull;

/**
 * Created by Shamil Lazar on 01.10.2018.
 */
public class FirebaseSignInException extends Exception {

    public FirebaseSignInException() {
    }

    public FirebaseSignInException(@NonNull String detailMessage) {
        super(detailMessage);
    }

    public FirebaseSignInException(@NonNull String detailMessage, @NonNull Throwable throwable) {
        super(detailMessage, throwable);
    }

    public FirebaseSignInException(@NonNull Throwable throwable) {
        super(throwable);
    }
}
