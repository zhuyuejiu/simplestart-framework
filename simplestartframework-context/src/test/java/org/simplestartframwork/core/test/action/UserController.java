package org.simplestartframwork.core.test.action;

import org.simplestartframwork.context.annotation.Inject;
import org.simplestartframwork.context.annotation.Matching;
import org.simplestartframwork.context.annotation.Scope;
import org.simplestartframwork.context.annotation.Scope.BeanDefinition;
import org.simplestartframwork.context.annotation.component.Action;
import org.simplestartframwork.core.test.service.UserService;


@Action
@Scope(BeanDefinition.PROTOTYPE)
public class UserController {
	

	//@InjectName(value="userServiceImpl")
	private UserService userService1;
	private UserService userService2;
	
	@Inject
	public void setUserService(@Matching(value="userServiceImpl") UserService userService1,@Matching(value="userServiceImpl2") UserService userService2) {
		this.userService1 = userService1;
		this.userService2 =userService2;
	}



	public void save(){
		System.out.println("-保存数据-Controller-");
		System.out.println(userService1);
		System.out.println(userService1);
		userService1.save();
		userService2.save();
	}

}
