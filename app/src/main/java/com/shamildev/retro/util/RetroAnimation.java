package com.shamildev.retro.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;

/**
 * Created by Shamil Lazar on 05.10.2018.
 */
public class RetroAnimation {

    public static  ViewPropertyAnimator PopUp(View view){

        return view.animate()
                .scaleY(1.3F).scaleX(1.3F)
                .withEndAction(() -> view.animate().scaleY(1).scaleX(1))
                ;


    }

    public static ViewPropertyAnimator PopAway(View view) {

       return view.animate()
                .scaleY(0F).scaleX(0F).setDuration(200)
                .setInterpolator(new AnticipateInterpolator())
               // .withEndAction(() -> view.animate().scaleY(0).scaleX(0).setDuration(200))

                ;

    }
    public static ViewPropertyAnimator ButtonPop(View view) {

        return view.animate()
                .scaleY(1.2F).scaleX(1.2F)
                .setInterpolator(new AnticipateInterpolator())
                .setDuration(200)
//                .withEndAction(() -> view.animate().scaleY(1).scaleX(1)
//                        .setDuration(100))

                ;

    }

    public static ViewPropertyAnimator ButtonPopDown(View view) {

        return view.animate()
                .scaleY(1.0F).scaleX(1.0F)
                .setInterpolator(new AnticipateInterpolator())
                .setDuration(200)
//                .withEndAction(() -> view.animate().scaleY(1).scaleX(1)
//                        .setDuration(100))

                ;

    }

    public static ViewPropertyAnimator ButtonPop2(View view) {
//        AnimatorSet as = new AnimatorSet();
//        as.playSequentially(ObjectAnimator.ofFloat(...), // anim 1
//        as.playSequentially(ObjectAnimator.ofFloat(...), // anim 1
//        ObjectAnimator.ofFloat(...), // anim 2
//        ObjectAnimator.ofFloat(...), // anim 3
//        ObjectAnimator.ofFloat(...)); // anim 4
//        as.setDuration(600);
//        as.start();
        return view.animate()
                .scaleY(1.2F).scaleX(1.2F).setDuration(100)
                .withEndAction(() -> view.animate().scaleY(0).scaleX(0).setDuration(100))

                ;

    }




    public static  ViewPropertyAnimator Pop(View view){

        return view.animate()
                .scaleY(1.3F).scaleX(1.3F)

                ;


    }

    public static  ViewPropertyAnimator Up(View view){

        return view.animate()
                .scaleY(1.0F).scaleX(1.0F)
                ;


    }
}
