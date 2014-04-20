package com.fremontunified.ifusdandroid;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;

public class Rssweb extends Activity {
	
	public static String rssText;
	
	Bundle bundle;
	String bundleString;
	InputSource url;
	WebView mWebView;
	
	public void onCreate(Bundle savedInstanceState) {

		final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
        super.onCreate(savedInstanceState);

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
        bundleString = bundle.getString("url");
        
        parseDoc();
       
        this.setContentView(R.layout.webview);
        
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        
        String htmlString = "<html><body>" + rssText + "</body></html>";
        
        
        
        mWebView.loadDataWithBaseURL(null, htmlString, "text/html", "UTF-8", null);
	}
	
	public void parseDoc() {
	    	
	    	ContentHandler rssHandler = new RSSContentHandler();
	    	
	    	url = new InputSource(bundleString);
	
	    	
	    	try {
	            // Instantiate a parser
	    		 SAXParserFactory parserFactory = SAXParserFactory.newInstance();
	             
	    		 try {
	    			 SAXParser parser = parserFactory.newSAXParser();
	                 XMLReader reader = parser.getXMLReader();
	                 reader.setContentHandler(rssHandler);
	                 // Parse the document
	                 reader.parse(url);
	    		 } catch (ParserConfigurationException e) {
	    			 //Like I care what happens
	    		 }
	        } catch (IOException e) {
	            System.out.println("Error reading URI: " + e.getMessage());
	        } catch (SAXException e) {
	            System.out.println("Error in parsing: " + e.getMessage());
	        }
	    }
}