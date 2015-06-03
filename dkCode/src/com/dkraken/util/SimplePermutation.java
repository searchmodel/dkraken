package com.dkraken.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 简单全排列
 * 
 * http://blog.sina.com.cn/s/blog_62cd5a980100iazu.html
 * 
 * @author Bin Xu
 */

public class SimplePermutation {
	/**
	 * 通过字母排列
	 * 
	 * @param orginal
	 * @return
	 */
	public static String[] permutationByStr(String orginal) {
		ArrayList<String> list = new ArrayList<String>();
		if (orginal.length() == 1) {
			return new String[] { orginal };
		} else {
			for (int i = 0; i < orginal.length(); i++) {
				String s = orginal.charAt(i) + "";
				String result = "";
				String resultA = result + s;
				String leftS = orginal.substring(0, i)
						+ orginal.substring(i + 1, orginal.length());
				for (String element : permutationByStr(leftS)) {
					result = resultA + element;
					list.add(result);
				}
			}
			return (String[]) list.toArray(new String[list.size()]);
		}
	}

	public static String[] permutationByChars(char[] orginal) {
		String[] result = {};
		if (orginal != null && orginal.length > 0) {
			result = permutationByStr(Arrays.toString(orginal));
		}
		return result;
	}

}