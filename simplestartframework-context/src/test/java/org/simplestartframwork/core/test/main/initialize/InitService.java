package org.simplestartframwork.core.test.main.initialize;

import org.simplestartframwork.context.annotation.Initialize;
import org.simplestartframwork.context.annotation.component.Service;

@Service
public class InitService {
	
	@Initialize
	public void init() {
		System.out.println("=========初始化方法==========");
	}

}
