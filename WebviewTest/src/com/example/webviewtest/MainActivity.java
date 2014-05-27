package com.example.webviewtest;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ProgressDialog progressBar; 
	private String test;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		WebView webview = (WebView) findViewById(R.id.sponsors_webview);
		
		 WebSettings settings = webview.getSettings();
	        settings.setJavaScriptEnabled(true);
	      
	        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

	        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

	        progressBar = ProgressDialog.show(this, "WebView Example", "Loading...");

	        webview.setWebViewClient(new WebViewClient() {
	            public boolean shouldOverrideUrlLoading(WebView view, String url) {
	               // Log.i(TAG, "Processing webview url click...");
	                view.loadUrl(url);
	                return true;
	            }

	            public void onPageFinished(WebView view, String url) {
	                //Log.i(TAG, "Finished loading URL: " +url);
	                if (progressBar.isShowing()) {
	                    progressBar.dismiss();
	                }
	            }

	            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
	               // Log.e(TAG, "Error: " + description);
	                Toast.makeText(MainActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
	               
	            }
	        });
	        
	        webview.setWebViewClient(new MyWebViewClient());
	        webview.loadUrl("http://boulevardia.com/explore/taps-tastes/?app");
		
		
	/*	Button facebook = (Button) findViewById(R.id.button1);
		Button tweet = (Button) findViewById(R.id.button2);
		facebook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Content to share");
				PackageManager pm = v.getContext().getPackageManager();
				List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
				for (final ResolveInfo app : activityList) {
				    if ((app.activityInfo.name).contains("facebook")) {
				        final ActivityInfo activity = app.activityInfo;
				        final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
				        shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
				        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |             Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				        shareIntent.setComponent(name);
				        v.getContext().startActivity(shareIntent);
				        break;
				   }
				}
				
			}
		});
		
		tweet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Content to share");
				PackageManager pm = v.getContext().getPackageManager();
				List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
				for (final ResolveInfo app : activityList) {
				    if ((app.activityInfo.name).contains("twitter")) {
				        final ActivityInfo activity = app.activityInfo;
				        final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
				        shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
				        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |             Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
				        shareIntent.setComponent(name);
				        v.getContext().startActivity(shareIntent);
				        break;
				   }
				}
			}
		});
		
	    */    
	      

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
	private class MyWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        if (Uri.parse(url).getHost().equals("www.example.com")) {
	            // This is my web site, so do not override; let my WebView load the page
	            return false;
	        }
	        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
	        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
	        startActivity(intent);
	        return true;
	    }
	}
}
