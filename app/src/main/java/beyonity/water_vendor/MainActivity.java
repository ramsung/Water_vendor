package beyonity.water_vendor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import beyonity.water_vendor.Adapter.Notification_Adapter;

public class MainActivity extends AppCompatActivity {
    private List<Notification> notificationList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Notification_Adapter mAdapter;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
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
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new Notification_Adapter(notificationList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareNotificationData();

    }
    private void prepareNotificationData() {
        Notification notif = new Notification("Ram", "7358339907", "2017", "Wed", "3", "15:00");
        notificationList.add(notif);

        notif = new Notification("Ram", "7358339907", "2017", "Fri", "5", "14:00");
        notificationList.add(notif);

        notif = new Notification("Mohan", "7358339907", "2017", "Sat", "2", "13:00");
        notificationList.add(notif);

        notif = new Notification("Kumar", "7358339907", "2017", "Sun", "5", "12:00");
        notificationList.add(notif);

        notif = new Notification("Ravi", "7358339907", "2017", "Wed", "1", "11:00");
        notificationList.add(notif);

        notif = new Notification("Chandran", "7358339907", "2017", "Wed", "9", "10:00");
        notificationList.add(notif);


        mAdapter.notifyDataSetChanged();
    }
}
