package com.fremontunified.ifusdandroid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
 
public class SchoolsList extends ListActivity {
        ListView myListView;
        ArrayList<String> elements;
 
    	InputSource url;
    	
    	Intent intent;
        
        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

        	    final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
                
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
                
                elements = new ArrayList<String>();
            	switch (Global.schoolType) {
         		case 0:
                    elements = new ArrayList<String>(Arrays.asList(Global.elemNames));
        			break;
        			
         		case 1:
        			elements = new ArrayList<String>(Arrays.asList(Global.middleNames));

        			break;
         		case 2:
        			elements = new ArrayList<String>(Arrays.asList(Global.highNames));

        			break;
        			
        		default: break;
            	}
                
                Collections.sort(elements); // Must be sorted!
 
                // listview
                myListView = getListView();
                myListView.setFastScrollEnabled(true);
                
                MyIndexerAdapter<String> adapter = new MyIndexerAdapter<String>(
                                this, android.R.layout.simple_list_item_1,
                                elements);
                myListView.setAdapter(adapter);
 
        }
        
        @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
        	intent = new Intent(SchoolsList.this, SchoolInfo.class);
        	intent.putExtra("location", position);
        	startActivity(intent);
        }
	public void parseDoc() {
	    	
	    	ContentHandler rssHandler = new SchoolsRSS();
        	switch (Global.schoolType) {
     		case 0:
    	    	url = new InputSource("https://spreadsheets.google.com/feeds/list/0AmrEJsynQlIZdGFvTjFqVnMydHJQVVNIRTZsSzgtaEE/1/public/values");
    			break;
    			
     		case 1:
    	    	url = new InputSource("https://spreadsheets.google.com/feeds/list/0AmrEJsynQlIZdGFvTjFqVnMydHJQVVNIRTZsSzgtaEE/2/public/values");

    			break;
     		case 2:
    	    	url = new InputSource("https://spreadsheets.google.com/feeds/list/0AmrEJsynQlIZdGFvTjFqVnMydHJQVVNIRTZsSzgtaEE/3/public/values");
    			break;
    			
    		default: break;
        	}
	
	    	
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
 
        class MyIndexerAdapter<T> extends ArrayAdapter<T> implements SectionIndexer {
 
                ArrayList<String> myElements;
                HashMap<String, Integer> alphaIndexer;
 
                String[] sections;
 
                public MyIndexerAdapter(Context context, int textViewResourceId,
                                List<T> objects) {
                        super(context, textViewResourceId, objects);
                        myElements = (ArrayList<String>) objects;
                        // here is the tricky stuff
                        alphaIndexer = new HashMap<String, Integer>();
                        // in this hashmap we will store here the positions for
                        // the sections
 
                        int size = elements.size();
                        for (int i = size - 1; i >= 0; i--) {
                                String element = elements.get(i);
                                alphaIndexer.put(element.substring(0, 1), i);
                        //We store the first letter of the word, and its index.
                        //The Hashmap will replace the value for identical keys are putted in
                        }
 
                        // now we have an hashmap containing for each first-letter
                        // sections(key), the index(value) in where this sections begins
 
                        // we have now to build the sections(letters to be displayed)
                        // array .it must contains the keys, and must (I do so...) be
                        // ordered alphabetically
 
                        Set<String> keys = alphaIndexer.keySet(); // set of letters ...sets
                        // cannot be sorted...
 
                        Iterator<String> it = keys.iterator();
                        ArrayList<String> keyList = new ArrayList<String>(); // list can be
                        // sorted
 
                        while (it.hasNext()) {
                                String key = it.next();
                                keyList.add(key);
                        }
 
                        Collections.sort(keyList);
 
                        sections = new String[keyList.size()]; // simple conversion to an
                        // array of object
                        keyList.toArray(sections);
 
                        // ooOO00K !
 
                }
 
                @Override
                public int getPositionForSection(int section) {
                        String letter = sections[section];
 
                        return alphaIndexer.get(letter);
                }
 
                @Override
                public int getSectionForPosition(int position) {
 
                        // you will notice it will be never called (right?)
                        return 0;
                }
 
                @Override
                public Object[] getSections() {
 
                        return sections; // to string will be called each object, to display
                        // the letter
                }
 
        }
}