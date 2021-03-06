package mrutrader.mrutrader;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment implements View.OnClickListener {


    private static final int REQUEST_CODE = 1;
    private static final String TAG = "SearchResultFragment";
    private FirebaseUser user;
    private String userID;
    private String deleteCourseKey = null;
    private ArrayList<String> list;
    private ArrayAdapter adapter;
    private int yassin;
    DatabaseReference mDatabase;
    String cName;
    String textBook;
    String notes;
    String tutoring;
    String trade;
    Bundle bundle;


    public SearchResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

      /*  FirebaseAuth fireBaseAuth = FirebaseAuth.getInstance();
        user = fireBaseAuth.getCurrentUser();
        userID = user.getUid();
        bundle = getArguments();*/
        return view;
    }

    @Override
    public void onClick(View v) {

    }


    /*@Override
    public void onStart() {
        super.onStart();


        cName = bundle.getString("courseName");
        textBook = bundle.getString("textBook");
        notes = bundle.getString("notes");
        tutoring = bundle.getString("tutoring");
        trade = bundle.getString("trade");
        yassin=0;
        if(textBook.equals("true"))
        {
            yassin++;
        }
        if(notes.equals("true"))
        {
            yassin++;
        }
        if(tutoring.equals("true"))
        {
            yassin++;
        }
        if(trade.equals("true"))
        {
            yassin++;
        }

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Course");

        list = new ArrayList<>();
        final ListView listView = (ListView) getView().findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        //Get datasnapshot at your "users" root node
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Course");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> lst = new ArrayList<>(); // Result will be held Here

                        Iterable<DataSnapshot> courses = dataSnapshot.getChildren();
                        Iterator<DataSnapshot> itr = courses.iterator();
                        DataSnapshot one = null;
                        String courseKey = null;
                        HashMap obj = null;
                        while (itr.hasNext()) {
                            one = itr.next();
                            courseKey = one.getKey();
                            obj = (HashMap) one.getValue();

                            if(obj.get("notes").equals(notes)){

                            }
                            if(obj.get("textBook").equals(textBook)){
                                String textBook = (String) obj.get("textBook");
                            }
                            if(obj.get("trade").equals(trade)){
                                String trade = (String) obj.get("trade");
                            }
                            if(obj.get("tutoring").equals(tutoring)){
                                String tutoring = (String) obj.get("tutoring");
                            }
                            if (!obj.get("userID").equals(userID)) {

                                if (obj.get("course name").equals(cName)) {


                                    if (yassin == 1){

                                        if (obj.get("notes").equals(notes)) {

                                            String courseName = (String) obj.get("course name");
                                            String price = (String) (obj.get("price"));
                                            String textBook = (String) obj.get("textBook");
                                            String lectureNotes = (String) obj.get("notes");
                                            String tutoring = (String) obj.get("tutoring");
                                            String trade = (String) obj.get("trade");
                                            Course course = new Course(userID, courseName, Integer.parseInt(price),
                                                    Boolean.valueOf(textBook), Boolean.valueOf(lectureNotes), Boolean.valueOf(tutoring),
                                                    courseKey, Boolean.valueOf(trade));
                                            adapter.add(course);

                                        } else if (obj.get("textBook").equals(textBook)) {

                                            String courseName = (String) obj.get("course name");
                                            String price = (String) (obj.get("price"));
                                            String textBook = (String) obj.get("textBook");
                                            String lectureNotes = (String) obj.get("notes");
                                            String tutoring = (String) obj.get("tutoring");
                                            String trade = (String) obj.get("trade");
                                            Course course = new Course(userID, courseName, Integer.parseInt(price),
                                                    Boolean.valueOf(textBook), Boolean.valueOf(lectureNotes), Boolean.valueOf(tutoring),
                                                    courseKey, Boolean.valueOf(trade));
                                            adapter.add(course);

                                        } else if (obj.get("trade").equals(trade)) {

                                            String courseName = (String) obj.get("course name");
                                            String price = (String) (obj.get("price"));
                                            String textBook = (String) obj.get("textBook");
                                            String lectureNotes = (String) obj.get("notes");
                                            String tutoring = (String) obj.get("tutoring");
                                            String trade = (String) obj.get("trade");
                                            Course course = new Course(userID, courseName, Integer.parseInt(price),
                                                    Boolean.valueOf(textBook), Boolean.valueOf(lectureNotes), Boolean.valueOf(tutoring),
                                                    courseKey, Boolean.valueOf(trade));
                                            adapter.add(course);

                                        } else if (obj.get("tutoring").equals(tutoring)) {

                                            String courseName = (String) obj.get("course name");
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
                                    else if(yassin == 2){
                                        if(obj.get("notes").equals(notes) && obj.get("trade").equals(trade)){
                                            String courseName = (String) obj.get("course name");
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
                                        else if(obj.get("textBook").equals(textBook) && obj.get("tutoring").equals(tutoring)){
                                            String courseName = (String) obj.get("course name");
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
                                        else if(obj.get("notes").equals(notes) && obj.get("textBook").equals(textBook)){
                                            String courseName = (String) obj.get("course name");
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
                                        else if(obj.get("notes").equals(notes) && obj.get("trade").equals(trade)){
                                            String courseName = (String) obj.get("course name");
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
                                        else if(obj.get("notes").equals(notes) && obj.get("tutoring").equals(tutoring)){
                                            String courseName = (String) obj.get("course name");
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
                                        else if(obj.get("tutoring").equals(tutoring) && obj.get("trade").equals(trade)){
                                            String courseName = (String) obj.get("course name");
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





                                    if(yassin ==3 ){
                                        if(obj.get("notes").equals(notes) && obj.get("trade").equals(trade) && obj.get("tutoring").equals(tutoring))
                                        {
                                            String courseName = (String) obj.get("course name");
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
                                        else if(obj.get("textBook").equals(textBook) && obj.get("trade").equals(trade) &&obj.get("tutoring").equals(tutoring)){

                                            String courseName = (String) obj.get("course name");
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
                                        else if(obj.get("notes").equals(notes) && obj.get("textBook").equals(textBook) &&obj.get("tutoring").equals(tutoring)){

                                            String courseName = (String) obj.get("course name");
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
                                        else if(obj.get("notes").equals(notes) && obj.get("trade").equals(trade) &&obj.get("textBook").equals(textBook)){

                                            String courseName = (String) obj.get("course name");
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
                                    if(yassin == 0) {
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
                            }
                        }

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            Course obj = null;
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                                obj = (Course) listView.getAdapter().getItem(position);
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
    public void getResulsFor0(String userID,String cName){


    }
    private boolean checkTextBook(boolean textBook){
        boolean checker = false;
        if(Boolean.valueOf(textBook).equals("true")){
            checker = true;

        }
        return checker;
    }

    private boolean checkTutor(boolean tutor){
        boolean checker = false;
        if(Boolean.valueOf(tutor).equals("true")){
            checker = true;

        }
        return checker;
    }

    private boolean checkTrade(boolean trade){
        boolean checker = false;
        if(Boolean.valueOf(trade).equals("true")){
            checker = true;

        }
        return checker;
    }

    private boolean checkNotes(boolean notes){
        boolean checker = false;
        if(Boolean.valueOf(notes).equals("true")){
            checker = true;

        }
        return checker;
    }




   /* private boolean checkFields() {
        boolean checker = true;
        if (textView.getText() == null || textView.getText() == " " || textView.getText() == "") {
            checker = false;
        }
        return checker;
    }*/

}