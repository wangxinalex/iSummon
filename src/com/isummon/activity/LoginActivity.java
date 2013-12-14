package com.isummon.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import com.isummon.R;


/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 * @author xhzhang
 * @author horzwxy
 *
 */
public class LoginActivity extends Activity {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"};

    /**
     * The default email to populate the email field with.
     */
    public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";
    public static final int ANIMATION_DURATION = 100;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // Values for email and password at the time of the login attempt.
    private String mEmail;
    private String mPassword;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.register_forms).setTranslationX(getScreenX());

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.login_email);
        mPasswordView = (EditText) findViewById(R.id.password);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * do signing in work
     */
    public void login(View v) {
        //showSigningInProgress();
        //attemptLogin();
        // now, skip the authenticating logic
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * do registering work
     */
    public void register(View v) {

    }

    /**
     * transform to register view
     */
    public void toRegister(View v) {
        ValueAnimator loginAnimation = ObjectAnimator.ofFloat(
                findViewById(R.id.login_forms),
                "translationX", 0f, -getScreenX() );
        loginAnimation.setDuration(ANIMATION_DURATION);
        loginAnimation.setInterpolator(new LinearInterpolator());
        loginAnimation.start();

        ValueAnimator registerAnimation = ObjectAnimator.ofFloat(
                findViewById(R.id.register_forms),
                "translationX", getScreenX(), 0f );
        registerAnimation.setDuration(ANIMATION_DURATION);
        registerAnimation.setInterpolator(new LinearInterpolator());
        registerAnimation.start();
    }

    /**
     * transform to sign in view
     */
    public void toLogIn(View v) {
        ValueAnimator loginAnimation = ObjectAnimator.ofFloat(
                findViewById(R.id.login_forms),
                "translationX", -getScreenX(), 0f );
        loginAnimation.setDuration(ANIMATION_DURATION);
        loginAnimation.setInterpolator(new LinearInterpolator());
        loginAnimation.start();

        ValueAnimator registerAnimation = ObjectAnimator.ofFloat(
                findViewById(R.id.register_forms),
                "translationX", 0f, getScreenX() );
        registerAnimation.setDuration(ANIMATION_DURATION);
        registerAnimation.setInterpolator(new LinearInterpolator());
        registerAnimation.start();
    }

    private int getScreenX() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        mEmail = mEmailView.getText().toString();
        mPassword = mPasswordView.getText().toString();

        boolean shouldCancel = false;
        View focusView = null;

        // Check for a valid password.
        if (TextUtils.isEmpty(mPassword)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            shouldCancel = true;
        } else if (mPassword.length() < 4) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            shouldCancel = true;
        }

        // todo email check result overrides pwd check result
        // Check for a valid email address.
        if (TextUtils.isEmpty(mEmail)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            shouldCancel = true;
            // todo email format check
        } else if (!mEmail.contains("@")) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            shouldCancel = true;
        }

        if (shouldCancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
            showSigningInProgress();
            mAuthTask = new UserLoginTask();
            mAuthTask.execute((Void) null);
        }
    }

    private void showSigningInProgress() {
        ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage(getString(R.string.login_progress_signing_in));
        mDialog.setCancelable(true);
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //mAuthTask.cancel(true);
            }
        });
        mDialog.show();
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    private class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            mDialog.dismiss();

            if (success) {
                finish();
            } else {
                mPasswordView
                        .setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
}
