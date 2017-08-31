package beyonity.water_vendor.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import beyonity.water_vendor.Adapter.Notification_Adapter;
import beyonity.water_vendor.calci;
import beyonity.water_vendor.untils.DividerItemDecoration;
import beyonity.water_vendor.untils.Notification;
import beyonity.water_vendor.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link mainContent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link mainContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mainContent extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_C = "content";
	private static final String TAG = "themeFragment";
	private FloatingActionButton fab;

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	View notification;
	View customers;
	View accounts;
	private List<Notification> notificationList = new ArrayList<>();
	private RecyclerView recyclerView;
	private Notification_Adapter mAdapter;
	private OnFragmentInteractionListener mListener;

	public mainContent() {
		// Required empty public constructor
	}

	public static mainContent newInstance(String content) {
		Bundle args = new Bundle();
		args.putString(ARG_C, content);
		mainContent fragment = new mainContent();
		fragment.setArguments(args);
		return fragment;
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		String content = getArguments().getString(ARG_C);
		notification = inflater.inflate(R.layout.main_notification, container, false);
		customers = inflater.inflate(R.layout.customers_layout, container, false);
		accounts = inflater.inflate(R.layout.activity_acchist, container, false);
		if (Integer.parseInt(content) == 0) {
			setNotificationView(notification);
			return notification;
		} else if (Integer.parseInt(content) == 1) {
			return customers;
		}
		setAccounts(accounts);
		return accounts;
	}
	/*private void setAcchistView(accounts View accounts){
		fab = (FloatingActionButton) view.findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getContext(), calci.class);
				startActivity(intent);
			}
		});*/



	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}



	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}

	private void setNotificationView(View view){
		recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

		mAdapter = new Notification_Adapter(notificationList);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(mAdapter);

		Notification notif = new Notification("Ram", "7358339907", "2017", "Wed", "3", "15:00", "Kodungaiyur");
		notificationList.add(notif);

		notif = new Notification("Ram", "7358339907", "2017", "Fri", "5", "14:00","2nd street");
		notificationList.add(notif);

		notif = new Notification("Mohan", "7358339907", "2017", "Sat", "2", "13:00","1st street");
		notificationList.add(notif);

		notif = new Notification("Kumar", "7358339907", "2017", "Sun", "5", "12:00","madras");
		notificationList.add(notif);

		notif = new Notification("Ravi", "7358339907", "2017", "Wed", "1", "11:00","chennai");
		notificationList.add(notif);

		notif = new Notification("Chandran", "7358339907", "2017", "Wed", "9", "10:00","lol");
		notificationList.add(notif);


		mAdapter.notifyDataSetChanged();
	}

private void setAccounts(View view){

		fab = (FloatingActionButton) view.findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getContext(), calci.class);
				startActivity(intent);
			}
		});

	TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
	//tabLayout.setupWithViewPager(viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("TODAY"));
        tabLayout.addTab(tabLayout.newTab().setText("WEEK"));
        tabLayout.addTab(tabLayout.newTab().setText("MONTH"));
        tabLayout.addTab(tabLayout.newTab().setText("YEAR"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

	final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
	final tabviewpager adapter = new tabviewpager
			(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
		@Override
		public void onTabSelected(TabLayout.Tab tab) {
			viewPager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabUnselected(TabLayout.Tab tab) {

		}

		@Override
		public void onTabReselected(TabLayout.Tab tab) {

		}
	});
}
}
