package beyonity.water_vendor.Activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import beyonity.water_vendor.Adapter.BottomPageNavigationAdapter;
import beyonity.water_vendor.R;
import beyonity.water_vendor.customviews.CustomViewPager;

public class MainActivity extends AppCompatActivity {


	CustomViewPager viewPager;
    BottomNavigationView bottomMenu;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new BottomPageNavigationAdapter(getSupportFragmentManager(),3));

	    viewPager.setPagingEnabled(false);

        bottomMenu = (BottomNavigationView)findViewById(R.id.navigation);

        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.navigation_home){
                    viewPager.setCurrentItem(0);
                }else if(item.getItemId() == R.id.navigation_notifications){
                    viewPager.setCurrentItem(1);
                }else if(item.getItemId() == R.id.navigation_dashboard){
                    viewPager.setCurrentItem(2);
               }
                updateNavigationBarState(item.getItemId());

                return true;
            }
        });

    }



    private void updateNavigationBarState(int actionId){
        Menu menu = bottomMenu.getMenu();

        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            item.setChecked(item.getItemId() == actionId);
        }
    }



}
