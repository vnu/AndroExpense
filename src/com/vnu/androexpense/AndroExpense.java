package com.vnu.androexpense;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.pushbots.push.Pushbots;
import com.vnu.androexpense.clients.AndroExpenseClient;
import com.vnu.androexpense.clients.TwitterClient;
import com.vnu.androexpense.receivers.CustomPushReceiver;

public class AndroExpense extends com.activeandroid.app.Application {
	private static Context context;
	private String SENDER_ID = "242052936053";
	private String PUSHBOTS_APPLICATION_ID = "5248ecf34deeae9d060036a1";

	@Override
	public void onCreate() {
		super.onCreate();
		Pushbots.init(this, SENDER_ID, PUSHBOTS_APPLICATION_ID);
		Pushbots.getInstance().setMsgReceiver(CustomPushReceiver.class);

		AndroExpense.context = this;

		// Create global configuration and initialize ImageLoader with this
		// configuration
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).defaultDisplayImageOptions(
				defaultOptions).build();
		ImageLoader.getInstance().init(config);
	}

	public static TwitterClient getRestClient() {
		return (TwitterClient) TwitterClient.getInstance(TwitterClient.class,
				AndroExpense.context);
	}

	public static AndroExpenseClient getAndroClient() {
		return (AndroExpenseClient) AndroExpenseClient.getInstance(
				AndroExpenseClient.class, AndroExpense.context);
	}

	public static boolean isActivityVisible() {
		return activityVisible;
	}

	public static void activityResumed() {
		activityVisible = true;
	}

	public static void activityPaused() {
		activityVisible = false;
	}

	private static boolean activityVisible;

}
