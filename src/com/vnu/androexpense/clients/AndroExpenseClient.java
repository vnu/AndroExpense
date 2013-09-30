package com.vnu.androexpense.clients;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AndroExpenseClient {
	private static AndroExpenseClient expenseClient = null;
	AsyncHttpClient client = new AsyncHttpClient();
	public static final String AE_URL = "http://10.0.2.2:3000/";
//	public static final String AE_URL = "http://androexpense.herokuapp.com/";
	public static final String TAG = "AndroExpenseClient";

	
	public void signin(RequestParams params, AsyncHttpResponseHandler handler) {
		client.post(AE_URL + "users.json", params, handler);
		
	}
	
	public void get_transactions(RequestParams params, AsyncHttpResponseHandler handler){
		client.get(AE_URL + "transactions.json", params, handler);
	}
	
	public void get_expenses(RequestParams params, AsyncHttpResponseHandler handler){
		client.get(AE_URL + "transactions/expenses.json", params, handler);
	}
	
	public void get_incomes(RequestParams params, AsyncHttpResponseHandler handler){
		client.get(AE_URL + "transactions/incomes.json", params, handler);
	}
	
	public void get_shared(RequestParams params, AsyncHttpResponseHandler handler){
		client.get(AE_URL + "shared_transactions/shared.json", params, handler);
	}
	public void get_toPay(RequestParams params, AsyncHttpResponseHandler handler){
		client.get(AE_URL + "shared_transactions/to_pay.json", params, handler);
	}
	
	public void get_toBePaid(RequestParams params, AsyncHttpResponseHandler handler){
		client.get(AE_URL + "shared_transactions/to_be_paid.json", params, handler);
	}
	
	public void get_Overview(RequestParams params, AsyncHttpResponseHandler handler){
		client.get(AE_URL + "transactions/overview.json", params, handler);
	}

	public void open_bills() {

	}

	public void closed_bills() {

	}

	public void accounts() {

	}

	public void account_transactions() {

	}

	public void new_transaction() {

	}

	public void new_account() {

	}
	
	 public void postTransaction( RequestParams params, AsyncHttpResponseHandler responseHandler) {
		  String url = "transactions.json" ;
		  client.post(AE_URL + url, params, responseHandler);
	 }
	  
	  
	  
//	  private static String getAbsoluteUrl(String relativeUrl) {
//	      return BASE_URL + relativeUrl;
//	  }

	public static AndroExpenseClient getInstance(
			Class<AndroExpenseClient> class1, Context context) {
		if(expenseClient == null){
			expenseClient = new AndroExpenseClient();
		}
		return expenseClient;
	}

}
