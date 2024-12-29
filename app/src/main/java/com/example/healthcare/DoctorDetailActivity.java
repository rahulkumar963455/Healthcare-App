package com.example.healthcare;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailActivity extends AppCompatActivity {


    private String[][] doctor_detail1 = {
            {"Doctor Name : Ajit Saste", "Hospital Address : Pimpri", "Exp : 10yrs", "Mobile No : 9898989898", "600"},
            {"Doctor Name : Ramesh Pawar", "Hospital Address : Wakad", "Exp : 8yrs", "Mobile No : 8787878787", "500"},
            {"Doctor Name : Sneha Kulkarni", "Hospital Address : Baner", "Exp : 5yrs", "Mobile No : 7676767676", "700"},
            {"Doctor Name : Ashok Patil", "Hospital Address : Hinjewadi", "Exp : 12yrs", "Mobile No : 6565656565", "650"},
            {"Doctor Name : Priya Deshmukh", "Hospital Address : Aundh", "Exp : 7yrs", "Mobile No : 5454545454", "550"}
    };

    private String[][] doctor_detail2 = {
            {"Doctor Name : Varun Gupta", "Hospital Address : Kothrud", "Exp : 9yrs", "Mobile No : 4343434343", "750"},
            {"Doctor Name : Anita Sharma", "Hospital Address : Karve Nagar", "Exp : 6yrs", "Mobile No : 3232323232", "600"},
            {"Doctor Name : Kiran Rao", "Hospital Address : Deccan", "Exp : 11yrs", "Mobile No : 2121212121", "800"},
            {"Doctor Name : Mohit Verma", "Hospital Address : Camp", "Exp : 4yrs", "Mobile No : 1010101010", "450"},
            {"Doctor Name : Aarti Naik", "Hospital Address : Swargate", "Exp : 8yrs", "Mobile No : 9090909090", "700"}
    };

    private String[][] doctor_detail3 = {
            {"Doctor Name : Suresh Jadhav", "Hospital Address : Shivaji Nagar", "Exp : 15yrs", "Mobile No : 8080808080", "900"},
            {"Doctor Name : Neha Jain", "Hospital Address : Erandwane", "Exp : 5yrs", "Mobile No : 7070707070", "500"},
            {"Doctor Name : Rajesh Singh", "Hospital Address : Pimple Saudagar", "Exp : 7yrs", "Mobile No : 6060606060", "650"},
            {"Doctor Name : Sunita Reddy", "Hospital Address : Chinchwad", "Exp : 10yrs", "Mobile No : 5050505050", "750"},
            {"Doctor Name : Vivek Desai", "Hospital Address : Nigdi", "Exp : 6yrs", "Mobile No : 4040404040", "600"}
    };

    private String[][] doctor_detail4 = {
            {"Doctor Name : Nilesh More", "Hospital Address : Dhanori", "Exp : 8yrs", "Mobile No : 3030303030", "550"},
            {"Doctor Name : Kavita Mehta", "Hospital Address : Kharadi", "Exp : 10yrs", "Mobile No : 2020202020", "750"},
            {"Doctor Name : Arjun Yadav", "Hospital Address : Viman Nagar", "Exp : 3yrs", "Mobile No : 1919191919", "400"},
            {"Doctor Name : Shweta Iyer", "Hospital Address : Hadapsar", "Exp : 7yrs", "Mobile No : 1818181818", "650"},
            {"Doctor Name : Rohit Malhotra", "Hospital Address : Magarpatta", "Exp : 12yrs", "Mobile No : 1717171717", "850"}
    };

    private String[][] doctor_detail5 = {
            {"Doctor Name : Alok Khanna", "Hospital Address : Wanowrie", "Exp : 6yrs", "Mobile No : 1616161616", "600"},
            {"Doctor Name : Anjali Bhosale", "Hospital Address : Sinhagad Road", "Exp : 9yrs", "Mobile No : 1515151515", "700"},
            {"Doctor Name : Manish Kulkarni", "Hospital Address : Bibwewadi", "Exp : 4yrs", "Mobile No : 1414141414", "500"},
            {"Doctor Name : Shilpa Dixit", "Hospital Address : Katraj", "Exp : 11yrs", "Mobile No : 1313131313", "800"},
            {"Doctor Name : Vikram Joshi", "Hospital Address : Warje", "Exp : 7yrs", "Mobile No : 1212121212", "650"}
    };


    String[][] doctor_details={};

    TextView tv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        tv=findViewById(R.id.default_textDD);
        btn=findViewById(R.id.buttonDDback);
        Intent intent = getIntent();
        String title=intent.getStringExtra("title");
        tv.setText(title);

        ArrayList list;
        SimpleAdapter sa;
        HashMap<String,String>item;


        if(title.compareTo("Family Physicians")==0){
            doctor_details=doctor_detail1;
        } else if (title.compareTo("Dietician")==0) {
            doctor_details=doctor_detail2;
        } else if (title.compareTo("Dentist")==0) {
            doctor_details=doctor_detail3;
        } else if (title.compareTo("Surgeon")==0) {
            doctor_details=doctor_detail4;
        } else if (title.compareTo("Carddiologists")==0) {
            doctor_details=doctor_detail5;
        }
        list=new ArrayList();
        for (int i=0 ; i < doctor_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fee " + doctor_details[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(
                this,
                list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
            ListView listView=findViewById(R.id.listViewDD);

            listView.setAdapter(sa);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent1=new Intent(DoctorDetailActivity.this,BookAppointmentActivity.class);
                    intent1.putExtra("text1",title);
                    intent1.putExtra("text2",doctor_details[position][0]);
                    intent1.putExtra("text3",doctor_details[position][1]);
                    intent1.putExtra("text4",doctor_details[position][3]);
                    intent1.putExtra("text5",doctor_details[position][4]);
                    startActivity(intent1);
                }
            });


    }
}