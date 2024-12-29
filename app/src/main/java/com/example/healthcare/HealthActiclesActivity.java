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

public class HealthActiclesActivity extends AppCompatActivity {

    private String[][] health_detaiils={
            {"Walking Daily","","","","Click More Details"},
            {"Home care of COVID-19","","","","Click More Details"},
            {"Stop smoking","","","","Click More Details"},
            {"Menstrual Cramps","","","","Click More Details"},
            {"Healthy Gut","","","","Click More Details"},
    };
    private int[] images={
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnback;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_acticles);
     btnback=findViewById(R.id.back_btn_HA);
     listView=findViewById(R.id.listViewHA);

     btnback.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(HealthActiclesActivity.this,MainActivity.class));
         }
     });

     list=new ArrayList();
     for(int i=0; i<health_detaiils.length;i++){
         item=new HashMap<String,String>();
         item.put("line1",health_detaiils[i][0]);
         item.put("line2",health_detaiils[i][1]);
         item.put("line3",health_detaiils[i][2]);
         item.put("line4",health_detaiils[i][3]);
         item.put("line5",health_detaiils[i][4]);
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
                Intent it=new Intent(HealthActiclesActivity.this,HealthArticlesDetailActivity.class);
                it.putExtra("text1",health_detaiils[position][0]);
                it.putExtra("text2",images[position]);
                startActivity(it);
            }
        });


    }
}