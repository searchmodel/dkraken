package com.dkraken.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IASUtils {
	
	//Provide log support
	private static Log LOG = LogFactory.getLog(IASUtils.class);
	
	public static boolean isEmptyString(String content) {
		return isEmptyString(content, true);
	}
	
	public static boolean isEmptyString(String content, boolean trim) {
		if (!trim)
			return content == null || content.length() == 0;
		else
			return content == null || content.trim().length() == 0;
	}
	
	public static <T> void addAll(Collection<T> collection, T[] elements) {
		for (int i = 0; i < elements.length; i++) {
			T element = elements[i];
			collection.add(element);
		} //end of [elements] iteration
	}
	
	public static <T> void addAll(Collection<T> collection, T[] elements, int offset, int count) {
		for (int i = offset; i < Math.min(elements.length, offset + count); i++) {
			T element = elements[i];
			collection.add(element);
		} //end of [elements] iteration
	}
	
	public static String removeWhiteSpace(String content) {
		StringBuilder builder = new StringBuilder(content.length());
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			if (Character.isWhitespace(c))
				continue;
			builder.append(c);
		}
		
		if (builder.length() > 0)
			return builder.toString();
		return null;
	}
	
	public static String removeSpecialChars(String content, Character... values) {
		return replaceSpecialChars(null, content, values);
	}
	
	public static String replaceSpecialChars(String placeholder, String content, Character... values) {
		Set<Character> set = new HashSet<Character>();
		IASUtils.addAll(set, values);
		
		StringBuilder builder = new StringBuilder(content.length());
		for (int i = 0; i < content.length(); i++) {
			char c = content.charAt(i);
			if (set.contains(c)) {
				if (placeholder != null)
					builder.append(placeholder);
				continue;
			}
			builder.append(c);
		}
		
		/*if (builder.length() > 0)
			return builder.toString();
		return null;*/

		return builder.toString();
	}
	
	public static String getValidFilename(String filename) {
		return replaceSpecialChars("_", filename, '/', '\\', ':', '*', '?', '"', '<', '>', '|');
	}
	
	@SuppressWarnings("rawtypes")
	public static String concatCollection(Collection collection, String separator) {
		if (collection == null || collection.size() == 0)
			return "";

		int length = collection.size();
		StringBuilder builder = new StringBuilder();

		int count = 0;
		for (Iterator iter = collection.iterator(); iter.hasNext()
				&& count < length; count++) {
			Object element = iter.next();
			if(element == null){
				builder.append("");
			}else{
				builder.append(element.toString());
			}
			
			if (count != length - 1)
				builder.append(separator);
		}

		return builder.toString();
	}
	
	
	public static String md5(String content) {
		if(content == null || content.trim().equalsIgnoreCase("")){
			return null;
		}
		String result = null;
		
		try {
			byte[] defaultBytes = content.getBytes();
			
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();
			
			StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() > 1)
					hexString.append(hex);
				else
					hexString.append('0').append(hex);
			}
			
//			return hexString.toString();
			result = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace(); // Auto-generated catch block
			LOG.error(String.format("md[%s] exception", content), e);
		}
		
//		return "-md5 failed-" + System.currentTimeMillis();
		return result;
	}
	
	 
	
	
}
