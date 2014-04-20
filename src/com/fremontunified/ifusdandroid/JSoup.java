package com.fremontunified.ifusdandroid;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JSoup {

	
	
	public static void parse(String[] args) throws Exception {
	    // Get The Site and Parse it
	    Document doc = Jsoup.connect("http://www.fremont.k12.ca.us/domain/14").get();
	    // Select Table
	    Element table = doc.select(".mon_list").first();
	    Iterator<Element> lines = table.select("tr").iterator();
	    while (lines.hasNext()) {
	        Element line = lines.next();
	        System.out.println("TD text : "+line.text());
	    }
	}
}
