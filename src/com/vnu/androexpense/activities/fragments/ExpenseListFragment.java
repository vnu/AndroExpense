package com.vnu.androexpense.activities.fragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vnu.androexpense.AndroExpense;
import com.vnu.androexpense.models.Transaction;

public class ExpenseListFragment extends TransactionsFragment {
	
	ListView lvExpenses;
	RequestParams params;
	AsyncHttpResponseHandler handler;
	String type;
	String username;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialConfig();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadSavedPreferences();
		params.put("username", username);

		if(type.equalsIgnoreCase("Income")){
			getIncomes();
		}else if(type.equalsIgnoreCase("Expense")){
			getExpenses();
		}else if(type.equalsIgnoreCase("Shared")){
			getShared();
		}
		
	}
	
	public void initialConfig(){
		params = new RequestParams();
		Intent i = getActivity().getIntent();
		type = i.getStringExtra("type");
		handler = new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String response) {
				JSONObject json;
				try {
					json = new JSONObject(response);
					JSONArray jsonArray = json.getJSONArray("transactions");
					trans = Transaction.fromJson(jsonArray);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				getAdapter().addAll(trans);
			}
			
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
				Log.e("Err", arg1);
			}
			
			@Override
			public void onFinish() {
				super.onFinish();
			}
			
		};
	}
	
	public void getExpenses(){
		AndroExpense.getAndroClient().get_expenses(params, handler);
	}
	
	public void getIncomes(){
		AndroExpense.getAndroClient().get_incomes(params, handler);
	}
	
	public void getShared(){
		AndroExpense.getAndroClient().get_shared(params, handler);
	}
	
	private void loadSavedPreferences() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this.getActivity());
		username = sharedPreferences.getString("username", "androexp1");

	}

}
