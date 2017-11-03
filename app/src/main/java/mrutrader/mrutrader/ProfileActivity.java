package mrutrader.mrutrader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth fireBaseAuth;

    private TextView textViewUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fireBaseAuth = FirebaseAuth.getInstance();

        if(fireBaseAuth.getCurrentUser() == null){

        }

        FirebaseUser user = fireBaseAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {

    }
}
