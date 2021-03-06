package mrutrader.mrutrader;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CODE = 1;
    private static final String TAG = "ProfileActivity";
    private FirebaseUser user;
    private String userID;
    private String deleteCourseKey = null;
    private ArrayList<String> list;
    private ArrayAdapter adapter;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseAuth fireBaseAuth = FirebaseAuth.getInstance();

        user = fireBaseAuth.getCurrentUser();
        TextView textView = (TextView) findViewById(R.id.textViewProfileName);
        textView.setText(user.getEmail());
        if (user != null) {
            userID = user.getUid();
        }
    }


    @Override
    protected void onStart(){
        super.onStart();

         mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Course").child(userID);

        list = new ArrayList<>();
        final ListView listView = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

    //Get datasnapshot at your "users" root node
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Course");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> lst = new ArrayList<String>(); // Result will be held Here


                        Iterable<DataSnapshot> courses = dataSnapshot.child(userID).getChildren();
                        Iterator<DataSnapshot> itr = courses.iterator();
                        DataSnapshot one = null;
                        String courseKey = null;
                        HashMap obj = null;
                        while(itr.hasNext()){
                            one = itr.next();
                            courseKey = one.getKey();
                            obj = (HashMap) one.getValue();

                            String courseName = (String)obj.get("course name");
                            String userID = (String)obj.get("userID");
                            String price = (String)(obj.get("price"));
                            String textBook = (String)obj.get("textBook");
                            String lectureNotes = (String)obj.get("notes");
                            String tutoring = (String)obj.get("tutoring");
                            String trade = (String)obj.get("trade");
                            Course course = new Course(userID,courseName,Integer.parseInt(price),
                                    Boolean.valueOf(textBook),Boolean.valueOf(lectureNotes),Boolean.valueOf(tutoring),
                                    courseKey, Boolean.valueOf(trade));
                            adapter.add(course);
                        }

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                Course obj = null;
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                                obj = (Course)listView.getAdapter().getItem(position);
                                deleteCourseKey = obj.courseKey;
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }


    public void deleteCourse(View view){

        if(deleteCourseKey != null){
            mDatabase.child("Course").child(userID).child(deleteCourseKey).removeValue();
            onStart();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
     /*  SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));*/
        return true;
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
