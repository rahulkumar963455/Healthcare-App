package com.example.healthcare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class BuyMedicineDetailActivity extends AppCompatActivity {

    TextView tvpackageName, tvprice;
    EditText edtList;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    Button btnBack, btnAddtoCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_detail);
        edtList = findViewById(R.id.edt_buy_medicineBMD);
        tvprice = findViewById(R.id.txt_priceBMD);
        tvpackageName = findViewById(R.id.buy_medicine_textBMD);
        btnBack = findViewById(R.id.back_btn_BMD);
        btnAddtoCart = findViewById(R.id.add_to_cartBMD);

        Intent intent = getIntent();

        if (intent != null) {
            tvpackageName.setText(intent.getStringExtra("text1"));
            tvprice.setText("Price " + intent.getStringExtra("text3") + "/-");
            edtList.setText(intent.getStringExtra("text2"));
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailActivity.this, BuyMedicineActivity.class));
                finish();
            }
        });

        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("share_prefs", MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tvpackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database database = new Database(getApplicationContext(), "healthcare", null, 1);
                if (database.checkCart(username, product) == 1) {
                    Toast.makeText(BuyMedicineDetailActivity.this, "Already cart Added", Toast.LENGTH_SHORT).show();
                } else {
                    database.addCart(username, product, price, "Medicine");
                    Toast.makeText(BuyMedicineDetailActivity.this, "Record inserted to the cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailActivity.this, BuyMedicineActivity.class));
                }
            }
        });

    }
}