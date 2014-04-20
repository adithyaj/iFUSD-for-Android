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

public class Emergency extends Activity {
	
	public static String rssText;
	
	WebView mWebView;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

	    final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

	    //code to center title. Must be on every page
        
	    if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                    R.layout.windowtitle);
        }
        final TextView myTitleText = (TextView) findViewById(R.id.header);
            myTitleText.setText("Emergency");
            myTitleText.setGravity(Gravity.CENTER);

	    //
        
        parseDoc();

        
        this.setContentView(R.layout.webview);
        
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        
        String htmlString = "<html><body>" + rssText + "</body></html>";
        
        mWebView.loadData(htmlString, "text/html", "UTF-8");
	}
	
	public void parseDoc() {
	    	
	    	ContentHandler rssHandler = new RSSContentHandler();
	
	    	InputSource url = new InputSource("http://www.fremont.k12.ca.us/site/RSS.aspx?DomainID=3692&ModuleInstanceID=7420&PageID=31");
	
	    	
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