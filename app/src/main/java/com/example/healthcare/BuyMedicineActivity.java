package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages={
            {"Uprise-03 1000IU Capsule","","","","50"},
            {"HealthBoost-01 1000IU Capsule", "", "", "", "60"},
            {"VitalForce-02 1000IU Capsule", "", "", "", "70"},
            {"MaxStrength-03 1000IU Capsule", "", "", "", "65"},
            {"PowerShield-04 1000IU Capsule", "", "", "", "80"},
            {"ImmuneGuard-05 1000IU Capsule", "", "", "", "75"},
            {"BoneCare-06 1000IU Capsule", "", "", "", "85"},
            {"StrongLife-07 1000IU Capsule", "", "", "", "90"},
            {"BoneCare-05 1000IU Capsule", "", "", "", "90"}
    };

    private String[] package_details={
                    "Building and keeping the bones % teeth strong\n",
                    "Strengthening bones and teeth for long-term health\n" ,
                    "Reducing fatigue, stress, and muscle discomfort\n" ,
                    "Promoting better bone and dental health\n" ,
                    "Supporting overall well-being through strong bones and teeth\n" ,
                    "Helping to maintain strong bones and healthy teeth\n" ,
                    "Encouraging strong teeth and bones for a healthier lifestyle\n" ,
                    "Enhancing bone density and tooth strength\n" ,
                    "Fostering a healthy skeletal system and strong teeth\n" ,
                    "Contributing to long-lasting bone and dental health\n" ,
                    "Helping to fight muscle aches and bone-related discomfort\n" ,
                    "Boosting bone and teeth health for a stronger body\n" ,
                    "Supporting your body with strong bones and healthy teeth\n" ,
                    "Improving the strength of bones and teeth for better mobility\n" ,
                    "Strengthening the foundation of your health through bones and teeth"
    };

    HashMap<String ,String> item;
    ArrayList list;
    ListView listView;
    Button btnBack, gotoCart;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_buy_medicine);
        listView=findViewById(R.id.listViewBM);
        btnBack=findViewById(R.id.btn_back_BM);
        gotoCart=findViewById(R.id.btn_goto_cartBM);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,MainActivity.class));
                finish();
            }
        });

        gotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });
        list =new ArrayList();
        System.out.println(package_details.length);
        System.out.println(packages.length);

        for (int i=0 ; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost :" + packages[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(
                this,
                list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        listView.setAdapter(sa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(BuyMedicineActivity.this,BuyMedicineDetailActivity.class);
                intent.putExtra("text1",packages[position][0]);
                intent.putExtra("text2",package_details[position]);
                intent.putExtra("text3",packages[position][4]);
                startActivity(intent);
            }
        });

    }
}