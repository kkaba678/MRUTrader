package mrutrader.mrutrader;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.dashGap;
import static android.R.attr.name;
import static android.R.attr.showText;


public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "AddCourseActivity";
    EditText courseNameEditText;
    android.widget.SearchView simpleSearchView;// = (SearchView) findViewById(R.id.simpleSearchView);
    Button button2;
    Button button4;
    DatabaseReference mDatabase;
    EditText priceEditText;
    RadioButton textBookRadioButton;
    RadioButton notesRadioButton;
    RadioButton tutorRadioButton;
    RadioButton tradeRadioButton;
    String userID;
    String courseID;
    ListView lv;
    SearchView sv;
    TextView textView;
    String[] courses={"COMP3512","COMP4455","COMP5520","GNED1203","GNED1203"};
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        userID = b.getString("userID");

        lv=(ListView) findViewById(R.id.listView1);
        sv=(SearchView) findViewById(R.id.searchView1);
        textView = (TextView) findViewById(R.id.textView3);
        textView.setVisibility(View.INVISIBLE);

        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,courses);
        lv.setAdapter(adapter);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                return false;
            }
            @Override
            public boolean onQueryTextChange(String text) {
                adapter.getFilter().filter(text);
                return false;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                sv.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                String txt = (String)lv.getAdapter().getItem(position);
                textView.setText(txt);
                          }
        });
    }

    @Override
    public void onClick(View v){
        button2 = (Button) findViewById(R.id.button2);
        button4 = (Button) findViewById(R.id.button4);
        if(v == button2) {
            if (checkFields()) {

                priceEditText = (EditText) findViewById(R.id.priceEditText);
                textBookRadioButton = (RadioButton) findViewById(R.id.textbookRadioButton);
                notesRadioButton = (RadioButton) findViewById(R.id.notesRadioButton);
                tutorRadioButton = (RadioButton) findViewById(R.id.tutorRadioButton);
                tradeRadioButton = (RadioButton) findViewById(R.id.tradeRadioButton);

                String cName = (String) textView.getText();
                String ePrice = priceEditText.getText().toString();

                try {
                    boolean textBook = textBookRadioButton.isChecked();
                    boolean notes = notesRadioButton.isChecked();
                    boolean tutoring = tutorRadioButton.isChecked();
                    boolean trade = tradeRadioButton.isChecked();
                    int price = Integer.parseInt(ePrice);
                    Course course = new Course(userID, cName, price, textBook, notes, tutoring, trade);

                    mDatabase = FirebaseDatabase.getInstance().getReference();

                    Map<String, String> taskMap = new HashMap<>();
                    taskMap.put("userID", userID);
                    taskMap.put("course name", course.courseName);
                    taskMap.put("price", Integer.toString(course.getPrice()));
                    taskMap.put("textBook", String.valueOf(course.getTextBook()));
                    taskMap.put("notes", String.valueOf(course.getLectureNotes()));
                    taskMap.put("tutoring", String.valueOf(course.getTutoring()));
                    taskMap.put("trade", String.valueOf(course.trade));
                    mDatabase.child("Course").push().setValue(taskMap);
                } catch (NumberFormatException e) {
                    e.getMessage();
                }
                finish();
            } else {
                Toast.makeText(this, "Please fill all the fields required", Toast.LENGTH_SHORT);
            }
        }
        else if(v == button4) {
            finish();
        }
    }

    private boolean checkFields(){
        boolean checker = true;
        if(textView.getText() == null){
            checker = false;
        }
        return checker;
    }

    @Override
    public void finish() {
        // Prepare data intent
        Intent data = getIntent();

        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        super.finish();
    }

}
