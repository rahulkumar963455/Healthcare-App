package com.example.healthcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LabTextDetailActivity extends AppCompatActivity {
    TextView txt_packages, txt_cost;
    EditText edt_details;
    Button backBtn, addtoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_text_detail);
    txt_packages=findViewById(R.id.txt_lab_textAndPackagesLTD);
    txt_cost=findViewById(R.id.txt_LTD);
    edt_details=findViewById(R.id.listViewLTD);
    backBtn=findViewById(R.id.back_btn_LTD);
    addtoCart=findViewById(R.id.go_to_cart_LTD);


    backBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LabTextDetailActivity.this,LabTextActivity.class));
            finish();
        }
    });
    edt_details.setKeyListener(null);


        Intent intent=getIntent();
        if(intent != null){
            txt_packages.setText(intent.getStringExtra("text1"));
        edt_details.setText(intent.getStringExtra("text2"));
        txt_cost.setText("Total Cost :"+ intent.getStringExtra("text3"));

}
      addtoCart.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              SharedPreferences sharedPreferences=getSharedPreferences("share_prefs",MODE_PRIVATE);
              String username=sharedPreferences.getString("username","").toString();
              String product=txt_packages.getText().toString();
              float price=Float.parseFloat(intent.getStringExtra("text3").toString());

              Database database=new Database(getApplicationContext(),"healthcare",null,1);
              if(database.checkCart(username,product)==1){
                  Toast.makeText(LabTextDetailActivity.this, "Already cart Added", Toast.LENGTH_SHORT).show();
              }else{
                  database.addCart(username,product,price,"Lab");
                  Toast.makeText(LabTextDetailActivity.this, "Record inserted to the cart", Toast.LENGTH_SHORT).show();
                  startActivity(new Intent(LabTextDetailActivity.this,LabTextActivity.class));
              }
          }
      });
    }
}