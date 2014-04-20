package com.fremontunified.ifusdandroid;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class HtmlPage extends Activity {
	
	Bundle bundle;
	String url;
	WebView mWebView;
	Bundle nextUrlBundle = new Bundle();
	String fileName;
	File pdf;
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
        
        bundle = getIntent().getExtras();
        url = bundle.getString("url");
        
        WebViewClient webClient = new WebViewClient() {
        	
        	@Override
        	public boolean shouldOverrideUrlLoading (WebView view, String overrideUrl) {
        		if (overrideUrl.contains("?PageID=")) {
        			Intent intent = new Intent(HtmlPage.this, HtmlPage.class);
        			intent.putExtra("url", overrideUrl);
        			startActivity(intent);
        			return true;
        		} else if (overrideUrl.endsWith(".pdf") && overrideUrl.startsWith("/cms/lib/")) {
        			String newUrl = "http://www.fremont.k12.ca.us" + overrideUrl;
        			Log.e("here", newUrl);
        			view.loadUrl("http://docs.google.com/gview?embedded=true&url=" + newUrl);
        			return true;
        		} else if (overrideUrl.endsWith(".pdf") && overrideUrl.startsWith("http:")) {
        			view.loadUrl("http://docs.google.com/gview?embedded=true&url=" + overrideUrl);
        			return true;
        		} else {
        			return false;
        		}
        	}
        };
		
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
			boolean append = false;
			int tagCount = 0;
			while((line = reader.readLine()) != null)
			{
				if (line.contains("<div id=\"sw-content-layout")) {
				    append = true;
				}
				if (append == true) {
					str.append(line);
					
					for (int i=0; i<line.length(); i++) {
						if (line.charAt(i) == '<') {
							if (line.charAt(i+1) == '/') {
								tagCount--;
							} else {
								tagCount++;
							}
						}
					}
				}
				if (tagCount < 0) {
					append = false;
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
		htmlString = htmlString.replace("\"" + "/cms/", "\"" + "http://www.fremont.k12.ca.us/cms/");
		Log.e("html string", htmlString);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(webClient);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadDataWithBaseURL(null, htmlString, "text/html", "UTF-8", null);

	}
}