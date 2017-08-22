package com.mobile.veloconnecte.vcandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobile.veloconnecte.vcandroid.R;
import com.mobile.veloconnecte.vcandroid.entities.User;
import com.mobile.veloconnecte.vcandroid.utils.database.UserManager;
import com.mobile.veloconnecte.vcandroid.utils.volley.MyVolley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    //private CheckBox password_save;
    private Button login_button;
    private Button register_button;

    UserManager userManager;

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.login_edit_text_username);
        password = (EditText) findViewById(R.id.login_edit_text_password);
        //password_save = (CheckBox) findViewById(R.id.login_check);
        login_button = (Button) findViewById(R.id.login_button);
        register_button = (Button) findViewById(R.id.register_button);

        //TODO : change login method
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userManager = new UserManager(LoginActivity.this);

                //currentUser = userManager.getUserByUsername(username.getText().toString());

                //if (currentUser != null)
                //{
                    Intent intent = new Intent(LoginActivity.this, RideListActivity.class);
                    LoginActivity.this.startActivity(intent);
                //}
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
    }

    private void login(String username, String password){
        String url = "http://my-json-feed";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        // Access the RequestQueue through your singleton class.
        MyVolley.getInstance(LoginActivity.this).addToRequestQueue(jsObjRequest);
    }
}
