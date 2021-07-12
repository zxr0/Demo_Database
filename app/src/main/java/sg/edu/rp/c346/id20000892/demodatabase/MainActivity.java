package sg.edu.rp.c346.id20000892.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Button btnIns, btnGetTasks;
    TextView tvResults;
    EditText etdesc, etdate;
    ListView lv;
    ArrayList<Task> al;
    ArrayAdapter<Task> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIns = findViewById(R.id.btnInsert);
        tvResults = findViewById(R.id.textView);
        btnGetTasks = findViewById(R.id.button2);
        etdesc = findViewById(R.id.edittext);
        etdate = findViewById(R.id.exittext2);

        lv = findViewById(R.id.lv);
        al = new ArrayList<>();
        aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        btnIns.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etdesc.getText().toString(), etdate.getText().toString());

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                    aa.clear();
                    aa.addAll(db.getTasks2());
                    aa.notifyDataSetChanged();

            }
        });




    }
}