package com.example.healthcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

public class OrderDetailActivity extends AppCompatActivity {

    private String[][] orderDetail={};
    ArrayList list;
    HashMap<String,String> item;
    ListView listView;
    Button backbtn;
    SimpleAdapter simpleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        listView=findViewById(R.id.listView_OD);
        backbtn=findViewById(R.id.back_btn_OD);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailActivity.this,MainActivity.class));
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("share_prefs",MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();


            Database database = new Database(getApplicationContext(), "healthcare", null, 1);
            ArrayList dbData = database.getOrderData(username);


            orderDetail = new String[dbData.size()][];
        if (dbData == null || dbData.size() == 0) {
            System.out.println("No orders found for the user.");
            return; // Exit early or show a placeholder message in the UI
        }

        System.out.println(orderDetail.length);

            for (int i = 0; i < orderDetail.length; i++) {
                orderDetail[i] = new String[5];
                String arrData = dbData.get(i).toString();
                String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
                orderDetail[i][0] = strData[0];
                orderDetail[i][1] = strData[1];
                if (strData[7].compareTo("medicine") == 0) {
                    orderDetail[i][3] = "Del:" + strData[4];
                } else {
                    orderDetail[i][3] = "Del:" + strData[4] + " " + strData[5];
                }
                orderDetail[i][2] = "Rs. " + strData[6];
                orderDetail[i][4] = strData[7];
            }


                list = new ArrayList();


        for (int i=0 ; i < orderDetail.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", orderDetail[i][0]);
            item.put("line2", orderDetail[i][1]);
            item.put("line3", orderDetail[i][2]);
            item.put("line4", orderDetail[i][3]);
            item.put("line5",orderDetail[i][4]);
            list.add(item);
        }

        simpleAdapter = new SimpleAdapter(
                this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        listView.setAdapter(simpleAdapter);
    }
}