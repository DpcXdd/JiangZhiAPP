package com.example.jiangzhiapp.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * Created by JHO on 2017-04-26.
 */

public class AnimationUtil {

    public static void showPopupListViewAnim(View view) {
        RotateAnimation rorateAnim = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rorateAnim.setFillAfter(true);
        rorateAnim.setDuration(200);
        view.startAnimation(rorateAnim);
    }

    public static void hintPopupListViewAnim(View view) {
        RotateAnimation rorateAnim = new RotateAnimation(180, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rorateAnim.setFillAfter(true);
        rorateAnim.setDuration(200);
        view.startAnimation(rorateAnim);
    }
}
