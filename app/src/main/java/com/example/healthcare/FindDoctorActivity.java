package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        CardView exit=findViewById(R.id.cardFDBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctorActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        CardView familyphysician =findViewById(R.id.cardFamilyPhysicians);
        familyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          Intent intent=new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
          intent.putExtra("title","Family Physicians");
          startActivity(intent);

            }
        });
        CardView dietician =findViewById(R.id.cardFDDietecian);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                intent.putExtra("title","Dietician");
                startActivity(intent);
            }
        });

        CardView dentist=findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                intent.putExtra("title","Dentist");
                startActivity(intent);
            }
        });

        CardView surgeon=findViewById(R.id.cardFDSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                intent.putExtra("title","Surgeon");
                startActivity(intent);
            }
        });
        CardView carddiologists=findViewById(R.id.cardFDCardiologists);
        carddiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindDoctorActivity.this,DoctorDetailActivity.class);
                intent.putExtra("title","Carddiologists");
                startActivity(intent);
            }
        });

    }
}