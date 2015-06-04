package com.dkraken.masterworker.impl;

import com.dkraken.component.DKIProcess;
import com.dkraken.component.impl.DKSimpleProcess;
import com.dkraken.masterworker.Master;
import com.dkraken.masterworker.Worker;

/**
 * 任务节点
 * @author Bin Xu 
 *
 */
public class TaskWorker extends Worker<DKIProcess>{
	@Override
	public Object handle(DKIProcess input) {
		input.process(null);
		return null;
	}

	public static void main(String[] args) {
		// 固定使用5个Worker，并指定Worker
		Master<DKIProcess> m = new Master<DKIProcess>(new TaskWorker(),100);
		
		m.submit(new DKSimpleProcess());
		 
		m.execute(); // 开始计算
	}
}
