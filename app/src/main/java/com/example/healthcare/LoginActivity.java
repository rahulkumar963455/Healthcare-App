package com.example.healthcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.NetworkRegistrationInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    TextView regisNewUser;
    EditText inputUserName, inputPassword;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Database db=new Database(LoginActivity.this,"healthcare",null,1);

        regisNewUser=findViewById(R.id.register_for_new_user);
        inputPassword=findViewById(R.id.edt_passowrd);
        inputUserName=findViewById(R.id.edt_username);
        loginButton=findViewById(R.id.login_button);

        SharedPreferences preferences=getSharedPreferences("Login",MODE_PRIVATE);
        Boolean check=preferences.getBoolean("username",false);
        if(check){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finishAffinity();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userNAme;
                userNAme=inputUserName.getText().toString();
                String userPassword;
                userPassword=inputPassword.getText().toString();
                if(TextUtils.isEmpty(userNAme)){
                    inputUserName.setError("Enter UserName");
                } else if (TextUtils.isEmpty(userPassword)) {
                    inputPassword.setError("Enter Password");
                }else {
                    if(db.loginUser(userNAme,userPassword)==1){
                        Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        SharedPreferences sharedPreferences=getSharedPreferences("Login",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("flag",true);
                        editor.apply();
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        regisNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}