package com.vnu.androexpense.clients;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class TwitterClient extends OAuthBaseClient {
	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
	public static final String REST_URL = "https://api.twitter.com/1.1";
	public static final String REST_CONSUMER_KEY = "oUHs6yS5NNxLx9ZeRum3w";
	public static final String REST_CONSUMER_SECRET = "sry3Sg0hmKgfyOlHlPDwNCJT2p2Evm7VsoFsTEpxok";
	public static final String REST_CALLBACK_URL = "oauth://androexpense";

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY,
				REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	public void getUserInfo(RequestParams params,
			AsyncHttpResponseHandler handler) {
		Log.w("UserInfo","Getting User Profile");
		String url = getApiUrl("account/verify_credentials.json");
		client.get(url, params, handler);
	}

	public void invalidate_token(AsyncHttpResponseHandler handler) {
		String url = getApiUrl("/invalidate_token");
		client.post(url, null, handler);
	}

}