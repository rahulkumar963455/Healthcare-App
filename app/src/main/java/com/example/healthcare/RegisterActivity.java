package com.example.healthcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

     EditText reg_userName, reg_Email, reg_password, regRePassword;
     Button regBtn;
     TextView alreadyAccount;
     Database db=new Database(RegisterActivity.this,"healthcare",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        reg_userName=findViewById(R.id.edt_reg_username);
        reg_Email=findViewById(R.id.edt_reg_email);
        reg_password=findViewById(R.id.edt_reg_passowrd);
        regRePassword=findViewById(R.id.edt_confirm_reg_password);

        regBtn=findViewById(R.id.register_button);

        alreadyAccount=findViewById(R.id.already__account);
        SharedPreferences pref=getSharedPreferences("Login",MODE_PRIVATE);
        if(pref.getBoolean("flag",false)){
            Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        regBtn.setOnClickListener(v -> {
            String Username = reg_userName.getText().toString();
            String UserEmail = reg_Email.getText().toString();
            String UserPassowrd = reg_password.getText().toString();
            String ConfirmPassword = regRePassword.getText().toString();
            if(TextUtils.isEmpty(Username)){
                reg_userName.setError("Enter Username");
            } else if (!Patterns.EMAIL_ADDRESS.matcher(UserEmail).matches()) {
                 reg_Email.setError("Enter valid Email");
            } else if (TextUtils.isEmpty(UserPassowrd)) {
                reg_password.setError("Enter Password");
            } else if (!ConfirmPassword.equals(UserPassowrd)) {
                regRePassword.setError("Enter Re-Password");
            }else {
                if(isValid(ConfirmPassword)){
                    db.registerUser(Username,UserEmail,ConfirmPassword);
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);

                    SharedPreferences preferences=getSharedPreferences("share_prefs",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("username",Username);
                    editor.apply();

                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(this, "Enter Strong Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
    public static boolean isValid(String passwordhere){
        int f1=0, f2=0, f3=0;
        if(passwordhere.length()<8){
           return false;
        }else {

            for(int p=0;p<passwordhere.length();p++){
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1=1;

                }
            }
            for(int r=0;r<passwordhere.length();r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for(int q=0;q<passwordhere.length();q++){
                char c=passwordhere.charAt(q);
                if(c>=33&&c<=46 || c==64){
                    System.out.println(Character.isLetter(passwordhere.charAt(q)));
                    f3=1;
                }
            }
            if(f1==1&& f2==1 && f3==1){
                return true;
            }
            return false;
        }

    }
}