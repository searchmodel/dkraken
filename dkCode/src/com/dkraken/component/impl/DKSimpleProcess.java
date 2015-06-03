package com.dkraken.component.impl;

import com.dkraken.component.DKIProcess;
import com.dkraken.util.SimplePermutation;

public class DKSimpleProcess implements DKIProcess {

	String orginal = "abcdefghigklmnopqrstuvwxyz";// 9s

	/**
	 * eg: 3
	 * 
	 */
	public boolean execute(int num) {
		orginal = "abcdefghi";// 9s
		orginal = "jklmnopq";// 9s
		orginal = "tuvwxyzrsf";// 9s
		
		
		long start = System.currentTimeMillis();
		System.out.println(SimplePermutation.permutationByStr(orginal).length);
		

		Long time = System.currentTimeMillis() - start;
		System.out.println(time / 1000.0 + " s");
		return false;
	}
}
