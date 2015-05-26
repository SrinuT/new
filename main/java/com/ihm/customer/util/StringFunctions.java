package com.ihm.customer.util;

/**
 * @author SARDAR WAQAS AHMED
 * @email  architect_pakistan@hotmail.com
 * @since  17-DEC-2015
 * @version 1.0
 */

import static java.lang.Math.round;
import static java.lang.Math.random;
import static java.lang.Math.pow;
import static java.lang.Math.abs;
import static java.lang.Math.min;
import static org.apache.commons.lang.StringUtils.leftPad;

import java.util.List;

public class StringFunctions {

	/**
	 * Replace.
	 * 
	 * @param line
	 *            the line
	 * @param oldString
	 *            the old string
	 * @param newString
	 *            the new string
	 * @return the string
	 */
	public static String replace(String line, String oldString, String newString) {
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	public static String appendCharAtLeadNTrailEnd(String orignal, String lead,
			String trail) {
		return (lead + orignal + trail);
	}
	
	public static String advancedAppendCharAtLeadNTrailEnd(List<String> orignal, String lead,
			String trail) {
		String orgnl= null;
		for(int i=0; i< orignal.size(); i++){
			orgnl = orignal.get(i).toString();
		}
		
		return (lead + orgnl + trail);
		
	}

	public static String removeCharAtLeadNTrailEnd(String orignal, String lead,
			String trail) {
		if (orignal.contains("(")) {
			orignal = orignal.replaceAll(lead, "").replaceAll(trail, "");
		}
		return orignal;
	}

	public static String gen(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = length; i > 0; i -= 12) {
			int n = min(12, abs(i));
			sb.append(leftPad(Long.toString(round(random() * pow(36, n)), 36),
					n, '0'));
		}
		return sb.toString();
	}
}
