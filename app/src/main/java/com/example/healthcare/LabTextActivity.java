package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class LabTextActivity extends AppCompatActivity {

    private String[][] packages={
            {"Packages 1 : Full Body Checkup","","","","999"},
            {"Packages 2 : Blood Glucose Fasting","","","","599"},
            {"Packages 3 : COVID=19 Antibody - IgG","","","","899"},
            {"Packages 4 : Thyroid Check","","","","399"},
            {"Packages 5 : Immunity Check","","","","799"}


    };
    private String[] package_details ={
            "Blood Glucose Fasting \n"+
                    "HbA1c\n"+
                    "Iron studies\n"+
                    "Kidney Function Test\n"+
                    "KDG Lactate Dehydrogenase, Serum\n"+
                    "Lipid Profile\n"+
                    "Liver Function Test",
            "Blood Glucose Fasting",
            "COVID-19 Antibody - IgG",
            "Thyroid Profile-Total(T3, T4 & TSH Ultra-Sensitive)",
            "Complete Hemogram\n" +
            "CRP (C Reactive Protein) Quantitative, Serum\n"+
            "Iron Studies \n" +
            "Kidney Function Test"+
            "Vitamin D Total-25 Hydroxy\n"+
            "Liver Function Test\n"+
            "Lipid Profile"
    };
    HashMap<String,String>item;
    ArrayList list;
    Button gotoCart, Back;
    SimpleAdapter simpleAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lab_text);

        list=new ArrayList();
        gotoCart=findViewById(R.id.btn_goto_cart);
        Back=findViewById(R.id.btn_back_LT);
        listView=findViewById(R.id.listViewCL);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LabTextActivity.this,MainActivity.class);
                finishAffinity();
                startActivity(intent);
            }
        });
        for (int i=0 ; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost" + packages[i][4] + "/-");
            list.add(item);
        }

        simpleAdapter = new SimpleAdapter(
                this,
                list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(LabTextActivity.this,LabTextDetailActivity.class);
                intent.putExtra("text1",packages[position][0]);
                intent.putExtra("text2",package_details[position]);
                intent.putExtra("text3",packages[position][4]);
                startActivity(intent);
            }
        });

        gotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTextActivity.this,CartLabActivity.class));
                finish();
            }
        });

    }
}