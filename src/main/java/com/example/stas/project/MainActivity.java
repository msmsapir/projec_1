package com.example.stas.project;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends Activity {

        private CallbackManager callbackManager;
        private TextView info;
        private LoginButton loginButton;
        private Button next,stas;

        String user;
        String Access;

        String facebook_id,f_name, m_name, l_name, gender, profile_image, full_name, email_id;
        ProgressDialog progress;
        TextView tv;
        ProfilePictureView profilePictureView;
        Button find,publish;





    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);



                    //generate Hash key for facebook sdk
            try {
                PackageInfo info = getPackageManager().getPackageInfo(
                        "com.example.stas.project",
                        PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {

            } catch (NoSuchAlgorithmException e) {

            }


            FacebookSdk.sdkInitialize(getApplicationContext());
            callbackManager = CallbackManager.Factory.create();
            setContentView(R.layout.activity_main);
            loginButton = (LoginButton)findViewById(R.id.login_button);


            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {  // Facebook Log in button activities
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Profile profile = Profile.getCurrentProfile();  //create profile object

                    if (profile != null) {                  //get data
                        facebook_id = profile.getId();
                        f_name = profile.getFirstName();
                        m_name = profile.getMiddleName();
                        l_name = profile.getLastName();
                        full_name = profile.getName();
                        profile_image = profile.getProfilePictureUri(400, 400).toString();


                    }

                    // on Successfull  login keep user detail on local storage
                    SharedPreferences sharedPref = getSharedPreferences("mySettings", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    SharedPreferences sharedPref2 = getSharedPreferences("mySettings2", MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = sharedPref.edit();
                    editor.putString("mySetting", facebook_id);
                    editor.putString("mySetting2", full_name);
                    editor.commit();


                    //use facebook graph get user's data
                    GraphRequest request = GraphRequest.newMeRequest(
                            AccessToken.getCurrentAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(
                                        JSONObject object,
                                        GraphResponse response) {
                                    gender = object.optString("gender"); //gender is a part of Graph and not profile object

                                    // Transfer Data to Register Activity
                                    Intent myIntent = new Intent(MainActivity.this, Register.class);
                                    myIntent.putExtra("full_name", full_name); //Optional parameters
                                    myIntent.putExtra("f_name", f_name); //Optional parameters
                                    myIntent.putExtra("l_name", l_name); //Optional parameters
                                    myIntent.putExtra("id", facebook_id); //Optional parameters
                                    myIntent.putExtra("gender", gender); //Optional parameters
                                    myIntent.putExtra("picture", profile_image); //Optional parameters
                                    MainActivity.this.startActivity(myIntent);


                                }
                            });
                    Bundle parameters = new Bundle();  //Bandle is a part of facebook grpah
                    parameters.putString("fields", "id,gender,link");
                    request.setParameters(parameters);
                    request.executeAsync();

                }


                @Override
                public void onCancel() {
                    info.setText("Login attempt cancelled.");
                }

                @Override
                public void onError(FacebookException e) {
                    info.setText("Login attempt failed.");
                }
            });


        }


    public void onResume() {  //when it is not the first time, user is logged in automaticly
        super.onResume();

        SharedPreferences sharedPref = getSharedPreferences("mySettings", MODE_PRIVATE);

        String get = sharedPref.getString("mySetting", null); //get data from local store
        String get2 = sharedPref.getString("mySetting2", null); //get data from local store

        if(get!=(null)) {


            tv = (TextView)findViewById(R.id.info);
            tv.setText("Welcome" + "  " + get2);
            profilePictureView = (ProfilePictureView) findViewById(R.id.picture); //show profile picture
            profilePictureView.setProfileId(get);


            Button orderButton = (Button)findViewById(R.id.post);
    

            orderButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Post.class);
                    startActivity(intent);
                }

            });








            Button find = (Button)findViewById(R.id.find_btn);

            find.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Find_Ride.class);
                    startActivity(intent);
                }

            });



        }


        }





        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }



    }