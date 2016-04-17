package com.example.stas.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import com.facebook.login.widget.ProfilePictureView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Register extends ActionBarActivity {

    //vars declare
    EditText fname,lname,_phone,_gender;
    ProfilePictureView profilePictureView;
    Button register;
    String f_name,l_name,gender,facebook_id,full_name,phone,pic;


    //host Adress
    private static final String REGISTER_URL = "http://sapironmyway.pe.hu/register.php";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();  //get data from another activity
        setContentView(R.layout.activity_register);


        //display to user his data
         f_name = intent.getStringExtra("f_name");
         l_name = intent.getStringExtra("l_name");
         gender = intent.getStringExtra("gender");


        //fill the text boxes
        _phone=(EditText) findViewById(R.id.phone);
        fname = (EditText) findViewById(R.id.name);   //
        fname.setText(f_name);
        fname.setEnabled(false);
        lname = (EditText) findViewById(R.id.lname);   //
        lname.setText(l_name);
        lname.setEnabled(false);

         facebook_id = intent.getStringExtra("id");
         pic = intent.getStringExtra("picture");

        profilePictureView = (ProfilePictureView) findViewById(R.id.picture); //show profile picture
        profilePictureView.setProfileId(facebook_id);
        register = (Button) findViewById(R.id.register);

        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               register(facebook_id, f_name, l_name, _phone.getText().toString(), gender);
                Intent back = new Intent(Register.this, MainActivity.class);
                startActivity(back);

            }
        };

        register.setOnClickListener(oclBtnOk);


    }

    // Send the data to php page
    private void register(String facebook_id, String f_name,String lname, String phone,String gender) {
        String urlSuffix = "?user_id="+facebook_id+"&user_name="+f_name+"&user_lname="+lname+"&user_phone="+phone+"&gender="+gender;

        System.out.println(urlSuffix);

        class RegisterUser extends AsyncTask<String, Void, String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;
                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }
}