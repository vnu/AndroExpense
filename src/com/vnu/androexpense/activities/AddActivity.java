package com.vnu.androexpense.activities;

import java.util.RandomAccess;

import org.json.JSONObject;


import com.vnu.androexpense.R;
import com.vnu.androexpense.activities.fragments.AddFragment;
import com.vnu.androexpense.activities.fragments.AddMoreExpenseFragment;
import com.vnu.androexpense.activities.fragments.AddMoreIncomeFragment;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddActivity extends FragmentActivity {

	
	TextView tvToggle;
	TextView tvVendor;
	RadioGroup rgTransaction;
	RadioButton rbExpense;
	RadioButton rbIncome;
	Fragment addMoreExpenseFragment;
	Fragment addMoreIncomeFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		setupViews();
		insertPrimaryFragment();
		insertSecondaryFragment();
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.add, menu);
//		return true;
//	}
	
	public void insertPrimaryFragment() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = fragmentManager.beginTransaction();
		fts.replace(R.id.add_frame_container, new AddFragment());
		fts.commit();
		
		
	}
	
	public void insertSecondaryFragment() {
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = fragmentManager.beginTransaction();
		fts.replace(R.id.add_more_frame_container, new AddMoreExpenseFragment());
		addMoreExpenseFragment = (getSupportFragmentManager().findFragmentById(R.id.add_more_frame_container));
		fts.commit();
		
	}
	
	public void setupViews() {
	
		tvToggle = (TextView) findViewById(R.id.tvToggle);
		tvVendor = (TextView) findViewById(R.id.tvVendor);
		
		rgTransaction = (RadioGroup) findViewById(R.id.rgTransaction);
		
		
	}
	
	public void toggleFormVisibility(View v ) {
		android.support.v4.app.FragmentTransaction fts = getSupportFragmentManager().beginTransaction(); 

		if(tvToggle.getText().toString().equals("More")){
			findViewById(R.id.add_more_frame_container).setVisibility(View.VISIBLE);
			tvToggle.setText("Less");
		}
			
		
		else{
			findViewById(R.id.add_more_frame_container).setVisibility(View.INVISIBLE);
			tvToggle.setText("More");
		
			
		}
		
	}
	

	

}
