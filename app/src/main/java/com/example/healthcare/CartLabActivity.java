package com.example.healthcare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class CartLabActivity extends AppCompatActivity {
    Button btnback,btncheck ,btnSelecttime, btnSelectdate;
    TextView txtPackages,txtTotalprice,setTime, setDate;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    String username;
    String[][] packages={};
    SimpleAdapter simpleAdapter;
    ListView listView;
    HashMap<String,String>item;
    ArrayList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);
    btnback=findViewById(R.id.bakc_btn_CT);
    txtPackages=findViewById(R.id.txt_lab_textAndPackagesCL);
    txtTotalprice=findViewById(R.id.total_cost_CL);
    listView=findViewById(R.id.listViewCL);
    btncheck=findViewById(R.id.checkout_card_CT);
        setDate = findViewById(R.id.txt_date_CL);
        setTime = findViewById(R.id.txt_time_CL);
        btnSelectdate = findViewById(R.id.btn_selectdate_CL);
        btnSelecttime = findViewById(R.id.btn_selecttime_CL);

        initDatePicker();
        btnSelectdate.setOnClickListener(v -> datePickerDialog.show());

        initTimePicker();
        btnSelecttime.setOnClickListener(v -> timePickerDialog.show());

        SharedPreferences sharedPreferences=getSharedPreferences("share_prefs",MODE_PRIVATE);
        username= sharedPreferences.getString("username","");
        Database database=new Database(getApplicationContext(),"healthcare",null,1);

        float totalAmount=0;
        if(username!=null) {
            ArrayList dbData = database.getCartData(username, "Lab");
            Toast.makeText(this, ""+dbData, Toast.LENGTH_SHORT).show();
            packages=new String[dbData.size()][];
            for (int i=0;i<packages.length;i++){
                packages[i]=new String[5];
            }
            for(int i=0;i<dbData.size();i++){
                String arrData=dbData.get(i).toString();
                String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
                packages[i][0]=strData[0];
                packages[i][4]="Cost :"+strData[1]+"/-";

                totalAmount=totalAmount+Float.parseFloat(strData[1]);

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
        }

        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartLabActivity.this,LabTextBookActivity.class);
                intent.putExtra("price",txtTotalprice.getText());
                intent.putExtra("date",setDate.getText());
                intent.putExtra("time",setTime.getText());
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

    private void initTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(this, (view, selectedHour, selectedMinute) -> {
            String time = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
            setTime.setText(time);
        }, hour, minute, true); // Set `true` for 24-hour format
    }
}