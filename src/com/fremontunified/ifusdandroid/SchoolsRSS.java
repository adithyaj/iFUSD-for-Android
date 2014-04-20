package com.fremontunified.ifusdandroid;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import android.util.Log;

public class SchoolsRSS implements ContentHandler {
	
    final String parseMark = "hafindjn";
	
	Locator locator;
	
    StringBuilder longName = new StringBuilder();
    StringBuilder longLocation = new StringBuilder();
    StringBuilder longPhone = new StringBuilder();
    StringBuilder longPrincipal = new StringBuilder();
    StringBuilder longSecretary = new StringBuilder();
    StringBuilder longLatitude = new StringBuilder();
    StringBuilder longLongitude = new StringBuilder();
    StringBuilder longSite = new StringBuilder();
    StringBuilder longHeadline = new StringBuilder();
    StringBuilder longEvent = new StringBuilder();
    StringBuilder longAnnouncement = new StringBuilder();
    
	boolean inEntry = false;
	boolean inName = false;
	boolean inLocation = false;
	boolean inPhone = false;
	boolean inPrincipal = false;
	boolean inSecretary = false;
	boolean inLatitude = false;
	boolean inLongitude = false;
	boolean inSite = false;
	boolean inHeadline = false;
	boolean inEvent = false;
	boolean inAnnouncement = false;
	
	public void setDocumentLocator(Locator locator) {
	    
    	this.locator = locator;
    	Log.e("tag", locator.getSystemId());
    
    }
    /**
     * <p>
     * This indicates the start of a Document parse—this precedes
     *   all callbacks in all SAX Handlers with the sole exception
     *   of <code>{@link #setDocumentLocator}</code>.
     * </p>
     *
     * @throws <code>SAXException</code> when things go wrong
     */
    public void startDocument() throws SAXException {
        longName = new StringBuilder();
        longLocation = new StringBuilder();
        longPhone = new StringBuilder();
        longPrincipal = new StringBuilder();
        longSecretary = new StringBuilder();
        longLatitude = new StringBuilder();
        longLongitude = new StringBuilder();
        longSite = new StringBuilder();
        longHeadline = new StringBuilder();
        longEvent = new StringBuilder();
        longAnnouncement = new StringBuilder();
    }
    /**
     * <p>
     * This indicates the end of a Document parse—this occurs after
     *   all callbacks in all SAX Handlers.</code>.
     * </p>
     *
     * @throws <code>SAXException</code> when things go wrong
     */
    public void endDocument() throws SAXException {

    	longName.delete(longName.length()-parseMark.length(), longName.length());
    	longLocation.delete(longLocation.length()-parseMark.length(), longLocation.length());
    	longPhone.delete(longPhone.length()-parseMark.length(), longPhone.length());
    	longPrincipal.delete(longPrincipal.length()-parseMark.length(), longPrincipal.length());
    	longSecretary.delete(longSecretary.length()-parseMark.length(), longSecretary.length());
    	longLatitude.delete(longLatitude.length()-parseMark.length(), longLatitude.length());
    	longLongitude.delete(longLongitude.length()-parseMark.length(), longLongitude.length());
    	longSite.delete(longSite.length()-parseMark.length(), longSite.length());
    	longHeadline.delete(longHeadline.length()-parseMark.length(), longHeadline.length());
    	longEvent.delete(longEvent.length()-parseMark.length(), longEvent.length());
    	longAnnouncement.delete(longAnnouncement.length()-parseMark.length(), longAnnouncement.length());
    	
    	switch (Global.schoolType) {
     		case 0:
    			Global.elemNames = longName.toString().split(parseMark, -1);
    			Global.elemLocation = longLocation.toString().split(parseMark, -1);
    			Global.elemPhone = longPhone.toString().split(parseMark, -1);
    			Global.elemPrincipal = longPrincipal.toString().split(parseMark, -1);
    			Global.elemSecretary = longSecretary.toString().split(parseMark, -1);
    			Global.elemLatitude = longLatitude.toString().split(parseMark, -1);
    			Global.elemLongitude = longLongitude.toString().split(parseMark, -1);
    			Global.elemSite = longSite.toString().split(parseMark, -1);
    			Global.elemHeadlines = longHeadline.toString().split(parseMark, -1);
    			Global.elemEvents = longEvent.toString().split(parseMark, -1);
    			Global.elemAnnouncements = longAnnouncement.toString().split(parseMark, -1);
    			break;
    			
     		case 1:
    			Global.middleNames = longName.toString().split(parseMark, -1);
    			Global.middleLocation = longLocation.toString().split(parseMark, -1);
    			Global.middlePhone = longPhone.toString().split(parseMark, -1);
    			Global.middlePrincipal = longPrincipal.toString().split(parseMark, -1);
    			Global.middleSecretary = longSecretary.toString().split(parseMark, -1);
     			Global.middleLatitude = longLatitude.toString().split(parseMark, -1);
    			Global.middleLongitude = longLongitude.toString().split(parseMark, -1);
    			Global.middleSite = longSite.toString().split(parseMark, -1);
    			Global.middleHeadlines = longHeadline.toString().split(parseMark, -1);
    			Global.middleEvents = longEvent.toString().split(parseMark, -1);
    			Global.middleAnnouncements = longAnnouncement.toString().split(parseMark, -1);
    			break;
     		case 2:
    			Global.highNames = longName.toString().split(parseMark, -1);
    			Global.highLocation = longLocation.toString().split(parseMark, -1);
    			Global.highPhone = longPhone.toString().split(parseMark, -1);
    			Global.highPrincipal = longPrincipal.toString().split(parseMark, -1);
    			Global.highSecretary = longSecretary.toString().split(parseMark, -1);
     			Global.highLatitude = longLatitude.toString().split(parseMark, -1);
    			Global.highLongitude = longLongitude.toString().split(parseMark, -1);
    			Global.highSite = longSite.toString().split(parseMark, -1);
    			Global.highHeadlines = longHeadline.toString().split(parseMark, -1);
    			Global.highEvents = longEvent.toString().split(parseMark, -1);
    			Global.highAnnouncements = longAnnouncement.toString().split(parseMark, -1);
    			break;
    			
    		default: break;
    	}
    }
    /**
     * <p>
     * This indicates that a processing instruction (other than
     *   the XML declaration) has been encountered.
     * </p>
     *
     * @param target <code>String</code> target of PI
     * @param data <code>String</code containing all data sent to the PI.
     *             This typically looks like one or more attribute value
     *             pairs.
     * @throws <code>SAXException</code> when things go wrong
     */
    public void processingInstruction(String target, String data)
        throws SAXException {
    }
    /**
     * <p>
     * This indicates the beginning of an XML Namespace prefix
     *   mapping.  Although this typically occurs within the root element
     *   of an XML document, it can occur at any point within the
     *   document.  Note that a prefix mapping on an element triggers
     *   this callback <i>before</i> the callback for the actual element
     *   itself (<code>{@link #startElement}</code>) occurs.
     * </p>
     *
     * @param prefix <code>String</code> prefix used for the namespace
     *               being reported
     * @param uri <code>String</code> URI for the namespace
     *               being reported
     * @throws <code>SAXException</code> when things go wrong
     */
    public void startPrefixMapping(String prefix, String uri) {
    }
    /**
     * <p>
     * This indicates the end of a prefix mapping, when the namespaceCONTENT HANDLERS 57
Book Title, eMatter Edition
Copyright © 2000 O’Reilly & Associates, Inc. All rights reserved.
     *   reported in a <code>{@link #startPrefixMapping}</code> callback
     *   is no longer available.
     * </p>
     *
     * @param prefix <code>String</code> of namespace being reported
     * @throws <code>SAXException</code> when things go wrong
     */
    public void endPrefixMapping(String prefix) {
    }
    /**
     * <p>
     * This reports the occurrence of an actual element. It includes
     *   the element's attributes, with the exception of XML vocabulary
     *   specific attributes, such as
     *   <code>xmlns:[namespace prefix]</code> and
     *   <code>xsi:schemaLocation</code>.
     * </p>
     *
     * @param namespaceURI <code>String</code> namespace URI this element
     *                     is associated with, or an empty
     *                     <code>String</code>
     * @param localName <code>String</code> name of element (with no
     *                  namespace prefix, if one is present)
     * @param rawName <code>String</code> XML 1.0 version of element name:
     *                [namespace prefix]:[localName]
     * @param atts <code>Attributes</code> list for this element
     * @throws <code>SAXException</code> when things go wrong
     */
    public void startElement(String namespaceURI, String localName,
                             String rawName, Attributes atts)
        throws SAXException {

    	if (localName.equals("entry")) {
    		inEntry = true;
    	}
    	
    	if (inEntry == true) {
	    	if (localName.equals("name")) {
	    		inName = true;
	    	} else if (localName.equals("location")) {
	    		inLocation = true;
	    	} else if (localName.equals("phonenumber")) {
	    		inPhone = true;
	    	} else if (localName.equals("principal")) {
	    		inPrincipal = true;
	    	} else if (localName.equals("administrativesecretary")) {
	    		inSecretary = true;
	    	} else if (localName.equals("latitude")) {
	    		inLatitude = true;
	    	} else if (localName.equals("longitude")) {
	    		inLongitude = true;
	    	} else if (localName.equals("website")) {
	    		inSite = true;
	    	} else if (localName.equals("headlines")) {
	    		inHeadline = true;
	    	} else if (localName.equals("events")) {
	    		inEvent = true;
	    	} else if (localName.equals("alerts")) {
	    		inAnnouncement = true;
	    	}
    	}

    }
    /**
     * <p>
     * Indicates the end of an element
     *   (<code>&lt;/[element name]&gt;</code>) is reached. Note that
     *   the parser does not distinguish between empty
     *   elements and non-empty elements, so this occurs uniformly.
     * </p>
     *
     * @param namespaceURI <code>String</code> URI of namespace this
     *                     element is associated with
     * @param localName <code>String</code> name of element without prefix
     * @param rawName <code>String</code> name of element in XML 1.0 form
     * @throws <code>SAXException</code> when things go wrong
     */
    public void endElement(String namespaceURI, String localName, String rawName)
        throws SAXException {
    	if (localName.equals("entry")) {
    		inEntry = false;
    	}
    	
    	if (inEntry == true) {
	    	if (localName.equals("name")) {
	    		longName.append(parseMark);
	    		inName = false;
	    	} else if (localName.equals("location")) {
	    		longLocation.append(parseMark);
	    		inLocation = false;
	    	} else if (localName.equals("phonenumber")) {
	    		longPhone.append(parseMark);
	    		inPhone = false;
	    	} else if (localName.equals("principal")) {
	    		longPrincipal.append(parseMark);
	    		inPrincipal = false;
	    	} else if (localName.equals("administrativesecretary")) {
	    		longSecretary.append(parseMark);
	    		inSecretary = false;
	    	} else if (localName.equals("latitude")) {
	    		longLatitude.append(parseMark);
	    		inLatitude = false;
	    	} else if (localName.equals("longitude")) {
	    		longLongitude.append(parseMark);
	    		inLongitude = false;
	    	} else if (localName.equals("website")) {
	    		longSite.append(parseMark);
	    		inSite = false;
	    	} else if (localName.equals("headlines")) {
	    		longHeadline.append(parseMark);
	    		inHeadline = false;
	    	} else if (localName.equals("events")) {
	    		longEvent.append(parseMark);
	    		inEvent = false;
	    	} else if (localName.equals("alerts")) {
	    		longAnnouncement.append(parseMark);
	    		inAnnouncement = false;
	    	}
    	}

    }
    /**
     * <p>
     * This reports character data (within an element).
     * </p>
     *
     * @param ch <code>char[]</code> character array with character data
     * @param start <code>int</code> index in array where data starts.
     * @param end <code>int</code> index in array where data ends.
     * @throws <code>SAXException</code> when things go wrong
     */
    public void characters(char[] ch, int start, int length)
        throws SAXException {
	
		if (inName == true) {
    		longName.append(ch, start, length);
    	} else if (inLocation == true) {
    		longLocation.append(ch, start, length);
    	} else if (inPhone == true) {
    		longPhone.append(ch, start, length);
    	}  else if (inPrincipal == true) {
    		longPrincipal.append(ch, start, length);
    	} else if (inSecretary == true) {
    		longSecretary.append(ch, start, length);
    	} else if (inLatitude == true) {
    		longLatitude.append(ch, start, length);
    	} else if (inLongitude == true) {
    		longLongitude.append(ch, start, length);
    	} else if (inSite == true) {
    		longSite.append(ch, start, length);
    	} else if (inHeadline == true) {
    		longHeadline.append(ch, start, length);
    	} else if (inEvent == true) {
    		longEvent.append(ch, start, length);
    	} else if (inAnnouncement == true) {
    		longAnnouncement.append(ch, start, length);
    	}  

    }
/**
     * <p>
     * This reports whitespace that can be ignored in the
     *   originating document. This is typically invoked only when
     *   validation is ocurring in the parsing process.
     * </p>
     *
     * @param ch <code>char[]</code> character array with character data
     * @param start <code>int</code> index in array where data starts.
     * @param end <code>int</code> index in array where data ends.
     * @throws <code>SAXException</code> when things go wrong
     */
    public void ignorableWhitespace(char[] ch, int start, int end)
        throws SAXException {
    }
/**
     * <p>
     * This reports an entity that is skipped by the parser. This
     *   should only occur for non-validating parsers, and then is still
     *   implementation-dependent behavior.
     * </p>
     *
     * @param name <code>String</code> name of entity being skipped
     * @throws <code>SAXException</code> when things go wrong
     */
    public void skippedEntity(String name) throws SAXException {
    }
}