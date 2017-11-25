package mrutrader.mrutrader;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileBlankFragment extends Fragment implements View.OnClickListener{

    private static final int REQUEST_CODE = 1;
    private static final String TAG = "ProfileActivity";
    private FirebaseUser user;
    private String userID;
    private String deleteCourseKey = null;
    private ArrayList<String> list;
    private ArrayAdapter adapter;
    DatabaseReference mDatabase;

    public ProfileBlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_blank, container, false);
        // Inflate the layout for this fragment
        FirebaseAuth fireBaseAuth = FirebaseAuth.getInstance();

        user = fireBaseAuth.getCurrentUser();
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(user.getEmail());
        if (user != null) {
            userID = user.getUid();
        }

        Button addCourse = (Button) view.findViewById(R.id.addCourseButton);
        addCourse.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getActivity(),AddCourseActivity.class);

            intent.putExtra("userID", userID);
            startActivityForResult(intent, REQUEST_CODE);
        }
        });

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Course");

        list = new ArrayList<>();
        final ListView listView = (ListView) getView().findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
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
            mDatabase.child("Course").child(deleteCourseKey).removeValue();
            onStart();
        }
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
