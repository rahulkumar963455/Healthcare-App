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

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookAppointmentActivity extends AppCompatActivity {
    TextView textView, textTime, textDate;
    Button timeButton, dateButton , btnbook, btnback;
    EditText edtFullName, edtAddress, edtContact, edtFee;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        // Initialize views

        textView = findViewById(R.id.bookappointmentBA);
        timeButton = findViewById(R.id.btn_selecttime_BA);
        dateButton = findViewById(R.id.btn_selectdate_BA);
        edtFullName = findViewById(R.id.edt_reg_fullname);
        edtAddress = findViewById(R.id.edt_address_BA);
        edtContact = findViewById(R.id.edt_contact_no_BA);
        edtFee = findViewById(R.id.edt_fee_BA);
        textTime = findViewById(R.id.txt_time_BA);
        btnbook=findViewById(R.id.bookAppointmentBA);
        textDate = findViewById(R.id.txt_date_BA);
        btnback = findViewById(R.id.btn_back_BA);

        edtFullName.setKeyListener(null);
        edtContact.setKeyListener(null);
        edtAddress.setKeyListener(null);
        edtFee.setKeyListener(null);//



        Intent intent=getIntent();
        String title=intent.getStringExtra("text1");
        String fullname=intent.getStringExtra("text2");
        String address=intent.getStringExtra("text3");
        String contactNo=intent.getStringExtra("text4");
        String fees=intent.getStringExtra("text5");

        textView.setText(title);
        edtFullName.setText(fullname);
        edtAddress.setText(address);
        edtContact.setText(contactNo);
        edtFee.setText("Cons fee :"+fees+"/-");


        initDatePicker();
        dateButton.setOnClickListener(v -> datePickerDialog.show());

        initTimePicker();
        timeButton.setOnClickListener(v -> timePickerDialog.show());

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this,MainActivity.class));
                finish();
            }
        });

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("share_prefs",MODE_PRIVATE);
                String username= sharedPreferences.getString("username","");
                Database database=new Database(getApplicationContext(),"healthcare",null,1);

                    database.addOrder(username,fullname,address,0,contactNo,Float.parseFloat(fees.toString()),textDate.getText().toString(),textTime.getText().toString(),"Appointment");
                    Toast.makeText(BookAppointmentActivity.this, "Booked Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BookAppointmentActivity.this,MainActivity.class));
                    finishAffinity();


            }
        });


    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, month, dayOfMonth);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            textDate.setText(dateFormat.format(selectedDate.getTime()));
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
            textTime.setText(time);
        }, hour, minute, true); // Set `true` for 24-hour format
    }
}
