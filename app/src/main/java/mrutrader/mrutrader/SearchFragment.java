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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "AddCourseActivity";
    private static final int RESULT_OK = 1;
    private FirebaseUser user;
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
    String[] courses = {"COMP3512", "COMP4455", "COMP5520", "GNED1203", "GNED1203"};
    ArrayAdapter<String> adapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_search, container, false);

        FirebaseAuth fireBaseAuth = FirebaseAuth.getInstance();
        user = fireBaseAuth.getCurrentUser();
        userID = user.getUid();

        lv = (ListView) view.findViewById(R.id.listView1);
        sv = (SearchView) view.findViewById(R.id.searchView1);
        textView = (TextView) view.findViewById(R.id.textView3);
        textView.setVisibility(View.INVISIBLE);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, courses);
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
                String txt = (String) lv.getAdapter().getItem(position);
                textView.setText(txt);
            }
        });

        button2 = (Button) view.findViewById(R.id.button2);
        button4 = (Button) view.findViewById(R.id.button4);

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (checkFields()) {

                    textBookRadioButton = (RadioButton) getView().findViewById(R.id.textbookRadioButton);
                    notesRadioButton = (RadioButton) getView().findViewById(R.id.notesRadioButton);
                    tutorRadioButton = (RadioButton) getView().findViewById(R.id.tutorRadioButton);
                    tradeRadioButton = (RadioButton) getView().findViewById(R.id.tradeRadioButton);

                    String cName = (String) textView.getText();

                    try {
                        boolean textBook = textBookRadioButton.isChecked();
                        boolean notes = notesRadioButton.isChecked();
                        boolean tutoring = tutorRadioButton.isChecked();
                        boolean trade = tradeRadioButton.isChecked();
                        Course course = new Course(userID, cName, 0, textBook, notes, tutoring, trade);

                        Intent intent = new Intent(getActivity(), SearchResultFragment.class);
                        intent.putExtra("courseName", cName);
                        intent.putExtra("textBook", textBook);
                        intent.putExtra("notes", notes);
                        intent.putExtra("tutoring", tutoring);
                        intent.putExtra("trade", trade);
                        startActivity(intent);


                    }

                    catch (NumberFormatException e) {
                        e.getMessage();
                    }

   }

            }
        });


        return view;
    }

    @Override
    public void onClick(View v) {
/*
        if (v == button2) {

            else {
                Toast.makeText(getActivity(), "Please enter a course name", Toast.LENGTH_SHORT);
            }
        } else if (v == button4) {
            Intent intent = new Intent(getActivity(), ProfileBlankFragment.class);
            startActivity(intent);
        } */
    }


    private boolean checkFields() {
        boolean checker = true;
        if (textView.getText() == null || textView.getText() == " " || textView.getText() == "") {
            checker = false;
        }
        return checker;
    }

}
