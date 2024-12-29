package com.example.healthcare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class CartBuyMedicineActivity extends AppCompatActivity {

    private String[][] packages={};
    Button btnback,btncheck;
    TextView txtPackages,txtTotalprice , setDate;
    String username;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    SimpleAdapter simpleAdapter;
    ListView listView;
    HashMap<String,String> item;
    ArrayList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        setDate = findViewById(R.id.txt_date_CBM);
        btnback = findViewById(R.id.bakc_btn_CBM);
        txtPackages = findViewById(R.id.txt_lab_textAndPackagesCBM);
        txtTotalprice = findViewById(R.id.total_cost_CBM);
        listView = findViewById(R.id.listViewCBM);
        btncheck = findViewById(R.id.checkout_card_CBM);
        dateButton = findViewById(R.id.btn_selectdate_CBM);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartBuyMedicineActivity.this,BuyMedicineDetailActivity.class));
                finishAffinity();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("share_prefs", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        Database database = new Database(getApplicationContext(), "healthcare", null, 1);

        initDatePicker();
        dateButton.setOnClickListener(v -> datePickerDialog.show());


        float totalAmount = 0;
        if (username != null) {
            ArrayList dbData = database.getCartData(username, "Medicine");
            Toast.makeText(this, "" + dbData, Toast.LENGTH_SHORT).show();
            packages = new String[dbData.size()][];
            for (int i = 0; i < packages.length; i++) {
                packages[i] = new String[5];
            }
            for (int i = 0; i < dbData.size(); i++) {
                String arrData = dbData.get(i).toString();
                String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
                packages[i][0] = strData[0];
                packages[i][4] = "Cost :" + strData[1] + "/-";

                totalAmount = totalAmount + Float.parseFloat(strData[1]);

            }
        }

        txtTotalprice.setText("Total Price :"+totalAmount);
        list=new ArrayList();

        for (int i=0 ; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost" + packages[i][4] );
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

        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartBuyMedicineActivity.this,BookCartBuyMedicineActivity.class);
                intent.putExtra("date",dateButton.getText());
                intent.putExtra("price",txtTotalprice.getText());
                startActivity(intent);
            }
        });
    }
        private void initDatePicker() {
            DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                setDate.setText(dateFormat.format(selectedDate.getTime()));
            };

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            int style = AlertDialog.THEME_HOLO_DARK;
            datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, dayOfMonth);
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() + 86400000); // One day ahead
        }
}