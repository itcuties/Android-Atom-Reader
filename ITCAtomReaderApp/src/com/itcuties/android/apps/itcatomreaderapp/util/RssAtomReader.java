package com.itcuties.android.apps.itcatomreaderapp.util;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.itcuties.android.apps.itcatomreaderapp.data.RssAtomItem;

/**
 * Class reads RSS data.
 * 
 * @author ITCuties
 *
 */
public class RssAtomReader {
	
	private String rssUrl;

	/**
	 * Constructor
	 * 
	 * @param rssUrl
	 */
	public RssAtomReader(String rssUrl) {
		this.rssUrl = rssUrl;
	}

	/**
	 * Get RSS items.
	 * 
	 * @return
	 */
	public List<RssAtomItem> getItems() throws Exception {
		// SAX parse RSS data
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		SAXParser saxParser = factory.newSAXParser();

		RssAtomParseHandler handler = new RssAtomParseHandler();
		
		saxParser.parse(rssUrl, handler);
		
		return handler.getItems();
		
	}

}
