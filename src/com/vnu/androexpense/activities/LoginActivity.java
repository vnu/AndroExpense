package com.vnu.androexpense.activities;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.codepath.oauth.OAuthLoginActivity;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vnu.androexpense.AndroExpense;
import com.vnu.androexpense.R;
import com.vnu.androexpense.clients.TwitterClient;
import com.vnu.androexpense.models.User;

public class LoginActivity extends OAuthLoginActivity<TwitterClient> {
	
	protected static final String TAG = "Login Activity";
	Button btnSignin;
	LinearLayout llLoading;
	RelativeLayout rlSignup;
	RequestParams params;
	User user;
	AsyncHttpResponseHandler handler;
	String status = null;
	String username;
	String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void setProfileInfo(User user){
		params = new RequestParams();
		username = user.getScreenName();
		name = user.getName();
		params.put("uid", user.getUserId());
		params.put("username", username);
		params.put("name", name);
		savePreferences("username", username);
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
		if(status.equalsIgnoreCase("signup")){
			Intent signup = new Intent(this, SignupActivity.class);
			signup.putExtra("username",username);
			signup.putExtra("name", name);
			signup.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(signup);
			finish();
		}else{
			loginToExpense();
		}
			
		
	}
	
	private void getProfileInfo() {
		AndroExpense.getRestClient().getUserInfo(null,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject json) {
						user = User.fromJson(json);
						setProfileInfo(user);
						AndroExpense.getAndroClient().signin(params, handler);
					}

					@Override
					public void onFailure(Throwable error, String content) {
						Log.e("Failed", content);
						Toast.makeText(getApplicationContext(), "OOPs", Toast.LENGTH_LONG).show();
					}
				});
	}
	
	private void savePreferences(String key, String value) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}


	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setUpViews();
		if(getClient().isAuthenticated()){
			btnSignin.setVisibility(View.INVISIBLE);
			llLoading.setVisibility(View.VISIBLE);
			getProfileInfo();
		}
//		loginToExpense();
		
	}
	
	public void setUpViews(){
		btnSignin = (Button)findViewById(R.id.btnSignin);
		llLoading = (LinearLayout)findViewById(R.id.llLoading);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.codepath.oauth.OAuthBaseClient.OAuthAccessHandler#onLoginSuccess()
	 * 
	 * OAuth authenticated successfully, launch primary authenticated activity
	 * i.e Display application "Overview"
	 */
	@Override
	public void onLoginSuccess() {
		//Add Spinner, Make another Async Call to get Info, pass to andro tweet and then show sign up or sign in
		
		getProfileInfo();
	}
	
	public void loginToExpense(){
		Intent login = new Intent(this, OverviewActivity.class);
		login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(login);
		finish();
	}

	// OAuth authentication flow failed, handle the error
	// i.e Display an error dialog or toast
	@Override
	public void onLoginFailure(Exception e) {
		Toast.makeText(this, "Login Failed. Please Try Again", Toast.LENGTH_LONG).show();
		e.printStackTrace();
	}

	// Click handler method for the button used to start OAuth flow
	// Uses the client to initiate OAuth authorization
	// This should be tied to a button used to login
	public void loginToTwitter(View view) {
		getClient().connect();
		btnSignin.setVisibility(View.INVISIBLE);
		llLoading.setVisibility(View.VISIBLE);
	}

}
