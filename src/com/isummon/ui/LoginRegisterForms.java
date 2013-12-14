package com.isummon.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.isummon.R;

/**
 * Created by horzwxy on 12/14/13.
 */
public class LoginRegisterForms extends RelativeLayout {

    private FormsColumn loginForms;
    private Activity activity;
    private FormsColumn registerForms;

    public LoginRegisterForms(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.activity = (Activity) context;

        setGravity(Gravity.CENTER);
        setLayoutParams(new LayoutParams(2 * getScreenXPixels(), ViewGroup.LayoutParams.WRAP_CONTENT));

        loginForms = new FormsColumn(R.layout.login_forms);
        addView(loginForms);

        registerForms = new FormsColumn(R.layout.register_forms);
        addView(registerForms);
        registerForms.setVisibility(GONE);
    }

    private class FormsColumn extends LinearLayout {
        private FormsColumn(final int layoutId) {
            super(activity);

            setLayoutParams(new LayoutParams(getScreenXPixels(), ViewGroup.LayoutParams.WRAP_CONTENT));
            setGravity(Gravity.CENTER_HORIZONTAL);
            activity.getLayoutInflater().inflate(
                    layoutId,
                    this,
                    true);
        }
    }

    private int getScreenXPixels() {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public void prepareForAnimation() {
        registerForms.setVisibility(VISIBLE);

        setMeasuredDimension(2 * getScreenXPixels(), getMeasuredHeight());

        ValueAnimator animator = ObjectAnimator.ofFloat(registerForms, "translationX", 0f, getScreenXPixels());
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1);
        //animator.start();

        ValueAnimator animator2 = ObjectAnimator.ofFloat(registerForms, "translationX", getScreenXPixels(), 0f);
        animator2.setInterpolator(new LinearInterpolator());
        animator2.setDuration(3000);
        //animator2.start();

        AnimatorSet as = new AnimatorSet();
        as.play(animator).before(animator2);
        as.start();

//
//        invalidate();
//        registerForms.setTranslationX(getScreenXPixels());
    }
}
