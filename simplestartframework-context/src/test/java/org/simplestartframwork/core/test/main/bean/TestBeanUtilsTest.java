package org.simplestartframwork.core.test.main.bean;

import org.junit.Test;
import org.simplestartframwork.context.impl.AnntationContextManager;
import org.simplestartframwork.core.test.config.BeanConfig;

public class TestBeanUtilsTest {
	
	@Test
	public void bean(){
		try {
			AnntationContextManager context=new AnntationContextManager(BeanConfig.class);
			TestBeanUtils testBeanUtils = context.getBean(TestBeanUtils.class);
			 System.out.println(testBeanUtils);
			 System.out.println(testBeanUtils);
			 testBeanUtils.test();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
