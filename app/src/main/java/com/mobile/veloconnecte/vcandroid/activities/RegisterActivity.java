package com.mobile.veloconnecte.vcandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.veloconnecte.vcandroid.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText username;
    private EditText password;
    private EditText second_password;
    private Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.login_edit_text_email);
        username = (EditText) findViewById(R.id.login_edit_text_username);
        password = (EditText) findViewById(R.id.login_edit_text_password);
        second_password = (EditText) findViewById(R.id.login_edit_text_second_password);
        register_button = (Button) findViewById(R.id.register_user_button);

        //TODO : do logical register
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password == second_password)
                {

                } else {
                    Toast toast = Toast.makeText(RegisterActivity.this, "Password must be the same", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
