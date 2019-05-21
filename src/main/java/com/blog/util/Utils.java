package com.blog.util;

public class Utils {

	public String blankToPTag(String content) {
		StringBuilder str = new StringBuilder("<p>");
		str.append(content.replace("\n", "</p><p>"));
		str.append("</p>");
		return str.toString();
	}
}
