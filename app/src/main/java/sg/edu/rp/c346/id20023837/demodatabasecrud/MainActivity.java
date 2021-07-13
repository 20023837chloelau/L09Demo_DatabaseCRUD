package sg.edu.rp.c346.id20023837.demodatabasecrud;

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

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnEdit, btnRetrieve;
    EditText etContent;
    ListView lv;

    ArrayList<Note> alNotes;
    ArrayAdapter<Note> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        etContent = findViewById(R.id.etContent);
        lv = findViewById(R.id.lv);

        alNotes = new ArrayList<>();
        aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, alNotes);
        lv.setAdapter(aa);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                db.insertTask(addDesc.getText().toString(), addDate.getText().toString());

                addDesc.setText(null);
                addDate.setText(null);
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);

                ArrayList<String> al = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < al.size(); i++) {
                    Log.d("Database Content", i +". "+ al.get(i));
                    txt += i + ". " + al.get(i) + "\n";
                }
                tvResults.setText(txt);

                ArrayList<Task> al2 = db.getTasks();

                alTasks.clear();
                alTasks.addAll(al2);
                aa.notifyDataSetChanged();

                addDesc.setText(null);
                addDate.setText(null);
            }
        });
    }
}