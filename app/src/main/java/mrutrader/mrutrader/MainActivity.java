package mrutrader.mrutrader;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    ProfileBlankFragment profileBlankFragment = new ProfileBlankFragment();
                    android.app.FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.content, profileBlankFragment, profileBlankFragment.getTag()).commit();
                    return true;
                case R.id.navigation_dashboard:
                    SearchFragment searchFragmentFragment = new SearchFragment();
                    android.app.FragmentManager manager1 = getFragmentManager();
                    manager1.beginTransaction().replace(R.id.content, searchFragmentFragment, searchFragmentFragment.getTag()).commit();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ProfileBlankFragment profileBlankFragment = new ProfileBlankFragment();
        android.app.FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.content, profileBlankFragment, profileBlankFragment.getTag()).commit();

    }

}
