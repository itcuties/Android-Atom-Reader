package com.itcuties.android.apps.itcatomreaderapp.data;

/**
 * This class represents an ATOM entity - a post
 * 
 * @author itcuties
 *
 */
public class RssAtomItem {
	
	private String title;
	
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return title;
	}

}
