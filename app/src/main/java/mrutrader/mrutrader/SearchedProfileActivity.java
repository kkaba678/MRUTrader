package mrutrader.mrutrader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SearchedProfileActivity extends AppCompatActivity {

    private Intent intent;
    private TextView textView;
    private String userID;
    private ArrayList<String> list;
    private ArrayAdapter adapter;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_profile);

        intent = getIntent();


        textView = (TextView) findViewById(R.id.textView8);
        //textView.setText(user.getEmail());
        userID = intent.getStringExtra("userID");

    }

    @Override
    public void onStart(){
        super.onStart();

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Course");

        list = new ArrayList<>();
        final ListView listView = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        //Get datasnapshot at your "users" root node
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Course");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> lst = new ArrayList<String>(); // Result will be held Here


                        Iterable<DataSnapshot> courses = dataSnapshot.getChildren();
                        Iterator<DataSnapshot> itr = courses.iterator();
                        DataSnapshot one = null;
                        String courseKey = null;
                        HashMap obj = null;
                        while(itr.hasNext()) {
                            one = itr.next();
                            courseKey = one.getKey();
                            obj = (HashMap) one.getValue();
                            if (obj.get("userID").equals(userID)) {
                                String courseName = (String) obj.get("course name");
                                String userID = (String) obj.get("userID");
                                String price = (String) (obj.get("price"));
                                String textBook = (String) obj.get("textBook");
                                String lectureNotes = (String) obj.get("notes");
                                String tutoring = (String) obj.get("tutoring");
                                String trade = (String) obj.get("trade");
                                Course course = new Course(userID, courseName, Integer.parseInt(price),
                                        Boolean.valueOf(textBook), Boolean.valueOf(lectureNotes), Boolean.valueOf(tutoring),
                                        courseKey, Boolean.valueOf(trade));
                                adapter.add(course);
                            }
                        }

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            Course obj = null;
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                                obj = (Course)listView.getAdapter().getItem(position);

                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }


}
