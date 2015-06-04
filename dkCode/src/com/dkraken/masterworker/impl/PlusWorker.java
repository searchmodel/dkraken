package com.dkraken.masterworker.impl;

import java.util.Map;
import java.util.Set;

import com.dkraken.masterworker.Master;
import com.dkraken.masterworker.Worker;

public class PlusWorker extends Worker<Integer> {

	@Override
	public Object handle(Integer input) {
		Integer i = (Integer)input; // Worker,求立方和
		return i * i * i;
	}
	
	public static void main(String[] args) {
		// 固定使用5个Worker，并指定Worker
		Master<Integer> m = new Master<Integer>(new PlusWorker(), 100);
		
		for (int i = 0; i < 10000; i++) {
			m.submit(i); // 提交100个子任务
		}
		m.execute(); // 开始计算

		int re = 0; // 最终的计算结果保存在这
		// 处理结果
		Map<String, Object> resultMap = m.getResultMap();

		// 不需要等待所有Worker都执行完，即可开始计算最终结果
		while (resultMap.size() > 0 || !m.isComplete()) {
			Set<String> keys = resultMap.keySet();
			String key = null;
			for (String k : keys) {
				key = k;
				break;
			}
			Integer i = null;
			if (key != null) {
				i = (Integer) resultMap.get(key);
			}
			if (i != null) {
				re += i; // 最终结果
			}
			if (key != null) {
				resultMap.remove(key);// 移除已经被计算过的项
			}
		}
		System.out.println(re);
	}
}
