package com.example.apple.yunqiao_weex.Activity.FaceBook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.yunqiao_weex.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FacebookActivity extends AppCompatActivity {
    private static final String TAG = "FacebookActivity";
    @BindView(R.id.facebook_login)
    TextView facebookLogin;

    CallbackManager callbackManager;

    @BindView(R.id.facebook_logout)
    TextView facebookLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        ButterKnife.bind(this);
        //每次进入之前先退出facebook
        LoginManager.getInstance().logOut();


        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.e(TAG, "onSuccess: " + "token:" + loginResult.getAccessToken().getToken() + "userId" + loginResult.getAccessToken().getUserId());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.e(TAG, "onCancel: ");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.e(TAG, "onError: ");
                    }
                });

    }

    @OnClick({R.id.facebook_login,R.id.facebook_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.facebook_login:
                //登录
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","user_friends","email"));
                break;
            case R.id.facebook_logout:
                Toast.makeText(this, "ok", Toast.LENGTH_LONG).show();
                LoginManager.getInstance().logOut();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
