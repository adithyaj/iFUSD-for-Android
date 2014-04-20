package com.fremontunified.ifusdandroid;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class RSSNewsHandler implements ContentHandler {
	
    final String parseMark = "hafindjn";
	
	Locator locator;
	
    StringBuilder longTitle = new StringBuilder();
    StringBuilder longDescription = new StringBuilder();
    StringBuilder longLink = new StringBuilder();
	
	boolean inTitle = false;
	boolean inLink = false;
	boolean inItem = false;
	boolean inDescription = false;
	
	public void setDocumentLocator(Locator locator) {
	    
    	this.locator = locator;
    
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
    	longTitle.delete(longTitle.length()-parseMark.length(), longTitle.length());
    	longDescription.delete(longDescription.length()-parseMark.length(), longDescription.length());
    	longLink.delete(longLink.length()-parseMark.length(), longLink.length());
    	
    	News.titles = longTitle.toString().split(parseMark, -1);
    	News.descriptions = longDescription.toString().split(parseMark, -1);
    	News.links = longLink.toString().split(parseMark, -1);
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
    	if (rawName.equalsIgnoreCase("item")) {
    		inItem = true;
    	}
    	
    	if (rawName.equalsIgnoreCase("title") && inItem == true) {
    		inTitle = true;
    	} else if (rawName.equalsIgnoreCase("description") && inItem == true) {
    		inDescription = true;
    	} else if (rawName.equalsIgnoreCase("link") && inItem == true) {
    		inLink = true;
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

    	if (rawName.equalsIgnoreCase("item")) {
    		inItem = false;
    	}
    	
    	if (rawName.equalsIgnoreCase("title")) {
    		inTitle = false;
    		longTitle.append(parseMark);
    	} else if (rawName.equalsIgnoreCase("description")) {
    		inDescription = false;
    		longDescription.append(parseMark);
    	} else if (rawName.equalsIgnoreCase("link")) {
    		inLink = false;
    		longLink.append(parseMark);
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
    	if(inItem == true) {
    		if (inTitle == true) {
	    		longTitle.append(ch, start, length);
	    	} else if (inDescription == true) {
	    		longDescription.append(ch, start, length);
	    	} else if (inLink == true) {
	    		longLink.append(ch, start, length);
	    	}
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