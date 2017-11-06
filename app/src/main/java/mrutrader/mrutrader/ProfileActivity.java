package mrutrader.mrutrader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CODE = 1;
    private FirebaseUser user;
    private String userID;

    private ArrayList<String> list;
    private ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseAuth fireBaseAuth = FirebaseAuth.getInstance();

        user = fireBaseAuth.getCurrentUser();

        if (user != null) {
            userID = user.getUid();
        }
    }

    private void collectCourses(Map<String,Object> courses) {

        ArrayList<Long> courseName = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : courses.entrySet()){

            //Get user map
            Map singleCourse = (Map) entry.getValue();
            //Get phone field and append to list
            courseName.add((Long) singleCourse.get("userID"));
            adapter.add(courseName.get(0));
        }

    }

    @Override
    protected void onStart(){
        super.onStart();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Course").child(userID);

        list = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);


    //Get datasnapshot at your "users" root node
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Course");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        collectCourses((Map<String,Object>) dataSnapshot.getValue());


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
        /*
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    AddCourseActivity.Course course = (AddCourseActivity.Course) dataSnapshot.getValue(AddCourseActivity.Course.class);
                    String courseString = String.valueOf(course);
                    adapter.add(courseString);

                    /*String value = dataSnapshot.getValue(String.class);
                    list.add(value);
                    adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }



    @Override
    public void onClick(View v) {
        Button addCourse = (Button) findViewById(R.id.addCourseButton);
        Intent intent = new Intent(this,AddCourseActivity.class);


        intent.putExtra("userID", userID);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
