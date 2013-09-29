package com.vnu.androexpense.activities;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vnu.androexpense.AndroExpense;
import com.vnu.androexpense.R;

public class SignupActivity extends Activity {
	
	EditText etName;
	EditText etEmail;
	Button btnSignup;
	AsyncHttpResponseHandler handler;
	RequestParams params;
	String status;
	String username;
	String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		setUpViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
		return true;
	}
	
	public void setUpViews(){
		etEmail = (EditText)findViewById(R.id.etEmail);
		etName = (EditText)findViewById(R.id.etName);
		btnSignup = (Button)findViewById(R.id.btnSignup);
		params = new RequestParams();
		Intent intent = getIntent();
		username = intent.getStringExtra("username");
		name = intent.getStringExtra("name");
		etName.setText(name);
		
		handler = new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				try {
					JSONObject object = new JSONObject(response);
					status = (String) object.get("auth");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				next_step(status);
			}
			
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
			}
		};
	}
	
	public void next_step(String status){
		if(status.equalsIgnoreCase("signin")){
			loginToExpense();
		}else{
			Toast.makeText(this, "There was some error with your signup. Please Try Again", Toast.LENGTH_LONG).show();
		}
	}	
	public void loginToExpense(){
		Intent login = new Intent(this, OverviewActivity.class);
		login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(login);
		finish();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		AndroExpense.getRestClient().clearAccessToken();
		Intent logout = new Intent(this, LoginActivity.class);
		logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(logout);
		finish();
	}
	
	public void onSignup(View v){
		String email = etEmail.getText().toString();
		String name = etName.getText().toString();
		params.put("email", email);
		params.put("name", name);
		params.put("username", username);
		AndroExpense.getAndroClient().signin(params, handler);
	}

}
