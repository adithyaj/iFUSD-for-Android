package com.fremontunified.ifusdandroid;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import android.util.Log;

/**
 * <b><code>MyContentHandler</code></b> implements the SAX
 *   <code>ContentHandler</code> interface and defines callback
 *   behavior for the SAX callbacks associated with an XML
 *   document's content.
 */
class RSSContentHandler implements ContentHandler  {
    /** Hold onto the locator for location information */

    private final String TAG = "MyActivity";

	private Locator locator;
	
	StringBuilder giantString = new StringBuilder();
	
	int titleNum;
	
	boolean inLink = false;
	boolean inItem = false;
	boolean inTitle = false;
    /**
     * <p>
     * Provide reference to <code>Locator</code> which provides
     *   information about where in a document callbacks occur.
     * </p>
     *
     * @param locator <code>Locator</code> object tied to callback
     *                process
     */

    
    public void setDocumentLocator(Locator locator) {
    
    	this.locator = locator;
    	Log.e(TAG, locator.getSystemId());
    
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
    	Log.e(TAG, "Starting doc");
    	
    	titleNum = 0;
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
    	Rssweb.rssText = giantString.toString();
 
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
    	
    	if (rawName.equalsIgnoreCase("title")) {
    		if (titleNum > 0) {	inTitle = true; }
    		titleNum++;
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

    	if (rawName.equalsIgnoreCase("title")) {
    		inTitle = false;
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
    	
    	if (inTitle == true && String.valueOf(ch, start, length) != " ") {
    		Log.e("character", String.valueOf(ch, start, length));
    		giantString.append(ch, start, length);
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