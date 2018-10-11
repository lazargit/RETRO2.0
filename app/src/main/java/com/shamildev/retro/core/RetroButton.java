package com.shamildev.retro.core;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Shamil Lazar on 09.10.2018.
 */
public class RetroButton extends AppCompatButton {

    public RetroButton(Context context) {
        super(context);
    }

    public RetroButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RetroButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);

    }

}
