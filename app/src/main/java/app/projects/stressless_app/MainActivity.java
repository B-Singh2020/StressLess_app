package app.projects.stressless_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        Data d = new Data("hi", "hello");
        List<Data> myDataList = new ArrayList<>();

        myDataList.add(d);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ADD HERE
        Button addBtn = (Button)findViewById(R.id.btnAddItem);
        EditText edTxt = (EditText)findViewById(R.id.etNewItem);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this,
                R.layout.list_textline, items);
        lvItems.setAdapter(itemsAdapter);
        items.add("First Item");
        items.add("Second Item");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edTxt.getText().toString().isEmpty() && !edTxt.getText().toString().trim().isEmpty()
                )
                {
                    String af = edTxt.getText().toString();
                    items.add(af);
                    itemsAdapter.notifyDataSetChanged();
                    edTxt.getText().clear();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please add text for your affirmation",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int arg2, long arg3) {

                items.remove(arg2);
                itemsAdapter.notifyDataSetChanged();

                return false;
            }
        });
    }



}