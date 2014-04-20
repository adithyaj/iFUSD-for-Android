package com.fremontunified.ifusdandroid;



import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;

public class Website extends Activity {
	 
	Bundle bundle;
	String url;
	WebView webView;
	@Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 
		 final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	 
		 this.setContentView(R.layout.webview);
	     
 	    //code to center title. Must be on every page
         
 	    if (customTitleSupported) {
             getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                     R.layout.windowtitle);
         }
         final TextView myTitleText = (TextView) findViewById(R.id.header);
             myTitleText.setText(Global.htmlTitle);
             myTitleText.setGravity(Gravity.CENTER);

 	    //
		 
		 bundle = getIntent().getExtras();
	     url = bundle.getString("url");
	     
	     //Needed for titles besides the news
	     try {
	    	 myTitleText.setText(bundle.getString("title"));
	     } finally {
	    	 //do nothing
	     }
	     
		 webView = (WebView)findViewById(R.id.webview);
		 webView.getSettings().setJavaScriptEnabled(true);
		 webView.loadUrl(url);
		 
	 }
}