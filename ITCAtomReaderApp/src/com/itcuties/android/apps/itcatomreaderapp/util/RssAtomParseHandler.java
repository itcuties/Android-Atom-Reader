package com.itcuties.android.apps.itcatomreaderapp.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.itcuties.android.apps.itcatomreaderapp.data.RssAtomItem;

/**
 * SAX tag handler
 * 
 * @author ITCuties
 *
 */
public class RssAtomParseHandler extends DefaultHandler {

	private List<RssAtomItem> rssItems;
	
	// Used to reference item while parsing
	private RssAtomItem currentItem;
	
	// Parsing title indicator
	private boolean parsingTitle;
	// Parsing link indicator
	private boolean parsingContents;
	// A buffer for title contents
	private StringBuffer currentTitleSb;
	// A buffer for content tag contents
	private StringBuffer currentContentSb;
	
	public RssAtomParseHandler() {
		rssItems = new ArrayList<RssAtomItem>();
	}
	
	public List<RssAtomItem> getItems() {
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if ("entry".equals(qName)) {
			currentItem = new RssAtomItem();
		} else if ("title".equals(qName)) {
			parsingTitle = true;
			currentTitleSb = new StringBuffer();
		} else if ("content".equals(qName)) {
			parsingContents = true;
			currentContentSb = new StringBuffer();
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if ("entry".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("title".equals(qName)) {
			parsingTitle = false;
			
			if (currentItem != null) // There is a title tag for a whole channel present. It is being parsed before the entry tag is present, so we need to check if item is not null
				currentItem.setTitle(currentTitleSb.toString());
			
		} else if ("content".equals(qName)) {
			parsingContents = false;
			
			if (currentItem != null)
				currentItem.setContent(currentContentSb.toString());
			
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		if (parsingTitle) {
			if (currentItem != null)
				currentTitleSb.append(new String(ch, start, length));
		} else if (parsingContents) {
			if (currentItem != null)
				currentContentSb.append(new String(ch, start, length));
		}
	}
	
}
