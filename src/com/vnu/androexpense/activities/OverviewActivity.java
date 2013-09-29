package com.vnu.androexpense.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vnu.androexpense.AndroExpense;
import com.vnu.androexpense.ExpenseListActivity;
import com.vnu.androexpense.R;
import com.vnu.androexpense.SharedListActivity;

public class OverviewActivity extends Activity {
	
	String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.overview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_logout:
			Log.e("Came here", "Logout");
			AndroExpense.getRestClient().clearAccessToken();
			Intent logout = new Intent(this, LoginActivity.class);
			logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(logout);
			finish();
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void showActivity(){
		Intent trans = new Intent(this, ExpenseListActivity.class);
		trans.putExtra("type",type);
		trans.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(trans);
	}
	
	public void showSharedActivity(){
		Intent trans = new Intent(this, SharedListActivity.class);
		trans.putExtra("type",type);
		trans.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(trans);
	}
	
	public void showExpenseList(View v){
		type = "Expense";
		showActivity();
	}
	
	public void showPayeeList(View v){
		type = "Expense";
		showSharedActivity();
	}
	
	public void showPayerList(View v){
		type = "Income";
		showSharedActivity();
	}
	
	public void showIncomeList(View v){
		type = "Income";
		showActivity();
	}
	
	public void showSharedList(View v){
		type = "Shared";
		showSharedActivity();
	}


}
