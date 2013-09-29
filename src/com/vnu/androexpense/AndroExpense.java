package com.vnu.androexpense;

import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.vnu.androexpense.clients.AndroExpenseClient;
import com.vnu.androexpense.clients.TwitterClient;

public class AndroExpense extends com.activeandroid.app.Application {
	private static Context context;
	
    @Override
    public void onCreate() {
        super.onCreate();
        AndroExpense.context = this;
        
        // Create global configuration and initialize ImageLoader with this configuration
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
        		cacheInMemory().cacheOnDisc().build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
            .defaultDisplayImageOptions(defaultOptions)
            .build();
        ImageLoader.getInstance().init(config);
    }
    
    public static TwitterClient getRestClient() {
    	return (TwitterClient) TwitterClient.getInstance(TwitterClient.class, AndroExpense.context);
    }
    
    public static AndroExpenseClient getAndroClient() {
    	return (AndroExpenseClient) AndroExpenseClient.getInstance(AndroExpenseClient.class, AndroExpense.context);
    }
    

}
