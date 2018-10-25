package com.shamildev.retro.views;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.animation.AnticipateInterpolator;

/**
 * Created by Shamil Lazar on 17.10.2018.
 */
public class CircleButton extends android.support.v7.widget.AppCompatButton {


    private static final String TAG = "CircleButton";

    public interface Callable {
        void call();
    }


    private Callable callable;



    public CircleButton(Context context) {
        super(context);
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    public void onPress(Callable fun) {
        callable = fun;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: DOWN");
                this.animate()
                        .scaleY(1.3F).scaleX(1.3F)
                        .setInterpolator(new AnticipateInterpolator())
                        .setDuration(150).start();
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: UP");
                this.animate()
                        .scaleY(1.0F).scaleX(1.0F)
                        .setInterpolator(new AnticipateInterpolator())
                        .setDuration(50)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                callable.call();
                            }
                        })
                        .start();
                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onTouchEvent: CANCEL");
                break;
        }

        return true;
    }




}
