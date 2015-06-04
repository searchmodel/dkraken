package com.dkraken.component.impl;

import com.dkraken.component.DKIProcess;
import com.dkraken.component.TaskDefault;
import com.dkraken.util.SimplePermutation;

public class DKSimpleProcess implements DKIProcess {

	String orginal = "abcdefghigklmnopqrstuvwxyz";// 9s

	public void process(TaskDefault taskDefault) {
		orginal = "abcdefghi";// 9s
		orginal = "jklmnopq";// 9s
		orginal = "tuvwxyz";// 9s
		System.out.println("threadName:" + Thread.currentThread().getName());

		long start = System.currentTimeMillis();
		System.out.println(SimplePermutation.permutationByStr(orginal).length);

		Long time = System.currentTimeMillis() - start;
		System.out.println(time / 1000.0 + " s");
	}

	public static void main(String[] args) {

		DKIProcess p = new DKSimpleProcess();
		p.process(null);
	}
}
