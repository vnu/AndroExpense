package com.vnu.androexpense.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vnu.androexpense.AndroExpense;
import com.vnu.androexpense.R;

public class SharedListActivity extends SherlockFragmentActivity {
	String username;
	RequestParams params;
	AsyncHttpResponseHandler handler;
	Button tvAction;
	Intent i;
	EditText etAcnt;
	View rv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_list);
		loadSavedPreferences();
		i = getIntent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	public void onAction(View v) {
		params = new RequestParams();
		params.put("username", username);
		tvAction = (Button) v.findViewById(R.id.tvSTransAction);
		String st_id = tvAction.getTag().toString();

		String action = (String) tvAction.getText();
		params.put("st_id", st_id);
		if (action.equalsIgnoreCase("Pay")) {
			AndroExpense.getAndroClient().pay_bill(params, handler);
		} else if (action.equalsIgnoreCase("Confirm")) {

			etAcnt = (EditText) rv.findViewById(R.id.etAcnt);
			String acnt = etAcnt.getText().toString();
			params.put("account", acnt);
			AndroExpense.getAndroClient().confirm_payment(params, handler);
		}
	}

	public void setValues(View v) {
		tvAction = (Button) v.findViewById(R.id.tvSTransAction);
		String action = (String) tvAction.getText();
		if (action.equalsIgnoreCase("Confirm")) {

			etAcnt = (EditText) rv.findViewById(R.id.etAcnt);
		}

	}

	public void restart() {
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);
	}

	public void initialize() {
		handler = new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				restart();
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
			}
		};
	}

	private void loadSavedPreferences() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		username = sharedPreferences.getString("username", "androexp1");

	}
}
