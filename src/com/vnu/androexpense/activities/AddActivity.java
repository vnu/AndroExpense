package com.vnu.androexpense.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vnu.androexpense.R;
import com.vnu.androexpense.activities.fragments.AddFragment;
import com.vnu.androexpense.activities.fragments.AddMoreExpenseFragment;

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

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.add, menu);
	// return true;
	// }

	public void insertPrimaryFragment() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = fragmentManager
				.beginTransaction();
		fts.replace(R.id.add_frame_container, new AddFragment());
		fts.commit();

	}

	public void insertSecondaryFragment() {

		FragmentManager fragmentManager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = fragmentManager
				.beginTransaction();
		fts.replace(R.id.add_more_frame_container, new AddMoreExpenseFragment());
		addMoreExpenseFragment = (getSupportFragmentManager()
				.findFragmentById(R.id.add_more_frame_container));
		fts.commit();

	}

	public void setupViews() {

		tvToggle = (TextView) findViewById(R.id.tvToggle);
		tvVendor = (TextView) findViewById(R.id.tvVendor);

		rgTransaction = (RadioGroup) findViewById(R.id.rgTransaction);

	}

	public void toggleFormVisibility(View v) {
		android.support.v4.app.FragmentTransaction fts = getSupportFragmentManager()
				.beginTransaction();

		if (tvToggle.getText().toString().equals("More")) {
			findViewById(R.id.add_more_frame_container).setVisibility(
					View.VISIBLE);
			tvToggle.setText("Less");
		}

		else {
			findViewById(R.id.add_more_frame_container).setVisibility(
					View.INVISIBLE);
			tvToggle.setText("More");

		}

	}
	
	public void onAdd(View v){
		FragmentManager fm = getSupportFragmentManager();
		AddFragment f = (AddFragment) fm.findFragmentById(R.id.add_frame_container);
		f.onAdd();
	}
}
