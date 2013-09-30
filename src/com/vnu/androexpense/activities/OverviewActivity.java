package com.vnu.androexpense.activities;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vnu.androexpense.AndroExpense;
import com.vnu.androexpense.ExpenseListActivity;
import com.vnu.androexpense.R;
import com.vnu.androexpense.SharedListActivity;

public class OverviewActivity extends Activity {

	String type;
	RequestParams params;
	AsyncHttpResponseHandler handler;
	TextView tvExpenseAmtOv;
	TextView tvOthersOweAmtOv;
	TextView tvYouOweAmtOv;
	TextView tvSharedAmtOv;
	TextView tvIncomeAmtOv;
	String username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
		initialize();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		updateOverview();
	}

	public void updateOverview() {
		AndroExpense.getAndroClient().get_Overview(params, handler);
	}

	public void updateFields(JSONObject jo) {
		try {
			tvExpenseAmtOv.setText(jo.getString("expenses"));
			tvIncomeAmtOv.setText(jo.getString("incomes"));
			tvSharedAmtOv.setText(jo.getString("shared"));
			tvYouOweAmtOv.setText(jo.getString("to_pay"));
			tvOthersOweAmtOv.setText(jo.getString("to_be_paid"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void initialize() {
		tvExpenseAmtOv = (TextView) findViewById(R.id.tvExpenseAmtOv);
		tvIncomeAmtOv = (TextView) findViewById(R.id.tvIncomeAmtOv);
		tvSharedAmtOv = (TextView) findViewById(R.id.tvSharedAmtOv);
		tvYouOweAmtOv = (TextView) findViewById(R.id.tvYouOweAmtOv);
		tvOthersOweAmtOv = (TextView) findViewById(R.id.tvOthersOweAmtOv);
		
		loadSavedPreferences();

		params = new RequestParams();
		params.put("username", username);
		handler = new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				try {
					JSONObject object = new JSONObject(response);
					updateFields(object);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
			}
		};

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

	public void showActivity() {
		Intent trans = new Intent(this, ExpenseListActivity.class);
		trans.putExtra("type", type);
		trans.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(trans);
	}

	public void showSharedActivity() {
		Intent trans = new Intent(this, SharedListActivity.class);
		trans.putExtra("type", type);
		trans.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(trans);
	}

	public void showExpenseList(View v) {
		type = "Expense";
		showActivity();
	}

	public void showPayeeList(View v) {
		type = "Expense";
		showSharedActivity();
	}

	public void showPayerList(View v) {
		type = "Income";
		showSharedActivity();
	}

	public void showIncomeList(View v) {
		type = "Income";
		showActivity();
	}

	public void showSharedList(View v) {
		type = "Shared";
		showSharedActivity();
	}
	
	private void loadSavedPreferences() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		username = sharedPreferences.getString("username", "androexp1");

	}

}
