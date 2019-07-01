package org.simplestartframwork.core.test.main.component;

import org.junit.Test;
import org.simplestartframwork.context.impl.AnntationContextManager;
import org.simplestartframwork.core.test.action.UserController;
import org.simplestartframwork.core.test.config.BeanConfig;

public class ComponentAllAnntationTest {
	
	@Test
	public void login(){
		try {
			AnntationContextManager context=new AnntationContextManager(BeanConfig.class);
			 UserController userController = context.getBean("userController", UserController.class);
			 System.out.println("3."+context.getContext().getObjects());
			 userController.save();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
