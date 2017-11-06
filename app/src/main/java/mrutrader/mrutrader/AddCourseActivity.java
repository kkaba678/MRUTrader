package mrutrader.mrutrader;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.dashGap;
import static android.R.attr.name;

public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener{

    public class Course {

        private String userID;
        private String courseName;
        private int price;
        private boolean textBook;
        private boolean lectureNotes;
        private boolean tutoring;


        public Course() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Course(String userID, String courseName,int price, boolean textBook, boolean lectureNotes, boolean tutoring) {
            this.userID = userID;
            this.courseName = courseName;
            this.price = price;
            this.textBook = textBook;
            this.lectureNotes = lectureNotes;
            this.tutoring = tutoring;
        }

        @Override
        public String toString() {
            return this.userID + ": " + this.courseName;
        }

    }

    private static final String TAG = "AddCourseActivity";
    EditText courseNameEditText;
    SearchView simpleSearchView;// = (SearchView) findViewById(R.id.simpleSearchView);
    Button button2;
    DatabaseReference mDatabase;
    EditText priceEditText;
    RadioButton textBookRadioButton;
    RadioButton notesRadioButton;
    RadioButton tutorRadioButton;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        userID = b.getString("userID");
    }


    @Override
    public void onClick(View v){
        button2 = (Button) findViewById(R.id.button2);
        courseNameEditText = (EditText) findViewById(R.id.courseNameEditText);
        priceEditText = (EditText) findViewById(R.id.priceEditText);
        textBookRadioButton = (RadioButton) findViewById(R.id.TextBookRadioButton);
        notesRadioButton = (RadioButton) findViewById(R.id.notesRadioButton);
        tutorRadioButton = (RadioButton) findViewById(R.id.tutorRadioButton);

        String cName = courseNameEditText.getText().toString();
        String ePrice = priceEditText.getText().toString();
        try {

            int price = Integer.parseInt(ePrice);
            boolean textBook = textBookRadioButton.isChecked();
            boolean notes = notesRadioButton.isChecked();
            boolean tutoring = tutorRadioButton.isChecked();
            Course course = new Course(userID, cName, price, textBook, notes, tutoring);

            mDatabase = FirebaseDatabase.getInstance().getReference();


            Map<String, Object> taskMap = new HashMap<>();
            taskMap.put("userID", userID);
            taskMap.put("course name", course.courseName);
            taskMap.put("price", course.price);
            taskMap.put("textBook", course.textBook);
            taskMap.put("notes", course.lectureNotes);
            taskMap.put("tutoring", course.tutoring);
            mDatabase.child("Course").child(userID).push().setValue(taskMap);
        }
        catch(NumberFormatException e){
            e.getMessage();
        }

        finish();

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
