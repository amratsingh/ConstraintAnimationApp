package com.chetu.amrats.constraintanimationapp;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.ChangeBounds;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout mConstraintLayout; // cache the ConstraintLayout
    private boolean mOld = true;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        mConstraintLayout = findViewById(R.id.activity_main);
        tv = findViewById(R.id.text);
    }

    public void toggle(View view) {
        ChangeBounds transition = new ChangeBounds();
        transition.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
//        transition.setInterpolator(new AccelerateDecelerateInterpolator());
//        transition.setInterpolator(new AccelerateInterpolator());
//        transition.setInterpolator(new AnticipateInterpolator());
//        transition.setInterpolator(new BounceInterpolator());
//        transition.setInterpolator(new CycleInterpolator(1.0f));
//        transition.setInterpolator(new DecelerateInterpolator(1.0f));
//        transition.setInterpolator(new LinearInterpolator());
//        transition.setInterpolator(new FastOutLinearInInterpolator());
//        transition.setInterpolator(new FastOutSlowInInterpolator());
//        transition.setInterpolator(new LinearOutSlowInInterpolator());
        transition.setDuration(1200);

        ConstraintSet mConstraintSet = new ConstraintSet(); // create a Constraint Set
        mConstraintSet.clone(mConstraintLayout);
        TransitionManager.beginDelayedTransition(mConstraintLayout, transition);

        if (mOld = !mOld) {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            mConstraintSet.constrainWidth(tv.getId(), ConstraintSet.WRAP_CONTENT);
            mConstraintSet.connect(tv.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
            mConstraintSet.applyTo(mConstraintLayout);
        } else {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
            mConstraintSet.constrainWidth(tv.getId(), 0);
            mConstraintSet.clear(tv.getId(), ConstraintSet.BOTTOM);
            mConstraintSet.applyTo(mConstraintLayout);
        }
    }
}