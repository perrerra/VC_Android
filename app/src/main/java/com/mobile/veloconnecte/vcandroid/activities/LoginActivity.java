package com.mobile.veloconnecte.vcandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.veloconnecte.vcandroid.R;
import com.mobile.veloconnecte.vcandroid.entities.User;
import com.mobile.veloconnecte.vcandroid.utils.database.UserManager;

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

                currentUser = userManager.getUserByUsername(username.getText().toString());

                if (currentUser != null)
                {
                    Intent intent = new Intent(LoginActivity.this, RideListActivity.class);
                    LoginActivity.this.startActivity(intent);
                }
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
}
