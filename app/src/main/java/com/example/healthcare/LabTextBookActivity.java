package com.example.healthcare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LabTextBookActivity extends AppCompatActivity {
    EditText edtFullname, edtAddress, edtPin,edtContact;
    Button btnBack, btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_text_book);
        edtFullname=findViewById(R.id.edt_fullnameLTB);
        edtAddress=findViewById(R.id.edt_addressLTB);
        edtPin=findViewById(R.id.edt_pin_codeLTB);
        edtContact=findViewById(R.id.edt_contactLTB);
        btnBack=findViewById(R.id.btn_backLTB);
        btnBook=findViewById(R.id.book_btnLTB);

        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String time=intent.getStringExtra("time").toString();
        String date=intent.getStringExtra("date").toString();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LabTextDetailActivity.class));
                finish();
            }
        });

            btnBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String s1=edtFullname.getText().toString();
                    String s2=edtAddress.getText().toString();
                    String s3=edtPin.getText().toString();
                    String s4=edtContact.getText().toString();
                    if(s1.isEmpty()||s2.isEmpty()||s3.isEmpty()||s4.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Please fill information For Booking", Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences sharedPreferences = getSharedPreferences("share_prefs", MODE_PRIVATE);
                        String username = sharedPreferences.getString("username", "");
                        Database database = new Database(getApplicationContext(), "healthcare", null, 1);

                        database.addOrder(username, edtFullname.getText().toString(), edtAddress.getText().toString(), Integer.parseInt(edtPin.getText().toString()), edtContact.getText().toString(), Float.parseFloat(price[1].toString()), date.toString(), time.toString(), "Lab");
                        database.removeCart(username, "Lab");
                        Toast.makeText(LabTextBookActivity.this, "Order successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LabTextBookActivity.this, MainActivity.class));
                        finishAffinity();
                    }
                }
            });


    }
}