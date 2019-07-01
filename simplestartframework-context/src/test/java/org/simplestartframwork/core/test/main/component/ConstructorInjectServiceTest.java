package org.simplestartframwork.core.test.main.component;

import org.junit.Test;
import org.simplestartframwork.context.impl.AnntationContextManager;
import org.simplestartframwork.core.test.config.BeanConfig;

public class ConstructorInjectServiceTest {
	
	@Test
	public void test() {
		AnntationContextManager context=new AnntationContextManager(BeanConfig.class);
		ConstructorInjectService service = context.getBean("constructorInjectService", ConstructorInjectService.class);
		service.test();
	}
	
}
