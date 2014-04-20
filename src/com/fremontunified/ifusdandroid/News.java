package com.fremontunified.ifusdandroid;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class News extends ListActivity {
	
	static String[] titles;
	static String[] descriptions;
	static String[] links;
	
	Intent intent;
	Bundle bundle;
	
	public void onCreate(Bundle savedInstanceState) {
		
	    final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coloredlistview);
	    //code to center title. Must be on every page
        
	    if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                    R.layout.windowtitle);
        }
        final TextView myTitleText = (TextView) findViewById(R.id.header);
        
            myTitleText.setText(this.getTitle());
            myTitleText.setGravity(Gravity.CENTER);

	    //
	    
        parseDoc();
	    
        titles = removeElement1(titles);
        descriptions = removeElement1(descriptions);
        links = removeElement1(links);
        
        this.setListAdapter(new CustomArrayAdapter(this, titles, descriptions));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		intent = new Intent(News.this, NewsHTMLPage.class);
		intent.putExtra("url", links[position]);
		startActivity(intent);
	}
	
	public void parseDoc() {
	    	
	    	ContentHandler rssHandler = new RSSNewsHandler();
	
	    	InputSource url = new InputSource("http://www.fremont.k12.ca.us/site/RSS.aspx?DomainID=1&ModuleInstanceID=4613&PageID=1");
	
	    	
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
	
	
	private String[] removeElement1(String[] initial) {
		String[] finalString = new String[initial.length - 1];
		
		for (int n=1; n < initial.length; n++) {
			finalString[n-1] = initial[n];
		}
		return finalString;
	}
}