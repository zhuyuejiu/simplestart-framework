package org.simplestartframwork.core.test.main.value;

import org.junit.Test;
import org.simplestartframwork.context.impl.AnntationContextManager;
import org.simplestartframwork.core.test.config.BeanConfig;

public class ValueAnnotationTest {
	
	@Test
	public void field() {
		AnntationContextManager context=new AnntationContextManager(BeanConfig.class);
		UserService userService = context.getBean(UserService.class);
		userService.login();
	}

}
