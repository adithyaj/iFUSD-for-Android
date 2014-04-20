package com.fremontunified.ifusdandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;

public class NewsHTMLPage extends Activity {
	
	Bundle bundle;
	String url;
	WebView mWebView;
	
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
            myTitleText.setText(this.getTitle());
            myTitleText.setGravity(Gravity.CENTER);

	    //
	    
		
        bundle = getIntent().getExtras();
        url = bundle.getString("url");
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		InputStream in = null;
		try {
			in = response.getEntity().getContent();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder str = new StringBuilder();
		String line = null;
		
		try {
			
			boolean appendThis = false;
			while((line = reader.readLine()) != null)
			{
				if (line.contains("<div class=\"ui-headline-author\">")) {
				    appendThis = false;
				}
				
				if (appendThis == true) {
					str.append(line);
				}
				
				if (line.contains("<div class=\"ui-widget app headlines detail\">")) {
				    appendThis = true;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			
		} 
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
//		String htmlString = restresponse;
		String htmlString = "<html><body>" + str.toString() + "</body></html>";		
		htmlString = htmlString.replace("\"" + "/cms/lib/CA01000848/Centricity/Domain", "\"" + "http://www.fremont.k12.ca.us/cms/lib/CA01000848/Centricity/Domain");

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadDataWithBaseURL(null, htmlString, "text/html", "UTF-8", null);

	}
}