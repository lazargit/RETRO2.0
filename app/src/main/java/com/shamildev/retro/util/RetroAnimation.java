package com.shamildev.retro.util;

import android.view.View;
import android.view.ViewPropertyAnimator;

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
                .scaleY(1.2F).scaleX(1.2F).setDuration(100)
                .withEndAction(() -> view.animate().scaleY(0).scaleX(0).setDuration(200))

                ;

    }
}
