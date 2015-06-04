package com.dkraken.masterworker;

import java.util.Map;
import java.util.Queue;

public class Worker<T> implements Runnable {
	// 任务队列，用于取得子任务
	protected Queue<T> workQueue;
	// 子任务处理结果集
	protected Map<String, Object> resultMap;

	public void setWorkQueue(Queue<T> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	// 子任务处理的逻辑, 在子类中实现具体逻辑
	public Object handle(T input) {
		return input;
	}

	@Override
	public void run() {
		while (true) {
			// 获取子任务
			T input = workQueue.poll();
			if (input == null)
				break;
			// 处理子任务
			Object res = handle(input);
			// 将处理结果写入结果集
			resultMap.put(Integer.toString(input.hashCode()), res);
		}
	}

}
