package org.simplestartframwork.core.test.main.initialize;

import org.junit.Test;
import org.simplestartframwork.context.impl.AnntationContextManager;
import org.simplestartframwork.core.test.config.BeanConfig;

public class InitAnnotationTest {
	
	@Test
	public void init(){
		AnntationContextManager anntationContextManager=new AnntationContextManager(BeanConfig.class);
		anntationContextManager.getBean(InitService.class);
		
		
	}


}
