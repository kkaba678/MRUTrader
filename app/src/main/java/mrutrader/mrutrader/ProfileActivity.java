package mrutrader.mrutrader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{


    private static final int REQUEST_CODE = 1;
    private FirebaseAuth fireBaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fireBaseAuth = FirebaseAuth.getInstance();

        if(fireBaseAuth.getCurrentUser() == null){

        }

        FirebaseUser user = fireBaseAuth.getCurrentUser();
    }

    public void addCourse(View v){

    }

    @Override
    public void onClick(View v) {
        Button addCourse = (Button) findViewById(R.id.addCourseButton);
        Intent intent = new Intent(this,AddCourseActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("id")) {

            }
        }
    }
}
