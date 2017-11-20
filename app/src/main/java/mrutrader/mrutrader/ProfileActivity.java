package mrutrader.mrutrader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CODE = 1;
    private static final String TAG = "ProfileActivity";
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
                        //collectCourses((Map<String,Object>) dataSnapshot.getValue());
                        //for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                            //Log.v(TAG,""+ childDataSnapshot.getKey()); //displays the key for the node
                            //Log.v(TAG,""+ childDataSnapshot.child("Course").getValue());   //gives the value for given keyname
                            //String value = childDataSnapshot.child("Course").child(userID).get;
                            //adapter.add(value);
                            //adapter.add(childDataSnapshot.child("Course").child(userID));
                        List<String> lst = new ArrayList<String>(); // Result will be held Here


                            for(DataSnapshot dsp : dataSnapshot.child(userID).getChildren()){

                                lst.add(String.valueOf(dsp.getValue())); //add result into array list
                            }

                            ArrayList<Course> list = new ArrayList<Course>();
                            for(int i=0;i<lst.size();i++){
                                    String line = lst.get(i);
                                    String[] array = line.split(",");
                                    String userId = array[0].substring(array[0].lastIndexOf("=") + 1);
                                    String cName = array[1].substring(array[1].lastIndexOf("=") + 1);
                                    String txtB = array[2].substring(array[2].lastIndexOf("=") + 1);
                                    String price = array[3].substring(array[3].lastIndexOf("=") + 1);
                                    int priceR = Integer.parseInt(price);
                                    String tutoring = array[4].substring(array[4].lastIndexOf("=") + 1);
                                    String notes = array[5].substring(array[5].lastIndexOf("=") + 1);
                                    String n = notes.substring(0, notes.length() -1);
                                    Boolean boolean1 = Boolean.parseBoolean(txtB);
                                    Boolean boolean2 = Boolean.parseBoolean(tutoring);
                                    Boolean boolean3 = Boolean.parseBoolean(n);
                                Course course = new Course(userId, cName, priceR, boolean1,
                                        boolean2, boolean3);
                                list.add(course);
                                adapter.add(list.get(i).toString());
                            }
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

       // CourseListAdapter courseListAdapter  new CourseListAdapter(this, adapter_view_layout, list);
        //mListView.setAdapter(courseListAdapter);
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
