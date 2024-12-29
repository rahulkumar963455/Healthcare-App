package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView findDoctors=findViewById(R.id.CardFindDoctor);


        CardView exit=findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findDoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,FindDoctorActivity.class);
                startActivity(intent);
            }
        });
        CardView labText=findViewById(R.id.LabText);

        labText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,LabTextActivity.class);
                startActivity(intent);
            }
        });
        CardView orderDetail=findViewById(R.id.cardOrderDetail);
        orderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,OrderDetailActivity.class);
                startActivity(intent);
            }
        });
        CardView byMedicine=findViewById(R.id.CardBuyMedicine);
        byMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BuyMedicineActivity.class);
                startActivity(intent);
            }
        });

        CardView HealthArti=findViewById(R.id.CardHealthDoctor);
        HealthArti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, HealthActiclesActivity.class);
                startActivity(intent);
            }
        });







    }
}